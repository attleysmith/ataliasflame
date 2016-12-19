package hu.asgames.messages;

/**
 * @author AMiklo on 2016.12.08.
 */
public final class MessageUtil extends MessageUtilBase {

    private MessageUtil() {}

    public static final PseudoMessage USER_AUTH_FAILED_WITH_ID = error("user.auth.failed.with.id", "User authentication failed! User ID: ${userId}");
    public static final PseudoMessage USER_AUTH_FAILED_WITH_USERNAME =
            error("user.auth.failed.with.username", "User authentication failed! Username: ${username}");
    public static final PseudoMessage CONFIRM_REGISTRATION_WITH_WRONG_STATE = error("confirm.registration.with.wrong.state",
            "Only a ${correctRegistrationState} registration can be confirmed! Registration state: ${registrationState}");
}
