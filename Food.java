/**
 * This Food class has a constructor that initializes
 * a Food object with a name, calories, and a price.
 * With this, it also includes methods that retrieve
 * the respective attributes and a method that sets the price
 * then displays the specifications of a Food object.
 *
 * @author Cassandra Lois Ang and Jose Mari Del Castillo - S17B
 */
public class Food {
    private final String name;
    private final double calories;
    private float price;
    private boolean isAddOnOnly;

    /**
     * This constructs and initializes a food item to be added to the vending machine
     *
     * @param name the name of the food item
     * @param calories the amount of calories the food item contains
     * @param price the selling price of the food item
     */
    public Food(String name, double calories, float price, boolean isAddOnOnly) { // Del
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.isAddOnOnly = isAddOnOnly;
    }

    /**
     * This method retrieves the name of the food item
     *
     * @return the name of food item
     */
    public String getName() { // newly added
        return this.name;
    } // newly added

    /**
     * This method retrieves the amount of calories the food item contains
     *
     * @return the amount of calories of the food item
     */
    public double getCalories() { // newly added
        return this.calories;
    } // newly added

    /**
     * This method retrieves the selling price of the food item in the vending machine
     *
     * @return the selling price of the food item
     */
    public float getPrice() { // newly added
        return this.price;
    } // newly added

    /**
     * This method sets the new selling price for the food item in the vending machine
     *
     * @param price the new selling price of the food item
     */
    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getIsAddOnOnly() {
        return this.isAddOnOnly;
    }

    public void displayFoodItem() {
        System.out.println("Here is your order: " + this.getName()+ " (PhP "+this.getPrice() + ") " + "with " + this.getCalories() + " calories");
    }

    public String stringFoodItem() { // new
        return "Here is your order: " + this.getName()+ " (PhP "+this.getPrice() + ") " + "with " + this.getCalories() + " calories";
    }

}
