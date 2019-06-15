import java.util.Scanner;

/**
 * 6/14/19
 * AtteJantunen
 */

public class Mainclass {

    public static void main(String[] args) {

        Scanner lukija = new Scanner(System.in);
        final Account pankkirajapinta = new Account() {};
        boolean running = true;

        while(running) {
            pankkirajapinta.printMenu();
            int valinta = Integer.parseInt(lukija.nextLine());

            switch (valinta) {
                case 1:
                    pankkirajapinta.addDailyAccount();
                    break;
                case 2:
                    pankkirajapinta.addCreditAccount();
                    break;
                case 3:
                    pankkirajapinta.addMoney();
                    break;
                case 4:
                    pankkirajapinta.takeMoney();
                    break;
                case 5:
                    pankkirajapinta.rmAccount();
                    break;
                case 6:
                    pankkirajapinta.printSingleAccount();
                    break;
                case 7:
                    pankkirajapinta.printAllAccounts();
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
