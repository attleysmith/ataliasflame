package hu.asgames.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "race")
public class Race extends IdentifiedEntityBase {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "enabledRaceList", fetch = FetchType.LAZY)
    private List<Gender> enabledGenderList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "race_enabled_caste_map", joinColumns = @JoinColumn(name = "race_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "caste_id", referencedColumnName = "id"))
    private List<Caste> enabledCasteList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "race_enabled_god_map", joinColumns = @JoinColumn(name = "race_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "god_id", referencedColumnName = "id"))
    private List<God> enabledGodList;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RaceAttributeMultiplierMap> attributeMultiplierMapList = new ArrayList<>();

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

    public List<Gender> getEnabledGenderList() {
        return enabledGenderList;
    }

    public void setEnabledGenderList(final List<Gender> enabledGenderList) {
        this.enabledGenderList = enabledGenderList;
    }

    public List<Caste> getEnabledCasteList() {
        return enabledCasteList;
    }

    public void setEnabledCasteList(final List<Caste> enabledCasteList) {
        this.enabledCasteList = enabledCasteList;
    }

    public List<God> getEnabledGodList() {
        return enabledGodList;
    }

    public void setEnabledGodList(final List<God> enabledGodList) {
        this.enabledGodList = enabledGodList;
    }

    public List<RaceAttributeMultiplierMap> getAttributeMultiplierMapList() {
        return attributeMultiplierMapList;
    }

    public void setAttributeMultiplierMapList(final List<RaceAttributeMultiplierMap> attributeMultiplierMapList) {
        this.attributeMultiplierMapList = attributeMultiplierMapList;
    }
}
