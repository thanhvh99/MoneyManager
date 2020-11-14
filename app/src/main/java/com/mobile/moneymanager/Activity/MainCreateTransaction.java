package com.mobile.moneymanager.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import static android.content.ContentValues.TAG;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.Model.Wallet;
import com.mobile.moneymanager.R;
import com.mobile.moneymanager.Utility.DateUtility;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;

public class MainCreateTransaction extends AppCompatActivity {
    private EditText inputAmount;
    private EditText inputCategory;
    private EditText inputNote;
    private EditText inputWallet;
    private String categoryResult = "";
    private String walletResult="";
    private TextView saveTran;
    int LAUNCH_SECOND_ACTIVITY = 1;
    private DecimalFormat df;
    private DecimalFormat dfnd;
    private  int categoryId;
    private int walletId;
    private boolean hasFractionalPart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_transaction);
        inputAmount=findViewById(R.id.input_amount);
        inputCategory=findViewById(R.id.input_category);
        inputNote=findViewById(R.id.input_note);
        inputWallet=findViewById(R.id.input_wallet);
        saveTran=findViewById(R.id.save_tran);
        inputAmount.requestFocus();
        inputCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainCreateTransaction.this, SlectCategory.class);
                i.putExtra("afterCategory",inputCategory.getText().toString());
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputCategory.getWindowToken(), 0);
            }
        });
        inputWallet.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainCreateTransaction.this, SelectWallet.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputCategory.getWindowToken(), 0);
            }
        });
        saveTran.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("MoneyManager",MODE_PRIVATE,null);
                int amount=Integer.parseInt(inputAmount.getText().toString());
                String note=inputNote.getText().toString();
                Calendar time=Calendar.getInstance();
                ContentValues values = new ContentValues();

                values.put("amount",amount);
                values.put("note",note);
                values.put("time", DateUtility.dateToString(time, "dd/MM/yyyy"));
                values.put("walletId",walletId);
                values.put("categoryId",categoryId);
                db.insert("Transac", null, values);
                Intent i= new Intent (MainCreateTransaction.this,MainActivity.class);
                startActivity(i);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(inputCategory.getWindowToken(), 0);
            }
        });
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        hasFractionalPart = false;
//        inputAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator())))
//                {
//                    hasFractionalPart = true;
//                } else {
//                    hasFractionalPart = false;
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                inputAmount.removeTextChangedListener(this);
//
//                try {
//                    int inilen, endlen;
//                    inilen = inputAmount.getText().length();
//
//                    String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
//                    Number n = df.parse(v);
//                    int cp = inputAmount.getSelectionStart();
//                    if (hasFractionalPart) {
//                        inputAmount.setText(df.format(n));
//                    } else {
//                        inputAmount.setText(dfnd.format(n));
//                    }
//                    endlen = inputAmount.getText().length();
//                    int sel = (cp + (endlen - inilen));
//                    if (sel > 0 && sel <= inputAmount.getText().length()) {
//                        inputAmount.setSelection(sel);
//                    } else {
//                        // place cursor at the end?
//                        inputAmount.setSelection(inputAmount.getText().length() - 1);
//                    }
//                } catch (NumberFormatException nfe) {
//                    // do nothing?
//                } catch (ParseException e) {
//                    // do nothing?
//                }
//
//                inputAmount.addTextChangedListener(this);
//            }
//        });
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputCategory=findViewById(R.id.input_category);
        inputWallet=findViewById(R.id.input_wallet);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
               Category category= (Category) data.getSerializableExtra("result");
                Wallet wallet= (Wallet) data.getSerializableExtra("resultWallet");
                if(category!=null){
                    inputCategory.setText(category.name);
                    categoryId=category.id;
                }
                if(wallet!=null){
                    inputWallet.setText(wallet.name);
                    walletId=wallet.id;
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                categoryResult="";
            }
        }
    }
}