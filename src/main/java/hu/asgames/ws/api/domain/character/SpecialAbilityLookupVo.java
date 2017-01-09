package hu.asgames.ws.api.domain.character;

import hu.asgames.ws.api.domain.IdentifiedVoBase;

/**
 * @author AMiklo on 2017.01.04.
 */
public class SpecialAbilityLookupVo extends IdentifiedVoBase {

    private String name;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
