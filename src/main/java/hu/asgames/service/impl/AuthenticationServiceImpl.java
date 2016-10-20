package hu.asgames.service.impl;

import hu.asgames.service.api.AuthenticationService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author AMiklo on 2016.10.18.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encodePassword(final String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean checkPassword(final String rawPassword, final String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

}
