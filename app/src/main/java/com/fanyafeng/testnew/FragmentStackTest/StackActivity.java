package com.fanyafeng.testnew.FragmentStackTest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class StackActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_stack_one, btn_stack_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试fragment压入栈";
        toolbar.setVisibility(View.GONE);
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
        btn_stack_one = (Button) findViewById(R.id.btn_stack_one);
        btn_stack_one.setOnClickListener(this);
        btn_stack_two = (Button) findViewById(R.id.btn_stack_two);
        btn_stack_two.setOnClickListener(this);
    }

    private void initData() {


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_stack_one:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                查看源代码
                fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out,R.anim.push_left_in, R.anim.push_right_out);
                StackFragment stackFragment = new StackFragment();
                fragmentTransaction.add(R.id.layout_container, stackFragment, "one");
                fragmentTransaction.addToBackStack("one");
                fragmentTransaction.commit();
                break;
            case R.id.btn_stack_two:
//                FragmentManager fragmentManager1 = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
////                查看源代码
//                fragmentTransaction1.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out, R.anim.push_left_in, R.anim.push_right_out);
//                StackFragment stackFragment1 = new StackFragment();
//                fragmentTransaction1.add(R.id.layout_container, stackFragment1, "one1");
//                StackOneFragment stackFragment2 = new StackOneFragment();
//                fragmentTransaction1.add(R.id.layout_container, stackFragment2, "one2");
//                fragmentTransaction1.addToBackStack("one1");
//                fragmentTransaction1.addToBackStack("one2");
//                fragmentTransaction1.commit();

                FragmentManager fragmentManage = getSupportFragmentManager();
                FragmentTransaction fragmentTransactio = fragmentManage.beginTransaction();
//                查看源代码
                fragmentTransactio.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out,R.anim.push_left_in, R.anim.push_right_out);
                StackFragment stackFragmen = new StackFragment();
                fragmentTransactio.add(R.id.layout_container, stackFragmen, "one");
                fragmentTransactio.addToBackStack("one");
                fragmentTransactio.commit();

                FragmentManager fragmentManag = getSupportFragmentManager();
                FragmentTransaction fragmentTransacti = fragmentManag.beginTransaction();
//                查看源代码
                fragmentTransacti.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out,R.anim.push_left_in, R.anim.push_right_out);
                StackOneFragment stackFragme = new StackOneFragment();
                fragmentTransacti.add(R.id.layout_container, stackFragme, "one");
                fragmentTransacti.addToBackStack("one");
                fragmentTransacti.commit();
                break;
        }
    }
}
