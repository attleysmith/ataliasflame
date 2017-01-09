package hu.asgames.ws.api.domain.character;

/**
 * @author AMiklo on 2017.01.04.
 */
public class CreateCharacterRequest {

    private Long userId;
    private String name;
    private Long genderId;
    private Long raceId;
    private Long protectiveGodId;

    // Getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(final Long genderId) {
        this.genderId = genderId;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(final Long raceId) {
        this.raceId = raceId;
    }

    public Long getProtectiveGodId() {
        return protectiveGodId;
    }

    public void setProtectiveGodId(final Long protectiveGodId) {
        this.protectiveGodId = protectiveGodId;
    }
}
