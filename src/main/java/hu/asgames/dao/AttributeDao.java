package hu.asgames.dao;

import hu.asgames.domain.entities.Attribute;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AMiklo on 2017.01.05.
 */
public interface AttributeDao extends JpaRepository<Attribute, Long> {

}
