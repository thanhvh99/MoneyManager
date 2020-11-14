package com.mobile.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;
import com.mobile.moneymanager.Adapter.WalletAdapter;
import com.mobile.moneymanager.Database.DatabaseConect;
import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.Model.Wallet;
import com.mobile.moneymanager.R;

import java.util.ArrayList;

public class SelectWallet extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter walletAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<Wallet> wallets = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wallet);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewWallet);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        try{
            DatabaseConect database= new DatabaseConect(this,"MoneyManager",null,1);
            Cursor dataWallets= database.getData("SELECT * FROM Wallet");
            while (dataWallets.moveToNext()){
                String nameWallet= dataWallets.getString(1);
                int balanceWallet= dataWallets.getInt(2);
                int id= dataWallets.getInt(0);
                wallets.add(new Wallet(id,nameWallet,balanceWallet));
            }
        }catch (Exception e){
            Log.d(TAG,"err");
        }
        walletAdapter= new WalletAdapter(wallets,this);
        recyclerView.setAdapter(walletAdapter);
        ImageView back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}