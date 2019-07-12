package com.example.bankapp_aj;

import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */
public class User {
    private String name;
    private String address;
    private String password;
    private ArrayList<Account> accountlist = new ArrayList<>();
    private ArrayList<Card> cardlist = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private void addAccountToUser(Account account) {
        this.accountlist.add(account);
    }
    private void addCardToUser(Card card) {
        this.cardlist.add(card);
    }

    private String getName() {
        return this.name;
    }
    private String getAddress() {
        return this.address;
    }
    private ArrayList<Account> getAccountlist() {
        return this.accountlist;
    }
    private ArrayList<Card> getCardlist() {
        return this.cardlist;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setAddress(String address) {
        this.address = address;
    }

}
