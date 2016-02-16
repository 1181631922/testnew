package com.fanyafeng.testnew.testFloatingActionButton;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testslide.ScreenUtils;
import com.fanyafeng.testnew.util.FitScreenUtil;

public class TestFloatingActionButtonActivity extends BaseActivity implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private GestureDetector gestureDetector;
    private Scroller scroller;
    private FloatingActionButton fab;
    private int fabPaddingBottom = 0, fabHeight = 0, fabPaddingRight = 0, fabWidth = 0;
    private LinearLayout layout_fab;
    private TextView tv_fab_test;
    private ImageView iv_fab_test;
    private Animation translate_toptobottom, translate_bottomtotop;
    private boolean isTop = true, isAnimator = false;
    private float myTextSize = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_floating_action_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isShowEmail = true;
        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        gestureDetector = new GestureDetector(this, this);
        initView();
        initData();
    }

    private void initView() {
//        fab.getHeight();
        fab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (fab.getHeight() == 0)
                    return;
//                System.out.println(fab.getHeight());
//                Toast.makeText(TestFloatingActionButtonActivity.this, "fab的高度：" + fab.getHeight(), Toast.LENGTH_SHORT).show();
//                int[] fabLoaction = new int[2];
//                fab.getLocationOnScreen(fabLoaction);
//                Toast.makeText(TestFloatingActionButtonActivity.this, "fab距离x位置" + fabLoaction[0] + "fab距离y位置" + fabLoaction[1], Toast.LENGTH_SHORT).show();
//                Toast.makeText(TestFloatingActionButtonActivity.this,"fab距离底部的位置"+fab.getBottom(),Toast.LENGTH_SHORT).show();
//                System.out.println(fabLoaction[0] + "|" + fabLoaction[1]);
//                System.out.println(fab.getBottom());
                fabPaddingBottom = fab.getPaddingBottom();
                fabPaddingRight = fab.getPaddingRight();
                fabWidth = fab.getWidth();
                fabHeight = fab.getHeight();
                System.out.println(fab.getPaddingBottom());
                fab.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        layout_fab = (LinearLayout) findViewById(R.id.layout_fab);
//        layout_fab.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                System.out.println("linear上下滑动的距离scrollY："+scrollY);
//                System.out.println("linear上下滑动的旧的距离oldScrollY："+oldScrollY);
//            }
//        });
        layout_fab.setOnTouchListener(this);
        translate_toptobottom = AnimationUtils.loadAnimation(this, R.anim.translate_toptobottom);
        translate_bottomtotop = AnimationUtils.loadAnimation(this, R.anim.translate_bottomtotop);
        tv_fab_test = (TextView) findViewById(R.id.tv_fab_test);
        iv_fab_test = (ImageView) findViewById(R.id.iv_fab_test);
        iv_fab_test.setImageResource(R.drawable.xg_icon);
    }

    private void initData() {

        scroller = new Scroller(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        大于零为向上滑动
//        if (e1.getY() - e2.getY() > 60) {
//            if (isTop = true) {
//                fromLeft2Right(fab, 0, 2 * fabHeight + fabPaddingBottom, 500);
//                isTop = false;
//            }
//        } else if (e2.getY() - e1.getY() > 60) {
//            if (isTop = false) {
//                fromLeft2Right(fab, 2 * fabHeight + fabPaddingBottom, 0, 2000);
//                isTop = true;
//            }
//        }

        if (e1.getY() - e2.getY() > 0) {
            if (myTextSize > 60) {

            } else {
                myTextSize++;
            }
            tv_fab_test.setTextSize(myTextSize);
            FitScreenUtil.FixScreenXY(iv_fab_test, (int) myTextSize * 10, (int) myTextSize * 10);
        } else if (e1.getY() - e2.getY() < 0) {
            if (myTextSize < 1) {
            } else {
                myTextSize--;
            }
            tv_fab_test.setTextSize(myTextSize);
            FitScreenUtil.FixScreenXY(iv_fab_test, (int) myTextSize * 10, (int) myTextSize * 10);
        }


        if (e1.getY() - e2.getY() > 60 && isAnimator == false && isTop == true && fab.isShown()) {
            fromLeft2Right(fab, 0, 2 * fabHeight + fabPaddingBottom, 300, true);
        } else if (e2.getY() - e1.getY() > 60 && isAnimator == false && isTop == false) {
            fromLeft2Right(fab, 0, 2 * fabHeight + fabPaddingBottom, 300, false);
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        System.out.println(e2.getY() - e1.getY());
        return false;
    }

    /**
     * case 0:
     //加速进入
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.accelerate_interpolator));
     break;
     case 1:
     //减速进入
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.decelerate_interpolator));
     break;
     case 2:
     //加速进入.与第一个的区别为当repeatMode为reverse时,仍为加速返回原点
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.accelerate_decelerate_interpolator));
     break;
     case 3:
     //先往后退一点再加速前进
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.anticipate_interpolator));
     break;
     case 4:
     //减速前进,冲过终点前再后退
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.overshoot_interpolator));
     break;
     case 5:
     //case 3,4的结合体
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.anticipate_overshoot_interpolator));
     break;
     case 6:
     //停止前来回振几下
     a.setInterpolator(AnimationUtils.loadInterpolator(this,
     android.R.anim.bounce_interpolator));
     break;
     * @param view
     * @param from
     * @param to
     * @param duration
     * @param top
     */

    private void fromLeft2Right(final View view, float from, float to, long duration, boolean top) {
        if (view == null)
            return;
        ValueAnimator valueAnimator = null;
        if (top) {
            valueAnimator = ValueAnimator.ofFloat(from, to);
            isTop = false;
        } else {
            valueAnimator = ValueAnimator.ofFloat(to, from);
            isTop = true;
        }
        valueAnimator.setDuration(duration);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setInterpolator(new AccelerateInterpolator(2f));
//        valueAnimator.setInterpolator(new BounceInterpolator());
//        仿钉钉
//        valueAnimator.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.overshoot_interpolator));
        valueAnimator.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.anticipate_overshoot_interpolator));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
//                view.setTranslationX(x);
                view.setTranslationY(y);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimator = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimator = false;
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


}
