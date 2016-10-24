package hu.asgames.ws.impl;

import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.UserWebService;
import hu.asgames.ws.api.vo.UserVo;

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
    public Long createUser(@RequestBody final UserVo userVo) {
        return userService.createUser(userVo);
    }

    @Override
    public UserVo getUser(@PathVariable final Long id) {
        return userService.getUser(id);
    }

    @Override
    public void modifyUser(@PathVariable Long id, @RequestBody final UserVo userVo) {
        userService.modifyUser(id, userVo);
    }

    @Override
    public void deleteUser(@PathVariable final Long id) {
        userService.deleteUser(id);
    }

}
