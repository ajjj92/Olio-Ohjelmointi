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

        PulloMaatti.addMoney();
        PulloMaatti.buyBottle();
        PulloMaatti.buyBottle();
        PulloMaatti.addMoney();
        PulloMaatti.addMoney();
        PulloMaatti.buyBottle();
        PulloMaatti.returnMoney();
    }
    
}
