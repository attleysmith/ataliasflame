package hu.asgames.ws.api.domain.character;

/**
 * @author AMiklo on 2017.01.05.
 */
public class CharacterAttributeCounterVo {

    private AttributeLookupVo attribute;
    private int counter;

    // Getters and setters

    public AttributeLookupVo getAttribute() {
        return attribute;
    }

    public void setAttribute(final AttributeLookupVo attribute) {
        this.attribute = attribute;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(final int counter) {
        this.counter = counter;
    }
}
