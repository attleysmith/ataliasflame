package hu.asgames.ws.api;

import hu.asgames.ws.api.domain.BaseRequest;
import hu.asgames.ws.api.domain.GenericRequest;
import hu.asgames.ws.api.domain.GenericResponse;
import hu.asgames.ws.api.domain.character.CharacterVo;
import hu.asgames.ws.api.domain.character.CreateCharacterRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author AMiklo on 2017.01.04.
 */
@RequestMapping(path = "/character")
public interface CharacterWebService {

    @RequestMapping(path = "/list", method = RequestMethod.POST)
    GenericResponse<List<CharacterVo>> getCharacterList(@RequestBody BaseRequest request);

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    GenericResponse<Long> createCharacter(@RequestBody GenericRequest<CreateCharacterRequest> request);

    @RequestMapping(path = "/get/{id}", method = RequestMethod.POST)
    GenericResponse<CharacterVo> getCharacter(@PathVariable Long id, @RequestBody BaseRequest request);

}
