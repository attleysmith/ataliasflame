package hu.asgames.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.12.
 */
@Entity
@Table(name = "god")
public class God extends IdentifiedEntityBase {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "influence")
    private String influence;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "enabledGodList", fetch = FetchType.LAZY)
    private List<Gender> enabledGenderList;

    @ManyToMany(mappedBy = "enabledGodList", fetch = FetchType.LAZY)
    private List<Race> enabledRaceList;

    @ManyToMany(mappedBy = "enabledGodList", fetch = FetchType.LAZY)
    private List<Caste> enabledCasteList;

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

    public String getInfluence() {
        return influence;
    }

    public void setInfluence(final String influence) {
        this.influence = influence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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

    public List<Caste> getEnabledCasteList() {
        return enabledCasteList;
    }

    public void setEnabledCasteList(final List<Caste> enabledCasteList) {
        this.enabledCasteList = enabledCasteList;
    }
}
