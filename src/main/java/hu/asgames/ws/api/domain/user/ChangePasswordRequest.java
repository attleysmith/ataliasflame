package hu.asgames.ws.api.domain.user;

/**
 * @author AMiklo on 2016.10.24.
 */
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;

    // Getters and setters

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {

        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
