package com.zaita.aliyounes.gsbvc2017.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zaita.aliyounes.gsbvc2017.fragments.InvoicesFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.TransactionsFragment;
import com.zaita.aliyounes.gsbvc2017.fragments.TransferFragment;


/**
 * Created by mohammad.younes on 2/22/2017.
 */

public class ActivityTabsPagerAdapter extends FragmentPagerAdapter {
    private int mNumOfTabs;
    public ActivityTabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TransactionsFragment();
            case 1:
                return new InvoicesFragment();
            case 2:
                return new TransferFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
