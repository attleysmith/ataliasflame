package hu.asgames.ws.api;

import hu.asgames.ws.api.vo.UserVo;

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

    @RequestMapping(method = RequestMethod.GET)
    List<UserVo> getUserList();

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    Long createUser(@RequestBody UserVo userVo);

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    UserVo getUser(@PathVariable Long id);

    @RequestMapping(path = "/{id}/modify", method = RequestMethod.POST)
    void modifyUser(@PathVariable Long id, @RequestBody UserVo userVo);

    @RequestMapping(path = "/{id}/delete", method = RequestMethod.POST)
    void deleteUser(@PathVariable Long id);

}
