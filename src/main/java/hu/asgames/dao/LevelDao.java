package hu.asgames.dao;

import hu.asgames.domain.entities.Level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author AMiklo on 2017.01.05.
 */
public interface LevelDao extends JpaRepository<Level, Long> {

    @Query(value = "select l from Level l where l.level = 1")
    Level findFirstLevel();

}
