package com.mobile.moneymanager.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobile.moneymanager.Fragment.MonthlyTransactionsFragment;

import java.util.ArrayList;

public class TransactionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<MonthlyTransactionsFragment> fragments;

    public TransactionsPagerAdapter(@NonNull FragmentManager fm, ArrayList<MonthlyTransactionsFragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

}
