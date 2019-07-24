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

    public Card() {
        //do stuff

    }

    public Account getLinkedaccount() {
        return this.linkedaccount;
    }
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
    private int creditlimit;

    public CreditCard(Account linkedaccount, int creditlimit) {
        //do stuff
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



    public int getCreditlimit() {
        return this.creditlimit;
    }

    @Override
    public String toString() {
        return getCode() + " " + getCreditlimit() + " € credit limit";
    }
}
    class DebitCard extends Card implements Serializable {


        public DebitCard(Account linkedaccount) {
            this.linkedaccount = linkedaccount;

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