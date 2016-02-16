package com.fanyafeng.testnew.testswitch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class TestSwitchActivity extends BaseActivity {
    private View layout_switch;
    private TextView tv_left, tv_right;
//    Drawable shape;
    View test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_switch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试switch控件";
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

        test = findViewById(R.id.text);
    }

    private void initView() {
        layout_switch = findViewById(R.id.layout_switch);
        layout_switch.setOnClickListener(this);

        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);
//        shape = getResources().getDrawable(R.drawable.shape_select_box);
//        tv_left.setBackgroundDrawable(shape);
//        TextView tv = (TextView)findViewById( R.id.shapeTxtId );
//        tv.setBackgroundDrawable(shape);
    }

    private void initData() {

    }

    boolean isLeftSelect = true;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.layout_switch:
                float from, to;

                if (isLeftSelect) {
                    from = 0;
                    to = test.getWidth();
                } else {
                    to = 0;
                    from = test.getWidth();
                }

                ValueAnimator a = ValueAnimator.ofFloat(from, to);
                a.setDuration(200);
                a.setInterpolator(new LinearInterpolator());
                a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        test.setTranslationX(value);
                    }
                });
                a.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isLeftSelect = !isLeftSelect;

                        if (isLeftSelect) {
                            tv_right.setTextColor(getResources().getColor(R.color.test_text_color));
                            tv_left.setTextColor(Color.WHITE);
                        } else {
                            tv_left.setTextColor(getResources().getColor(R.color.test_text_color));
                            tv_right.setTextColor(Color.WHITE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                a.start();
                break;
        }
    }
}
