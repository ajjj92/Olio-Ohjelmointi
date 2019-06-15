/**
 * 6/14/19
 * AtteJantunen
 */

import java.util.Scanner;


public class Mainclass {

    public static void main(String[] args) {

        Integer valinta;
        String tili;
        Integer raha;
        Integer luottoraja;
        boolean running = true;
        Scanner lukija = new Scanner(System.in);
        Bank pankki = new Bank();

        while(running) {
            System.out.println();
            System.out.println("*** PANKKIJÄRJESTELMÄ ***");
            System.out.println("1) Lisää tavallinen tili");
            System.out.println("2) Lisää luotollinen tili");
            System.out.println("3) Tallenna tilille rahaa");
            System.out.println("4) Nosta tililtä");
            System.out.println("5) Poista tili");
            System.out.println("6) Tulosta tili");
            System.out.println("7) Tulosta kaikki tilit");
            System.out.println("0) Lopeta");
            System.out.print("Valintasi: ");

            valinta = Integer.parseInt(lukija.nextLine());

            switch (valinta) {
                case 1:
                    System.out.print("Syötä tilinumero: ");
                    tili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    pankki.addDailyAccount(tili, raha);
                    break;
                case 2:
                    System.out.print("Syötä tilinumero: ");
                    tili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    System.out.print("Syötä luottoraja: ");
                    luottoraja = Integer.parseInt(lukija.nextLine());
                    pankki.addCreditAccount(tili, raha, luottoraja);
                    break;
                case 3:
                    System.out.print("Syötä tilinumero: ");
                    tili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    pankki.addMoney(tili, raha);
                    break;
                case 4:
                    System.out.print("Syötä tilinumero: ");
                    tili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    pankki.takeMoney(tili, raha);
                    break;
                case 5:
                    System.out.print("Syötä poistettava tilinumero: ");
                    tili = lukija.nextLine();
                    pankki.rmAccount(tili);
                    break;
                case 6:
                    System.out.print("Syötä tulostettava tilinumero: ");
                    tili = lukija.nextLine();
                    pankki.printSingleAccount(tili);
                    break;
                case 7:
                        pankki.printAllAccounts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Valinta ei kelpaa.");
                    break;
            }
        }
    }
}

