package hu.asgames.ws.impl;

import hu.asgames.domain.exceptions.BaseException;
import hu.asgames.messages.MessageUtil;
import hu.asgames.service.api.CharacterService;
import hu.asgames.ws.api.CharacterWebService;
import hu.asgames.ws.api.domain.BaseRequest;
import hu.asgames.ws.api.domain.BaseResponse;
import hu.asgames.ws.api.domain.GenericRequest;
import hu.asgames.ws.api.domain.GenericResponse;
import hu.asgames.ws.api.domain.ResponseStatus;
import hu.asgames.ws.api.domain.character.CharacterVo;
import hu.asgames.ws.api.domain.character.CreateCharacterRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AMiklo on 2017.01.04.
 */
@RestController
public class CharacterWebServiceImpl implements CharacterWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWebServiceImpl.class);

    @Autowired
    private CharacterService characterService;

    @Override
    public GenericResponse<List<CharacterVo>> getCharacterList(@RequestBody final BaseRequest request) {
        return doResponse(new GenericResponse<List<CharacterVo>>(), "getCharacterList");
    }

    @Override
    public GenericResponse<Long> createCharacter(@RequestBody final GenericRequest<CreateCharacterRequest> request) {
        return doResponse(new GenericResponse<Long>(), "createCharacter", request.getRequestBody());
    }

    @Override
    public GenericResponse<CharacterVo> getCharacter(@PathVariable final Long id, @RequestBody final BaseRequest request) {
        return doResponse(new GenericResponse<CharacterVo>(), "getCharacter", id);
    }

    @SuppressWarnings("unchecked")
    private <R extends BaseResponse> R doResponse(R response, String methodName, Object... args) {
        try {
            if (response instanceof GenericResponse) {
                callServiceWithGenericResponse((GenericResponse<?>)response, methodName, args);
            } else {
                callServiceWithBaseResponse(response, methodName, args);
            }
            response.setResponseStatus(ResponseStatus.OK);
        } catch (InvocationTargetException exception) {
            // TODO: using interceptor or something...
            LOGGER.error(exception.getMessage(), exception);
            Throwable targetException = exception.getTargetException();
            if (targetException instanceof BaseException) {
                setBaseException(response, (BaseException)targetException);
            } else {
                setGeneralException(response, targetException);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            setGeneralException(response, exception);
        }
        response.setResponseTime(LocalDateTime.now());
        return response;
    }

    @SuppressWarnings("unchecked")
    private <T> void callServiceWithGenericResponse(GenericResponse<T> response, String methodName, Object... args) throws ReflectiveOperationException {
        T responseBody = (T)characterService.getClass().getMethod(methodName, getParamTypes(args)).invoke(characterService, args);
        response.setResponseBody(responseBody);
    }

    @SuppressWarnings("unchecked")
    private <T> void callServiceWithBaseResponse(BaseResponse response, String methodName, Object... args) throws ReflectiveOperationException {
        characterService.getClass().getMethod(methodName, getParamTypes(args)).invoke(characterService, args);
    }

    private Class<?>[] getParamTypes(Object... params) {
        List<Class<?>> paramTypes = Arrays.stream(params).map(Object::getClass).collect(Collectors.toList());
        return paramTypes.toArray(new Class<?>[paramTypes.size()]);
    }

    private void setBaseException(BaseResponse response, BaseException exception) {
        response.setResponseStatus(ResponseStatus.ERROR);
        response.setResponseMessage(exception.getErrorMessage());
    }

    private void setGeneralException(BaseResponse response, Throwable exception) {
        response.setResponseStatus(ResponseStatus.ERROR);
        response.setResponseMessage(MessageUtil.generalException(exception.getClass().getSimpleName() + " - " + exception.getMessage()));
    }
}
