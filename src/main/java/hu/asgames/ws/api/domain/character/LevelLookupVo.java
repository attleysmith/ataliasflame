package hu.asgames.ws.api.domain.character;

import hu.asgames.ws.api.domain.IdentifiedVoBase;

/**
 * @author AMiklo on 2017.01.04.
 */
public class LevelLookupVo extends IdentifiedVoBase {

    private int level;

    // Getters and setters

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }
}
