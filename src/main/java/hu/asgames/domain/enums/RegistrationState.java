package hu.asgames.domain.enums;

/**
 * @author AMiklo on 2016.09.11.
 */
public enum RegistrationState {

    /**
     * Newly created registration waiting for confirmation
     */
    NEW,

    /**
     * A confirmed registration
     */
    CONFIRMED,

    /**
     * Expired registration without confirmation
     */
    EXPIRED
}
