package com.mobile.moneymanager.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobile.moneymanager.Fragment.ExpenseFragment;
import com.mobile.moneymanager.Fragment.IncomeFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    public CategoryPagerAdapter(FragmentManager fm) {
        super(fm);
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
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Expense";
                break;
            case 1:
                title = "Income";
                break;
        }
        return title;
    }
}
