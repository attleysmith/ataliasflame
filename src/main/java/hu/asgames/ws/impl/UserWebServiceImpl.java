package hu.asgames.ws.impl;

import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.UserWebService;
import hu.asgames.ws.api.vo.user.ChangePasswordRequest;
import hu.asgames.ws.api.vo.user.CreateUserRequest;
import hu.asgames.ws.api.vo.user.LoginRequest;
import hu.asgames.ws.api.vo.user.ModifyUserRequest;
import hu.asgames.ws.api.vo.user.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AMiklo on 2016.10.15.
 */
@RestController
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserService userService;

    @Override
    public List<UserVo> getUserList() {
        return userService.getUserList();
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
