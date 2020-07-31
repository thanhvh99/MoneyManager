package com.mobile.moneymanager.Model;

import java.io.Serializable;
import java.util.Calendar;

public class Transaction implements Serializable {

    public double amount = 0;
    public Category category = null;
    public Calendar time = Calendar.getInstance();

}
