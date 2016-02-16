package com.fanyafeng.testnew.ViewPagerTest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.MyUtils;

import org.w3c.dom.Text;

import java.security.KeyPairGenerator;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity {
    private AutoScrollViewPager pager_autopager;
    private RadioGroup groupPoint;
    private LayoutInflater inflater = null;
    private List<String> picList = new ArrayList<>();
    private List<View> viewList = null;
    private View view = null;
    private static final int COUNT = 2;
    private TextView textView;
    private SimpleDraweeView pager_simpledraweeview;
//    private ScrollPagerAdapter scrollPagerAdapter = new ScrollPagerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试ViewPager";
        isShowEmail = true;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        KeyPairGenerator

        getViews();
        groupPoint = (RadioGroup) findViewById(R.id.groupPoint);
        addPointView(COUNT);
        initView();
        initData();

    }


    private void initView() {
        pager_autopager = (AutoScrollViewPager) findViewById(R.id.pager_autopager);
        ScrollPagerAdapter scrollPagerAdapter = new ScrollPagerAdapter(viewList);
        pager_autopager.setAdapter(scrollPagerAdapter);
        if (COUNT != 1) {
            pager_autopager.startAutoScroll();
            pager_autopager.setCycle(true);
            pager_autopager.setCurrentItem(300);
        } else {
            pager_autopager.stopAutoScroll();
            pager_autopager.setCurrentItem(0);
            pager_autopager.setCycle(false);
        }
        pager_autopager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                View child = groupPoint.getChildAt(position % viewList.size());
                if (child instanceof RadioButton) {
                    RadioButton radioButton = (RadioButton) child;
                    radioButton.setChecked(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
    }


    private void initData() {
    }

    private void getViews() {
        viewList = new ArrayList<>();
        if (COUNT != 2) {
            for (int i = 0; i < COUNT; i++) {
                view = inflater.inflate(R.layout.view_pager_layout, null);
                textView = (TextView) view.findViewById(R.id.pager_text);
                textView.setText("view pager Text" + i);
                pager_simpledraweeview = (SimpleDraweeView) view.findViewById(R.id.pager_simpledraweeview);
                pager_simpledraweeview.setImageURI(Uri.parse("http://static.shanxiu365.com/images/temple/1202/502_T1gOw.jpg"));
                viewList.add(view);
            }
        } else if (COUNT == 2) {
            for (int i = 0; i < COUNT * 2; i++) {
                view = inflater.inflate(R.layout.view_pager_layout, null);
                textView = (TextView) view.findViewById(R.id.pager_text);
                textView.setText("view pager Text" + i % 2);
                viewList.add(view);
            }
//            for (int i = 0; i < COUNT; i++) {
//                view = inflater.inflate(R.layout.view_pager_layout, null);
//                textView = (TextView) view.findViewById(R.id.pager_text);
//                textView.setText("view pager Text" + i);
//                viewList.add(view);
//            }
        }
    }

    private void addPointView(int count) {
        if (count > 0 && count != 1 && count != 2) {
            for (int i = 0; i < count; i++) {
                RadioButton radBtn = new RadioButton(this);
                radBtn.setClickable(false);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(25, RadioGroup.LayoutParams.WRAP_CONTENT);
                radBtn.setButtonDrawable(R.drawable.page_control_sel);
                groupPoint.addView(radBtn, params);
            }
        } else if (count == 2) {
            for (int i = 0; i < count * 2; i++) {
                RadioButton radBtn = new RadioButton(this);
                radBtn.setClickable(false);
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(25, RadioGroup.LayoutParams.WRAP_CONTENT);
                radBtn.setButtonDrawable(R.drawable.page_control_sel);
                groupPoint.addView(radBtn, params);
            }
        } else if (count == 1) {

        }

        View v = groupPoint.getChildAt(0);
        if (v != null) {
            RadioButton radioBtn = (RadioButton) v;
            radioBtn.setChecked(true);
        }
    }


}
