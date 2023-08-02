import java.util.ArrayList;

public class SpecialVendingMachine extends RegularVendingMachine {
    private ArrayList<Ramen> ramenList;

    public SpecialVendingMachine(int limit, float change) {
        super(limit, change);
        this.ramenList = new ArrayList<Ramen>();

    }

    public double computeCalories() { // checked
        double totalCalories = 0;

        totalCalories += this.ramenList.get(ramenList.size() - 1).getCalorie();

        for(int i = 0; i < this.getRamenList().get(ramenList.size() - 1).getAddOnList().size(); i++){
            totalCalories += this.getRamenList().get(ramenList.size() - 1).getAddOnList().get(i).getCalories();
        }

        return totalCalories;
    }

    public double getRamenCalories() { // checked
        return this.ramenList.get(ramenList.size() - 1).getCalorie();
    }

    public void displayProcess(boolean isSuccessfulRamenOrder) { // checked
        if(isSuccessfulRamenOrder) {
            System.out.println("Blanching noodles...");
            delayProcess();
            System.out.println("Heating broth...");
            delayProcess();
            System.out.println("Placing noodles in the cup...");
            delayProcess();

            for(int i = 0; i < this.getRamenList().get(getRamenList().size() - 1).getAddOnList().size(); i++) {

                System.out.println("Topping with " + this.getRamenList().get(getRamenList().size() - 1).getAddOnList().get(i).getName() + "...");
                delayProcess();
            }

            System.out.println("Pouring broth...");
            delayProcess();
            System.out.println("Ramen done.");
            delayProcess();
        }
    }

    public void delayProcess() { // checked
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAddOnListAmountOfItems() { // checked
        return this.getRamenList().get(this.getRamenList().size() - 1).getAddOnList().size();
    }



    public void customizeRamen(String name, float price,  double calories){ // checked
        this.getRamenList().get(this.getRamenList().size() - 1).setPrice(price);
        this.getRamenList().get(this.getRamenList().size() - 1).setCalorie(calories);
        this.getRamenList().get(this.getRamenList().size() - 1).setName(name);
    }

    public int getFoodIndex(String name) { // checked
        for(int i = 0; i < this.getItemSlot().size(); i++) {
            if(this.getItemSlot().get(i).getSlotName().equals(name)) {
                return i;
            }
        }
        return -1; //if -1, give error message for wrong/missing slot name
    }

    public ArrayList<Integer> getAddOnIndexes(ArrayList<String> chosenFood) {
        ArrayList<Integer> indexList = new ArrayList<Integer>();

        for(int i = 0; i < chosenFood.size(); i++) {
            if(this.getFoodIndex(chosenFood.get(i)) != -1){
                indexList.add(i);
            }
        }

        return indexList;
    }

    public Ramen dispenseCustomizedRamen() { // checked
        Ramen ramen = this.getRamenList().get(this.getRamenList().size() - 1);

        this.getRamenList().remove(this.getRamenList().size() - 1);

        return ramen;
    }

    public boolean isAllFoodItemsAvailable(ArrayList<Integer> indexList/*indices of the items that are available*/) {
        for(int i = 0; i < indexList.size(); i++) {
            if(!isAvailable(indexList.get(i))) {
                return false;
            }
        }

        return true;
    }

    public void setQuantitySoldForEachItem(ArrayList<Integer> indexList) {
        for(int i = 0; i < indexList.size(); i++) {
            if(isAllFoodItemsAvailable(indexList)){
                this.getQuantitySold()[indexList.get(i)] += 1;
                // dispose the items
                this.getItemSlot().get(i).getContainer().remove(this.getItemSlot().get(i).getContainer().size() - 1);
            }
        }
    }

    public boolean isSuccessfulRamenOrder(ArrayList<Integer> indexList) { // Cloe
        float change = this.getCashReceived() - this.getTotalPriceOfARamenWithAddOns();

        //check for every index of the add on if it is available
        if (isAllFoodItemsAvailable(indexList) && isRamenAvailable()) {
            if (this.getCashReceived() >= this.getTotalPriceOfARamenWithAddOns() && change <= this.getChange()) {
                setCashReceived(change);
                subtractChange(this.getChange());
                float cashEarned = this.getTotalPriceOfARamenWithAddOns();
                this.collectPayment(cashEarned);
                setQuantitySoldForEachItem(indexList);
                return true;
            }
        }
        return false;
    }

    public boolean isRamenAvailable() { // checked
        return this.getRamenList().size() > 1;
    } // checked

    public boolean isAddOnItemOnly(int index) { // checked
        return this.getItemSlot().get(index).getContainer().get(this.getItemSlot().get(index).getContainer().size() - 1).getIsAddOnOnly()
                && !(displayAmountOfItemsAvailable(index) == 0);
    }

    public ArrayList<Ramen> getRamenList() {
        return this.ramenList;
    }

    public float getTotalPriceOfARamenWithAddOns() { // checked
        return this.getPriceOfARamen() + this.getTotalPriceOfAddOns();
    } // checked

    public float getPriceOfARamen() { // checked
        return this.getRamenList().get(getRamenList().size() - 1).getPrice();
    } // checked

    public float getTotalPriceOfAddOns() { // checked
        float totalAddOnPrice = 0;

        for(int i = 0; i < this.getRamenList().get(getRamenList().size() - 1).getAddOnList().size(); i++) {
            totalAddOnPrice += this.getRamenList().get(getRamenList().size() - 1).getAddOnList().get(i).getPrice();
        }

        return totalAddOnPrice;
    }

    public void addRamen(Ramen ramen) { // checked
        this.getRamenList().add(ramen);
    } // checked

    public void addAmountOfRamens(int amount) { // checked
        Ramen ramen = new Ramen(150, 430, "Ramen");

        for(int i = 0; i < amount; i++) {
            addRamen(ramen);
        }
    }

    public void displayRamenOrder() { // checked
        StringBuilder ramenWithStats = new StringBuilder();

        // Here is your order: Fishcake (PhP 145.0) with 241.0 calories

        ramenWithStats.append("Here is your ramen order: ").append(this.getRamenList().get(this.getRamenList().size() - 1).getName()).append(" (Total: (PhP ")
                      .append(this.getTotalPriceOfARamenWithAddOns()).append(", ").append(this.computeCalories()).append(" calories), ").append("Total W/O add-ons: (PhP ").append(this.getPriceOfARamen()).append(", ").append(this.getRamenCalories()).append(" calories)").append(") ");

        System.out.println(ramenWithStats);
        System.out.println(this.getRamenList().get(1).getAddOnListFoodItems());
    }

    public boolean isAddOnOnly(int index) { // checked
        return this.getItemSlot().get(index).getContainer().get(this.getItemSlot().get(index).getContainer().size() - 1).getIsAddOnOnly();
    }

    public static void main(String[] args) {
        SpecialVendingMachine svm = new SpecialVendingMachine(90, 400);

        /*
        Ramen r1 = new Ramen(90,90,"Ramen");
        Ramen r2 = new Ramen(90,90,"Ramen");

        svm.addRamen(r1);
        svm.addRamen(r2);

         */

        svm.addAmountOfRamens(2);

       // svm.displayProcess(true);

        Food fishCakeF = new Food("Fish cake", 90, 90, true);


        Food friedTofuF = new Food("Fried tofu", 90, 90, false);


        String[] foodNames = new String[]{"Ajitsuke Tamago", "Fishcake", "Fried Tofu", "Tempura", "Tonkatsu", "Gyoza", "Karaage", "Mentaiko"};

        double[] foodCalories = new double[]{100, 241, 271, 175, 245, 110, 230, 96};
        float[] foodPrice = new float[]{165, 145, 200, 138, 140, 150, 130, 110};

        Slot ajitsukeTamago = new Slot(foodNames[0]);
        Food ajitsukeTamagoF = new Food(foodNames[0], foodCalories[0], foodPrice[0], true);

        Slot fishCakes = new Slot(foodNames[1]);
        Food fishCakee = new Food(foodNames[1], foodCalories[1], foodPrice[1], false);

        svm.getRamenList().get(1).getAddOnList().add(fishCakeF);
        svm.getRamenList().get(1).getAddOnList().add(friedTofuF);


        System.out.println("Addons: " + svm.getTotalPriceOfAddOns());
        System.out.println("Ramen with addons: " + svm.getTotalPriceOfARamenWithAddOns());

        svm.getItemSlot().add(ajitsukeTamago);
        svm.getItemSlot().add(fishCakes);
        svm.getItemSlot().get(0).getContainer().add(ajitsukeTamagoF);
        svm.getItemSlot().get(0).getContainer().add(ajitsukeTamagoF);
        svm.getItemSlot().get(1).getContainer().add(fishCakee);
        svm.getItemSlot().get(1).getContainer().add(fishCakee);

        System.out.println("Index 1 - " + svm.isAddOnItemOnly(0));
        System.out.println("Index 2 - " + svm.isAddOnItemOnly(1));
        //svm.dispenseCustomizedRamen();
        System.out.println("IsRamenAvailable: " + svm.isRamenAvailable());

        //System.out.println(svm.getRamenList().get(1).getAddOnListFoodItems());

        svm.displayRamenOrder();
        svm.customizeRamen("DEL", 100, 100);
        svm.displayRamenOrder();

        System.out.println(svm.getFoodIndex("Ajitsuke Tamago"));

        System.out.println(svm.getAddOnListAmountOfItems());
        //System.out.println(svm.getRamenList().get(svm.getRamenList().size() - 1).getAddOnList().size());

        System.out.println(svm.isAddOnItemOnly(0));
        System.out.println(svm.isAddOnItemOnly(1));

        //svm.displayProcess(true);

        ArrayList<String> sample = new ArrayList<String>();
        sample.add("Ajitsuke Tamago");
        sample.add("Fishcake");

        System.out.println(svm.getAddOnIndexes(sample));



    }

}
