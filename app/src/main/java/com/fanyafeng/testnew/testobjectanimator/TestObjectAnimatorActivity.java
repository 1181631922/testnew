package com.fanyafeng.testnew.testobjectanimator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

import java.io.FileInputStream;

public class TestObjectAnimatorActivity extends BaseActivity {

    private ImageView text_obj_circle;
    private Button btn_obj_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_object_animator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试ObjectAnimator动画";
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
        text_obj_circle = (ImageView) findViewById(R.id.text_obj_circle);
        btn_obj_start = (Button) findViewById(R.id.btn_obj_start);
        btn_obj_start.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_obj_start:
                startObjAnim(text_obj_circle);
                break;
        }
    }

    private void startObjAnim(View view) {
        if (view == null)
            return;
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 100);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 1f);
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 730);
        PropertyValuesHolder pvhsX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 2, 1);
        PropertyValuesHolder pvhsY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 2, 1);
        PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 1);
        final ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhR, pvhsX, pvhsY, pvhA);
        animation.setDuration(2000);
        animation.setInterpolator(new OvershootInterpolator(0.9f));
        animation.start();
    }
}
