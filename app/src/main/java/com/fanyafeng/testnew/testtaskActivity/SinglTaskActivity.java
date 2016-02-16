package com.fanyafeng.testnew.testtaskActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.R;

public class SinglTaskActivity extends ABaseActivity {
    private final static String TAG = "SinglTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_singl_task);
        super.initView();
        isShowEmail = true;
        title = "测试SinglTask启动模式";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(SinglTaskActivity.this, SinglTaskActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(this, TAG + "onCreate周期", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, TAG + "onNewIntent周期", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, TAG + "onDestory周期", Toast.LENGTH_SHORT).show();
    }
}
