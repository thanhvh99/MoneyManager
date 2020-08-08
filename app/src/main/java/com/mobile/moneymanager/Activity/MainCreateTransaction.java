package com.mobile.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mobile.moneymanager.R;

import java.text.DecimalFormat;
import java.text.ParseException;

public class MainCreateTransaction extends AppCompatActivity {
    private EditText inputAmount;
    private String current = "";
    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_transaction);
        EditText editText=findViewById(R.id.input_category);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainCreateTransaction.this, SlectCategory.class);
                startActivity(i);
            }
        });
        inputAmount=findViewById(R.id.input_amount);
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        hasFractionalPart = false;
        inputAmount.addTextChangedListener(new TextWatcher() {
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
                inputAmount.removeTextChangedListener(this);

                try {
                    int inilen, endlen;
                    inilen = inputAmount.getText().length();

                    String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
                    Number n = df.parse(v);
                    int cp = inputAmount.getSelectionStart();
                    if (hasFractionalPart) {
                        inputAmount.setText(df.format(n));
                    } else {
                        inputAmount.setText(dfnd.format(n));
                    }
                    endlen = inputAmount.getText().length();
                    int sel = (cp + (endlen - inilen));
                    if (sel > 0 && sel <= inputAmount.getText().length()) {
                        inputAmount.setSelection(sel);
                    } else {
                        // place cursor at the end?
                        inputAmount.setSelection(inputAmount.getText().length() - 1);
                    }
                } catch (NumberFormatException nfe) {
                    // do nothing?
                } catch (ParseException e) {
                    // do nothing?
                }

                inputAmount.addTextChangedListener(this);
            }
        });
    }
}