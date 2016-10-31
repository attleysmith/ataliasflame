package hu.asgames.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author AMiklo on 2016.09.12.
 */
@Entity
@Table(name = "level")
public class Level extends IdentifiedEntityBase {

    @Column(name = "level", nullable = false)
    private int level;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_level_id")
    private Level nextLevel;

    @Column(name = "exp_to_level_up")
    private long expToLevelUp;

    // Getters and setters

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public long getExpToLevelUp() {
        return expToLevelUp;
    }

    public void setExpToLevelUp(long expToLevelUp) {
        this.expToLevelUp = expToLevelUp;
    }
}
