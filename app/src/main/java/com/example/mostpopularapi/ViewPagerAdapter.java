package com.example.mostpopularapi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int NumbOfTabs = 3;
    private String Titles[] = new String[] { "Emailed", "Shared", "Viewed" };
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            EmailedFragment tab = new EmailedFragment();
            return tab;
        }
        if (position == 1) {
            SharedFragment tab = new SharedFragment();
            return tab;
        }
        else {
            ViewedFragment tab = new ViewedFragment();
            return tab;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}