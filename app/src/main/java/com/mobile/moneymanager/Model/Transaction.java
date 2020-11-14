package com.mobile.moneymanager.Model;

import java.io.Serializable;
import java.util.Calendar;

public class Transaction implements Serializable {
    public int amount = 0;
    public String note = "";
    public String categoryName="";
    public int balance=0;
    public int income=0;
    public  String walletName="";
    public Calendar time = Calendar.getInstance();

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public Transaction(int amount, String node, Calendar time){
        this.amount=amount;
        this.note=node;
        this.time=time;
    }
    public Transaction(){};
}
