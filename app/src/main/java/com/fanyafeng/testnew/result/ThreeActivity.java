package com.fanyafeng.testnew.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class ThreeActivity extends BaseActivity {
    private Button btn_three_result, btn_three_start_result;
    private TextView tv_three_refresh;
    private static int REQUEST_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "第三个activity";
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
        btn_three_result = (Button) findViewById(R.id.btn_three_result);
        btn_three_result.setOnClickListener(this);
        btn_three_start_result = (Button) findViewById(R.id.btn_three_start_result);
        btn_three_start_result.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_three_start_result:
//                setResult(RESULT_OK);
                this.sendBroadcast(new Intent(CustomActions.REFRESH_ONE));
                System.out.println("发送广播");
                break;
        }
    }
}
