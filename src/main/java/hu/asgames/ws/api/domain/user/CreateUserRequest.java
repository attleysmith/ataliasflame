package hu.asgames.ws.api.domain.user;

/**
 * @author AMiklo on 2016.10.24.
 */
public class CreateUserRequest {

    private String displayName;
    private String username;
    private String password;
    private String email;

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
}
