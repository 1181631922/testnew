package com.fanyafeng.testnew.testactivitytranslate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class ToActivity extends BaseActivity {
    private ImageView iv_to;

    public static void lauch(AppCompatActivity appCompatActivity, View transitionView, int resId) {
        Intent intent = new Intent(appCompatActivity, ToActivity.class);
        intent.putExtra("resId", resId);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(appCompatActivity, transitionView, "image");
        ActivityCompat.startActivity(appCompatActivity, intent, activityOptionsCompat.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "跳转后的页面";
        isShowEmail = true;
        initView();
        initData();
    }

    private void initView() {
        iv_to = (ImageView) findViewById(R.id.iv_to);
        iv_to.setImageResource(getIntent().getIntExtra("resId", R.mipmap.img_1));
        ViewCompat.setTransitionName(iv_to, "image");
    }

    private void initData() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
