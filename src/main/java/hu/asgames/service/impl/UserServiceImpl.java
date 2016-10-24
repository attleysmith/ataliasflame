package hu.asgames.service.impl;

import hu.asgames.dao.UserDao;
import hu.asgames.domain.entities.Registration;
import hu.asgames.domain.entities.User;
import hu.asgames.domain.enums.RegistrationState;
import hu.asgames.domain.enums.UserState;
import hu.asgames.service.api.AuthenticationService;
import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AMiklo on 2016.10.15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserVo> getUserList() {
        return entitiesToVos(userDao.findAll());
    }

    @Override
    public Long createUser(final UserVo userVo) {

        User user = new User();
        user.setUsername(userVo.getUsername());
        user.setPassword(authenticationService.encodePassword(userVo.getPassword()));
        user.setEmail(userVo.getEmail());
        user.setState(UserState.TEMPORARY);

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setState(RegistrationState.NEW);
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setRegistrationCode(generateRegistrationCode());
        user.setRegistration(registration);

        // TODO: roles and permissions

        userDao.save(user);

        return user.getId();
    }

    @Override
    public UserVo getUser(final Long id) {
        return entityToVo(userDao.findOne(id));
    }

    @Override
    public void modifyUser(final Long id, final UserVo userVo) {
        User user = userDao.findOne(id);

        user.setUsername(userVo.getUsername());
        // TODO: change password method
        user.setPassword(authenticationService.encodePassword(userVo.getPassword()));
        user.setEmail(userVo.getEmail());
        userDao.save(user);
    }

    @Override
    public void deleteUser(final Long id) {
        User user = userDao.getOne(id);
        user.setState(UserState.DELETED);
        userDao.save(user);
    }

    private List<UserVo> entitiesToVos(List<User> userList) {
        return userList.stream().map(this::entityToVo).collect(Collectors.toList());
    }

    private UserVo entityToVo(User user) {
        if (user != null) {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setUsername(user.getUsername());
            userVo.setEmail(user.getEmail());
            userVo.setUserState(user.getState().name());
            if (user.getRegistration() != null) {
                userVo.setRegistrationState(user.getRegistration().getState().name());
                userVo.setRegistrationCode(user.getRegistration().getRegistrationCode());
            }

            return userVo;
        }
        return null;
    }

    // TODO: maybe this can go into a helper service
    private String generateRegistrationCode() {
        // TODO: implementation
        return "DUMMY";
    }
}
