import java.util.ArrayList;

/**
 * This Slot class has a constructor that initializes ArrayList
 * of Food objects that serves as a container of the food
 * objects. With this, a slot name is also included
 * to distinguish the slot. With this, it also contains methods
 * that return the respective attributes.
 *
 * @author Cassandra Lois Ang and Jose Mari Del Castillo - S17B
 */
public class Slot {
    private final ArrayList<Food> container;
    private final String slotName; // newly added to distinguish slots? just like the playlist name

    /**
     * This constructs and initializes a slot in the vending machine
     *
     * @param slotName the name of the slot in the machine
     */
    public Slot(String slotName) {
        this.container = new ArrayList<Food>();
        this.slotName = slotName;
    }

    /**
     * This method retrieves the slot which contains the food item
     *
     * @return the slot which contains the food item
     */
    public ArrayList<Food> getContainer() { // newly added
        return this.container;
    }

    /**
     * This method retrieves the name of the slot in the vending machine
     *
     * @return the name of the slot
     */
    public String getSlotName() {
        return this.slotName;
    }
}
