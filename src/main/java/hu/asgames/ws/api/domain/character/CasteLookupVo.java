package hu.asgames.ws.api.domain.character;

import hu.asgames.ws.api.domain.IdentifiedVoBase;

/**
 * @author AMiklo on 2017.01.04.
 */
public class CasteLookupVo extends IdentifiedVoBase {

    private String code;

    // Getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
