package com.fanyafeng.testnew.PermissionTest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class FragmentNestActivity extends BaseActivity {
    private FrameLayout nest_container;
    private Button btn_add_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_nest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "activity嵌套fragment权限请求";
        isShowEmail = true;
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
        nest_container = (FrameLayout) findViewById(R.id.nest_container);
        btn_add_fragment=(Button)findViewById(R.id.btn_add_fragment);
        btn_add_fragment.setOnClickListener(this);
    }

    private void initData() {


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_add_fragment:
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_left_in,R.anim.push_right_out,R.anim.push_left_in,R.anim.push_right_out);
                NestFragment nestFragment=new NestFragment();
                fragmentTransaction.add(R.id.nest_container,nestFragment,"nestFragment");
                fragmentTransaction.addToBackStack("nestFragment");
                fragmentTransaction.commit();
                break;
        }
    }
}
