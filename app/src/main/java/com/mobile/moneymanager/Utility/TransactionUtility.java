package com.mobile.moneymanager.Utility;

import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.Model.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public final class TransactionUtility {

    public static ArrayList<ArrayList<Transaction>> splitByDate(ArrayList<Transaction> transactions) {
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o2.time.compareTo(o1.time);
            }
        });
        ArrayList<ArrayList<Transaction>> result = new ArrayList<>();
        int index = 0;
        int start = 0;
        while (start < transactions.size()) {
            ArrayList<Transaction> temp = new ArrayList<>();
            Transaction first = transactions.get(start);
            while (index < transactions.size()) {
                Transaction second = transactions.get(index);
                if (DateUtility.compareDate(first.time, second.time) == 0) {
                    temp.add(second);
                    index++;
                } else {
                    break;
                }
            }
            start = index;
            result.add(temp);
        }
        return result;
    }

    public static ArrayList<ArrayList<Transaction>> splitByCategory(ArrayList<Transaction> transactions) {
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o1.categoryName.compareTo(o2.categoryName);
            }
        });
        ArrayList<ArrayList<Transaction>> result = new ArrayList<>();
        int index = 0;
        int start = 0;
        while (start < transactions.size()) {
            ArrayList<Transaction> temp = new ArrayList<>();
            Transaction first = transactions.get(start);
            while (index < transactions.size()) {
                Transaction second = transactions.get(index);
                if (first.categoryName.equals(second.categoryName)) {
                    temp.add(second);
                    index++;
                } else {
                    break;
                }
            }
            start = index;
            result.add(temp);
        }
        return result;
    }

    public static ArrayList<Transaction> filter(ArrayList<Transaction> transactions, Category... categories) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            for (Category category : categories) {
                if (transaction.categoryName.equals(category.name)) {
                    result.add(transaction);
                }
            }
        }
        return result;
    }

    public static ArrayList<Transaction> filter(ArrayList<Transaction> transactions, Calendar start, Calendar end) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (DateUtility.isBetweenDate(transaction.time, start, end)) {
                result.add(transaction);
            }
        }
        return result;
    }

    public static int calculateAmount(ArrayList<Transaction> transactions) {
        int result = 0;
        for (Transaction transaction : transactions) {
            if(transaction.income==0){
                result -= transaction.amount;
            }
            else{
                result += transaction.amount;
            }
        }
        return result;
    }

    public static String getAmountString(Transaction transaction) {
        if(transaction.categoryName == null){
            return (transaction.amount<=0? " ":"+") + transaction.amount;
        }
        if (transaction.amount == Math.round(transaction.amount)) {
            return (transaction.income == 0 ? "-" : "+") + ((long) transaction.amount);
        }
        return (transaction.income == 0 ? "-":"+") + transaction.amount;
    }

}
