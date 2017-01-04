package hu.asgames.jobs;

import hu.asgames.dao.RegistrationDao;
import hu.asgames.dao.UserDao;
import hu.asgames.domain.entities.Registration;
import hu.asgames.domain.entities.User;
import hu.asgames.domain.enums.RegistrationState;
import hu.asgames.domain.enums.UserState;
import hu.asgames.messages.MessageBuilder;
import hu.asgames.messages.MessageUtil;
import hu.asgames.ws.api.domain.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AMiklo on 2017.01.02.
 */
@Component
public class UserRegistrationExpiryJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationExpiryJob.class);

    @Autowired
    private RegistrationDao registrationDao;

    @Autowired
    private UserDao userDao;

    @Value(value = "${registration.expiry.days:30}")
    private int expiryDays;

    @Scheduled(cron = "${registration.expiry.cron}")
    private void expireRegistrations() {
        LocalDateTime dateExpired = LocalDate.now().minusDays(expiryDays).atStartOfDay();

        logInfo(new MessageBuilder(MessageUtil.SCHEDULED_REGISTRATION_EXPIRY).arg("dateExpired", dateExpired.toString()).build());

        List<Registration> expiredRegistrations = registrationDao.findExpiredRegistrations(dateExpired);
        expiredRegistrations.forEach(this::expireRegistration);
    }

    private void expireRegistration(Registration registration) {
        User user = registration.getUser();

        if (user.getState() != UserState.TEMPORARY) {
            logWarn(new MessageBuilder(MessageUtil.EXPIRE_REGISTRATION_WITH_UNEXPECTED_USER_STATE).arg("correctUserState", UserState.TEMPORARY.name())
                    .arg("userId", user.getId().toString()).arg("userState", user.getState().name()).build());
        }
        if (registration.getExpirationDate() != null) {
            logWarn(new MessageBuilder(MessageUtil.EXPIRE_REGISTRATION_WITH_NOT_EMPTY_EXPIRATION_DATE).arg("userId", user.getId().toString())
                    .arg("expirationDate", registration.getExpirationDate().toString()).build());
        }
        if (registration.getConfirmationDate() != null) {
            logWarn(new MessageBuilder(MessageUtil.EXPIRE_REGISTRATION_WITH_NOT_EMPTY_CONFIRMATION_DATE).arg("userId", user.getId().toString())
                    .arg("confirmationDate", registration.getConfirmationDate().toString()).build());
        }

        registration.setState(RegistrationState.EXPIRED);
        registration.setExpirationDate(LocalDateTime.now());
        user.setState(UserState.DELETED);

        userDao.save(user); // cascade saving with registration

        logInfo(new MessageBuilder(MessageUtil.USER_REGISTRATION_EXPIRED).arg("username", user.getUsername()).build());
    }

    private void logInfo(Message message) {
        LOGGER.info(message.fullMessage());
    }

    private void logWarn(Message message) {
        LOGGER.warn(message.fullMessage());
    }

}
