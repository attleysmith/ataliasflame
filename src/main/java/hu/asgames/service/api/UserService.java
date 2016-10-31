package hu.asgames.service.api;

import hu.asgames.ws.api.vo.user.ChangePasswordRequest;
import hu.asgames.ws.api.vo.user.CreateUserRequest;
import hu.asgames.ws.api.vo.user.LoginRequest;
import hu.asgames.ws.api.vo.user.ModifyUserRequest;
import hu.asgames.ws.api.vo.user.UserVo;

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
}
