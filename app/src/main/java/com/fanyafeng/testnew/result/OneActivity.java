package com.fanyafeng.testnew.result;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testnotification.TestTaskActivity;

public class OneActivity extends BaseActivity {
    private Button btn_one_result, btn_one_start_result;
    private TextView tv_one_refresh;
    private static int REQUEST_RESULT = 0;
    private RefreshReceiver refreshReceiver = new RefreshReceiver();
    FloatingActionButton fab;
    private LinearLayout layout_circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "第一个activity";
        isShowEmail=true;
         fab = (FloatingActionButton) findViewById(R.id.fab);
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
        btn_one_result = (Button) findViewById(R.id.btn_one_result);
        btn_one_result.setOnClickListener(this);
        btn_one_start_result = (Button) findViewById(R.id.btn_one_start_result);
        btn_one_start_result.setOnClickListener(this);
        tv_one_refresh = (TextView) findViewById(R.id.tv_one_refresh);
        tv_one_refresh.setText("刷新失败");
        layout_circle=(LinearLayout)findViewById(R.id.layout_circle);
        layout_circle.setVisibility(View.GONE);
    }


    private void initData() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CustomActions.REFRESH_ONE);
        registerReceiver(refreshReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_one_start_result:
                Intent intent = new Intent();
                intent.setClass(this, TwoActivity.class);
                startActivityForResult(intent, REQUEST_RESULT);
                break;
            case R.id.btn_one_result:
                tv_one_refresh.setText("用来显示刷新状态");
                layout_circle.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESULT && resultCode == RESULT_OK) {

        }
    }

    class RefreshReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("收到广播:" + intent.getAction());
            switch (intent.getAction()) {
                case CustomActions.REFRESH_ONE:
                    tv_one_refresh.setText("刷新成功");
                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()){
            unregisterReceiver(refreshReceiver);
        }
    }
}
