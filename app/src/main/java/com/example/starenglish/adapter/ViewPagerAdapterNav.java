package com.example.starenglish.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.starenglish.DictionaryFragment;
import com.example.starenglish.ExamFragment;
import com.example.starenglish.HomeFragment;
import com.example.starenglish.UserFragment;

public class ViewPagerAdapterNav extends FragmentStatePagerAdapter {

    private final int COUNT_PAGE_NAV = 4;

    public ViewPagerAdapterNav(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new DictionaryFragment();

            case 2:
                return new ExamFragment();

            case 3:
                return new UserFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT_PAGE_NAV;
    }
}
