package hu.asgames.service.impl;

import hu.asgames.domain.entities.Attribute;
import hu.asgames.domain.entities.Caste;
import hu.asgames.domain.entities.CharacterAttributeCounterMap;
import hu.asgames.domain.entities.CharacterSkillCounterMap;
import hu.asgames.domain.entities.Gender;
import hu.asgames.domain.entities.God;
import hu.asgames.domain.entities.Level;
import hu.asgames.domain.entities.Race;
import hu.asgames.domain.entities.Skill;
import hu.asgames.domain.entities.SpecialAbility;
import hu.asgames.domain.entities.User;
import hu.asgames.ws.api.domain.character.AttributeLookupVo;
import hu.asgames.ws.api.domain.character.CasteLookupVo;
import hu.asgames.ws.api.domain.character.CharacterAttributeCounterVo;
import hu.asgames.ws.api.domain.character.CharacterSkillCounterVo;
import hu.asgames.ws.api.domain.character.GenderLookupVo;
import hu.asgames.ws.api.domain.character.GodLookupVo;
import hu.asgames.ws.api.domain.character.LevelLookupVo;
import hu.asgames.ws.api.domain.character.RaceLookupVo;
import hu.asgames.ws.api.domain.character.SkillLookupVo;
import hu.asgames.ws.api.domain.character.SpecialAbilityLookupVo;
import hu.asgames.ws.api.domain.character.UserLookupVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AMiklo on 2017.01.04.
 */
public final class CharacterServiceHelper {

    private CharacterServiceHelper() {}

    public static UserLookupVo getUserLookup(User user) {
        UserLookupVo userLookup = new UserLookupVo();
        userLookup.setId(user.getId());
        userLookup.setUsername(user.getUsername());
        return userLookup;
    }

    public static GenderLookupVo getGenderLookup(Gender gender) {
        GenderLookupVo genderLookup = new GenderLookupVo();
        genderLookup.setId(gender.getId());
        genderLookup.setCode(gender.getCode());
        return genderLookup;
    }

    public static RaceLookupVo getRaceLookup(Race race) {
        RaceLookupVo raceLookup = new RaceLookupVo();
        raceLookup.setId(race.getId());
        raceLookup.setCode(race.getCode());
        return raceLookup;
    }

    public static CasteLookupVo getCasteLookup(Caste caste) {
        if (caste == null) {
            return null;
        }
        CasteLookupVo casteLookup = new CasteLookupVo();
        casteLookup.setId(caste.getId());
        casteLookup.setCode(caste.getCode());
        return casteLookup;
    }

    public static GodLookupVo getGodLookup(God god) {
        GodLookupVo godLookup = new GodLookupVo();
        godLookup.setId(god.getId());
        godLookup.setCode(god.getCode());
        return godLookup;
    }

    public static LevelLookupVo getLevelLookup(Level level) {
        LevelLookupVo levelLookup = new LevelLookupVo();
        levelLookup.setId(level.getId());
        levelLookup.setLevel(level.getLevel());
        return levelLookup;
    }

    public static List<CharacterAttributeCounterVo> getAttributeCounterList(List<CharacterAttributeCounterMap> attributeCounterList) {
        return attributeCounterList.stream().map(CharacterServiceHelper::getAttributeCounter).collect(Collectors.toList());
    }

    private static CharacterAttributeCounterVo getAttributeCounter(CharacterAttributeCounterMap attributeCounter) {
        CharacterAttributeCounterVo attributeCounterVo = new CharacterAttributeCounterVo();
        attributeCounterVo.setCounter(attributeCounter.getCounter());
        attributeCounterVo.setAttribute(getAttributeLookup(attributeCounter.getAttribute()));
        return attributeCounterVo;
    }

    private static AttributeLookupVo getAttributeLookup(Attribute attribute) {
        AttributeLookupVo attributeLookup = new AttributeLookupVo();
        attributeLookup.setId(attribute.getId());
        attributeLookup.setCode(attribute.getCode());
        return attributeLookup;
    }

    public static List<CharacterSkillCounterVo> getSkillCounterList(List<CharacterSkillCounterMap> skillCounterList) {
        return skillCounterList.stream().map(CharacterServiceHelper::getSkillCounter).collect(Collectors.toList());
    }

    private static CharacterSkillCounterVo getSkillCounter(CharacterSkillCounterMap skillCounter) {
        CharacterSkillCounterVo skillCounterVo = new CharacterSkillCounterVo();
        skillCounterVo.setCounter(skillCounter.getCounter());
        skillCounterVo.setSkill(getSkillLookup(skillCounter.getSkill()));
        return skillCounterVo;
    }

    private static SkillLookupVo getSkillLookup(Skill skill) {
        SkillLookupVo skillLookup = new SkillLookupVo();
        skillLookup.setId(skill.getId());
        skillLookup.setCode(skill.getCode());
        return skillLookup;
    }

    public static List<SpecialAbilityLookupVo> getSpecialAbilityLookupList(List<SpecialAbility> specialAbilityList) {
        return specialAbilityList.stream().map(CharacterServiceHelper::getSpecialAbilityLookup).collect(Collectors.toList());
    }

    private static SpecialAbilityLookupVo getSpecialAbilityLookup(SpecialAbility specialAbility) {
        SpecialAbilityLookupVo specialAbilityLookup = new SpecialAbilityLookupVo();
        specialAbilityLookup.setId(specialAbility.getId());
        specialAbilityLookup.setName(specialAbility.getName());
        return specialAbilityLookup;
    }

}
