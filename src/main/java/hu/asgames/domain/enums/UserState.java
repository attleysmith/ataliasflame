package hu.asgames.domain.enums;

/**
 * @author AMiklo on 2016.09.11.
 */
public enum UserState {

    /**
     * Registered user waiting for confirmation
     */
    TEMPORARY,

    /**
     * Confirmed user for general usage
     */
    NORMAL,

    /**
     * User with disallowed connectivity
     */
    BANNED,

    /**
     * Inactive user
     */
    DORMANT,

    /**
     * Deleted user
     */
    DELETED
}
