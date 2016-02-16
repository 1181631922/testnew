package com.fanyafeng.testnew.testfragmentdialog;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class FloatDialogActivity extends BaseActivity {
    private FloatingActionButton fab;
    private Animation rotateanim, rotateanim_back;
    private boolean isRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_dialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "钉钉首页";
        isShowEmail = true;
        initView();
        initData();
    }

    private void initView() {
        rotateanim = AnimationUtils.loadAnimation(this, R.anim.float_rotate);
        rotateanim_back = AnimationUtils.loadAnimation(this, R.anim.float_rotate_back);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fab:
                if (isRotate) {
                    isRotate = !isRotate;
                    fab.startAnimation(rotateanim_back);
                } else {
                    isRotate = !isRotate;
                    fab.startAnimation(rotateanim);
                }
                FragmentManager fragmentManager =getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                FloatFragment floatFragment=new FloatFragment();
                fragmentTransaction.add(R.id.float_container,floatFragment);
                fragmentTransaction.addToBackStack("floatFragment");
                fragmentTransaction.commit();

                break;
        }
    }
}
