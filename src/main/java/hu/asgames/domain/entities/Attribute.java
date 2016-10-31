package hu.asgames.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.13.
 */
@Entity
@Table(name = "attribute")
public class Attribute extends IdentifiedEntityBase {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "attack_multiplier", nullable = false)
    private int attackMultiplier;

    @Column(name = "defense_multiplier", nullable = false)
    private int defenseMultiplier;

    @Column(name = "damage_multiplier", nullable = false)
    private int damageMultiplier;

    @Column(name = "health_multiplier", nullable = false)
    private int healthMultiplier;

    @Column(name = "magic_modifier", nullable = false)
    private int magicModifier;

    @Column(name = "description")
    private String description;

    // Getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAttackMultiplier() {
        return attackMultiplier;
    }

    public void setAttackMultiplier(final int attackMultiplier) {
        this.attackMultiplier = attackMultiplier;
    }

    public int getDefenseMultiplier() {
        return defenseMultiplier;
    }

    public void setDefenseMultiplier(final int defenseMultiplier) {
        this.defenseMultiplier = defenseMultiplier;
    }

    public int getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(final int damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public int getHealthMultiplier() {
        return healthMultiplier;
    }

    public void setHealthMultiplier(final int healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    public int getMagicModifier() {
        return magicModifier;
    }

    public void setMagicModifier(final int magicModifier) {
        this.magicModifier = magicModifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
