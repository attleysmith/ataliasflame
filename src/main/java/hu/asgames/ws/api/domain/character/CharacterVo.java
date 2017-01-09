package hu.asgames.ws.api.domain.character;

import hu.asgames.ws.api.domain.IdentifiedVoBase;

import java.util.List;

/**
 * @author AMiklo on 2017.01.04.
 */
public class CharacterVo extends IdentifiedVoBase {

    private UserLookupVo user;
    private String name;
    private GenderLookupVo gender;
    private RaceLookupVo race;
    private CasteLookupVo caste;
    private GodLookupVo protectiveGod;
    private LevelLookupVo level;
    private long experience;
    private String state;
    private int attack;
    private int defense;
    private int health;
    private int actualHealth;
    private int magic;
    private int actualMagic;
    private int attributePoints;
    private int skillPoints;
    private int wounds;
    private CasteLookupVo targetCaste;
    private List<CharacterAttributeCounterVo> attributeList;
    private List<CharacterSkillCounterVo> skillList;
    private List<SpecialAbilityLookupVo> specialAbilityList;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public GenderLookupVo getGender() {
        return gender;
    }

    public void setGender(final GenderLookupVo gender) {
        this.gender = gender;
    }

    public RaceLookupVo getRace() {
        return race;
    }

    public void setRace(final RaceLookupVo race) {
        this.race = race;
    }

    public CasteLookupVo getCaste() {
        return caste;
    }

    public void setCaste(final CasteLookupVo caste) {
        this.caste = caste;
    }

    public GodLookupVo getProtectiveGod() {
        return protectiveGod;
    }

    public void setProtectiveGod(final GodLookupVo protectiveGod) {
        this.protectiveGod = protectiveGod;
    }

    public LevelLookupVo getLevel() {
        return level;
    }

    public void setLevel(final LevelLookupVo level) {
        this.level = level;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(final long experience) {
        this.experience = experience;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(final int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(final int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public int getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(final int actualHealth) {
        this.actualHealth = actualHealth;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(final int magic) {
        this.magic = magic;
    }

    public int getActualMagic() {
        return actualMagic;
    }

    public void setActualMagic(final int actualMagic) {
        this.actualMagic = actualMagic;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public void setAttributePoints(final int attributePoints) {
        this.attributePoints = attributePoints;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(final int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(final int wounds) {
        this.wounds = wounds;
    }

    public CasteLookupVo getTargetCaste() {
        return targetCaste;
    }

    public void setTargetCaste(final CasteLookupVo targetCaste) {
        this.targetCaste = targetCaste;
    }

    public UserLookupVo getUser() {
        return user;
    }

    public void setUser(final UserLookupVo user) {
        this.user = user;
    }

    public List<CharacterAttributeCounterVo> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(final List<CharacterAttributeCounterVo> attributeList) {
        this.attributeList = attributeList;
    }

    public List<CharacterSkillCounterVo> getSkillList() {
        return skillList;
    }

    public void setSkillList(final List<CharacterSkillCounterVo> skillList) {
        this.skillList = skillList;
    }

    public List<SpecialAbilityLookupVo> getSpecialAbilityList() {
        return specialAbilityList;
    }

    public void setSpecialAbilityList(final List<SpecialAbilityLookupVo> specialAbilityList) {
        this.specialAbilityList = specialAbilityList;
    }
}
