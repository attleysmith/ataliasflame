package hu.asgames.messages;

/**
 * @author AMiklo on 2016.12.08.
 */
public final class MessageUtil extends MessageUtilBase {

    private MessageUtil() {}

    public static final PseudoMessage USER_NOT_EXIST = error("user.not.exist", "User doesn't exist! User ID: ${userId}");
    public static final PseudoMessage USER_AUTH_FAILED = error("user.auth.failed", "User authentication failed! Username: ${username}");
    public static final PseudoMessage USER_AUTH_FAILED_WHILE_CHANGING_PASSWORD =
            error("user.auth.failed.while.changing.password", "User authentication failed while changing password! Username: ${username}");
    public static final PseudoMessage USER_ALREADY_DELETED =
            error("user.already.deleted", "User is already deleted! User ID: ${userId}; Username: ${username}");
    public static final PseudoMessage CONFIRM_REGISTRATION_WITH_WRONG_STATE = error("confirm.registration.with.wrong.state",
            "Only a ${correctRegistrationState} registration can be confirmed! Registration state: ${registrationState}");
    public static final PseudoMessage REGISTRATION_NOT_EXIST =
            error("registration.not.exist", "Registration doesn't exist with the given code: ${registrationCode}");

}
