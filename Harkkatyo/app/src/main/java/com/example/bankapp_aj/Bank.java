package com.example.bankapp_aj;

import android.content.Context;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */
public class Bank implements Serializable {
    private static Bank bank;
    private static ArrayList<User> userlist = new ArrayList<>();
    private String name = "AJ_BANK";
    private User activeuser=null;
    private User tempuser=null;
    private boolean adminstatus=false;

    private Bank() {

    }

    //Singleton Principle
    public static Bank getInstance() {
        if (bank == null) {
            bank = new Bank();
        }
        return bank;
    }


    public User getActiveuser() {
        return this.activeuser;
    }

    public void setActiveuser(User user ) {
        this.activeuser = user;
    }

    public String getName() {
        return this.name;
    }


    public ArrayList<User> getUserlist() {
        return userlist;
    }

    public User getTempuser() {
        return this.tempuser;
    }

    public void setTempuser(User user ) {
        this.tempuser = user;
    }

    public void setAdminstatus(boolean status) {
        this.adminstatus=status;
    }
    public boolean getAdminstatus() {
        return this.adminstatus;
    }
}
