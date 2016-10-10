package hu.asgames.domain.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * @author AMiklo on 2016.09.11.
 */
@MappedSuperclass
abstract class IdentifiedEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_id_generator")
    @SequenceGenerator(name = "base_id_generator", sequenceName = "base_id_generator_seq")
    @Column(name = "id")
    private Long id;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
