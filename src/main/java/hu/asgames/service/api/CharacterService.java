package hu.asgames.service.api;

import hu.asgames.ws.api.domain.character.CharacterVo;
import hu.asgames.ws.api.domain.character.CreateCharacterRequest;

import java.util.List;

/**
 * @author AMiklo on 2017.01.04.
 */
public interface CharacterService {

    List<CharacterVo> getCharacterList();

    Long createCharacter(CreateCharacterRequest request);

    CharacterVo getCharacter(Long characterId);

}
