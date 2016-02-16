package com.fanyafeng.testnew.QQBrowser;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.MyUtils;

public class QQBrowserImageActivity extends BaseActivity implements View.OnTouchListener, MyScrollView.OnScrollListener,NestedScrollView.OnScrollChangeListener {
    private Toolbar toolbar;
    private CoordinatorLayout layout_coor;
//    private MyScrollView sv_qq_image;

    private NestedScrollView sv_qq_image;
    private LinearLayout layout_qq_image;
    private ImageView iv_qq_image;
    private static int imageHeight;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqbrowser_image);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.YELLOW);
        toolbar.setTitleTextColor(Color.RED);
        title = "上下滑动隐显图片和状态栏";
        isShowEmail = true;
        fab = (FloatingActionButton) findViewById(R.id.fab);


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        initView();
        initData();

    }

    private void initView() {
        layout_coor = (CoordinatorLayout) findViewById(R.id.layout_coor);
//        sv_qq_image = (MyScrollView) findViewById(R.id.sv_qq_image);
//        sv_qq_image.setOnTouchListener(this);
//        sv_qq_image.setOnScrollListener(this);
        sv_qq_image=(NestedScrollView)findViewById(R.id.sv_qq_image);
        sv_qq_image.setOnScrollChangeListener(this);
        layout_qq_image = (LinearLayout) findViewById(R.id.layout_qq_image);
        iv_qq_image = (ImageView) findViewById(R.id.iv_qq_image);
        iv_qq_image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (iv_qq_image.getHeight() == 0)
                    return;
                imageHeight = iv_qq_image.getHeight();
                System.out.println("得到的图片的高度：" + imageHeight);
                iv_qq_image.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });


    }

    private void initData() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_coor.startAnimation(AnimationUtils.loadAnimation(QQBrowserImageActivity.this, R.anim.setanim_qq));
            }
        });
//        sv_qq_image.set
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            sv_qq_image.getScaleY();
//            System.out.println("scrollview滑动的距离：" + sv_qq_image.getScaleY());
        }
        return false;
    }

    //相当于setOnScrollChangeListener可以兼容低版本
    @Override
    public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        System.out.println("手指上下滑动的距离scrollY:" + scrollY);
//        System.out.println("手指上下滑动的距离oldScrollY:" + oldScrollY);
        if (scrollY < imageHeight && scrollY > 0)
            toolbar.getBackground().setAlpha(255 - scrollY * 255 / imageHeight);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY < imageHeight && scrollY > 0)
            toolbar.getBackground().setAlpha(255 - scrollY * 255 / imageHeight);
    }
}
