package hu.asgames.dao;

import hu.asgames.domain.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2016.10.15.
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
