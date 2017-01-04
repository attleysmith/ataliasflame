package hu.asgames.dao;

import hu.asgames.domain.entities.LoginHistory;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2017.01.04.
 */
public interface LoginHistoryDao extends JpaRepository<LoginHistory, Long> {

}
