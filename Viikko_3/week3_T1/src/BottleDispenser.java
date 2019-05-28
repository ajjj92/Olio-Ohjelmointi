/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */
public class BottleDispenser {
    
    
    private int bottles;
    
    private int money;
    
    public BottleDispenser() {
        bottles = 5;
        money = 0;
        
    }
    
    public void addMoney() {
        money += 1;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
    }
    
    public void buyBottle() {
        if(money != 0){
            bottles -= 1;
            System.out.println("KACHUNK! Pullo tipahti masiinasta!");
        }else{
            System.out.println("Syötä rahaa ensin!");
        }
        }
    
    public void returnMoney() {
        money = 0;
        System.out.println("Klink klink. Sinne menivät rahat!");
    }
}
