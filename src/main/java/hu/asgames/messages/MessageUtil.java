package hu.asgames.messages;

/**
 * @author AMiklo on 2016.12.08.
 */
public final class MessageUtil extends MessageUtilBase {

    private MessageUtil() {}

    public static final PseudoMessage USER_NOT_EXIST = error("user.not.exist", "User doesn't exist! User ID: ${userId}");
    public static final PseudoMessage USER_AUTH_FAILED = error("user.auth.failed", "User authentication failed! Username: ${username}");
    public static final PseudoMessage USER_ALREADY_DELETED =
            error("user.already.deleted", "User is already deleted! User ID: ${userId}; Username: ${username}");
    public static final PseudoMessage CONFIRM_REGISTRATION_WITH_WRONG_STATE = error("confirm.registration.with.wrong.state",
            "Only a ${correctRegistrationState} registration can be confirmed! Registration state: ${registrationState}");
    public static final PseudoMessage CONFIRM_REGISTRATION_WITH_WRONG_USER_STATE =
            error("confirm.registration.with.wrong.user.state", "Only a ${correctUserState} user's registration can be confirmed! User state: ${userState}");
    public static final PseudoMessage REGISTRATION_CODE_NOT_MATCH = error("registration.code.not.match",
            "Registration code doesn't match with user's registration! User ID: ${userId}; Registration code: ${registrationCode}");
    public static final PseudoMessage REGISTRATION_NOT_EXIST =
            error("registration.not.exist", "User doesn't have any registration! User ID: ${userId}");

}
