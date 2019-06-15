
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
        
        BottleDispenser PulloMaatti = new BottleDispenser();
        Boolean running = true;
        Scanner lukija = new Scanner(System.in);
        Integer pullonumero;
       
        //Valikko mainmenu = new Valikko();
        Integer valinta;
        
        while (running) {
            
            
            System.out.println("\n*** LIMSA-AUTOMAATTI ***");
            System.out.println("1) Lisää rahaa koneeseen");
            System.out.println("2) Osta pullo");
            System.out.println("3) Ota rahat ulos");
            System.out.println("4) Listaa koneessa olevat pullot");
            System.out.println("0) Lopeta");
            System.out.print("Valintasi: ");
            //mainmenu.drawMenu();
            valinta = lukija.nextInt();
            
            switch (valinta) {
                case 1:
                    
                    PulloMaatti.addMoney();
                    break;
                case 2:
                    PulloMaatti.printBottles();
                    System.out.print("Valintasi: ");
                    pullonumero = lukija.nextInt();
                    PulloMaatti.buyBottle(pullonumero);
                    PulloMaatti.deleteBottle(pullonumero);
                    break;
                case 3:
                    PulloMaatti.returnMoney();
                    break;
                case 4:
                    PulloMaatti.printBottles();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    break;
            }
            
            
        }
        
    
    
}
}