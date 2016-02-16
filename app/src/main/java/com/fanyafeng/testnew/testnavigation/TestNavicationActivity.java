package com.fanyafeng.testnew.testnavigation;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testslide.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class TestNavicationActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout id_drawerlayout;
    private NavigationView id_navigationview;

    private TabLayout tablayout_nav;
    private ViewPager viewpager_nav;
    private List<Fragment> fragmentList;
    private String[] stringList;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_navication);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        initConfigViews();

    }

    private void initView() {
        id_drawerlayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        id_drawerlayout.setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
        id_navigationview = (NavigationView) findViewById(R.id.id_navigationview);
//        id_navigationview.setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
        tablayout_nav = (TabLayout) findViewById(R.id.tablayout_nav);
        viewpager_nav = (ViewPager) findViewById(R.id.viewpager_nav);
//        SurfaceView
    }

    private void initData() {
        stringList = getResources().getStringArray(R.array.tab_titles);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < stringList.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("flag", i);
            RecyclerFragment recyclerFragment = new RecyclerFragment();
            recyclerFragment.setArguments(bundle);
            fragmentList.add(i, recyclerFragment);
        }
    }

    private void initConfigViews() {
//        设置drawerlayout开关指示器，即Toorbar最左边的icon
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, id_drawerlayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        id_drawerlayout.setDrawerListener(actionBarDrawerToggle);

//        给navigationview填充顶部区域，也可以在xml中使用app：headerlayout="@layout/header_nav"来设置
        id_navigationview.inflateHeaderView(R.layout.layout_left);
//        给navigationview填充menu菜单，也可以在xml中使用app：menu="@menu/menu_nav"
        id_navigationview.inflateMenu(R.menu.menu_nav);

        onNavgationViewMenuItemSelected(id_navigationview);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), stringList, fragmentList);
        viewpager_nav.setAdapter(myViewPagerAdapter);
        viewpager_nav.setOffscreenPageLimit(5);
        viewpager_nav.addOnPageChangeListener(this);
        tablayout_nav.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout_nav.setupWithViewPager(viewpager_nav);
        tablayout_nav.setTabsFromPagerAdapter(myViewPagerAdapter);

    }


    private void onNavgationViewMenuItemSelected(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_home:
                        break;
                    case R.id.nav_menu_categories:
                        break;
                    case R.id.nav_menu_feedback:
                        break;
                    case R.id.nav_menu_setting:
                        break;
                }
                item.setChecked(true);
                id_drawerlayout.closeDrawers();
                return true;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_abase, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_test_recycleview) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toolbar.setTitle(stringList[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
