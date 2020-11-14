package com.mobile.moneymanager.Adapter;

import android.util.Log;

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

    private ArrayList<Calendar> calendars;

    public TransactionsPagerAdapter(@NonNull FragmentManager fm, ArrayList<Calendar> calendars) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.calendars = calendars;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        MonthlyTransactionsFragment fragment = new MonthlyTransactionsFragment();
        fragment.setCalendar(calendars.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return calendars.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == calendars.size() - 1) {
            return "This Month";
        } else if (position == calendars.size() - 2) {
            return "Last Month";
        }
        return DateUtility.dateToString(calendars.get(position), "MM/yyyy");
    }

}
