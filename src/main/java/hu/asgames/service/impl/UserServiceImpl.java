package hu.asgames.service.impl;

import hu.asgames.dao.UserDao;
import hu.asgames.domain.entities.Registration;
import hu.asgames.domain.entities.User;
import hu.asgames.domain.enums.RegistrationState;
import hu.asgames.domain.enums.UserState;
import hu.asgames.domain.exception.BaseException;
import hu.asgames.messages.MessageBuilder;
import hu.asgames.messages.MessageUtil;
import hu.asgames.service.api.AuthenticationService;
import hu.asgames.service.api.CodeGeneratorService;
import hu.asgames.service.api.UserService;
import hu.asgames.ws.api.domain.Message;
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
    private AuthenticationService authenticationService;

    @Autowired
    private CodeGeneratorService codeGeneratorService;

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
        registration.setRegistrationCode(codeGeneratorService.generateRegistrationCode());
        user.setRegistration(registration);

        // TODO: roles and permissions

        userDao.save(user); // cascade saving with registration

        LOGGER.info("User created - {}", user.getUsername());

        return user.getId();
    }

    @Override
    public UserVo getUser(final Long userId) {
        UserVo user = entityToVo(userDao.findOne(userId));
        userExist(userId, user);

        return user;
    }

    @Override
    public void modifyUser(final Long userId, final ModifyUserRequest request) {
        User user = userDao.findOne(userId);
        userExist(userId, user);

        user.setDisplayName(request.getDisplayName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userDao.save(user);

        LOGGER.info("User modified - {}", user.getUsername());
    }

    @Override
    public void deleteUser(final Long userId) {
        User user = userDao.findOne(userId);
        userExist(userId, user);

        if (user.getState() == UserState.DELETED) {
            handleError(new MessageBuilder(MessageUtil.USER_ALREADY_DELETED).arg("userId", userId.toString()).arg("username", user.getUsername()).build());
        }

        user.setState(UserState.DELETED);
        userDao.save(user);

        LOGGER.info("User deleted - {}", user.getUsername());
    }

    @Override
    public void changePassword(final Long userId, final ChangePasswordRequest request) {
        User user = userDao.findOne(userId);
        userExist(userId, user);

        if (authenticationService.checkPassword(request.getOldPassword(), user.getPassword())) {
            user.setPassword(authenticationService.encodePassword(request.getNewPassword()));
            userDao.save(user);

            LOGGER.info("User password changed - {}", user.getUsername());
        } else {
            // TODO: string-object map as args with type conversion
            handleError(new MessageBuilder(MessageUtil.USER_AUTH_FAILED).arg("username", user.getUsername()).build());
        }
    }

    @Override
    public Long login(final LoginRequest request) {
        User user = userDao.findByUsername(request.getUsername());
        if (user != null && authenticationService.checkPassword(request.getPassword(), user.getPassword())) {
            return user.getId();
        } else {
            handleError(new MessageBuilder(MessageUtil.USER_AUTH_FAILED).arg("username", request.getUsername()).build());
            return null;
        }
    }

    @Override
    public void registration(final Long userId, final String registrationCode) {
        User user = userDao.findOne(userId);
        userExist(userId, user);

        Registration registration = user.getRegistration();
        if (registration == null) {
            handleError(new MessageBuilder(MessageUtil.REGISTRATION_NOT_EXIST).arg("userId", userId.toString()).build());
        } else if (!registration.getRegistrationCode().equals(registrationCode)) {
            handleError(new MessageBuilder(MessageUtil.REGISTRATION_CODE_NOT_MATCH).arg("userId", userId.toString()).arg("registrationCode", registrationCode)
                    .build());
        } else if (registration.getState() != RegistrationState.NEW) {
            handleError(new MessageBuilder(MessageUtil.CONFIRM_REGISTRATION_WITH_WRONG_STATE).arg("correctRegistrationState", RegistrationState.NEW.name())
                    .arg("registrationState", registration.getState().name()).build());
        } else if (user.getState() != UserState.TEMPORARY) {
            handleError(new MessageBuilder(MessageUtil.CONFIRM_REGISTRATION_WITH_WRONG_USER_STATE).arg("correctUserState", UserState.TEMPORARY.name())
                    .arg("userState", user.getState().name()).build());
        } else {
            registration.setConfirmationDate(LocalDateTime.now());
            registration.setState(RegistrationState.CONFIRMED);

            user.setState(UserState.NORMAL);
            userDao.save(user); // cascade saving with registration

            LOGGER.info("Registration confirmed - {}", registrationCode);
        }
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

    private void userExist(Long userId, Object user) {
        if (user == null) {
            handleError(new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", userId.toString()).build());
        }
    }

    private void handleError(Message message) {
        LOGGER.error(message.fullMessage());
        throw new BaseException(message);
    }
}
