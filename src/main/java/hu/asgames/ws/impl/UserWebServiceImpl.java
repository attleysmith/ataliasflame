package hu.asgames.ws.impl;

import hu.asgames.domain.exception.BaseException;
import hu.asgames.messages.MessageUtil;
import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.UserWebService;
import hu.asgames.ws.api.domain.BaseRequest;
import hu.asgames.ws.api.domain.BaseResponse;
import hu.asgames.ws.api.domain.GenericRequest;
import hu.asgames.ws.api.domain.GenericResponse;
import hu.asgames.ws.api.domain.ResponseStatus;
import hu.asgames.ws.api.domain.user.ChangePasswordRequest;
import hu.asgames.ws.api.domain.user.CreateUserRequest;
import hu.asgames.ws.api.domain.user.LoginRequest;
import hu.asgames.ws.api.domain.user.ModifyUserRequest;
import hu.asgames.ws.api.domain.user.UserVo;

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
 * @author AMiklo on 2016.10.15.
 */
@RestController
public class UserWebServiceImpl implements UserWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWebServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public GenericResponse<List<UserVo>> getUserList(@RequestBody final BaseRequest request) {
        return doResponse(new GenericResponse<List<UserVo>>(), "getUserList");
    }

    @Override
    public GenericResponse<Long> createUser(@RequestBody final GenericRequest<CreateUserRequest> request) {
        return doResponse(new GenericResponse<Long>(), "createUser", request.getRequestBody());
    }

    @Override
    public GenericResponse<UserVo> getUser(@PathVariable final Long id, @RequestBody final BaseRequest request) {
        return doResponse(new GenericResponse<UserVo>(), "getUser", id);
    }

    @Override
    public BaseResponse modifyUser(@PathVariable final Long id, @RequestBody final GenericRequest<ModifyUserRequest> request) {
        return doResponse(new BaseResponse(), "modifyUser", id, request.getRequestBody());
    }

    @Override
    public BaseResponse deleteUser(@PathVariable final Long id, @RequestBody final BaseRequest request) {
        return doResponse(new BaseResponse(), "deleteUser", id);
    }

    @Override
    public BaseResponse changePassword(@PathVariable final Long id, @RequestBody final GenericRequest<ChangePasswordRequest> request) {
        return doResponse(new BaseResponse(), "changePassword", id, request.getRequestBody());
    }

    @Override
    public GenericResponse<Long> login(@RequestBody final GenericRequest<LoginRequest> request) {
        return doResponse(new GenericResponse<Long>(), "login", request.getRequestBody());
    }

    @Override
    public BaseResponse registration(@PathVariable final Long id, @PathVariable final String registrationCode, @RequestBody final BaseRequest request) {
        return doResponse(new BaseResponse(), "confirmRegistration", id, registrationCode);
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
        T responseBody = (T)userService.getClass().getMethod(methodName, getParamTypes(args)).invoke(userService, args);
        response.setResponseBody(responseBody);
    }

    @SuppressWarnings("unchecked")
    private <T> void callServiceWithBaseResponse(BaseResponse response, String methodName, Object... args) throws ReflectiveOperationException {
        userService.getClass().getMethod(methodName, getParamTypes(args)).invoke(userService, args);
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
