package com.example.bankapp_aj;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */
public class User implements Serializable {
    private String name;
    private String address;
    private String password;
    private ArrayList<Account> accountlist = new ArrayList<>();



    public User() {
        //Empty Contructor

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void transferMoney(int from, int to, int transferamount) {
        //Check that accounts are not the same
        if (from != to) {
            //Check that account has enough money
            if (transferamount <= this.accountlist.get(from).getMoneyamount()) {
                //do the transaction
                this.accountlist.get(from).takeMoney(transferamount);
                this.accountlist.get(to).addMoney(transferamount);

            }
        }

    }

    public void addAccountToUser(Account account) {

            this.accountlist.add(account);
        }




    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public ArrayList<Account> getAccountlist() {

            return this.accountlist;


    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return getName();
    }
}
