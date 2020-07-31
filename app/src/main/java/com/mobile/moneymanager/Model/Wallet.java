package com.mobile.moneymanager.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Wallet implements Serializable {

    public String name = "";
    public ArrayList<Transaction> transactions = new ArrayList<>();

}
