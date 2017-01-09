package hu.asgames.ws.api.domain.character;

import hu.asgames.ws.api.domain.IdentifiedVoBase;

/**
 * @author AMiklo on 2017.01.04.
 */
public class UserLookupVo extends IdentifiedVoBase {

    private String username;

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
