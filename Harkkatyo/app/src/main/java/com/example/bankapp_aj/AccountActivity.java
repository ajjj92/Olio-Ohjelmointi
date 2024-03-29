package com.example.bankapp_aj;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.DateFormat;
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
    private String date="";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public AccountActivity(String payee, String receiver, float moneyamount) {

        //Creates a new activity with date and money amount

        DateFormat df = new SimpleDateFormat("d-M-yyyy HH:mm");
        Date dateobj = new Date();

        this.payee = payee;
        this.receiver = receiver;
        this.moneyamount = moneyamount;
        this.date = df.format(dateobj);

    }

    @Override
    public String toString() {
        //Adding + sign for display print.
        if (moneyamount>0) {
            String activityString = String.format("%s  %s -> %s   +%.2f€",this.date, this.payee, this.receiver, this.moneyamount);
            return  activityString;
        }
        String activityString = String.format("%s  %s -> %s   %.2f€",this.date, this.payee, this.receiver, this.moneyamount);
        return  activityString;
    }
}

