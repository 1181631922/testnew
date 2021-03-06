package com.fanyafeng.testnew.testnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class NotifiBaseActivity extends BaseActivity {
private String activityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "跳转过度页面";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
//        initData();
    }

    private void initView(){

    }

    private void initData(){
        activityId=getIntent().getStringExtra("activityId");
        Intent intent=new Intent();
        switch (getIntent().getExtras().getString("activityId")){
            case "TestTaskActivity":
                intent.setClass(this,NotifiOneActivity.class);
                intent.putExtras(getIntent().getExtras());
            break;
        }
        startActivity(intent);
    }

}
