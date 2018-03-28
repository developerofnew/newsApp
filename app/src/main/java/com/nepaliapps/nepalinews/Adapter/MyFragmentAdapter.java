package com.nepaliapps.nepalinews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bino on 17/2/18.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> pages = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void addPage(Fragment f,String t){

        pages.add(f);
        title.add(t);

    }

    @Override
    public CharSequence getPageTitle(int position) {


        return  title.get(position);
    }
}
