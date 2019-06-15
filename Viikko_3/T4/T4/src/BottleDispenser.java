/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */
import java.util.ArrayList;
import java.util.Locale;

public class BottleDispenser {
    
    
    private int bottles;
    private int money;
    Bottle pullo = new Bottle();
    private ArrayList<Bottle> arrpullolist; 
    
    public BottleDispenser() {
        bottles = 6;
        money = 0;
        
        arrpullolist = new ArrayList<Bottle>(bottles);
        
        for(int i=0;i<bottles;i++) {
            arrpullolist.add(pullo);
        }
        
    }
    public void deleteBottle() {
        arrpullolist.remove(0);
    }
    public void addMoney() {
        money += 1;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
    }
    
    public void buyBottle() {
        if(money > pullo.getPrice()){
          
            deleteBottle();
            money -= pullo.getPrice();
            System.out.println(String.format("KACHUNK! %s tipahti masiinasta!", pullo.getName()));
        }else{
            System.out.println("Syötä rahaa ensin!");
        }
        }
    
    public void returnMoney() {
        money = 0;
        System.out.println("Klink klink. Sinne menivät rahat!");
    }
    public void printBottles() {
        for(int i=0;i<arrpullolist.size();i++) {
            System.out.println(String.format("%d. Nimi: %s", i+1, pullo.getName()));
            System.out.println(String.format(Locale.US, "	Koko: %.1f	Hinta: %.1f", pullo.getSize(), pullo.getPrice()));
        }
    }
}