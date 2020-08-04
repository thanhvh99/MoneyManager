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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        assert getFragmentManager() != null;
        TransactionsPagerAdapter adapter = new TransactionsPagerAdapter(getFragmentManager(), getFragments());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private ArrayList<MonthlyTransactionsFragment> getFragments() {
        ArrayList<MonthlyTransactionsFragment> fragments = new ArrayList<>();
        for (int i = 11; i >= 0; --i) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            MonthlyTransactionsFragment fragment = new MonthlyTransactionsFragment();
            fragment.initialize(calendar);
            fragments.add(fragment);
        }
        return fragments;
    }

}
