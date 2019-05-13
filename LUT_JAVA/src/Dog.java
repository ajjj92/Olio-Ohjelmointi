/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */
public class Dog {
    
    private String nimi;
    private String lause;
    public Dog(String n) { 
        lause = "Much wow!";
        nimi = n;
    }
    public void Speak() {
        System.out.println("Hei, nimeni on " + nimi);
        System.out.println(nimi +":"+ lause);
    }
   public void Speak(String l) {
        System.out.println("Hei, nimeni on " + nimi);
        System.out.println(nimi +":"+ l);
    }
    public String getName() {
        return nimi;
    }
}
