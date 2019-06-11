/*
    11-Jun-19
    @AtteJantunen
*/

import java.util.Scanner;

public class Mainclass {
    public static void main(String[] args) {
        //dostuff
        int valinta, hahmo, ase;

        boolean running=true;

        Scanner lukija = new Scanner(System.in);

        while(running) {

            System.out.println("*** TAISTELUSIMULAATTORI ***");
            System.out.println("1) Luo hahmo");
            System.out.println("2) Taistele hahmolla");
            System.out.println("0) Lopeta");
            System.out.print("Valintasi: ");
            valinta = lukija.nextInt();

            switch (valinta) {
                case 1:
                    System.out.print("Valitse hahmosi:");
                    System.out.print("1) Kuningas");
                    System.out.print("2) Ritari");
                    System.out.print("3) Kuningatar");
                    System.out.print("4) Peikko");
                    System.out.print("Valintasi: ");

                    hahmo = lukija.nextInt();

                    System.out.print("Valitse aseesi:");
                    System.out.print("1) Veitsi");
                    System.out.print("2) Kirves");
                    System.out.print("3) Miekka");
                    System.out.print("4) Nuija");
                    System.out.print("Valintasi: ");

                    ase = lukija.nextInt();

                case 2:

                case 0:
                    running = false;

                default:

            }
        }

    }
}
