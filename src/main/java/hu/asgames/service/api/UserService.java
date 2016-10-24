package hu.asgames.service.api;

import hu.asgames.ws.api.vo.UserVo;

import java.util.List;

/**
 * @author AMiklo on 2016.10.15.
 */
public interface UserService {

    List<UserVo> getUserList();

    Long createUser(UserVo userVo);

    UserVo getUser(Long id);

    void modifyUser(Long id, UserVo userVo);

    void deleteUser(Long id);

}
