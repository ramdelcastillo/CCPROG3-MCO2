import java.util.ArrayList;

public class Ramen {
    private ArrayList<Food> addOnList;
    private float price;
    private double calorie;
    private String name;

    public Ramen(float price, double calorie, String name) {
        this.addOnList = new ArrayList<Food>();
        this.price = price;
        this.calorie = calorie;
        this.name = name;
    }

    public ArrayList<Food> getAddOnList() {
        return this.addOnList;
    }

    public float getPrice() {
        return this.price;
    }

    public double getCalorie() {
        return this.calorie;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getName() {
        return this.name;
    }

    public double getCalories() {
        return this.calorie;
    }

    public String getAddOnListFoodItems() {
        ArrayList<String> addOnList = new ArrayList<String>();
        ArrayList<Float> addOnPriceList = new ArrayList<Float>();
        ArrayList<Double> addOnCaloriesList = new ArrayList<Double>();

        String with = "with ";

        StringBuilder addOns = new StringBuilder();

        if(this.getAddOnList().size() == 0) {
            return "with no add-ons.";
        }

        for(int i = 0; i < this.getAddOnList().size(); i++) {
            addOnList.add(this.getAddOnList().get(i).getName());
        }

        for(int i = 0; i < this.getAddOnList().size(); i++) {
            addOnPriceList.add(this.getAddOnList().get(i).getPrice());
        }

        for(int i = 0; i < this.getAddOnList().size(); i++) {
            addOnCaloriesList.add(this.getAddOnList().get(i).getCalories());
        }

        for(int i = 0; i < this.getAddOnList().size(); i++) {
            //addOns + addOnList.get(i) + ", ";

            // Here is your order: Fishcake (PhP 145.0) with 241.0 calories

            if(i == this.getAddOnList().size() - 1) {
                addOns.append("and ").append(addOnList.get(i)).append(" (PhP ").append(addOnPriceList.get(i)).append(", ").append(addOnCaloriesList.get(i)).append(" calories").append(")").append(".");
            }
            else {
                addOns.append(addOnList.get(i)).append(" (PhP ").append(addOnPriceList.get(i)).append(", ").append(addOnCaloriesList.get(i)).append(" calories").append(")").append(", ");
            }
        }

        return with + addOns;
    }
}
