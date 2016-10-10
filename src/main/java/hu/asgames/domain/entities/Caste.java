package hu.asgames.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.12.
 */
@Entity
@Table(name = "caste")
// TODO: funkció bekötése
public class Caste extends IdentifiedEntityBase {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    // TODO: fill out in db
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "caste_next_caste_map", joinColumns = @JoinColumn(name = "caste_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "next_caste_id", referencedColumnName = "id"))
    private List<Caste> nestCasteList;

    @ManyToMany(mappedBy = "enabledCasteList", fetch = FetchType.LAZY)
    private List<Gender> enabledGenderList;

    @ManyToMany(mappedBy = "enabledCasteList", fetch = FetchType.LAZY)
    private List<Race> enabledRaceList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "caste_enabled_god_map", joinColumns = @JoinColumn(name = "caste_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "god_id", referencedColumnName = "id"))
    private List<God> enabledGodList;

    // Getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Caste> getNestCasteList() {
        return nestCasteList;
    }

    public void setNestCasteList(final List<Caste> nestCasteList) {
        this.nestCasteList = nestCasteList;
    }

    public List<Gender> getEnabledGenderList() {
        return enabledGenderList;
    }

    public void setEnabledGenderList(final List<Gender> enabledGenderList) {
        this.enabledGenderList = enabledGenderList;
    }

    public List<Race> getEnabledRaceList() {
        return enabledRaceList;
    }

    public void setEnabledRaceList(final List<Race> enabledRaceList) {
        this.enabledRaceList = enabledRaceList;
    }
}
