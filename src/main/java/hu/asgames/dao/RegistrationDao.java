package hu.asgames.dao;

import hu.asgames.domain.entities.Registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AMiklo on 2017.01.02.
 */
public interface RegistrationDao extends JpaRepository<Registration, Long> {

    @Query("select r from Registration r where r.registrationDate < ?1 and r.state = 'NEW'")
    List<Registration> findExpiredRegistrations(LocalDateTime dateExpired);

}
