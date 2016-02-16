package com.fanyafeng.testnew.testactivitytranslate;

import android.app.ActivityOptions;
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

public class FromActivity extends BaseActivity {
    private ImageView iv_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试activity跳转动画";
        isShowEmail = true;

        initView();
        initData();
    }

    private void initView() {
        iv_from = (ImageView) findViewById(R.id.iv_from);
        iv_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToActivity.lauch(FromActivity.this, v, R.mipmap.img_1);
            }
        });
    }

    private void initData() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(FromActivity.this, ToActivity.class);
//                startActivity(intent);
//                iv_from.setImageResource(getIntent().getIntExtra("resId", R.mipmap.img_1));
//                ViewCompat.setTransitionName(iv_from, "image");
//                ToActivity.lauch(FromActivity.this, view, R.mipmap.img_1);

            }
        });
    }


}
