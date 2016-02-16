package com.fanyafeng.testnew.MatrixTest;

import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MatrixActivity extends BaseActivity {
    private ViewPager viewpager_matrix;
    private List<Fragment> fragmentLists;
    private ImageView iv_matrix_circle;
    private int currentIndex;
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试矩阵";
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
        iv_matrix_circle = (ImageView) findViewById(R.id.iv_matrix_circle);
        viewpager_matrix = (ViewPager) findViewById(R.id.viewpager_matrix);
        initCursorPosition();
    }

    private void initData() {
        fragmentLists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragmentLists.add(new MatrixFragment());
        }
        viewpager_matrix.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentLists));
        viewpager_matrix.setCurrentItem(0);
        currentIndex = 0;
        viewpager_matrix.addOnPageChangeListener(new MyOnPageChangeListener());

//        ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
//        ViewPagerTask pagerTask = new ViewPagerTask();
//        scheduledExecutorService.scheduleAtFixedRate(pagerTask, 4, 4, TimeUnit.SECONDS);


    }

    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            currentIndex = (currentIndex + 1) % fragmentLists.size();
            handler4.sendEmptyMessage(0);
        }
    }

    Handler handler4 = new Handler() {
        public void handleMessage(Message msg) {
            viewpager_matrix.setCurrentItem(currentIndex);
        }

    };

    private void initCursorPosition() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        Matrix matrix = new Matrix();
        matrix.postTranslate(width / 8, 0);
//        初始化点的位置
        iv_matrix_circle.setImageMatrix(matrix);
    }

    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            Matrix matrix = new Matrix();
//            设置最初0坐标
            matrix.postTranslate(width / 8, 0);
            offset = (width / 8);

            TranslateAnimation translateAnimation = null;
            switch (index) {
                case 0:
                    if (currentIndex == 1) {
                        translateAnimation = new TranslateAnimation(2 * offset, 0, 0, 0);
                    } else if (currentIndex == 3) {
                        translateAnimation = new TranslateAnimation(6 * offset, 0, 0, 0);
                    }
                    break;
                case 1:
                    if (currentIndex == 0) {
                        translateAnimation = new TranslateAnimation(0, 2 * offset, 0, 0);
                    } else if (currentIndex == 2) {
                        translateAnimation = new TranslateAnimation(4 * offset, 2 * offset, 0, 0);
                    }
                    break;
                case 2:
                    if (currentIndex == 1) {
                        translateAnimation = new TranslateAnimation(2 * offset, 4 * offset, 0, 0);
                    } else if (currentIndex == 3) {
                        translateAnimation = new TranslateAnimation(6 * offset, 4 * offset, 0, 0);
                    }
                    break;
                case 3:
                    if (currentIndex == 0) {
                        translateAnimation = new TranslateAnimation(6 * offset, 0, 0, 0);
                    } else if (currentIndex == 2) {
                        translateAnimation = new TranslateAnimation(4 * offset, 6 * offset, 0, 0);
                    }
                    break;
            }
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(300);
            iv_matrix_circle.startAnimation(translateAnimation);

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
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
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
