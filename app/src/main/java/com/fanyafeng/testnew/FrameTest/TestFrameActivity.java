package com.fanyafeng.testnew.FrameTest;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class TestFrameActivity extends BaseActivity {
    private Button btn_startframe, btn_stopframe;
    private ImageView iv_testframe;
    private AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_frame);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        title = "测试frame工具";
        initView();
        initData();
    }

    private void initView() {
        btn_startframe = (Button) findViewById(R.id.btn_startframe);
        btn_startframe.setOnClickListener(this);
        btn_stopframe = (Button) findViewById(R.id.btn_stopframe);
        btn_stopframe.setOnClickListener(this);
        iv_testframe = (ImageView) findViewById(R.id.iv_testframe);

        frameAnimation = new AnimationDrawable();
        for (int i = 1; i < 13; i++) {
            int id = getResources().getIdentifier("loading_" + i, "mipmap", getPackageName());
            frameAnimation.addFrame(getResources().getDrawable(id, null), 100);
        }

//        设置循环播放   false表示循环  true表示不循环，仅播放一次
        frameAnimation.setOneShot(false);
        iv_testframe.setBackground(frameAnimation);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_startframe:
                frameAnimation.start();
                break;
            case R.id.btn_stopframe:
                frameAnimation.stop();
                break;
        }
    }
}
