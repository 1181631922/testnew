package com.fanyafeng.testnew.testtaskActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.R;

public class SingleTopActivity extends ABaseActivity {
    private TextView text_singletop_number;
    private int i = 0;
    private final static String TAG = "SingleTopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_single_top);
        super.initView();
        title = "SingleTop测试";
        isShowEmail = true;
        text_singletop_number = (TextView) findViewById(R.id.text_singletop_number);
        text_singletop_number.setText("显示的数字" + ++i);
        text_singletop_number.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(SingleTopActivity.this, SingleTopActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(this, TAG + "onCreate周期", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        super.initData();
//        if (getIntent().getStringExtra("isclear").equals("yes")){
//
//        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, TAG + "onNewIntent周期", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onNewIntent生命周期");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, TAG + "onDestory周期", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_singletop_number:
                startActivity(new Intent(this, StandardActivity.class));
                break;
        }
    }
}
