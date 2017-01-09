package hu.asgames.dao;

import hu.asgames.domain.entities.Race;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2017.01.05.
 */
public interface RaceDao extends JpaRepository<Race, Long> {

}
