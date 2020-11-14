package com.mobile.moneymanager.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.mobile.moneymanager.Model.Transaction;
import com.mobile.moneymanager.R;
import com.mobile.moneymanager.Utility.DateUtility;
import com.mobile.moneymanager.Utility.TransactionUtility;

import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions.clear();
        ArrayList<ArrayList<Transaction>> group = TransactionUtility.splitByDate(transactions);
        for (ArrayList<Transaction> list : group) {
            Transaction header = new Transaction();
            header.categoryName = null;
            header.amount = TransactionUtility.calculateAmount(list);
            header.time = list.get(0).time;
            this.transactions.add(header);
            this.transactions.addAll(list);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(inflater.inflate(R.layout.header_transaction, parent, false));
        } else {
            View view = inflater.inflate(R.layout.item_transaction, parent, false);
            view.setOnClickListener(this);
            return new ItemHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        if (transaction.categoryName == null) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.amountTextView.setText(TransactionUtility.getAmountString(transaction));
            headerHolder.dateTextView.setText(DateUtility.dateToString(transaction.time, "dd/MM/yyyy"));
        } else {
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.amountTextView.setText(TransactionUtility.getAmountString(transaction));
            itemHolder.categoryTextView.setText(transaction.categoryName);
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    @Override
    public int getItemViewType(int position) {
        return transactions.get(position).categoryName == null ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public void onClick(View v) {
        RecyclerView recyclerView = (RecyclerView) v.getParent();
        Transaction transaction = transactions.get(recyclerView.getChildAdapterPosition(v));
        Toast.makeText(v.getContext(), transaction.categoryName, Toast.LENGTH_SHORT).show();
    }

    public static class ItemHolder extends ViewHolder {

        public TextView categoryTextView;
        public TextView amountTextView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }

    }

    public static class HeaderHolder extends ViewHolder {

        public TextView dateTextView;
        public TextView amountTextView;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }

    }

}
