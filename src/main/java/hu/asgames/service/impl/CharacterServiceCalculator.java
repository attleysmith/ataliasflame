package hu.asgames.service.impl;

import hu.asgames.domain.entities.Attribute;
import hu.asgames.domain.entities.Character;
import hu.asgames.domain.entities.CharacterAttributeCounterMap;
import hu.asgames.domain.entities.Race;
import hu.asgames.domain.entities.RaceAttributeMultiplierMap;

/**
 * @author AMiklo on 2017.01.05.
 */
public final class CharacterServiceCalculator {

    private CharacterServiceCalculator() {}

    private static final int BASE_ATTACK = 80;
    private static final int BASE_DEFENSE = 20;
    private static final int BASE_HEALTH = 100;
    private static final int BASE_MAGIC = 0;

    public static int calculateAttack(Character character) {
        int multiplier = 0;
        for (CharacterAttributeCounterMap attributeCounter : character.getAttributeList()) {
            int count = attributeCounter.getCounter();
            if (count != 0) {
                Attribute attribute = attributeCounter.getAttribute();
                multiplier += calculateEffect(count * attribute.getAttackMultiplier(), attribute, character.getRace());
            }
        }
        return multiply(BASE_ATTACK, multiplier);
    }

    public static int calculateDefense(Character character) {
        int multiplier = 0;
        for (CharacterAttributeCounterMap attributeCounter : character.getAttributeList()) {
            int count = attributeCounter.getCounter();
            if (count != 0) {
                Attribute attribute = attributeCounter.getAttribute();
                multiplier += calculateEffect(count * attribute.getDefenseMultiplier(), attribute, character.getRace());
            }
        }
        return multiply(BASE_DEFENSE, multiplier);
    }

    public static int calculateHealth(Character character) {
        int multiplier = 0;
        for (CharacterAttributeCounterMap attributeCounter : character.getAttributeList()) {
            int count = attributeCounter.getCounter();
            if (count != 0) {
                Attribute attribute = attributeCounter.getAttribute();
                multiplier += calculateEffect(count * attribute.getHealthMultiplier(), attribute, character.getRace());
            }
        }
        return multiply(BASE_HEALTH, multiplier);
    }

    public static int calculateMagic(Character character) {
        int modifier = 0;
        for (CharacterAttributeCounterMap attributeCounter : character.getAttributeList()) {
            int count = attributeCounter.getCounter();
            if (count != 0) {
                Attribute attribute = attributeCounter.getAttribute();
                modifier += calculateEffect(count * attribute.getMagicModifier(), attribute, character.getRace());
            }
        }
        return BASE_MAGIC + modifier;
    }

    private static int calculateEffect(int baseEffect, Attribute attribute, Race race) {
        int raceMultiplier = race.getAttributeMultiplierMapList().stream().filter(multiplierMap -> multiplierMap.getAttribute() == attribute)
                .mapToInt(RaceAttributeMultiplierMap::getMultiplier).sum();
        return multiply(baseEffect, raceMultiplier);
    }

    private static int multiply(int base, int multiplier) {
        // TODO: maybe BigDecimal values are better than integers
        return (int)Math.floor(base * (100 + multiplier) / 100f);
    }
}
