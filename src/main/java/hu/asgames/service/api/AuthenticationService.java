package hu.asgames.service.api;

/**
 * @author AMiklo on 2016.10.18.
 */
public interface AuthenticationService {

    String encodePassword(String password);

    boolean checkPassword(String rawPassword, String encodedPassword);

}
