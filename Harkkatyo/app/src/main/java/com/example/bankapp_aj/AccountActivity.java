package com.example.bankapp_aj;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 7/11/19
 * AtteJantunen
 */
public class AccountActivity implements Serializable {

    private String payee="";
    private String receiver="";
    private float moneyamount=0;
    private int code;
    private String date="";
    private SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy HH:mm");


    public AccountActivity(String payee, String receiver, float moneyamount) {

        this.payee = payee;
        this.receiver = receiver;
        this.moneyamount = moneyamount;

        this.date = formatter.format(new Date(System.currentTimeMillis()));


    }



    @Override
    public String toString() {
        if (moneyamount>0) {
            String activityString = String.format("%s  %s   +%.2f€",date, receiver, moneyamount);
            return  activityString;
        }
        String activityString = String.format("%s  %s   %.2f€",date, receiver, moneyamount);
        return  activityString;
    }
}

