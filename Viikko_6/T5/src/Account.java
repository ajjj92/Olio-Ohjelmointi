
/**
 * 6/14/19
 * AtteJantunen
 */

public abstract class Account {
    protected String accountnumber;
    protected int moneyamount;


    public Account() {
        //emptyconstruct
    }

}
 class CreditAccount extends Account {

    private int luottoraja;

    public CreditAccount(String accountnumber, int moneyamount, int luottoraja) {
        this.luottoraja = luottoraja;
        this.accountnumber = accountnumber;
        this.moneyamount = moneyamount;
    }

    public int returnLuotto() {
        return this.luottoraja;
    }

}

    class DailyAccount extends Account {

    public DailyAccount(String accountnumber, int moneyamount) {
        this.accountnumber = accountnumber;
        this.moneyamount = moneyamount;
    }
}







