package com.mobile.moneymanager;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.moneymanager.Database.DatabaseConect;
import com.mobile.moneymanager.Interface.OnDataChangedListener;
import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.Model.Transaction;
import com.mobile.moneymanager.Utility.DateUtility;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public final class MoneyManager extends Application {
    private static Context context;
    private static HashMap<Integer, ArrayList<Transaction>> data = new HashMap<>();
    private static HashMap<Integer, HashSet<OnDataChangedListener>> listener = new HashMap<>();
    @Override
    public void onCreate() {
        super.onCreate();
        MoneyManager.context=getApplicationContext();
    }
    static{
        loadData();
    }
    public static Context getAppContext() {
        return MoneyManager.context;
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
            ArrayList<Transaction> transactions = new ArrayList<>();
            int index = DateUtility.hashMonth(calendar);
            data.put(index, transactions);
        try {
            DatabaseConect database= new DatabaseConect(MoneyManager.getAppContext(),"MoneyManager",null,1);
            Cursor dataTrans= database.getDataJoin("SELECT Transac.amount,Transac.note, Transac.time,Category.name,Category.income,Wallet.name, Wallet.balance FROM Transac  INNER JOIN Category ON Transac.categoryId = Category.id INNER JOIN Wallet ON Transac.walletId = Wallet.id;");
            while (dataTrans.moveToNext()) {
                Transaction transaction = new Transaction();
                transaction.categoryName = dataTrans.getString(3);
                transaction.amount = dataTrans.getInt(0);
                transaction.note = dataTrans.getString(1);
                transaction.income = dataTrans.getInt(4);
                transaction.walletName = dataTrans.getString(5);
                transaction.balance = dataTrans.getInt(6);
                String time = dataTrans.getString(2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Calendar calendar2= Calendar.getInstance();
                calendar2.setTime(sdf.parse(time));
                transaction.time=calendar2;
                addTransaction(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        /*
         *  Lưu biến data ra file
         */
    }

    public static ArrayList<Transaction> getTransactionsForMonth(Calendar calendar) {
        loadData();
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

    public static void removeOnDataChangedListener(int hashMonth, OnDataChangedListener listener) {
        HashSet<OnDataChangedListener> set = MoneyManager.listener.get(hashMonth);
        if (set != null) {
            set.remove(listener);
        }
    }

    public static void removeOnDataChangedListener(OnDataChangedListener listener) {
        HashSet<OnDataChangedListener> set = MoneyManager.listener.get(0);
        if (set != null) {
            set.remove(listener);
        }
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

    public static void removeTransaction(Transaction transaction) {
        int index = DateUtility.hashMonth(transaction.time);
        ArrayList<Transaction> transactions = data.get(index);
        if (transactions != null) {
            transactions.remove(transaction);
        }
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
