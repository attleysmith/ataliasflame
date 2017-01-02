package hu.asgames.service.api;

import hu.asgames.ws.api.domain.user.ChangePasswordRequest;
import hu.asgames.ws.api.domain.user.CreateUserRequest;
import hu.asgames.ws.api.domain.user.LoginRequest;
import hu.asgames.ws.api.domain.user.ModifyUserRequest;
import hu.asgames.ws.api.domain.user.UserVo;

import java.util.List;

/**
 * @author AMiklo on 2016.10.15.
 */
public interface UserService {

    List<UserVo> getUserList();

    Long createUser(CreateUserRequest request);

    UserVo getUser(Long id);

    void modifyUser(Long id, ModifyUserRequest request);

    void deleteUser(Long id);

    void changePassword(Long id, ChangePasswordRequest request);

    Long login(LoginRequest request);

    // TODO: this should be connected to user by id
    UserVo registration(String registrationCode);
}
