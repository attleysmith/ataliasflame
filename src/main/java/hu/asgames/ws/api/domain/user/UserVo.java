package hu.asgames.ws.api.domain.user;

import hu.asgames.ws.api.domain.IdentifiedVoBase;

/**
 * @author AMiklo on 2016.10.15.
 */
public class UserVo extends IdentifiedVoBase {

    private String displayName;
    private String username;
    private String email;
    private String userState;
    private String registrationState;
    private String registrationCode;

    // Getters and setters

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(final String userState) {
        this.userState = userState;
    }

    public String getRegistrationState() {
        return registrationState;
    }

    public void setRegistrationState(final String registrationState) {
        this.registrationState = registrationState;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(final String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
