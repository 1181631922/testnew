package com.fanyafeng.testnew.ViewPagerTest;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by fanyafeng on 2016/1/11,0011.
 */
public class ScrollPagerAdapter extends PagerAdapter {
    private List<View> viewList = null;

    public ScrollPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }


    @Override
    public int getCount() {
        if (viewList.size() == 1) {
            return 1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position % viewList.size());

        if (view != null && view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
