package com.mobile.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobile.moneymanager.Fragment.MainTransactionsFragment;
import com.mobile.moneymanager.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainTransactionsFragment()).commit();
        }
    }
}