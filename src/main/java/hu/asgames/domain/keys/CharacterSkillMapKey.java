package hu.asgames.domain.keys;

import hu.asgames.domain.entities.Character;
import hu.asgames.domain.entities.Skill;

import java.io.Serializable;

/**
 * @author AMiklo on 2017.01.05.
 */
public class CharacterSkillMapKey implements Serializable {

    private static final long serialVersionUID = 3812301857947161733L;

    private Character character;

    private Skill skill;

    // Getters and setters

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(final Character character) {
        this.character = character;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(final Skill skill) {
        this.skill = skill;
    }
}
