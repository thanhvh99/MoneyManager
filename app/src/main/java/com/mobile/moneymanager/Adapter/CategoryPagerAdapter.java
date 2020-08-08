package com.mobile.moneymanager.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobile.moneymanager.Fragment.ExpenseFragment;
import com.mobile.moneymanager.Fragment.IncomeFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    int numCase;
    public CategoryPagerAdapter(FragmentManager fm,int numCase) {
        super(fm);
        this.numCase=numCase;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ExpenseFragment expenseFragment=new ExpenseFragment();
                return expenseFragment;
            case 1:
                IncomeFragment incomeFragment= new IncomeFragment();
                return incomeFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numCase;
    }
}
