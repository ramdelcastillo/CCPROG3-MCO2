import java.util.ArrayList;
import java.util.Scanner;

/**
 * This RegularVendingMachine class includes a constructor
 * that initializes an ArrayList of Slot objects, along with
 * the limit or maximum capacity of the vending machine,
 * the initial change that the vending machine can give
 * back to a user, and the quantity sold for each item.
 * The cash received and earned money attributes
 * are set by default to zero.
 *
 * The class also has methods that works with respect
 * to how a real-life vending machine scenario
 * would work.
 *
 * @author Cassandra Lois Ang and Jose Mari Del Castillo - S17B
 */
public class RegularVendingMachine {
    private final ArrayList<Slot> itemSlot;
    private final int limit;
    private float change;
    private float cashReceived = 0;
    private float earnedMoney = 0;
    private final int[] quantitySold;

    /**
     * This constructs and initializes a regular vending machine
     *
     * @param limit the limit of food items the vending machine can accommodate
     * @param change the change amount that the vending machine can give back to the user
     */
    public RegularVendingMachine(int limit, float change) {
        this.itemSlot = new ArrayList<Slot>();
        this.limit = limit;
        this.change = change;
        this.quantitySold = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    /**
     * This method checks the availability of the food item in the slot of the vending machine
     *
     * @param index the index of the slot where the food item can be found
     * @return true if there is a food item in the slot, false otherwise
     */
    public boolean isAvailable(int index) {
        return this.getItemSlot().get(index).getContainer().size() > 1;
    }

    /**
     * This method checks if the total number of food items displayed in the vending machine is below the limit
     *
     * @return true if the total number of food items in the vending machine don't go beyond the limit, false otherwise
     */
    public boolean isPastLimit(){
        int countOfItems = 0;

        for(int i = 0; i < this.getItemSlot().size(); i++) {
            countOfItems += this.displayAmountOfItemsAvailable(i);
        }

        return countOfItems >= this.getLimit();
    }

    /**
     * This method handles the payment order functionality of the vending machine
     *
     * @param index the index of the slot in the vending machine which contains the desired food item
     * @return true if payment is successful, false otherwise
     */
    public boolean payment(int index) {
        if(displayAmountOfItemsAvailable(index) == 0){
            System.out.println("No more items available for that item.");
            return false;
        }
        float change = this.cashReceived - this.getPriceOfAnItem(index); // 400 - 165 = 235
        if (isAvailable(index)) {
            if ((this.cashReceived >= this.getPriceOfAnItem(index)) && change <= this.getChange()) {
                this.cashReceived -= this.getPriceOfAnItem(index);
                subtractChange(change); //400 -= 165
                float cashEarned = this.getPriceOfAnItem(index);
                this.collectPayment(cashEarned);
                setQuantitySold(index);

                return true;
            }
            else
                return false;
            //cancelled
        }
        return false;
            //  cancelled
        }

    /**
     * This method dispenses the desired food item
     *
     * @param index the index of the slot in the vending machine which contains the desired food item
     * @return the food item to be dispensed
     */
    public Food dispenseItem(int index) { // Del
        if(index >= this.itemSlot.size() || index < 0){
            return null;
        }

        Food item = this.getItemSlot().get(index).getContainer()
                .get((this.getItemSlot().get(index).getContainer().size()) - 1);

        this.getItemSlot().get(index).getContainer().remove((this.getItemSlot().get(index).getContainer().size()) - 1);

        return item;
    }

    /**
     * This method returns the money back to the user when user fails to order an item
     *
     * @return the cash inserted by the user into the vending machine
     */
    public float returnMoney() { // Del
        float returnMoney = this.getCashReceived();
        this.setCashReceived(0);
        return returnMoney;
    }

    /**
     * This method adds a food item to the slot in the vending machine
     *
     * @param index the index of the slot in the vending machine where the food item will be added
     * @param amount the amount of food items to be added to the slot
     */
    public void addFood(int index, int amount) {
        for(int i = 0; i < amount; i++) {
            //Food newFood = this.getItemSlot().get(index).getContainer().get(0);
            //Food newFood = this.getItemSlot().get(index).getContainer().get((this.getItemSlot().get(index).getContainer().size()) - 1);
            if(!this.isPastLimit() && !(displayAmountOfItemsAvailable(index) == 0)){
                Food newFood = this.getItemSlot().get(index).getContainer().get(0);
                this.getItemSlot().get(index).getContainer().add(newFood);
            }
        }
    }

    /**
     * This method collects the total amount of money earned by the vending machine
     *
     * @param cash the cash received as payment by the user in the vending machine
     */
    public void collectPayment(float cash) { // Cloe
        this.earnedMoney += cash;
    }

    /**
     * This method subtracts the amount of change stored in the vending machine when change is given
     *
     * @param change the amount of change to be given to the user
     */
    public void subtractChange(float change) { // Del
        this.change -= change;
    }

    /**
     * This method adds more change to change value of the machine.
     *
     * @param change the amount of change to be given to the machine
     */
    public void addChange(float change) {
        this.change += change;
    }

    /**
     * This method displays the summary of transactions for each food item in the vending machine
     */
    public void displaySummary() {
        System.out.println("Summary of transactions: ");
        for(int i=0; i < this.getItemSlot().size(); i++){
            System.out.println("Amount sold for " +this.getItemSlot().get(i).getSlotName() + " - " + quantitySold[i]);
            }
            System.out.println("Earned money: " + this.getEarnedMoney());
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();

        sb.append("Summary of transactions: \n");
        for (int i = 0; i < this.getItemSlot().size(); i++) {
            String slotName = this.getItemSlot().get(i).getSlotName();
            int quantitySold = this.getQuantitySold()[i];

            sb.append("Amount sold for ").append(slotName).append(" - ").append(quantitySold).append("\n");
        }

        sb.append("Earned money: PhP ").append(this.getEarnedMoney());

        return sb.toString();
    }

    /**
     * This method applies the new selling price for all the food items in a slot
     *
     * @param price the new selling price of the food item
     * @param index the index of the slot which contains the food item
     */
    public void setPrice(float price, int index) { // MAINTENANCE FUNCTION
         for(int i = 0; i < this.getItemSlot().get(index).getContainer().size(); i++) {
             this.getItemSlot().get(index).getContainer().get(i).setPrice(price);
         }

    }

    /**
     * This method retrieves the money collected by the vending machine
     *
     * @return the amount of money earned by the machine
     */
    public float getEarnedMoney() {
        return this.earnedMoney;
    }

    /**
     * This method sets the earned money of the machine
     *
     */
    public void setEarnedMoney(float cash) {
        this.earnedMoney = cash;
    }

    /**
     * This method retrieves the slot in the vending machine
     *
     * @return the slot in the machine
     */
    public ArrayList<Slot> getItemSlot() {
        return this.itemSlot;
    }

    /**
     * This method retrieves the name of the slot in the vending machine
     *
     * @param index the index of the slot in the machine
     * @return the name of the slot
     */
    public String displaySlotName(int index) {
        return this.getItemSlot().get(index).getSlotName();
    }

    /**
     * This method retrieves the price of the food item in the vending machine
     *
     * @param index the index of the slot in the machine
     * @return the price of the food item
     */
    public float displayItemPrice(int index) {
        // return this.getItemSlot().get(index).getContainer().get((this.getItemSlot().get(index).getContainer().size()) - 1).getPrice();

        //use something to store


            return this.getItemSlot().get(index).getContainer().get((this.getItemSlot().get(index).getContainer().size()) - 1).getPrice();



    }

    /**
     * This method displays the number of food items available in the vending machine
     *
     * @param index the index of the slot in the machine
     * @return the number of food items available in the slot
     */
    public int displayAmountOfItemsAvailable(int index) {
        // check for invalid index
        return this.getItemSlot().get(index).getContainer().size() - 1;
    }

    /**
     * This method displays the amount of calories of the food item in the vending machine
     *
     * @param index the index of the slot in the machine
     * @return the amount of calories of the food item
     */
    public double displayCalories(int index) {
             return this.getItemSlot().get(index).getContainer().get((this.getItemSlot().get(index).getContainer().size()) - 1).getCalories();
        //return this.getItemSlot().get(index).getContainer().get((this.getItemSlot().get(index).getContainer().size()) - 1).getCalories();
        //return this.getItemSlot().get(index).getContainer().get(0).getCalories();
    }

    /**
     * This method displays the food items offered in the vending machine
     */
    public void displayVendingMachine() {
        System.out.println("FOOD ITEMS: " + this.displaySlotName(0) + "   " + this.displaySlotName(1) + "   " + this.displaySlotName(2)
                + "   " + this.displaySlotName(3) + "   " + this.displaySlotName(4) +  "   " + this.displaySlotName(5)
                +  "      " + this.displaySlotName(6) +  "    " + this.displaySlotName(7));

        System.out.println("CALORIES:   " + this.displayCalories(0) + "cal      " + "    " + this.displayCalories(1) + "cal" + "   " + this.displayCalories(2) + "cal"
                + "     " + this.displayCalories(3) + "cal" + "  " + this.displayCalories(4) + "cal" + "   " + this.displayCalories(5) + "cal"
                + "   " + this.displayCalories(6) + "cal" + "   " + this.displayCalories(7) + "cal");

        System.out.println("PRICE:      PhP " + this.displayItemPrice(0) + "   " + "      PhP " + this.displayItemPrice(1) + "  " + "PhP " + this.displayItemPrice(2)
                +  "    " + "PhP " + this.displayItemPrice(3) + " " + "PhP " + this.displayItemPrice(4) + "  " + "PhP " + this.displayItemPrice(5)
                + "  " + "PhP " + this.displayItemPrice(6) + "  " + "PhP " + this.displayItemPrice(7));

        System.out.println("QTY:        " + this.displayAmountOfItemsAvailable(0) + "                " + this.displayAmountOfItemsAvailable(1)
                + "         " + this.displayAmountOfItemsAvailable(2) + "           " + this.displayAmountOfItemsAvailable(3) + "        " + this.displayAmountOfItemsAvailable(4)
                + "         " + this.displayAmountOfItemsAvailable(5) + "         " + this.displayAmountOfItemsAvailable(6) + "         " + this.displayAmountOfItemsAvailable(7));

        System.out.println("OPTIONS:    0   " + "              1  " + "        2  " + "          3  " + "       4  " + "        5" + "          6" + "          7");
    }

    public String getVendingMachineDisplay() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.getItemSlot().size(); i++) {
            String itemName = this.displaySlotName(i);
            float itemPrice = this.displayItemPrice(i);
            double itemCalories = this.displayCalories(i);
            int itemQuantity = this.displayAmountOfItemsAvailable(i);

            sb.append(itemName).append(": (PhP ").append(itemPrice).append(", ").append(itemCalories).append(" calories) - (Quantity: ").append(itemQuantity).append(")\n");
        }

        return sb.toString();
    }

    /**
     * This method retrieves the limit of food items contained in the vending machine
     *
     * @return the limit of food items in the vending machine
     */
    public int getLimit(){
        return this.limit;
    }

    /**
     * This method retrieves the change stored in the vending machine
     *
     * @return the amount of change in the vending machine
     */
    public float getChange() {
        return this.change;
    }

    /**
     * This method retrieves the cash received by the vending machine
     *
     * @return the amount of cash received by the vending machine
     */
    public float getCashReceived() {
        return this.cashReceived;
    }

    /**
     * This method retrieves the selling price of a food item in the vending machine
     *
     * @param index the index of the slot which contains the food item
     * @return the selling price of the food item
     */
    public float getPriceOfAnItem(int index) {
        return this.getItemSlot().get(index).getContainer().get((this.getItemSlot().get(index).getContainer().size()) - 1).getPrice();
        //return this.getItemSlot().get(index).getContainer().get(0).getPrice();
    }

    /**
     * This method adds one to the quantity sold per the item that was chosen.
     *
     * @param index the index of the slot which contains the food item
     */
    public void setQuantitySold(int index) {
        this.quantitySold[index] += 1;
    }

    public int[] getQuantitySold() {
        return this.quantitySold;
    }

    /**
     * This method sets the amount of cash received by the vending machine by a certain value
     *
     * @param cashReceived the cash received by the vending machine
     */
    public void setCashReceived(float cashReceived) {
        this.cashReceived = cashReceived;
    }

    /**
     * This method runs and displays the vending machine simulator
     * along with the options for buying an item or doing maintenance.
     */
    public void displayVendingMachineSimulator() {
        Scanner sc = new Scanner(System.in);
        int option;
        do{

            this.displayVendingMachine();
            System.out.println("1 - Maintenance; 2 - Ordering; 3 - Exit");
            option = sc.nextInt();

            if(option == 3) {
                System.out.println("Thank you for playing Vending Machine Simulator!");
                break;
            }

            if (option < 1 || option > 3)
            {
                System.out.println("Invalid input.\n");
            }

        } while (option != 1 && option != 2);

        switch(option) {
            case 1: this.displayMaintenanceFeatures();
                break;
            case 2: this.displayShop();
                break;
            case 3: //exit
                break;
        }
    }

    /**
     * This method is executed in the displayVendingMachineSimulator
     * function that asks for the cash input and the item to purchase.
     */
    public void displayShop() {
        Scanner sc = new Scanner(System.in);
        int options;
        float inputCash;

        do {
            do {
                System.out.println("Please input cash to buy an item: [0 - Cancel Order] ");
                    inputCash = sc.nextFloat();
                    this.setCashReceived(inputCash);

                    if(inputCash == 0) {
                    System.out.println("Order is cancelled");
                    this.displayVendingMachineSimulator();
                    return;
                }
                this.displayInitialStats();
                this.displayVendingMachine();


                System.out.println("What item do you want to purchase? [8 - Cancel Order]");
                    options = sc.nextInt();

                if(options == 8) {
                    System.out.println("Returned money: " + this.returnMoney());
                    System.out.println("Order is cancelled");
                    this.displayVendingMachineSimulator();
                    return;
                }
                /*
                if(dispenseItem(options) == null) {
                    System.out.println("Invalid item.");
                }

                 */

                    //Food item = this.dispenseItem(options);
                    boolean validPayment = this.payment(options);
                   // this.dispenseItem(options).displayFoodItem();
                    //this.addFood(options,1);

                if (validPayment) {
                            this.dispenseItem(options).displayFoodItem();

                        System.out.println("Thank you for ordering.");
                        //this.dispenseItem(options).displayFoodItem();
                        this.displayAfterOrderStats();

                    } else {
                        this.addFood(options,1);
                    System.out.println("Returned money: " + this.returnMoney());
                        System.out.println("Order is cancelled (not enough cash or invalid item number)");
                        System.out.println("Thank you for playing Vending Machine Simulator!");
                    }


                if (options < 0 || options > 8) {
                    System.out.println("Invalid input.\n");
                }

            } while(options < 1 || options > 8);
        } while(true);
    }

    /**
     * This method is executed in the displayVendingMachineSimulator
     * function that has features that could restock an item, change the Food
     * items' price, collect money, addChange, and displays the summary of
     * transactions during the start and end of the method.
     */
    public void displayMaintenanceFeatures() {
        Scanner sc = new Scanner(System.in);
        int options;
        int item;
        int amount;
        float price;

        do {
            do {
                this.displaySummary();
                System.out.println();
                this.displayInitialStats();
                this.displayVendingMachine();
                System.out.println("1 - Food Restock ; 2 - Change Price; 3 - Collect Earned Money; 4 - Add Change; 5 - Exit");

                if (this.isPastLimit()){
                    System.out.println("Cannot add anymore items, max capacity is " + this.getLimit());
                }
                options = sc.nextInt();

                if(options == 5) {
                    this.displayVendingMachine();
                    this.displaySummary();
                    this.displayVendingMachineSimulator();
                    return;
                }

                if(options == 1) {
                    System.out.println("Item restocking.");
                    System.out.print("Please input item number: ");
                        item = sc.nextInt();
                    System.out.print("Please input amount of items to add: ");
                        amount = sc.nextInt();
                    addFood(item, amount);
                }

                if(options == 2) {
                    System.out.println("Change item price.");
                    System.out.print("Please input item number: ");
                        item = sc.nextInt();
                    System.out.print("Please input new price: ");
                        price = sc.nextFloat();
                    setPrice(price, item);
                }

                if(options == 3) {
                    System.out.println("Collecting earned money.");
                    System.out.println("Your machine earned you: Php " + this.getEarnedMoney());
                    this.setEarnedMoney(0);
                }

                if(options == 4) {
                    System.out.println("Adding change.");
                    System.out.print("Please input change to add: ");
                        price = sc.nextFloat();
                    this.addChange(price);
                }

                if (options < 1 || options > 5) {
                    System.out.println("Invalid input.\n");
                }

            } while(options < 1 || options > 5);
        } while(true);
    }

    /**
     * This method displays change, cash received (of the machine),
     * and earned money attributes after a before a transaction has been made.
     */
    public void displayInitialStats() {
        System.out.println("Available change of the machine: " + this.getChange());
        System.out.println("Cash received by the machine: " + this.getCashReceived());
        System.out.println("Earned money by the machine: " + this.getEarnedMoney());
    }

    /**
     * This method displays the change, cash received (change to give to the user),
     * and earned money attributes after a successful transaction has been made.
     */
    public void displayAfterOrderStats() {
        System.out.println("Available change of the machine: " + this.getChange());
        System.out.println("Here is your change: " + this.getCashReceived());
        System.out.println("Earned money by the machine: " + this.getEarnedMoney());
    }
}
