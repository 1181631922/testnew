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

public class TwoActivity extends BaseActivity {
    private Button btn_two_result, btn_two_start_result;
    private TextView tv_two_refresh;
    private static int REQUEST_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "第二个activity";
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
        btn_two_result = (Button) findViewById(R.id.btn_two_result);
        btn_two_result.setOnClickListener(this);
        btn_two_start_result = (Button) findViewById(R.id.btn_two_start_result);
        btn_two_start_result.setOnClickListener(this);
        tv_two_refresh = (TextView) findViewById(R.id.tv_two_refresh);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_two_result:
                break;
            case R.id.btn_two_start_result:
//                setResult(RESULT_OK);
                Intent intent = new Intent(this, ThreeActivity.class);
                startActivityForResult(intent, REQUEST_RESULT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESULT && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
        }
    }
}
