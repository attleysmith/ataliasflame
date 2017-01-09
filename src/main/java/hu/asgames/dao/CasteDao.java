package hu.asgames.dao;

import hu.asgames.domain.entities.Caste;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2017.01.05.
 */
public interface CasteDao extends JpaRepository<Caste, Long> {

    Caste findByCode(String code);

}
