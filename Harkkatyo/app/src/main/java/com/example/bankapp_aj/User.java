package com.example.bankapp_aj;

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
    private ArrayList<Account> accountlist;
    private ArrayList<Card> cardlist;
    private static final long serialVersionUID = 4L;


    public User(String name, String password, ArrayList<Account> accountlist, ArrayList<Card> cardlist) {
        this.name = name;
        this.password = password;
        this.accountlist = accountlist;
        this.cardlist = cardlist;

    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.cardlist = new ArrayList<>();
        this.accountlist = new ArrayList<>();
    }


    public void transferMoney(int from, int to, float transferamount) {
        if (from != to) {
            if (transferamount <= this.accountlist.get(from).getMoneyamount()) {
                this.accountlist.get(from).takeMoney(transferamount);
                this.accountlist.get(to).addMoney(transferamount);

            }
        }

    }

    public void addAccountToUser(Account account) {
        this.accountlist.add(account);
    }
    public void addCardToUser(Card card) {
        this.cardlist.add(card);
    }

    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public ArrayList<Account> getAccountlist() {
        if (accountlist.isEmpty()) {
            return null;
        } else {
            return this.accountlist;

        }
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }
    public ArrayList<Card> getCardlist() {
        return this.cardlist;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
