package hu.asgames.ws.api.domain.character;

/**
 * @author AMiklo on 2017.01.05.
 */
public class CharacterSkillCounterVo {

    private SkillLookupVo skill;
    private int counter;

    // Getters and setters

    public SkillLookupVo getSkill() {
        return skill;
    }

    public void setSkill(final SkillLookupVo skill) {
        this.skill = skill;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(final int counter) {
        this.counter = counter;
    }
}
