package hu.asgames.ws.impl;

import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.UserWebService;
import hu.asgames.ws.api.domain.BaseRequest;
import hu.asgames.ws.api.domain.GenericResponse;
import hu.asgames.ws.api.domain.ResponseStatus;
import hu.asgames.ws.api.domain.user.ChangePasswordRequest;
import hu.asgames.ws.api.domain.user.CreateUserRequest;
import hu.asgames.ws.api.domain.user.LoginRequest;
import hu.asgames.ws.api.domain.user.ModifyUserRequest;
import hu.asgames.ws.api.domain.user.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AMiklo on 2016.10.15.
 */
@RestController
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserService userService;

    @Override
    public GenericResponse<List<UserVo>> getUserList(@RequestBody final BaseRequest request) {
        GenericResponse<List<UserVo>> response = new GenericResponse<>();
        try {
            List<UserVo> userList = userService.getUserList();
            response.setResponseBody(userList);
            response.setResponseStatus(ResponseStatus.OK);
        } catch (Exception e) {
            response.setErrorMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.ERROR);
        }
        response.setResponseTime(LocalDateTime.now());
        return response;
    }

    @Override
    public Long createUser(@RequestBody final CreateUserRequest request) {
        return userService.createUser(request);
    }

    @Override
    public UserVo getUser(@PathVariable final Long id) {
        return userService.getUser(id);
    }

    @Override
    public void modifyUser(@PathVariable final Long id, @RequestBody final ModifyUserRequest request) {
        userService.modifyUser(id, request);
    }

    @Override
    public void deleteUser(@PathVariable final Long id) {
        userService.deleteUser(id);
    }

    @Override
    public void changePassword(@PathVariable final Long id, @RequestBody final ChangePasswordRequest request) {
        userService.changePassword(id, request);
    }

    @Override
    public Long login(@RequestBody final LoginRequest request) {
        return userService.login(request);
    }
}
