package com.mobile.moneymanager.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import static android.content.ContentValues.TAG;
import com.google.android.material.tabs.TabLayout;
import com.mobile.moneymanager.Adapter.CategoryPagerAdapter;
import com.mobile.moneymanager.Fragment.ExpenseFragment;
import com.mobile.moneymanager.R;

public class SlectCategory extends AppCompatActivity {
    public String afterCategory="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slect_category);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Intent intent=getIntent();
        afterCategory=intent.getStringExtra("afterCategory");
        Bundle bundle = new Bundle();
        bundle.putString("afterCategory", afterCategory);
        ExpenseFragment fragobj = new ExpenseFragment();
        fragobj.setArguments(bundle);
        final ViewPager viewPager=findViewById(R.id.viewpager);
        final CategoryPagerAdapter adapter = new CategoryPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        ImageView back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}