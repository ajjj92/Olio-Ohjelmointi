package com.example.bankapp_aj;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public AccountActivity(String payee, String receiver, float moneyamount) {
        DateFormat df = new SimpleDateFormat("d-M-yyyy HH:mm");
        Date dateobj = new Date();

        this.payee = payee;
        this.receiver = receiver;
        this.moneyamount = moneyamount;
        this.date = df.format(dateobj);


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

