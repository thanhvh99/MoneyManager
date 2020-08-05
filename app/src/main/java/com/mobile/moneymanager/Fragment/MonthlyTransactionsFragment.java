package com.mobile.moneymanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.moneymanager.Adapter.TransactionsAdapter;
import com.mobile.moneymanager.Interface.OnDataChangedListener;
import com.mobile.moneymanager.Model.Transaction;
import com.mobile.moneymanager.MoneyManager;
import com.mobile.moneymanager.R;
import com.mobile.moneymanager.Utility.DateUtility;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthlyTransactionsFragment extends Fragment implements OnDataChangedListener {

    private Calendar calendar;
    private TransactionsAdapter adapter;

    private TextView noRecordTextView;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoneyManager.addOnDataChangedListener(DateUtility.hashMonth(calendar), this);
        if (savedInstanceState != null) {
            calendar = (Calendar) savedInstanceState.getSerializable("Calendar");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Calendar", calendar);
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_transactions, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        noRecordTextView = view.findViewById(R.id.noRecordTextView);

        adapter = new TransactionsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateContent();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MoneyManager.removeOnDataChangedListener(this);
    }

    @Override
    public void onDataChanged() {
        updateContent();
    }

    private void updateContent() {
        ArrayList<Transaction> transactions = MoneyManager.getTransactionsForMonth(calendar);
        if (transactions.isEmpty()) {
            noRecordTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noRecordTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setTransactions(transactions);
            adapter.notifyDataSetChanged();
        }
    }

}
