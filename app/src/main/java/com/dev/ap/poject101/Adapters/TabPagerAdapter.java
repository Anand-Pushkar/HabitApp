package com.dev.ap.poject101.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dev.ap.poject101.Fragments.Fragment1;
import com.dev.ap.poject101.Fragments.Fragment2;
import com.dev.ap.poject101.Fragments.Fragment3;

/**
 * Created by Pushkar on 27-Aug-18.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter{


    String[] TabArray = new String[]{"One", "Two", "Three"};
    int tabNumber = 3;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TabArray[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch(position)
        {

            case 0:
                Fragment1 frag1 = new Fragment1();
                return frag1;
            case 1:
                Fragment2 frag2 = new Fragment2();
                return frag2;
            case 2:
                Fragment3 frag3 = new Fragment3();
                return frag3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabNumber;
    }
}
