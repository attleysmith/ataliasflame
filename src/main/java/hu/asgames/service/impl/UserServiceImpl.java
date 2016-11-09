package hu.asgames.service.impl;

import hu.asgames.dao.UserDao;
import hu.asgames.domain.entities.Registration;
import hu.asgames.domain.entities.User;
import hu.asgames.domain.enums.RegistrationState;
import hu.asgames.domain.enums.UserState;
import hu.asgames.service.api.AuthenticationService;
import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.domain.user.ChangePasswordRequest;
import hu.asgames.ws.api.domain.user.CreateUserRequest;
import hu.asgames.ws.api.domain.user.LoginRequest;
import hu.asgames.ws.api.domain.user.ModifyUserRequest;
import hu.asgames.ws.api.domain.user.UserVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserVo> getUserList() {
        return entitiesToVos(userDao.findAll());
    }

    @Override
    public Long createUser(final CreateUserRequest request) {
        User user = new User();
        user.setDisplayName(request.getDisplayName());
        user.setUsername(request.getUsername());
        user.setPassword(authenticationService.encodePassword(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setState(UserState.TEMPORARY);

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setState(RegistrationState.NEW);
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setRegistrationCode(generateRegistrationCode());
        user.setRegistration(registration);

        // TODO: roles and permissions

        userDao.save(user);

        LOGGER.info("User created - {}", user.getUsername());

        return user.getId();
    }

    @Override
    public UserVo getUser(final Long id) {
        return entityToVo(userDao.findOne(id));
    }

    @Override
    public void modifyUser(final Long id, final ModifyUserRequest request) {
        User user = userDao.findOne(id);
        user.setDisplayName(request.getDisplayName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userDao.save(user);

        LOGGER.info("User modified - {}", user.getUsername());
    }

    @Override
    public void deleteUser(final Long id) {
        User user = userDao.findOne(id);
        user.setState(UserState.DELETED);
        userDao.save(user);

        LOGGER.info("User deleted - {}", user.getUsername());
    }

    @Override
    public void changePassword(final Long id, final ChangePasswordRequest request) {
        User user = userDao.findOne(id);

        if (authenticationService.checkPassword(request.getOldPassword(), user.getPassword())) {
            user.setPassword(authenticationService.encodePassword(request.getNewPassword()));
            userDao.save(user);

            LOGGER.info("User password changed - {}", user.getUsername());
        } else {
            LOGGER.info("User password change is denied - {}", user.getUsername());
            // TODO: error message
        }
    }

    @Override
    public Long login(final LoginRequest request) {
        User user = userDao.findByUsername(request.getUsername());
        if (user != null && authenticationService.checkPassword(request.getPassword(), user.getPassword())) {
            return user.getId();
        }
        return null;
    }

    private List<UserVo> entitiesToVos(List<User> userList) {
        return userList.stream().map(this::entityToVo).collect(Collectors.toList());
    }

    private UserVo entityToVo(User user) {
        if (user != null) {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setDisplayName(user.getDisplayName());
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
