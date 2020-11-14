package com.mobile.moneymanager.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Wallet implements Serializable {

    public String name = "";
    public int balance=0;
    public  int id;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public Wallet(){}
    public Wallet(int id,String name, int balance) {
        this.id=id;
        this.name = name;
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


}
