package com.example.bankapp_aj;


/**
 * 7/11/19
 * AtteJantunen
 */
public abstract class Account {

    private int moneyamount;
    private double interest;
    private String accounttype;

}

class SavingAccount extends Account {
    private double savinginterest;

    public SavingAccount () {
        //dostuff
    }

}
class DailyAccount extends Account {

    public DailyAccount() {
        //dostuff
    }

}