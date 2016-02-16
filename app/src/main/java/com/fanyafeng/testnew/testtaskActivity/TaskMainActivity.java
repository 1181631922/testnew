package com.fanyafeng.testnew.testtaskActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.fanyafeng.testnew.ABaseActivity;
import com.fanyafeng.testnew.R;

public class TaskMainActivity extends ABaseActivity {
    private Button test_standard, test_singleTop, test_singleTask, test_singleInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_task_main);
        super.initView();
        title = "测试四种启动模式";
        test_standard = (Button) findViewById(R.id.test_standard);
        test_standard.setOnClickListener(this);
        test_singleTop = (Button) findViewById(R.id.test_singleTop);
        test_singleTop.setOnClickListener(this);
        test_singleTask = (Button) findViewById(R.id.test_singleTask);
        test_singleTask.setOnClickListener(this);
        test_singleInstance = (Button) findViewById(R.id.test_singleInstance);
        test_singleInstance.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.test_standard:
                Intent intent = new Intent(this, StandardActivity.class);
                startActivity(intent);
                break;
            case R.id.test_singleTop:
                Intent intent1 = new Intent(this, SingleTopActivity.class);
                startActivity(intent1);
                break;
            case R.id.test_singleTask:
                Intent intent2 = new Intent(this, SinglTaskActivity.class);
                startActivity(intent2);
                break;
            case R.id.test_singleInstance:
                Intent intent3 = new Intent(this, SingleInstanceActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
