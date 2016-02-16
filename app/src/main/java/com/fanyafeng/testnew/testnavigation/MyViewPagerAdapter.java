package com.fanyafeng.testnew.testnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by fanyafeng on 2015/10/28,0028.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titleList;
    private List<Fragment> fragmentList;

    public MyViewPagerAdapter(FragmentManager fm, String[] titleList, List<Fragment> fragmentList) {
        super(fm);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
