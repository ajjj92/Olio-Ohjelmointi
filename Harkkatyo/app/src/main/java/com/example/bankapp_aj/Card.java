package com.example.bankapp_aj;

/**
 * 7/11/19
 * AtteJantunen
 */
public abstract class Card {
    private String cardtype;
    private int paymentlimit;
    private int takelimit;

    public Card() {
        //do stuff

    }

}



class CreditCard extends Card {
    private int creditlimit;

    public CreditCard() {
        //do stuff
    }

}

 class DebitCard extends Card {
    private int creditlimit;
    private Account linkedaccount;
    public DebitCard() {
        //do stuff
    }

}