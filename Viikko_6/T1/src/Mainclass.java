import java.util.Scanner;

/**
 * 6/13/19
 * AtteJantunen
 */

public class Mainclass {

    public static void main(String[] args) {

        Integer valinta;
        String normitili;
        String luottotili;
        Integer raha;
        Integer luottoraja;


        boolean running = true;

        Scanner lukija = new Scanner(System.in);

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
                    normitili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    System.out.println("Tilinumero: "+normitili);
                    System.out.println("Rahamäärä: "+raha);

                    break;
                case 2:
                    System.out.print("Syötä tilinumero: ");
                    luottotili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    System.out.print("Syötä luottoraja: ");
                    luottoraja = Integer.parseInt(lukija.nextLine());
                    System.out.println("Tilinumero: "+luottotili);
                    System.out.println("Rahamäärä: "+raha);
                    System.out.println("Luotto: "+luottoraja);

                    break;
                case 3:
                    System.out.print("Syötä tilinumero: ");
                    normitili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    System.out.println("Tilinumero: "+normitili);
                    System.out.println("Rahamäärä: "+raha);

                    break;
                case 4:
                    System.out.print("Syötä tilinumero: ");
                    normitili = lukija.nextLine();
                    System.out.print("Syötä rahamäärä: ");
                    raha = Integer.parseInt(lukija.nextLine());
                    System.out.println("Tilinumero: "+normitili);
                    System.out.println("Rahamäärä: "+raha);

                    break;
                case 5:
                    System.out.print("Syötä poistettava tilinumero: ");
                    normitili = lukija.nextLine();
                    System.out.println("Tilinumero: "+ normitili);
                    break;
                case 6:
                    System.out.print("Syötä tulostettava tilinumero: ");
                    normitili = lukija.nextLine();
                    System.out.println("Tilinumero: "+ normitili);

                    break;
                case 7:
                    System.out.println("Tulostaa kaiken");
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
