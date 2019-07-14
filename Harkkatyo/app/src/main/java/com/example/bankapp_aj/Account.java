package com.example.bankapp_aj;


/**
 * 7/11/19
 * AtteJantunen
 */
public abstract class Account {
    protected int id;
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
        this.id = 123123123;
        //dostuff
    }

}