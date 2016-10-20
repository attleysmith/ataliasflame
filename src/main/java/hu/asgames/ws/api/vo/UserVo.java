package hu.asgames.ws.api.vo;

/**
 * @author AMiklo on 2016.10.15.
 */
public class UserVo extends IdentifiedVoBase {

    private String username;

    private String password;

    private String email;

    private String userState;

    // TODO: go into another object
    private String registrationState;

    private String registrationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
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
