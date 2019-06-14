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
    class Account{

        protected int accountnumber;
        protected int moneyamount;

        public Account() {
            //emptycontruct
        }
    }

    class CreditAccount extends Account {

        private int credit;

        public CreditAccount(int accountnumber, int moneyamount, int credit) {

            this.credit = credit;
            this.accountnumber = accountnumber;
            this.moneyamount = moneyamount;
        }
    }
    class DailyAccount extends Account{

        public DailyAccount(int accountnumber, int moneyamount) {
            this.accountnumber = accountnumber;
            this.moneyamount = moneyamount;
        }
    }

    void addDailyAccount(int accountnumber, int moneyamount) {

        tilit.add(new DailyAccount(accountnumber, moneyamount));
        System.out.printf("Pankkiin lisätään: %d, %d\n", accountnumber, moneyamount);
    }

    void addCreditAccount(int accountnumber, int moneyamount, int credit) {
        tilit.add(new CreditAccount(accountnumber, moneyamount, credit));
        System.out.printf("Pankkiin lisätään: %d, %d, %d\n", accountnumber, moneyamount, credit);
    }

    void printAllAccounts() {
        for (Account account : tilit) {
            System.out.println("Kaikki tilit: ");
            //System.out.printf("Pankkiin lisätään: %d, %d\n", account.accountnumber, account.moneyamount);
        }
    }

    void printSingleAccount(int accountnumber) {
        System.out.printf("Etsitään tiliä %d\n",accountnumber);
        for (Account account : tilit) {
            if(account.accountnumber == accountnumber) {
                System.out.println("Löytyi");
            }

        }
    }

    void rmAccount(int accountnumber) {

        Iterator<Account> itr = tilit.iterator();
        while(itr.hasNext()) {
            if(itr.next().accountnumber == accountnumber) {
                itr.remove();
                System.out.println("Tili poistettu.");

            }
        }
    }
}

