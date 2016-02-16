package com.fanyafeng.testnew.testviewpagertablayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

import java.util.ArrayList;
import java.util.List;

public class TabMainActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //    private String[] titleList = new String[]{"节目短信", "历史记录"};
    private List<Fragment> fragmentList;
    private List<String> titleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isShowEmail = true;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout_tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager_tab);
        titleList = new ArrayList<>();
        titleList.add("节日短信");
        titleList.add("历史记录");
        fragmentList = new ArrayList<>();
        fragmentList.add(new TestFragment());
        fragmentList.add(new Test2Fragment());
        for (int i = 0; i < 10; i++) {
            fragmentList.add(new Test2Fragment());
            titleList.add("标题头" + i);
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }


    private void initData() {

    }

}
