package com.fanyafeng.testnew.MatrixTest;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class MovementActivity extends BaseActivity {
    private ViewPager viewpager_movement;
    private List<Fragment> fragmentLists;
    private ImageView iv_movement_circle;
    private GestureDetector gestureDetector;
    private int currentIndex;
    private static int totalPage = 2;
    private TextView tv_custom, tv_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "仿华为指示器点";
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
        iv_movement_circle = (ImageView) findViewById(R.id.iv_movement_circle);
        viewpager_movement = (ViewPager) findViewById(R.id.viewpager_movement);
        tv_custom = (TextView) findViewById(R.id.tv_custom);
        tv_all = (TextView) findViewById(R.id.tv_all);

        initCursorPosition();
    }

    private void initData() {
        fragmentLists = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            fragmentLists.add(new MatrixFragment());
        }
        viewpager_movement.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentLists));
        viewpager_movement.setCurrentItem(0);
        currentIndex = 0;
        viewpager_movement.addOnPageChangeListener(new MyOnPageChangeListener());
        tv_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager_movement.setCurrentItem(0);
            }
        });
        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager_movement.setCurrentItem(1);
            }
        });
//        Service
    }


    private void initCursorPosition() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        Matrix matrix = new Matrix();
        matrix.postTranslate(width / (totalPage * 2), 0);
//        初始化点的位置
        iv_movement_circle.setImageMatrix(matrix);
    }


    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            System.out.println("第一个参数arg0："+position);
//            System.out.println("第二个参数arg1：" + positionOffset);
//            System.out.println("第三个参数positionOffsetPixels：" + positionOffsetPixels);
            if (positionOffsetPixels != 0)
                ViewCompat.setTranslationX(iv_movement_circle, positionOffsetPixels / totalPage);
        }

        public void onPageSelected(int index) {

            currentIndex = index;
        }
    }


    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
