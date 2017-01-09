package hu.asgames.domain.keys;

import hu.asgames.domain.entities.Attribute;
import hu.asgames.domain.entities.Character;

import java.io.Serializable;

/**
 * @author AMiklo on 2017.01.05.
 */
public class CharacterAttributeMapKey implements Serializable {

    private static final long serialVersionUID = -8919495568530497107L;

    private Character character;

    private Attribute attribute;

    // Getters and setters

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(final Attribute attribute) {
        this.attribute = attribute;
    }
}
