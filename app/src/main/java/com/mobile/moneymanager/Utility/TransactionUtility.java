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
                return o1.category.name.compareTo(o2.category.name);
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
                if (first.category.name.equals(second.category.name)) {
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
                if (transaction.category.name.equals(category.name)) {
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

    public static double calculateAmount(ArrayList<Transaction> transactions) {
        double result = 0;
        for (Transaction transaction : transactions) {
            if (transaction.category.income==1) {
                result += transaction.amount;
            } else {
                result -= transaction.amount;
            }
        }
        return result;
    }

    public static String getAmountString(Transaction transaction) {
        if (transaction.category == null) {
            return (transaction.amount < 0 ? "" : "+") + transaction.amount;
        }
        return (transaction.category.income==1 ? "+" : "-") +  transaction.amount;
    }

}
