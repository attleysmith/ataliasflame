package hu.asgames.messages;

/**
 * @author AMiklo on 2016.12.08.
 */
public final class MessageUtil extends MessageUtilBase {

    private MessageUtil() {}

    // ERROR messages
    public static final PseudoMessage USER_NOT_EXIST = error("user.not.exist", "User doesn't exist! User ID: ${userId}");
    public static final PseudoMessage USER_AUTH_FAILED = error("user.auth.failed", "User authentication failed! Username: ${username}");
    public static final PseudoMessage USER_ALREADY_DELETED =
            error("user.already.deleted", "User is already deleted! User ID: ${userId}; Username: ${username}");
    public static final PseudoMessage CONFIRM_REGISTRATION_WITH_WRONG_STATE = error("confirm.registration.with.wrong.state",
            "Only a ${correctRegistrationState} registration can be confirmed! User ID: ${userId}; Registration state: ${registrationState}");
    public static final PseudoMessage CONFIRM_REGISTRATION_WITH_WRONG_USER_STATE = error("confirm.registration.with.wrong.user.state",
            "Only a ${correctUserState} user's registration can be confirmed! User ID: ${userId}; User state: ${userState}");
    public static final PseudoMessage REGISTRATION_CODE_NOT_MATCH = error("registration.code.not.match",
            "Registration code doesn't match with user's registration! User ID: ${userId}; Registration code: ${registrationCode}");
    public static final PseudoMessage REGISTRATION_NOT_EXIST = error("registration.not.exist", "User doesn't have any registration! User ID: ${userId}");

    // WARNING messages
    public static final PseudoMessage EXPIRE_REGISTRATION_WITH_UNEXPECTED_USER_STATE = warn("expire.registration.with.unexpected.user.state",
            "Only a ${correctUserState} user's registration should be expired! User ID: ${userId}; User state: ${userState}");
    public static final PseudoMessage EXPIRE_REGISTRATION_WITH_NOT_EMPTY_EXPIRATION_DATE = warn("expire.registration.with.not.empty.expiration.date",
            "Expired registration already had an expiration date! User ID: ${userId}; Old expiration date: ${expirationDate}");
    public static final PseudoMessage EXPIRE_REGISTRATION_WITH_NOT_EMPTY_CONFIRMATION_DATE = warn("expire.registration.with.not.empty.confirmation.date",
            "Expired registration had a confirmation date! User ID: ${userId}; Confirmation date: ${confirmationDate}");

    // INFO messages
    public static final PseudoMessage USER_CREATED = info("user.created", "User created - ${username}");
    public static final PseudoMessage USER_MODIFIED = info("user.modified", "User modified - ${username}");
    public static final PseudoMessage USER_DELETED = info("user.deleted", "User deleted - ${username}");
    public static final PseudoMessage USER_PASSWORD_CHANGED = info("user.password.changed", "User password changed - ${username}");
    public static final PseudoMessage USER_REGISTRATION_CONFIRMED = info("user.registration.confirmed", "User registration confirmed - ${username}");
    public static final PseudoMessage USER_REGISTRATION_EXPIRED = info("user.registration.expired", "User registration expired - ${username}");
    public static final PseudoMessage SCHEDULED_REGISTRATION_EXPIRY =
            info("scheduled.registration.expiry", "Expiring registrations started - date: ${dateExpired}");
}
