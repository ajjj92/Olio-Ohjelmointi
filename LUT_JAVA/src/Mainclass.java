
import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Anna koiralle nimi: ");

        String nimi = scan.nextLine(); 
        
        Dog doge1 = new Dog();
        Dog doge2 = new Dog(nimi);
        System.out.println("Nimeni on " + doge1.getName());
        System.out.println("Nimeni on " + doge2.getName());
        System.out.println("Mitä koira sanoo: ");
        String lause = scan.nextLine();
        doge1.Speak();
        doge2.Speak(lause);
    }
    
}
