package hu.asgames.dao;

import hu.asgames.domain.entities.God;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2017.01.05.
 */
public interface GodDao extends JpaRepository<God, Long> {

}
