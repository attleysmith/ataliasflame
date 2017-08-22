package hu.asgames.jobs

import hu.asgames.ReplaceSlf4jLogger
import hu.asgames.dao.RegistrationDao
import hu.asgames.dao.UserDao
import hu.asgames.domain.entities.Registration
import hu.asgames.domain.entities.User
import hu.asgames.domain.enums.RegistrationState
import hu.asgames.domain.enums.UserState
import hu.asgames.messages.MessageBuilder
import hu.asgames.messages.MessageUtil
import hu.asgames.ws.api.domain.Message
import org.junit.Rule
import org.slf4j.Logger
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author AMiklo on 2017.01.03.
 */
class UserRegistrationExpiryJobTest extends Specification {

  private static final String DISPLAY_NAME = "DISPLAY_NAME"
  private static final String USERNAME = "USERNAME"
  private static final String EMAIL = "EMAIL"
  private static final String ENCODED_PASSWORD = "ENCODED_PASSWORD"
  private static final String REGISTRATION_CODE = "REGISTRATION_CODE"
  private static final int EXPIRY_DAYS = 30
  private static final LocalDateTime DATE_EXPIRED = LocalDate.now().minusDays(EXPIRY_DAYS).atStartOfDay()

  private final UserRegistrationExpiryJob userRegistrationExpiryJob = new UserRegistrationExpiryJob()
  private RegistrationDao registrationDao = Mock()
  private UserDao userDao = Mock()
  private Logger logger = Mock()

  @Rule
  ReplaceSlf4jLogger replaceSlf4jLogger = new ReplaceSlf4jLogger(UserRegistrationExpiryJob, logger)

  void setup() {
    userRegistrationExpiryJob.registrationDao = registrationDao
    userRegistrationExpiryJob.userDao = userDao
    userRegistrationExpiryJob.expiryDays = EXPIRY_DAYS
  }

  def "Registration expiry logs running even if there aren't any registration to be expired"() {
    given: "an info message about running registration expiry what we expect to be logged."
    Message expectedInfoMessage = new MessageBuilder(MessageUtil.SCHEDULED_REGISTRATION_EXPIRY).arg("dateExpired", DATE_EXPIRED as String).build()
    when: "expiring of user registrations runs"
    userRegistrationExpiryJob.expireRegistrations()
    then: "expired registrations in database are queried (and returns an empty list)"
    1 * registrationDao.findExpiredRegistrations(DATE_EXPIRED) >> []
    and: "the expected info message is logged about running"
    1 * logger.info(expectedInfoMessage.fullMessage())
    and: "saving user is never called."
    0 * userDao.save()
  }

  def "Registration expiry saves user and logs info with every expired registration"() {
    given: "an info message about running registration expiry what we expect to be logged."
    Message expectedInfoMessage = new MessageBuilder(MessageUtil.SCHEDULED_REGISTRATION_EXPIRY).arg("dateExpired", DATE_EXPIRED as String).build()
    when: "expiring of user registrations runs"
    userRegistrationExpiryJob.expireRegistrations()
    then: "expired registrations in database are queried"
    1 * registrationDao.findExpiredRegistrations(DATE_EXPIRED) >> registrationList
    and: "the expected info message is logged about running"
    1 * logger.info(expectedInfoMessage.fullMessage())
    and: "saving user with info logging happens with every expired registration"
    for (int i = 0; i < userCount; i++) {
      1 * userDao.save(registrationList[i].user)
      1 * logger.info(createUserMessage(registrationList[i].user.username).fullMessage())
    }
    where: "we try out some number of expired registrations."
    userCount | registrationList
    1         | createRegistrationList(userCount)
    2         | createRegistrationList(userCount)
    5         | createRegistrationList(userCount)
    17        | createRegistrationList(userCount)
    // NOTE: 1.1-groovy-2.4 spock version supports using variables from where in other where definitions, but 1.0-groovy-2.4 which comes with spring-boot, not yet
  }

  def "Registration expiry checks invalid state but results only in warnings without breaking expiration process"() {
    given: "an info message about running registration expiry what we expect to be logged"
    Message expectedInfoMessage = new MessageBuilder(MessageUtil.SCHEDULED_REGISTRATION_EXPIRY).arg("dateExpired", DATE_EXPIRED as String).build()
    and: "there is an expired registration in database"
    Registration registration = createRegistration(1L)
    and: "this registration has a mistakenly filled confirmation date"
    registration.confirmationDate = LocalDateTime.now()
    and: "there is a warning message about confirmation date"
    Message expectedWarningMessageAboutConfirmationDate =
            new MessageBuilder(MessageUtil.EXPIRE_REGISTRATION_WITH_NOT_EMPTY_CONFIRMATION_DATE).arg("userId", registration.user.id as String)
                    .arg("confirmationDate", registration.confirmationDate as String).build()
    and: "this registration has a mistakenly filled expiration date"
    registration.expirationDate = LocalDateTime.now()
    and: "there is a warning message about expiration date"
    Message expectedWarningMessageAboutExpirationDate =
            new MessageBuilder(MessageUtil.EXPIRE_REGISTRATION_WITH_NOT_EMPTY_EXPIRATION_DATE).arg("userId", registration.user.id as String)
                    .arg("expirationDate", registration.expirationDate as String).build()
    and: "this registration has a mistakenly not TEMPORARY user"
    registration.user.state = notTemporaryUserState
    and: "there is a warning message about user state."
    Message expectedWarningMessageAboutUserState =
            new MessageBuilder(MessageUtil.EXPIRE_REGISTRATION_WITH_UNEXPECTED_USER_STATE).arg("correctUserState", UserState.TEMPORARY.name())
                    .arg("userId", registration.user.id as String).arg("userState", registration.user.state.name()).build()
    when: "expiring of user registrations runs"
    userRegistrationExpiryJob.expireRegistrations()
    then: "expired registrations in database are queried"
    1 * registrationDao.findExpiredRegistrations(DATE_EXPIRED) >> [registration]
    and: "the expected info message is logged about running"
    1 * logger.info(expectedInfoMessage.fullMessage())
    and: "saving user with info logging happens with the expired registration"
    1 * userDao.save(registration.user)
    1 * logger.info(createUserMessage(registration.user.username).fullMessage())
    and: "all expected warning messages are logged"
    1 * logger.warn(expectedWarningMessageAboutConfirmationDate.fullMessage())
    1 * logger.warn(expectedWarningMessageAboutExpirationDate.fullMessage())
    1 * logger.warn(expectedWarningMessageAboutUserState.fullMessage())
    where: "we check all not TEMPORARY user state."
    notTemporaryUserState << [UserState.NORMAL, UserState.DELETED, UserState.BANNED, UserState.DORMANT]
  }

  def "Registration expiry saves user with an expired registration"() {
    given: "an info message about running registration expiry what we expect to be logged"
    Message expectedInfoMessage = new MessageBuilder(MessageUtil.SCHEDULED_REGISTRATION_EXPIRY).arg("dateExpired", DATE_EXPIRED as String).build()
    and: "there is an expired registration in database"
    Registration registration = createRegistration(1L)
    and: "this registration is in a valid state."
    assert registration.expirationDate == null
    assert registration.state == RegistrationState.NEW
    assert registration.user.state == UserState.TEMPORARY
    when: "expiring of user registrations runs"
    userRegistrationExpiryJob.expireRegistrations()
    then: "expired registrations in database are queried"
    1 * registrationDao.findExpiredRegistrations(DATE_EXPIRED) >> [registration]
    and: "the expected info message is logged about running"
    1 * logger.info(expectedInfoMessage.fullMessage())
    and: "the user is saved with an expired registration"
    1 * userDao.save({ User user ->
      user == registration.user &&
      user.registration.expirationDate != null &&
      user.registration.state == RegistrationState.EXPIRED &&
      user.state == UserState.DELETED
    })
    and: "an info about the user's expired registration is logged."
    1 * logger.info(createUserMessage(registration.user.username).fullMessage())
  }

  private List<Registration> createRegistrationList(int count) {
    List<Registration> registrationList = []
    for (int i = 0; i < count; i++) {
      registrationList << createRegistration(i)
    }
    return registrationList
  }

  private Registration createRegistration(Long userId) {
    return new Registration().with { Registration newRegistration ->
      user = new User().with {
        id = userId
        displayName = "${DISPLAY_NAME}_${userId}"
        username = "${USERNAME}_${userId}"
        email = "${EMAIL}_${userId}"
        password = ENCODED_PASSWORD
        state = UserState.TEMPORARY
        registration = newRegistration
        return it
      }
      state = RegistrationState.NEW
      registrationCode = "${REGISTRATION_CODE}_${userId}"
      return newRegistration
    }
  }

  private Message createUserMessage(String username) {
    return new MessageBuilder(MessageUtil.USER_REGISTRATION_EXPIRED).arg("username", username).build()
  }

}
