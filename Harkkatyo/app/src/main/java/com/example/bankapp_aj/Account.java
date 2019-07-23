package com.example.bankapp_aj;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */
public abstract class Account implements Serializable {
    protected String id;
    protected float moneyamount;
    protected int interest;
    protected String accounttype;
    private int luottoraja = 0;
    protected ArrayList<AccountActivity> accountactivity;

    public float getMoneyamount() {
        return this.moneyamount;
    }

    public String getId() {
        return this.id;
    }

    public void addMoney(float amount
    ) {
        this.moneyamount += amount;
        if (accountactivity==null) {
            accountactivity = new ArrayList<>();
            this.accountactivity.add(new AccountActivity("","",amount));

        } else {
            this.accountactivity.add(new AccountActivity("","",amount));

        }
    }

    public void takeMoney(float amount) {
            this.moneyamount -= amount;
        this.accountactivity.add(new AccountActivity("","",-amount));
        }

    public void addActivity(AccountActivity activity){
        this.accountactivity.add(activity);
    }
    public ArrayList<AccountActivity> getActivityList() {

            return this.accountactivity;


     }
    }



    class SavingAccount extends Account implements Serializable{
        private double savinginterest;

        public SavingAccount() {

            this.id = "FI12 1234 1234 99";
            this.addMoney(12000);
        }

        @Override
        public String toString() {
            return getId();
        }

    }

    class DailyAccount extends Account implements Serializable{

        public DailyAccount() {

            this.id = "FI21 4321 4321 4321 90";
            this.addMoney(8000);



        }

        @Override
        public String toString() {
            return getId();
        }
    }
