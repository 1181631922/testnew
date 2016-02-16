package com.fanyafeng.testnew.testvalueanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testslide.ScreenUtils;

public class TestValueAnimatorActivity extends BaseActivity {
    private TextView text_circle;
    private Button btn_start;
    private Toolbar toolbar;
    private boolean isLeft = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_value_animator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试valueanimator动画";
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
        text_circle = (TextView) findViewById(R.id.text_circle);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }

    private void initData() {

    }

    private void fromLeft2Right(final View view, float from, float to, long duration, final double scale) {
        if (view == null)
            return;
        ValueAnimator valueAnimator;
        if (isLeft) {
            valueAnimator = ValueAnimator.ofFloat(from, to);
        } else {
            valueAnimator = ValueAnimator.ofFloat(to, from);
        }
        isLeft = !isLeft;
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                view.setTranslationX(x);
//                if (scale != 0) {
//                    view.setTranslationY((float) (x * scale));
                view.setTranslationY(x * (ScreenUtils.getScreenHeight(TestValueAnimatorActivity.this) - toolbar.getHeight()) /
                        ScreenUtils.getScreenWidth(TestValueAnimatorActivity.this));
//                }
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_start:
                fromLeft2Right(text_circle, 0, ScreenUtils.getScreenWidth(this) - text_circle.getWidth(), 600, (ScreenUtils.getScreenHeight(this) - ScreenUtils.getStatusHeight(this) - toolbar.getHeight()) / ScreenUtils.getScreenWidth(this));
                break;
        }
    }
}
