package com.example.bankapp_aj;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */
public class Bank {
    private static Bank bank;
    private static ArrayList<User> userlist = new ArrayList<>();
    private String name = "AJ_BANK";
    private User activeuser=null;
    private int activeuser_id=0;

    private Bank() {

    }

    //Singleton Principle
    public static Bank getInstance() {
        if (bank == null) {
            bank = new Bank();

        }
        return bank;
    }

    public void createUser(String name, String password) {
        User user = new User(name, password);
        userlist.add(user);
    }

    public User getActiveuser() {
        return this.activeuser;
    }

    public void setActiveuser(User user ) {
        this.activeuser = user;
    }
    public void setActiveuser_id(int i) {
        this.activeuser_id = i;
    }
    public int getActiveuser_id() {
        return this.activeuser_id;
    }

    public String getName() {
        return this.name;
    }




    public ArrayList<User> Bank_getUserlist() {
        return userlist;
    }


}
