package com.example.starenglish.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.starenglish.LoginFragment;
import com.example.starenglish.SignupFragment;

public class ViewPagerAdapterLogin extends FragmentStatePagerAdapter{

    private final int COUNT_PAGE_LOGIN = 2;

    public ViewPagerAdapterLogin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();

            case 1:
                return new SignupFragment();

            default:
                return new LoginFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT_PAGE_LOGIN;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Login";
                break;

            case 1:
                title = "Sign up";
                break;
        }
        return title;
    }
}
