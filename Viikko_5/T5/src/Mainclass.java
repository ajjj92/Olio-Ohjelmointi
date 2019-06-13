/*
    11-Jun-19
    @AtteJantunen
*/

import java.util.Scanner;

public class Mainclass {
    public static void main(String[] args) {
        int valinta, hahmoluku, aseluku;
        boolean running=true;
        Scanner lukija = new Scanner(System.in);
        Character hahmo = new Character();
        hahmo.new Weapon();
        while(running) {

            System.out.println("*** TAISTELUSIMULAATTORI ***");
            System.out.println("1) Luo hahmo");
            System.out.println("2) Taistele hahmolla");
            System.out.println("0) Lopeta");
            System.out.print("Valintasi: ");
            valinta = lukija.nextInt();

            switch (valinta) {
                case 1:
                    System.out.println("Valitse hahmosi:");
                    System.out.println("1) Kuningas");
                    System.out.println("2) Ritari");
                    System.out.println("3) Kuningatar");
                    System.out.println("4) Peikko");
                    System.out.print("Valintasi: ");

                    hahmoluku = lukija.nextInt();
                    switch (hahmoluku) {
                        case 1:
                            hahmo = hahmo.new King();
                            break;
                        case 2:
                            hahmo = hahmo.new Knight();
                            break;
                        case 3:
                            hahmo = hahmo.new Queen();
                            break;
                        case 4:
                            hahmo = hahmo.new Troll();
                            break;
                    }
                    System.out.println("Valitse aseesi:");
                    System.out.println("1) Veitsi");
                    System.out.println("2) Kirves");
                    System.out.println("3) Miekka");
                    System.out.println("4) Nuija");
                    System.out.print("Valintasi: ");


                    aseluku = lukija.nextInt();

                    switch (aseluku) {
                        case 1:
                            hahmo.new Knife();
                            break;
                        case 2:
                            hahmo.new Axe();
                            break;
                        case 3:
                            hahmo.new Sword();
                            break;
                        case 4:
                            hahmo.new Club();
                            break;
                        default:
                            //nothing
                            break;
                    }
                //end case 1 main menu
                break;

                case 2:
                    hahmo.printFight();
                    break;
                case 0:
                    running = false;
                default:
                    //dostuff
                    break;
            }
        }
    }
}
