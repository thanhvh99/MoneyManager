package com.mobile.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobile.moneymanager.R;

import java.text.DecimalFormat;
import java.text.ParseException;


public class CreateWallet extends AppCompatActivity {
    private Button button;
    private EditText inputNameWallet;
    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;
    private EditText inputBalance;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);
        inputBalance=findViewById(R.id.input_balance);
        inputNameWallet= findViewById(R.id.input_name);
        inputNameWallet.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        hasFractionalPart = false;
        button=findViewById(R.id.goon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateWallet.this, MainCreateTransaction.class);
                startActivity(i);
            }
        });
        inputBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator())))
                {
                    hasFractionalPart = true;
                } else {
                    hasFractionalPart = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputBalance.removeTextChangedListener(this);

                try {
                    int inilen, endlen;
                    inilen = inputBalance.getText().length();

                    String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
                    Number n = df.parse(v);
                    int cp = inputBalance.getSelectionStart();
                    if (hasFractionalPart) {
                        inputBalance.setText(df.format(n));
                    } else {
                        inputBalance.setText(dfnd.format(n));
                    }
                    endlen = inputBalance.getText().length();
                    int sel = (cp + (endlen - inilen));
                    if (sel > 0 && sel <= inputBalance.getText().length()) {
                        inputBalance.setSelection(sel);
                    } else {
                        // place cursor at the end?
                        inputBalance.setSelection(inputBalance.getText().length() - 1);
                    }
                } catch (NumberFormatException nfe) {
                    // do nothing?
                } catch (ParseException e) {
                    // do nothing?
                }

                inputBalance.addTextChangedListener(this);
            }
        });
    }
}