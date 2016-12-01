package hu.asgames.dao;

import hu.asgames.domain.entities.Registration;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2016.11.29.
 */
public interface RegistrationDao extends JpaRepository<Registration, Long> {

    Registration findByRegistrationCode(String registrationCode);

}
