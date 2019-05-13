/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Atte
 */
public class Mainclass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World!");
        
        Dog doge = new Dog("Rocky");
        Dog doge2 = new Dog("Pertti");
        System.out.println("Nimeni on " + doge.getName());
        System.out.println("Nimeni on " + doge2.getName());
        doge.Speak();
        doge2.Speak("HELLO OLEN MYÖS KOIRA RUF RUF");
    }
    
}
