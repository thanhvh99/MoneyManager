package com.mobile.moneymanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mobile.moneymanager.Adapter.TransactionsPagerAdapter;
import com.mobile.moneymanager.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainTransactionsFragment extends Fragment {

    private static final int NUMBER_OF_TAB = 20;

    private int lastTabIndex = NUMBER_OF_TAB - 1;
    private TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            lastTabIndex = savedInstanceState.getInt("LastTabIndex", NUMBER_OF_TAB - 1);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("LastTabIndex", tabLayout.getSelectedTabPosition());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_transactions, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        ArrayList<Calendar> calendars = new ArrayList<>();
        for (int i = NUMBER_OF_TAB - 1; i >= 0; --i) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            calendars.add(calendar);
        }

        TransactionsPagerAdapter adapter = new TransactionsPagerAdapter(getChildFragmentManager(), calendars);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(lastTabIndex, false);

        return view;
    }

}
