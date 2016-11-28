package hu.asgames.domain.keys;

import hu.asgames.domain.entities.Attribute;
import hu.asgames.domain.entities.Race;

import java.io.Serializable;

/**
 * @author AMiklo on 2016.09.18.
 */
public class RaceAttributeMapKey implements Serializable {

    private static final long serialVersionUID = -1985691385295208833L;

    private Race race;

    private Attribute attribute;

    // Getters and setters

    public Race getRace() {
        return race;
    }

    public void setRace(final Race race) {
        this.race = race;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(final Attribute attribute) {
        this.attribute = attribute;
    }
}
