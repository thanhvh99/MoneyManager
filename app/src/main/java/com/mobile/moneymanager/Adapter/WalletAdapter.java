package com.mobile.moneymanager.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.moneymanager.Model.Transaction;
import com.mobile.moneymanager.Model.Wallet;
import com.mobile.moneymanager.R;

import java.util.ArrayList;

public class WalletAdapter extends  RecyclerView.Adapter<WalletAdapter.MyViewHolder>  implements View.OnClickListener{
    public ArrayList<Wallet> wallets;
    private Activity mActivity;
    @NonNull
    @Override
    public WalletAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wallet, parent, false);
        v.setOnClickListener(this);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    public WalletAdapter(ArrayList<Wallet>myDataset,Activity mActivity ) {
        this.wallets = myDataset;
        this.mActivity=mActivity;
    }

    @Override
    public void onClick(View v) {
        RecyclerView recyclerView = (RecyclerView) v.getParent();
        Wallet wallet = wallets.get(recyclerView.getChildAdapterPosition(v));
        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultWallet",wallet);
        mActivity.setResult(Activity.RESULT_OK,returnIntent);
        mActivity.finish();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameTextView;
        public  TextView blanceTextView;
        public MyViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.walletTextView);
            blanceTextView=v.findViewById(R.id.balanceTextView);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull WalletAdapter.MyViewHolder holder, int position) {
        Wallet wallet= wallets.get(position);
        holder.nameTextView.setText(wallet.name);
        holder.blanceTextView.setText(Integer.toString(wallet.balance));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

}
