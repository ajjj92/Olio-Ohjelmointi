package com.example.bankapp_aj;

import java.io.Serializable;
import java.util.Random;

/**
 * 7/11/19
 * AtteJantunen
 */
public class Card implements Serializable {
    protected  String cardtype;
    protected int paymentlimit;
    protected Account linkedaccount;
    protected String code;
    protected  int creditlimit;

    public Card() {
        //Empty Constructor

    }
    public int getCreditlimit() {
        return this.creditlimit;
    }


    public Account getLinkedaccount() {
        return this.linkedaccount;
    }

    //Methods for payment limits, not used in demo currently.
    public void setPaymentlimit(int paymentlimit) {
        this.paymentlimit = paymentlimit;
    }

    public int getPaymentlimit() {
        return this.paymentlimit;
    }

    public String getCardtype() {
        return this.cardtype;
    }
    public String getCode() {
        return this.code;
    }


}



class CreditCard extends Card implements Serializable {

    //Creates a card with randomly generated code

    public CreditCard(Account linkedaccount, int creditlimit) {
        this.linkedaccount = linkedaccount;
        this.creditlimit = creditlimit;
        this.cardtype = "Creditcard";
        Random rand = new Random();
        String acc = "CRE";
        for (int i = 0; i < 13; i++) {
            int randnumber = rand.nextInt(10);
            acc += Integer.toString(randnumber);
            if (i % 4 == 0) {
                acc += " ";
            }
        }
        this.code = acc;
    }



    //Also shows credit limit if credit card
    @Override
    public String toString() {
        return getCode() + " " + getCreditlimit() + " € credit limit";
    }
}
    class DebitCard extends Card implements Serializable {

        //Creates a card with randomly generated code


        public DebitCard(Account linkedaccount) {
            this.linkedaccount = linkedaccount;
            this.creditlimit = 0;
            this.cardtype = "Debitcard";

            Random rand = new Random();
            String acc = "DEB";
            for (int i = 0; i < 13; i++) {
                int randnumber = rand.nextInt(10);
                acc += Integer.toString(randnumber);
                if (i % 4 == 0) {
                    acc += " ";
                }
            }
            this.code = acc;
        }

        @Override
        public String toString() {
            return getCode();
        }



}