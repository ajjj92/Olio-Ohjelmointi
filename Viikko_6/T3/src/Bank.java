import java.util.ArrayList;
import java.util.Iterator;

/**
 * 6/14/19
 * AtteJantunen
 */

public class Bank {

    private ArrayList<Account> tilit = new ArrayList<>();
    private String bankname;

    public Bank() {
        //emptycontruct
    }

    class Account {

        protected String accountnumber;
        protected int moneyamount;

        public Account() {
            //emptycontruct
        }
    }

    class CreditAccount extends Account {

        private int credit;

        public CreditAccount(String accountnumber, int moneyamount, int credit) {
            this.credit = credit;
            this.accountnumber = accountnumber;
            this.moneyamount = moneyamount;
        }
    }

    class DailyAccount extends Account {

        public DailyAccount(String accountnumber, int moneyamount) {
            this.accountnumber = accountnumber;
            this.moneyamount = moneyamount;
        }
    }

    void addDailyAccount(String accountnumber, int moneyamount) {
        tilit.add(new DailyAccount(accountnumber, moneyamount));
        System.out.printf("Pankkiin lisätään: %s,%d\n", accountnumber, moneyamount);
    }

    void addCreditAccount(String accountnumber, int moneyamount, int credit) {
        tilit.add(new CreditAccount(accountnumber, moneyamount, credit));
        System.out.printf("Pankkiin lisätään: %s,%d,%d\n", accountnumber, moneyamount, credit);
    }

    void printAllAccounts() {
        System.out.println("Kaikki tilit:");
        for (Account account : tilit) {
            //System.out.printf("Tili %d, %d\n", account.accountnumber, account.moneyamount);
        }
    }

    void printSingleAccount(String accountnumber) {
        System.out.printf("Etsitään tiliä: %s\n", accountnumber);
        for (Account account : tilit) {
            if (account.accountnumber.equals(accountnumber)) {
                //System.out.println("Löytyi");
            }
        }
    }

    void rmAccount(String accountnumber) {

        Iterator<Account> itr = tilit.iterator();
        while (itr.hasNext()) {
            if (itr.next().accountnumber.equals(accountnumber)) {
                itr.remove();
                //System.out.println("Tili poistettu.");
            }
        }
        System.out.println("Tili poistettu.");
    }

    void addMoney(String accountnumber, int moneyamount) {
        Iterator<Account> itr = tilit.iterator();

        while (itr.hasNext()) {
            Account element = itr.next();
            if (element.accountnumber.equals(accountnumber)) {
                element.moneyamount += moneyamount;
                //System.out.printf("Talletetaan tilille: %s rahaa %d", accountnumber, moneyamount);
            }
        }
        System.out.printf("Talletetaan tilille: %s rahaa %d\n", accountnumber, moneyamount);
    }

    void takeMoney(String accountnumber, int moneyamount) {
        Iterator<Account> itr = tilit.iterator();

        while (itr.hasNext()) {
            Account element = itr.next();
            if (element.accountnumber.equals(accountnumber)) {
                element.moneyamount -= moneyamount;
                //System.out.printf("Talletetaan tilille: %s rahaa %d", accountnumber, moneyamount);
            }
        }
        System.out.printf("Nostetaan tililtä: %s rahaa %d\n", accountnumber, moneyamount);
    }
}
