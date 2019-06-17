package com.example.t1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 6/16/19
 * AtteJantunen
 */
public class BottleDispenser {


    private int bottles;
    private float money;
    private ArrayList<Bottle> pullot= new ArrayList<>();
    private static BottleDispenser bottleDispenser = null;
    private String printti = "";

    public BottleDispenser() {
        bottles = 6;
        money = 100;


        pullot.add(new Bottle("Pepsi Max", 0.5f, 1.80f));
        pullot.add(new Bottle("Pepsi Max", 1.5f, 2.20f));
        pullot.add(new Bottle("Coca-Cola Zero", 0.5f, 2.00f));
        pullot.add(new Bottle("Coca-Cola Zero", 1.5f, 2.50f));
        pullot.add(new Bottle("Fanta Zero", 0.5f, 1.95f));
        pullot.add(new Bottle("Fanta Zero", 0.5f, 1.95f));





    }
    public static BottleDispenser getInstance() {
        if (bottleDispenser == null){
            bottleDispenser = new BottleDispenser();
        }
        return bottleDispenser;

    }


    public void addMoney() {
        money += 1;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
    }

    public void buyBottle(int pullonumero) {
        if(pullonumero < pullot.size()){

            if(money - pullot.get(pullonumero).getPrice() >= 0 && !pullot.isEmpty()){
                money -= pullot.get(pullonumero).getPrice();
                System.out.println(String.format("KACHUNK! %s tipahti masiinasta!", pullot.get(pullonumero).getName()));
                pullot.remove((pullonumero));
            }else{
                System.out.println("Syötä rahaa ensin!");
            }

        } else {
            System.out.println("EMPTY");
        }


    }
    public void returnMoney() {


        System.out.println(String.format("Klink klink. Sinne menivät rahat! Rahaa tuli ulos %.2f€", money));
        money = 0;
    }
    public String printBottles() {
        int i;
        i = 0;
        printti = "";
        if(pullot.size() == 0) {
            return "PULLOT LOPPU";
        }else {
            for (Bottle element : pullot) {
                printti += String.format("%d. Nimi: %s\n", i + 1, element .getName());
                printti += String.format(Locale.US, "	Koko: %s	Hinta: %s\n", Float.toString(element .getSize()), Float.toString(element .getPrice()));
                i += 1;
            }
            return printti;
        }

    }
}
