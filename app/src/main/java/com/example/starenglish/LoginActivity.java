package com.example.starenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.starenglish.adapter.ViewPagerAdapterLogin;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTablayout = findViewById(R.id.tab_layout_login);
        mViewPager = findViewById(R.id.view_pager_login);

        ViewPagerAdapterLogin viewPagerAdapterLogin = new ViewPagerAdapterLogin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mViewPager.setAdapter(viewPagerAdapterLogin);
        mTablayout.setupWithViewPager(mViewPager);
    }
}