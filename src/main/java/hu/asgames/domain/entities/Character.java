package hu.asgames.domain.entities;

import hu.asgames.domain.enums.CharacterState;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.12.
 */
@Entity
@Table(name = "character")
public class Character extends IdentifiedEntityBase {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caste_id", nullable = false)
    private Caste caste;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "protective_god_id", nullable = false)
    private God protectiveGod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @Column(name = "experience", nullable = false)
    private long experience;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CharacterState state;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "defense", nullable = false)
    private int defense;

    @Column(name = "health", nullable = false)
    private int health;

    @Column(name = "actual_health", nullable = false)
    private int actualHealth;

    @Column(name = "magic", nullable = false)
    private int magic;

    @Column(name = "actual_magic", nullable = false)
    private int actualMagic;

    @Column(name = "attribute_points", nullable = false)
    private int attributePoints;

    @Column(name = "skill_points", nullable = false)
    private int skillPoints;

    @Column(name = "wounds", nullable = false)
    private int wounds;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_caste_id")
    private Caste targetCaste;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "character_attribute_map", joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"))
    private List<Attribute> attributeList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "character_skill_map", joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private List<Skill> skillList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "character_special_ability_map", joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "special_ability_id", referencedColumnName = "id"))
    private List<SpecialAbility> specialAbilityList;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Caste getCaste() {
        return caste;
    }

    public void setCaste(Caste caste) {
        this.caste = caste;
    }

    public God getProtectiveGod() {
        return protectiveGod;
    }

    public void setProtectiveGod(God protectiveGod) {
        this.protectiveGod = protectiveGod;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public CharacterState getState() {
        return state;
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(int actualHealth) {
        this.actualHealth = actualHealth;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getActualMagic() {
        return actualMagic;
    }

    public void setActualMagic(int actualMagic) {
        this.actualMagic = actualMagic;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public void setAttributePoints(int attributePoints) {
        this.attributePoints = attributePoints;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public Caste getTargetCaste() {
        return targetCaste;
    }

    public void setTargetCaste(Caste targetCaste) {
        this.targetCaste = targetCaste;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public List<SpecialAbility> getSpecialAbilityList() {
        return specialAbilityList;
    }

    public void setSpecialAbilityList(List<SpecialAbility> specialAbilityList) {
        this.specialAbilityList = specialAbilityList;
    }
}
