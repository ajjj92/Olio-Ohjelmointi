package com.example.bankapp_aj;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * 7/11/19
 * AtteJantunen
 */
public abstract class Account implements Serializable {
    protected String id;
    protected float moneyamount=0;
    protected int interest;
    protected String accounttype;
    protected ArrayList<AccountActivity> accountactivity;
    protected ArrayList<Card> cardlist;

    public float getMoneyamount() {
        return this.moneyamount;
    }

    public void addCardtoAccount(Card card) {

            this.cardlist.add(card);
        }

    public String getId() {
        return this.id;
    }

    public void addMoney(float amount
    ) {
        this.moneyamount += amount;

            this.accountactivity.add(new AccountActivity("","",amount));

        }



    public ArrayList<Card> getCardlist() {
        return this.cardlist;
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

        public SavingAccount(float moneyamount) {
            this.cardlist = new ArrayList<>();
            this.accountactivity = new ArrayList<>();
            Random rand = new Random();
            String acc = "SA";
            for (int i = 0; i<14; i++) {
                int randnumber = rand.nextInt(10);
                acc += Integer.toString(randnumber);
                if( i % 4==0) {
                    acc +=" ";
                }
            }
            this.id = acc;
            this.addMoney(moneyamount);
        }

        @Override
        public String toString() {
            return getId();
        }

    }

    class DailyAccount extends Account implements Serializable{

        public DailyAccount(float moneyamount) {
            this.cardlist = new ArrayList<>();
            this.accountactivity = new ArrayList<>();
            Random rand = new Random();
            String acc = "DA";
            for (int i = 0; i<14; i++) {
                int randnumber = rand.nextInt(10);
                acc += Integer.toString(randnumber);
                if( i % 4==0) {
                    acc +=" ";
                }
            }
            this.id = acc;
            this.addMoney(moneyamount);



        }

        @Override
        public String toString() {
            return getId();
        }
    }
