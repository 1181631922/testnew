package com.fanyafeng.testnew.testfragmenttranslate;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

import java.util.List;

public class MainFragmentActivity extends BaseActivity {
    private static String[] FRAGEMENT_TAGS = new String[]{"1", "2", "3", "4"};
    private View[] tabs = new View[4];
    private TranslateFragmentOne translateFragmentOne;
    private TranslateFragmentTwo translateFragmentTwo;
    private TranslateFragmentThree translateFragmentThree;
    private TranslateFragmentFour translateFragmentFour;


    private TextView tab_text1, tab_text2, tab_text3, tab_text4;
    private FragmentManager fragmentManager;

    private List<Fragment> fragmentList;

    // FIXME: 2015/10/26 色值获取出现问题
    private int selectColor = 0x7f040000;
    private int unSelectColor = 0x7f040000;
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue = 0xFF0AB2FB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试fragment切换动画";
        isShowEmail = true;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fragmentManager = getSupportFragmentManager();

        tab_text1 = (TextView) findViewById(R.id.tab_text1);
        tab_text2 = (TextView) findViewById(R.id.tab_text2);
        tab_text3 = (TextView) findViewById(R.id.tab_text3);
        tab_text4 = (TextView) findViewById(R.id.tab_text4);


        tabs[0] = findViewById(R.id.tab_one);
        tabs[1] = findViewById(R.id.tab_two);
        tabs[2] = findViewById(R.id.tab_three);
        tabs[3] = findViewById(R.id.tab_four);

        selectTab(0);

        for (int i = 0; i < tabs.length; i++) {
            final int j = i;
            tabs[i].setTag(i);
            tabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectTab(j);
                }
            });
        }

    }


    private void selectTab(int i) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearTabSelection();
        hideFragement(fragmentTransaction);
//        fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
        switch (i) {
            case 0:
                tab_text1.setTextColor(blue);
                fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
                if (translateFragmentOne == null) {
                    translateFragmentOne = new TranslateFragmentOne();
                    fragmentTransaction.add(R.id.container, translateFragmentOne, FRAGEMENT_TAGS[0]);
                } else {
                    fragmentTransaction.show(translateFragmentOne);
                }
                break;
            case 1:
                tab_text2.setTextColor(blue);
                fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
                if (translateFragmentTwo == null) {
                    translateFragmentTwo = new TranslateFragmentTwo();
                    fragmentTransaction.add(R.id.container, translateFragmentTwo, FRAGEMENT_TAGS[1]);
                } else {
                    fragmentTransaction.show(translateFragmentTwo);
                }
                break;
            case 2:
                tab_text3.setTextColor(blue);
                fragmentTransaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
                if (translateFragmentThree == null) {
                    translateFragmentThree = new TranslateFragmentThree();
                    fragmentTransaction.add(R.id.container, translateFragmentThree, FRAGEMENT_TAGS[2]);
                } else {
                    fragmentTransaction.show(translateFragmentThree);
                }
                break;
            case 3:
                tab_text4.setTextColor(blue);
                fragmentTransaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
                if (translateFragmentFour == null) {
                    translateFragmentFour = new TranslateFragmentFour();
                    fragmentTransaction.add(R.id.container, translateFragmentFour, FRAGEMENT_TAGS[3]);
                } else {
                    fragmentTransaction.show(translateFragmentFour);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 设置为默认颜色
     */
    private void clearTabSelection() {
        tab_text1.setTextColor(gray);
        tab_text2.setTextColor(gray);
        tab_text3.setTextColor(gray);
        tab_text4.setTextColor(gray);
    }

    /**
     * 默认隐藏所有的fragment
     *
     * @param fragmentTransaction
     */

    private void hideFragement(FragmentTransaction fragmentTransaction) {
        fragmentManager.popBackStack();
        if (translateFragmentOne != null) {
            fragmentTransaction.hide(translateFragmentOne);
        }
        if (translateFragmentTwo != null) {
            fragmentTransaction.hide(translateFragmentTwo);
        }
        if (translateFragmentThree != null) {
            fragmentTransaction.hide(translateFragmentThree);
        }
        if (translateFragmentFour != null) {
            fragmentTransaction.hide(translateFragmentFour);
        }
    }


}
