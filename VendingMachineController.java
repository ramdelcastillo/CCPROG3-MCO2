import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class VendingMachineController {
    private HomeView homeView;
    private RegularVendingMachine rvm;
    private SpecialVendingMachine svm;
    private int itemIndex;
    private ArrayList<Integer> addOnIndexesList;
    private ArrayList<String> addOnNamesList;

    public VendingMachineController(HomeView homeView, RegularVendingMachine rvm, SpecialVendingMachine svm) {
        this.homeView = homeView;
        this.rvm = rvm;
        this.svm = svm;
        this.addOnIndexesList = new ArrayList<Integer>();
        this.addOnNamesList = new ArrayList<String>();

        this.homeView.rvmBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeView.getFrame().setVisible(false);
                homeView.getRv().getFrame().setVisible(true);
            }
        });
        this.homeView.svmBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeView.getFrame().setVisible(false);
                homeView.getSv().getFrame().setVisible(true);
            }
        });
        this.homeView.getRv().newRB1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(0);
            }
        });
        this.homeView.getRv().newRB2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(1);
            }
        });
        this.homeView.getRv().newRB3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(2);
            }
        });
        this.homeView.getRv().newRB4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(3);
            }
        });
        this.homeView.getRv().newRB5Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(4);
            }
        });
        this.homeView.getRv().newRB6Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(5);
            }
        });
        this.homeView.getRv().newRB7Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(6);
            }
        });
        this.homeView.getRv().newRB8Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemIndex(7);
            }
        });
        this.homeView.getRv().button3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cashReceived = JOptionPane.showInputDialog("Input cash: ");
                try {
                    if(cashReceived != null && validateCashReceived(Float.parseFloat(cashReceived))) {
                        JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Regular Vending Machine has received PhP " + cashReceived);
                        rvm.setCashReceived(Float.parseFloat(cashReceived));
                        System.out.println(rvm.getCashReceived());
                    }
                    else {
                        JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Invalid cash input");
                        System.out.println(rvm.getCashReceived());
                    }
                } catch (NumberFormatException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Invalid cash input");
                }
            }
        });
        this.homeView.getSv().btn3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cashReceived = JOptionPane.showInputDialog("Input cash: ");
                try {
                    if(cashReceived != null && validateCashReceived(Float.parseFloat(cashReceived))) {
                        JOptionPane.showMessageDialog(homeView.getSv().getFrame(), "Special Vending Machine has received PhP " + cashReceived);
                        svm.setCashReceived(Float.parseFloat(cashReceived));
                        System.out.println(svm.getCashReceived());
                    }
                    else {
                        JOptionPane.showMessageDialog(homeView.getSv().getFrame(), "Invalid cash input");
                        System.out.println(svm.getCashReceived());
                    }
                } catch (NumberFormatException | HeadlessException ex) {
                    JOptionPane.showMessageDialog(homeView.getSv().getFrame(), "Invalid cash input");
                }
            }
        });
        this.homeView.getRv().buttonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean payment = rvm.payment(itemIndex);
                if(payment) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), rvm.getItemSlot().get(itemIndex).getContainer().get((rvm.getItemSlot().get(itemIndex).getContainer().size()) - 1).stringFoodItem() + " with change of PhP " + rvm.getCashReceived());
                    rvm.dispenseItem(itemIndex);
                    rvm.setCashReceived(0);
                    System.out.println(rvm.displayAmountOfItemsAvailable(itemIndex));
                }
                else {
                    System.out.println(rvm.displayAmountOfItemsAvailable(itemIndex));
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Not enough cash to transact order, or vending machine has not enough change, or no more items for that food item.");
                }
            }
        });
        this.homeView.getRv().button2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rvm.getCashReceived() == 0) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "No money to be refunded.");
                }
                else {
                    float returnedMoney = rvm.returnMoney();
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Here is your refunded money of PhP " + Float.parseFloat(String.valueOf(returnedMoney)));
                }
            }
        });
        this.homeView.getRv().button4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(homeView.getRv().getFrame(),rvm.getVendingMachineDisplay());
            }
        });
        this.homeView.getRv().button5Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountOfItems = JOptionPane.showInputDialog(homeView.getRv().getFrame(), "Please input amount of items for restock.");

                try {
                    rvm.addFood(itemIndex,Integer.parseInt(amountOfItems));
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Added " + amountOfItems + " items of " + rvm.getItemSlot().get(itemIndex).getContainer().get((rvm.getItemSlot().get(itemIndex).getContainer().size()) - 1).getName());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Invalid input. ");
                }
                if(rvm.isPastLimit()) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Cannot add anymore items.");
                }
            }
        });
        this.homeView.getRv().button6Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String priceChange = JOptionPane.showInputDialog(homeView.getRv().getFrame(), "Please give a new price for this item.");

                try {
                    rvm.setPrice(Float.parseFloat(priceChange), itemIndex);
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Item price changed to: PhP " + rvm.getItemSlot().get(itemIndex).getContainer().get((rvm.getItemSlot().get(itemIndex).getContainer().size()) - 1).getPrice());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Invalid input. ");
                }

            }
        });
        this.homeView.getRv().button7Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Owner has earned: PhP " + rvm.getEarnedMoney());
                rvm.setEarnedMoney(0);
            }
        });
        this.homeView.getRv().button8Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addChange = JOptionPane.showInputDialog(homeView.getRv().getFrame(), "Please input amount of change to the vending machine: ");

                try {
                    rvm.addChange(Float.parseFloat(addChange));
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Vending machine change changed to: PhP " + rvm.getChange());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Invalid input. ");
                }
            }
        });
        this.homeView.getRv().button9Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(homeView.getRv().getFrame(), rvm.getSummary());
            }
        });
        this.homeView.getSv().btn1RamenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svm.customizeRamen("Tonkotsu Ramen", 140, 900);
            }
        });
        this.homeView.getSv().btn2RamenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svm.customizeRamen("Shoyu Ramen", 150, 700);
            }
        });
        this.homeView.getSv().btn2RamenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svm.customizeRamen("Hakata Ramen", 145, 850);
            }
        });
        this.homeView.getSv().btn1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> indexList = svm.getAddOnIndexes(addOnNamesList);
                System.out.println(addOnNamesList);
                System.out.println(indexList.toString());

                boolean isSuccess = svm.isSuccessfulRamenOrder(indexList);

                System.out.println(isSuccess);

                if(isSuccess) {
                    svm.displayProcess(true);
                }


                /*
                boolean payment = rvm.payment(itemIndex);
                if(payment) {
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), rvm.getItemSlot().get(itemIndex).getContainer().get((rvm.getItemSlot().get(itemIndex).getContainer().size()) - 1).stringFoodItem() + " with change of PhP " + rvm.getCashReceived());
                    rvm.dispenseItem(itemIndex);
                    rvm.setCashReceived(0);
                    System.out.println(rvm.displayAmountOfItemsAvailable(itemIndex));
                }
                else {
                    System.out.println(rvm.displayAmountOfItemsAvailable(itemIndex));
                    JOptionPane.showMessageDialog(homeView.getRv().getFrame(), "Not enough cash to transact order, or vending machine has not enough change, or no more items for that food item.");
                }
                 */
            }
        });


    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public boolean validateCashReceived(float cashReceived) {
        return cashReceived > 0;
    }

    public static void main(String[] args) {
        RegularVendingMachine rvm = new RegularVendingMachine(90, 400);

        String[] foodNames = new String[]{"Ajitsuke Tamago", "Fishcake", "Fried Tofu", "Tempura", "Tonkatsu", "Gyoza", "Karaage", "Mentaiko", "Black Garlic", "Green Onion"};

        double[] foodCalories = new double[]{100, 241, 271, 175, 245, 110, 230, 96, 839, 32};
        float[] foodPrice = new float[]{165, 145, 200, 138, 140, 150, 130, 110, 175, 90};

        Slot ajitsukeTamago = new Slot(foodNames[0]);
        Food ajitsukeTamagoF = new Food(foodNames[0], foodCalories[0], foodPrice[0], false);

        Slot fishCake = new Slot(foodNames[1]);
        Food fishCakeF = new Food(foodNames[1], foodCalories[1], foodPrice[1], false);

        Slot friedTofu = new Slot(foodNames[2]);
        Food friedTofuF = new Food(foodNames[2], foodCalories[2], foodPrice[2], false);

        Slot tempura = new Slot(foodNames[3]);
        Food tempuraF = new Food(foodNames[3], foodCalories[3], foodPrice[3], false);

        Slot tonkatsu = new Slot(foodNames[4]);
        Food tonkatsuF = new Food(foodNames[4], foodCalories[4], foodPrice[4], false);

        Slot gyoza = new Slot(foodNames[5]);
        Food gyozaF = new Food(foodNames[5], foodCalories[5], foodPrice[5], false);

        Slot karaage = new Slot(foodNames[6]);
        Food karaageF = new Food(foodNames[6], foodCalories[6], foodPrice[6], false);

        Slot mentaiko = new Slot(foodNames[7]);
        Food mentaikoF = new Food(foodNames[7], foodCalories[7], foodPrice[7], false);

        Slot blackGO = new Slot(foodNames[8]);
        Food blackGOF = new Food(foodNames[8], foodCalories[8], foodPrice[8], true);

        Slot greenOnion = new Slot(foodNames[9]);
        Food greenOnionF = new Food(foodNames[9], foodCalories[9], foodPrice[9], true);

        ArrayList<Slot> slots = new ArrayList<Slot>();
        Collections.addAll(slots, ajitsukeTamago, fishCake, friedTofu, tempura, tonkatsu, gyoza, karaage, mentaiko);
        ArrayList<Food> foods = new ArrayList<Food>();
        Collections.addAll(foods, ajitsukeTamagoF, fishCakeF, friedTofuF, tempuraF, tonkatsuF, gyozaF, karaageF, mentaikoF);


        for (Slot slot : slots) {
            rvm.getItemSlot().add(slot);
        }

        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 11; j++) { // first index is to retain the structure of the Slot ArrayList
                rvm.getItemSlot().get(i).getContainer().add(foods.get(i));
            }
        }

        SpecialVendingMachine svm = new SpecialVendingMachine(90, 400);

        slots = new ArrayList<Slot>();
        Collections.addAll(slots, ajitsukeTamago, fishCake, friedTofu, tempura, tonkatsu, gyoza, karaage, mentaiko,blackGO);
        foods = new ArrayList<Food>();
        Collections.addAll(foods, ajitsukeTamagoF, fishCakeF, friedTofuF, tempuraF, tonkatsuF, gyozaF, karaageF, mentaikoF, blackGOF);

        for (Slot slot : slots) {
            svm.getItemSlot().add(slot);
        }

        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 11; j++) { // first index is to retain the structure of the Slot ArrayList
                svm.getItemSlot().get(i).getContainer().add(foods.get(i));
            }
        }

        Ramen ramen = new Ramen(90, 90, "Ramen");

        for(int j = 0; j < 11; j++) { // first index is to retain the structure of the Slot ArrayList
            svm.getRamenList().add(ramen);
        }

        HomeView homeView = new HomeView();
        VendingMachineController vmc = new VendingMachineController(homeView, rvm, svm);
    }
}
