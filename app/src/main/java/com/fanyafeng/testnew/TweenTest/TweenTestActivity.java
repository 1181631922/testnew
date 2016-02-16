package com.fanyafeng.testnew.TweenTest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class TweenTestActivity extends BaseActivity {
    private TextView text_smalltobig;
    private Button btn_smalltobig, btn_alphaanim, btn_rotateanim, btn_translateanim, btn_setanim, btn_scaleanim;
    private Animation smallToBig, alphaanim, rotateanim, scaleanim, setanim, translateanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_test);
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
        title = "测试tween动画";
        initView();
        initData();
    }

    private void initView() {
        text_smalltobig = (TextView) findViewById(R.id.text_smalltobig);
        btn_smalltobig = (Button) findViewById(R.id.btn_smalltobig);
        btn_smalltobig.setOnClickListener(this);
        smallToBig = AnimationUtils.loadAnimation(this, R.anim.small_to_big);

        btn_alphaanim = (Button) findViewById(R.id.btn_alphaanim);
        btn_alphaanim.setOnClickListener(this);
        alphaanim = AnimationUtils.loadAnimation(this, R.anim.alphaanim);

        btn_rotateanim = (Button) findViewById(R.id.btn_rotateanim);
        btn_rotateanim.setOnClickListener(this);
        rotateanim = AnimationUtils.loadAnimation(this, R.anim.tutorail_rotate);

        btn_translateanim = (Button) findViewById(R.id.btn_translateanim);
        btn_translateanim.setOnClickListener(this);
        scaleanim = AnimationUtils.loadAnimation(this, R.anim.scaleanim);

        btn_setanim = (Button) findViewById(R.id.btn_setanim);
        btn_setanim.setOnClickListener(this);
        setanim = AnimationUtils.loadAnimation(this, R.anim.setanim);

        btn_scaleanim = (Button) findViewById(R.id.btn_scaleanim);
        btn_scaleanim.setOnClickListener(this);
        translateanim = AnimationUtils.loadAnimation(this, R.anim.translateanim);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_smalltobig:
                text_smalltobig.startAnimation(smallToBig);
                break;
            case R.id.btn_alphaanim:
                text_smalltobig.startAnimation(alphaanim);
                break;
            case R.id.btn_rotateanim:
                text_smalltobig.startAnimation(rotateanim);
                break;
            case R.id.btn_translateanim:
                text_smalltobig.startAnimation(scaleanim);
                break;
            case R.id.btn_setanim:
                text_smalltobig.startAnimation(setanim);
                break;
//            平移调节
            case R.id.btn_scaleanim:
                text_smalltobig.startAnimation(translateanim);
                break;
        }
    }
}
