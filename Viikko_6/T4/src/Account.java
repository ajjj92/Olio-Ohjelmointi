import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 6/14/19
 * AtteJantunen
 */

 public abstract class Account implements Bank {
    protected Integer luottoraja;
    protected String accountnumber;
    protected int moneyamount;
    private ArrayList<Account> tilit = new ArrayList<>();
    Scanner lukija = new Scanner(System.in);

    public Account() {
        //emptyconstruct
    }

public class CreditAccount extends Account {
    private int credit;
    public CreditAccount(String accountnumber, int moneyamount, int credit) {
        this.credit = credit;
        this.accountnumber = accountnumber;
        this.moneyamount = moneyamount;
    }
}

public class DailyAccount extends Account {

    public DailyAccount(String accountnumber, int moneyamount) {
        this.accountnumber = accountnumber;
        this.moneyamount = moneyamount;
    }
}

    public void printAllAccounts() {
        System.out.println("Kaikki tilit:");
        for (Account account : tilit) {
            System.out.printf("Tilinumero: %s Tilillä rahaa: %d\n", account.accountnumber, account.moneyamount);
        }
    }

    public void printSingleAccount() {
        System.out.print("Syötä tulostettava tilinumero: ");
        accountnumber = lukija.nextLine();
        for (Account account : tilit) {
            if (account.accountnumber.equals(accountnumber)) {
                System.out.printf("Tilinumero: %s Tilillä rahaa: %d\n", account.accountnumber, account.moneyamount);
            }
        }
    }

    public void rmAccount() {
        System.out.print("Syötä poistettava tilinumero: ");
        accountnumber = lukija.nextLine();
        Iterator<Account> itr = tilit.iterator();
        while (itr.hasNext()) {
            if (itr.next().accountnumber.equals(accountnumber)) {
                itr.remove();
                System.out.println("Tili poistettu.");
            }
        }
    }

    public void addMoney() {
        System.out.print("Syötä tilinumero: ");
        accountnumber = lukija.nextLine();
        System.out.print("Syötä rahamäärä: ");
        moneyamount = Integer.parseInt(lukija.nextLine());

        Iterator<Account> itr = tilit.iterator();

        while (itr.hasNext()) {
            Account element = itr.next();
            if (element.accountnumber.equals(accountnumber)) {
                element.moneyamount += moneyamount;
                System.out.printf("Talletetaan tilille: %s rahaa %d\n", accountnumber, moneyamount);
            }
        }
        System.out.printf("Talletetaan tilille: %s rahaa %d\n", accountnumber, moneyamount);
    }

    public void takeMoney() {
        System.out.print("Syötä tilinumero: ");
        accountnumber = lukija.nextLine();
        System.out.print("Syötä rahamäärä: ");
        moneyamount = Integer.parseInt(lukija.nextLine());

        Iterator<Account> itr = tilit.iterator();

        while (itr.hasNext()) {
            Account element = itr.next();
            if (element.accountnumber.equals(accountnumber)) {
                element.moneyamount -= moneyamount;
            }
        }
    }

    public void printMenu() {
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


    }

    public void addCreditAccount() {
        System.out.print("Syötä tilinumero: ");
        accountnumber = lukija.nextLine();
        System.out.print("Syötä rahamäärä: ");
        moneyamount = Integer.parseInt(lukija.nextLine());
        System.out.print("Syötä luottoraja: ");
        luottoraja = Integer.parseInt(lukija.nextLine());
        tilit.add(new CreditAccount(accountnumber, moneyamount, luottoraja));
        System.out.printf("Pankkiin lisätään: %s,%d,%d\n", accountnumber, moneyamount, luottoraja);
    }

    public void addDailyAccount() {
        System.out.print("Syötä tilinumero: ");
        accountnumber = lukija.nextLine();
        System.out.print("Syötä rahamäärä: ");
        moneyamount = Integer.parseInt(lukija.nextLine());
        tilit.add(new DailyAccount(accountnumber, moneyamount));
        System.out.println("Tili luotu.");
    }

}
