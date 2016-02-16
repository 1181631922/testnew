package com.fanyafeng.testnew.ViewDragHelperTest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.FragmentStackTest.StackFragment;
import com.fanyafeng.testnew.R;

public class ViewDrawHelperActivity extends BaseActivity {
private Button btn_draw_add;
    private FrameLayout layout_draw_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_draw_helper);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "ViewDrawHelper";
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

    private void initView(){
        btn_draw_add=(Button)findViewById(R.id.btn_draw_add);
        btn_draw_add.setOnClickListener(this);
        layout_draw_container=(FrameLayout)findViewById(R.id.layout_draw_container);
    }
    private void initData(){}

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_draw_add:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out,R.anim.push_left_in, R.anim.push_right_out);
                DrawFragment drawFragment = new DrawFragment();
                fragmentTransaction.add(R.id.layout_draw_container, drawFragment, "one");
                fragmentTransaction.addToBackStack("one");
                fragmentTransaction.commit();
                break;
        }








    }
}
