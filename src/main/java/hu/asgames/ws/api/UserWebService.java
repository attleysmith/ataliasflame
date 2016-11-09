package hu.asgames.ws.api;

import hu.asgames.ws.api.domain.BaseRequest;
import hu.asgames.ws.api.domain.GenericResponse;
import hu.asgames.ws.api.domain.user.ChangePasswordRequest;
import hu.asgames.ws.api.domain.user.CreateUserRequest;
import hu.asgames.ws.api.domain.user.LoginRequest;
import hu.asgames.ws.api.domain.user.ModifyUserRequest;
import hu.asgames.ws.api.domain.user.UserVo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author AMiklo on 2016.10.15.
 */
@RequestMapping(path = "/user")
public interface UserWebService {

    @RequestMapping(path = "/list", method = RequestMethod.POST)
    GenericResponse<List<UserVo>> getUserList(@RequestBody BaseRequest request);

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    Long createUser(@RequestBody CreateUserRequest request);

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    UserVo getUser(@PathVariable Long id);

    @RequestMapping(path = "/modify/{id}", method = RequestMethod.POST)
    void modifyUser(@PathVariable Long id, @RequestBody ModifyUserRequest request);

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    void deleteUser(@PathVariable Long id);

    @RequestMapping(path = "/password/{id}", method = RequestMethod.POST)
    void changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request);

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    Long login(@RequestBody LoginRequest request);
}
