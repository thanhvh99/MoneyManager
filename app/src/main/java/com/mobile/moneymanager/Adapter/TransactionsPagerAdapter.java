package com.mobile.moneymanager.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobile.moneymanager.Fragment.MonthlyTransactionsFragment;
import com.mobile.moneymanager.Utility.DateUtility;

import java.util.ArrayList;
import java.util.Calendar;

public class TransactionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<MonthlyTransactionsFragment> fragments = new ArrayList<>();

    public TransactionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        initialize();
    }

    private void initialize() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -11);
        for (int i = 0; i < 12; i++) {
            fragments.add(new MonthlyTransactionsFragment(DateUtility.dateToString(calendar, "MM/yyyy")));
            calendar.add(Calendar.MONTH, 1);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
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
