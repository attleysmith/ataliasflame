package hu.asgames.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.13.
 */
@Entity
@Table(name = "special_ability")
public class SpecialAbility extends IdentifiedEntityBase {

    @Column(name = "name", nullable = false)
    private String name;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
