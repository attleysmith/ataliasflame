package hu.asgames.service.impl;

import hu.asgames.dao.RegistrationDao;
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

    @Autowired
    private RegistrationDao registrationDao;

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
    public UserVo getUser(final Long id) {
        UserVo user = entityToVo(userDao.findOne(id));
        userExist(id, user);

        return user;
    }

    @Override
    public void modifyUser(final Long id, final ModifyUserRequest request) {
        User user = userDao.findOne(id);
        userExist(id, user);

        user.setDisplayName(request.getDisplayName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userDao.save(user);

        LOGGER.info("User modified - {}", user.getUsername());
    }

    @Override
    public void deleteUser(final Long id) {
        User user = userDao.findOne(id);
        userExist(id, user);

        if (user.getState() == UserState.DELETED) {
            Message message = new MessageBuilder(MessageUtil.USER_ALREADY_DELETED).arg("userId", id.toString()).arg("username", user.getUsername()).build();
            LOGGER.error(message.fullMessage());
            throw new BaseException(message);
        }

        user.setState(UserState.DELETED);
        userDao.save(user);

        LOGGER.info("User deleted - {}", user.getUsername());
    }

    @Override
    public void changePassword(final Long id, final ChangePasswordRequest request) {
        User user = userDao.findOne(id);
        userExist(id, user);

        if (authenticationService.checkPassword(request.getOldPassword(), user.getPassword())) {
            user.setPassword(authenticationService.encodePassword(request.getNewPassword()));
            userDao.save(user);

            LOGGER.info("User password changed - {}", user.getUsername());
        } else {
            // TODO: string-object map as args with type conversion
            Message message = new MessageBuilder(MessageUtil.USER_AUTH_FAILED_WHILE_CHANGING_PASSWORD).arg("username", user.getUsername()).build();
            LOGGER.error(message.fullMessage());
            throw new BaseException(message);
        }
    }

    @Override
    public Long login(final LoginRequest request) {
        User user = userDao.findByUsername(request.getUsername());
        if (user != null && authenticationService.checkPassword(request.getPassword(), user.getPassword())) {
            return user.getId();
        } else {
            Message message = new MessageBuilder(MessageUtil.USER_AUTH_FAILED).arg("username", request.getUsername()).build();
            LOGGER.error(message.fullMessage());
            throw new BaseException(message);
        }
    }

    @Override
    public UserVo registration(final String registrationCode) {
        Registration registration = registrationDao.findByRegistrationCode(registrationCode);
        if (registration != null) {
            // TODO: checking of user state
            if (registration.getState() != RegistrationState.NEW) {
                Message message = new MessageBuilder(MessageUtil.CONFIRM_REGISTRATION_WITH_WRONG_STATE)
                        .arg("correctRegistrationState", RegistrationState.NEW.name()).arg("registrationState", registration.getState().name()).build();
                LOGGER.error(message.fullMessage());
                throw new BaseException(message);
            }
            registration.setConfirmationDate(LocalDateTime.now());
            registration.setState(RegistrationState.CONFIRMED);

            User user = registration.getUser();
            user.setState(UserState.NORMAL);
            userDao.save(user); // cascade saving with registration

            LOGGER.info("Registration confirmed - {}", registrationCode);

            return entityToVo(user);
        } else {
            Message message = new MessageBuilder(MessageUtil.REGISTRATION_NOT_EXIST).arg("registrationCode", registrationCode).build();
            LOGGER.error(message.fullMessage());
            throw new BaseException(message);
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
            Message message = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", userId.toString()).build();
            LOGGER.error(message.fullMessage());
            throw new BaseException(message);
        }
    }
}
