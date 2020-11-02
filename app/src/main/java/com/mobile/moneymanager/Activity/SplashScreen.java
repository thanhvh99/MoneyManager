package com.mobile.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mobile.moneymanager.Database.DatabaseConect;
import com.mobile.moneymanager.R;

public class SplashScreen extends AppCompatActivity implements Animation.AnimationListener {
    private static int SPLASH_TIME_OUT = 3000;
    ImageView imageView;
    Animation animation;
    DatabaseConect database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDatabase();
        setContentView(R.layout.activity_splash_screen);
        imageView= findViewById(R.id.logo);
        animation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_splash);
        animation.setAnimationListener(this);
        imageView.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                database=new DatabaseConect(SplashScreen.this,"MoneyManager",null,1);
                Cursor dataExpense= database.getData("SELECT * FROM Wallet");
                if(dataExpense.moveToNext()){
                    Intent i = new Intent(SplashScreen.this,  MainCreateTransaction.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(SplashScreen.this, CreateWallet.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
    public void createDatabase(){
        database=new DatabaseConect(this,"MoneyManager",null,1);
//        database.queryData("delete from Wallet");
//        database.queryData("delete from Category");
        database.queryData("CREATE TABLE IF NOT EXISTS  Category (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(200),income INTEGER);");
        database.queryData("CREATE TABLE IF NOT EXISTS  Wallet (id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(200), balance INTEGER);");
        if(database.tableEmpty("Category")){
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Food & Beverage',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Bills & Utilities',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Transportation',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Shopping',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Friends & Lover',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Entertainment',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Travel',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Health & Fitness',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Gifts & Donations',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Family',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Education',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Investment',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Business',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Insurances',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Fees & Charges',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Others',0);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Award',1);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Interest Money',1);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Salary',1);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Gifts',1);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Selling',1);");
            database.queryData("INSERT INTO Category(id,name,income) VALUES(null,'Others',1);");
        }


    }
    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}