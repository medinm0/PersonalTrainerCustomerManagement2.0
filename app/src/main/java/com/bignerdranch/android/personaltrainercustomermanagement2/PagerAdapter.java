package com.bignerdranch.android.personaltrainercustomermanagement2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CustomerListFragment tab1 = new CustomerListFragment();
                return tab1;
            case 1:
                CustomerFragment tab2 = new CustomerFragment();
                return tab2;
            case 2:
                SessionFragment tab3 = new SessionFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}