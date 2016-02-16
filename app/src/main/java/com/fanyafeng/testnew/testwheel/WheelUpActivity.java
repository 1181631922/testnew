package com.fanyafeng.testnew.testwheel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testwheel.wheelviews.ArrayWheelAdapter;
import com.fanyafeng.testnew.testwheel.wheelviews.OnWheelChangedListener;
import com.fanyafeng.testnew.testwheel.wheelviews.WheelView;

public class WheelUpActivity extends BaseActivity implements OnWheelChangedListener {
    private Button btn_testwheel;
    private WheelView id_username;
    private String username;
    String[] userNameList = new String[]{"一道长", "二道长", "三道长"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_up);
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
        title = "测试滚轮控件";
        initView();
        initData();
        setUpListener();
//        updateUsername();
        setUpData();
    }

    private void initView() {
        btn_testwheel = (Button) findViewById(R.id.btn_testwheel);
        btn_testwheel.setOnClickListener(this);
        id_username = (WheelView) findViewById(R.id.id_username);
    }

    private void initData() {

    }

    private void setUpData() {
        if (userNameList == null) {
            userNameList = new String[]{""};
        }
        id_username.setViewAdapter(new ArrayWheelAdapter<String>(this, userNameList));
        id_username.setCurrentItem(0);
        id_username.setVisibleItems(3);
        updateUsername();
    }

    private void setUpListener() {
        id_username.addChangingListener(this);
    }

    private void updateUsername() {
        int pCurrent = id_username.getCurrentItem();
        Toast.makeText(this, pCurrent + "", Toast.LENGTH_SHORT).show();
        Log.d("当前选中的item", pCurrent + "");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_testwheel:
                id_username.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == id_username) {
            updateUsername();
        }
    }
}
