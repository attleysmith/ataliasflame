package hu.asgames.service.impl;

import hu.asgames.dao.AttributeDao;
import hu.asgames.dao.CasteDao;
import hu.asgames.dao.CharacterDao;
import hu.asgames.dao.GenderDao;
import hu.asgames.dao.GodDao;
import hu.asgames.dao.LevelDao;
import hu.asgames.dao.RaceDao;
import hu.asgames.dao.SkillDao;
import hu.asgames.dao.UserDao;
import hu.asgames.domain.entities.Character;
import hu.asgames.domain.entities.CharacterAttributeCounterMap;
import hu.asgames.domain.entities.CharacterSkillCounterMap;
import hu.asgames.domain.enums.CharacterState;
import hu.asgames.domain.exceptions.BaseException;
import hu.asgames.messages.MessageBuilder;
import hu.asgames.messages.MessageUtil;
import hu.asgames.service.api.CharacterService;
import hu.asgames.ws.api.domain.Message;
import hu.asgames.ws.api.domain.character.CharacterVo;
import hu.asgames.ws.api.domain.character.CreateCharacterRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AMiklo on 2017.01.04.
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String BASE_CASTE = "ROGUE";
    private static final int STARTING_ATTRIBUTE_POINTS = 0;
    private static final int STARTING_SKILL_POINTS = 0;

    @Autowired
    private CharacterDao characterDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GenderDao genderDao;

    @Autowired
    private RaceDao raceDao;

    @Autowired
    private CasteDao casteDao;

    @Autowired
    private GodDao godDao;

    @Autowired
    private LevelDao levelDao;

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private SkillDao skillDao;

    @Transactional
    @Override
    public List<CharacterVo> getCharacterList() {
        return entitiesToVos(characterDao.findAll());
    }

    @Transactional
    @Override
    public Long createCharacter(final CreateCharacterRequest request) {
        Character character = new Character();
        character.setName(request.getName());
        character.setUser(userDao.findOne(request.getUserId())); // TODO: existence validation
        character.setCaste(casteDao.findByCode(BASE_CASTE)); // TODO: existence validation
        character.setGender(genderDao.findOne(request.getGenderId())); // TODO: existence validation
        character.setRace(raceDao.findOne(request.getRaceId())); // TODO: existence validation
        character.setProtectiveGod(godDao.findOne(request.getProtectiveGodId())); // TODO: existence validation

        // TODO: validate property consistency with allowed pairings

        character.setLevel(levelDao.findFirstLevel());
        character.setState(CharacterState.ALIVE);
        character.setAttributePoints(STARTING_ATTRIBUTE_POINTS);
        character.setSkillPoints(STARTING_SKILL_POINTS);
        character.setExperience(0);
        character.setWounds(0);

        final List<CharacterAttributeCounterMap> attributeList = new ArrayList<>();
        attributeDao.findAll().forEach(attribute -> {
            CharacterAttributeCounterMap attributeCounter = new CharacterAttributeCounterMap();
            attributeCounter.setCharacter(character);
            attributeCounter.setAttribute(attribute);
            attributeCounter.setCounter(0);
            attributeList.add(attributeCounter);
        });
        character.setAttributeList(attributeList);

        final List<CharacterSkillCounterMap> skillList = new ArrayList<>();
        skillDao.findAll().forEach(skill -> {
            CharacterSkillCounterMap skillCounter = new CharacterSkillCounterMap();
            skillCounter.setCharacter(character);
            skillCounter.setSkill(skill);
            skillCounter.setCounter(0);
            skillList.add(skillCounter);
        });
        character.setSkillList(skillList);

        // TODO: specialAbilityList

        character.setAttack(CharacterServiceCalculator.calculateAttack(character));
        character.setDefense(CharacterServiceCalculator.calculateDefense(character));
        character.setHealth(CharacterServiceCalculator.calculateHealth(character));
        character.setMagic(CharacterServiceCalculator.calculateMagic(character));

        character.setActualMagic(character.getMagic());
        character.setActualHealth(character.getHealth());

        characterDao.save(character);

        logInfo(new MessageBuilder(MessageUtil.CHARACTER_CREATED).arg("userId", character.getUser().getId().toString())
                .arg("characterId", character.getId().toString()).build());

        return character.getId();
    }

    @Transactional
    @Override
    public CharacterVo getCharacter(final Long characterId) {
        Character characterEntity = characterDao.findOne(characterId);
        CharacterVo character = entityToVo(characterEntity);
        characterExist(characterId, character);

        return character;
    }

    private List<CharacterVo> entitiesToVos(List<Character> characterList) {
        return characterList.stream().map(this::entityToVo).collect(Collectors.toList());
    }

    private CharacterVo entityToVo(Character character) {
        if (character != null) {
            CharacterVo characterVo = new CharacterVo();
            characterVo.setId(character.getId());
            characterVo.setUser(CharacterServiceHelper.getUserLookup(character.getUser()));
            characterVo.setName(character.getName());
            characterVo.setGender(CharacterServiceHelper.getGenderLookup(character.getGender()));
            characterVo.setRace(CharacterServiceHelper.getRaceLookup(character.getRace()));
            characterVo.setCaste(CharacterServiceHelper.getCasteLookup(character.getCaste()));
            characterVo.setProtectiveGod(CharacterServiceHelper.getGodLookup(character.getProtectiveGod()));
            characterVo.setLevel(CharacterServiceHelper.getLevelLookup(character.getLevel()));
            characterVo.setExperience(character.getExperience());
            characterVo.setState(character.getState().name());
            characterVo.setAttack(character.getAttack());
            characterVo.setDefense(character.getDefense());
            characterVo.setHealth(character.getHealth());
            characterVo.setActualHealth(character.getActualHealth());
            characterVo.setMagic(character.getMagic());
            characterVo.setActualMagic(character.getActualMagic());
            characterVo.setAttributePoints(character.getAttributePoints());
            characterVo.setSkillPoints(character.getSkillPoints());
            characterVo.setWounds(character.getWounds());
            characterVo.setTargetCaste(CharacterServiceHelper.getCasteLookup(character.getTargetCaste()));
            if (character.getAttributeList() != null && !character.getAttributeList().isEmpty()) {
                characterVo.setAttributeList(CharacterServiceHelper.getAttributeCounterList(character.getAttributeList()));
            }
            if (character.getSkillList() != null && !character.getSkillList().isEmpty()) {
                characterVo.setSkillList(CharacterServiceHelper.getSkillCounterList(character.getSkillList()));
            }
            if (character.getSpecialAbilityList() != null && !character.getSpecialAbilityList().isEmpty()) {
                characterVo.setSpecialAbilityList(CharacterServiceHelper.getSpecialAbilityLookupList(character.getSpecialAbilityList()));
            }

            return characterVo;
        }
        return null;
    }

    private void characterExist(Long characterId, Object character) {
        if (character == null) {
            handleError(new MessageBuilder(MessageUtil.CHARACTER_NOT_EXIST).arg("characterId", characterId.toString()).build());
        }
    }

    private void handleError(Message message) {
        LOGGER.error(message.fullMessage());
        throw new BaseException(message);
    }

    private void logInfo(Message message) {
        LOGGER.info(message.fullMessage());
    }
}
