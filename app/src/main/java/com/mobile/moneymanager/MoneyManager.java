package com.mobile.moneymanager;

import android.app.Application;

import com.mobile.moneymanager.Interface.OnDataChangedListener;
import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.Model.Transaction;
import com.mobile.moneymanager.Utility.DateUtility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

public final class MoneyManager extends Application {

    private static HashMap<Integer, ArrayList<Transaction>> data = new HashMap<>();
    private static HashMap<Integer, HashSet<OnDataChangedListener>> listener = new HashMap<>();

    static {
        loadData();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private static void loadData() {
        /*
         *  Đọc từ file biến data
         */

        // Code cứng tạo dữ liệu ảo
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 5; i++) {
            ArrayList<Transaction> transactions = new ArrayList<>();
            int index = DateUtility.hashMonth(calendar);
            data.put(index, transactions);
            for (int j = 0; j < 10; j++) {
                Transaction transaction = new Transaction();
                transaction.amount = 1000 * (i + 1);
                transaction.category = new Category();
                transaction.time = (Calendar) calendar.clone();
                transaction.time.add(Calendar.DATE, -j / 2);
                addTransaction(transaction);
            }
            calendar.add(Calendar.MONTH, -1);
        }
    }

    public static void saveData() {
        /*
         *  Lưu biến data ra file
         */
    }

    public static ArrayList<Transaction> getTransactionsForMonth(Calendar calendar) {
        int index = DateUtility.hashMonth(calendar);
        if (data.containsKey(index)) {
            return data.get(index);
        }
        return new ArrayList<>();
    }

    public static void addOnDataChangedListener(OnDataChangedListener listener) {
        HashSet<OnDataChangedListener> set = MoneyManager.listener.get(0);
        if (set == null) {
            set = new HashSet<>();
            MoneyManager.listener.put(0, set);
        }
        set.add(listener);
    }

    public static void addOnDataChangedListener(int hashMonth, OnDataChangedListener listener) {
        HashSet<OnDataChangedListener> set = MoneyManager.listener.get(hashMonth);
        if (set == null) {
            set = new HashSet<>();
            MoneyManager.listener.put(hashMonth, set);
        }
        set.add(listener);
    }

    public static void addTransaction(Transaction transaction) {
        int index = DateUtility.hashMonth(transaction.time);
        ArrayList<Transaction> transactions = data.get(index);
        if (transactions == null) {
            transactions = new ArrayList<>();
            data.put(index, transactions);
        }
        transactions.add(transaction);
        notifyListener(index);
        notifyListener(0);
    }

    public static void deleteTransaction(Transaction transaction) {
        int index = DateUtility.hashMonth(transaction.time);
        ArrayList<Transaction> transactions = data.get(index);
        if (transactions == null) {
            transactions = new ArrayList<>();
            data.put(index, transactions);
        }
        transactions.add(transaction);
        notifyListener(index);
        notifyListener(0);
    }

    public static void updateTransaction(Transaction oldTransaction, Transaction newTransaction) {
        int index = DateUtility.hashMonth(oldTransaction.time);
        ArrayList<Transaction> transactions = data.get(index);
        if (transactions != null) {
            transactions.remove(oldTransaction);
            notifyListener(index);
        }
        addTransaction(newTransaction);
    }

    private static void notifyListener(int index) {
        HashSet<OnDataChangedListener> set = MoneyManager.listener.get(index);
        if (set != null) {
            for (OnDataChangedListener listener : set) {
                listener.onDataChanged();
            }
        }
    }

}
