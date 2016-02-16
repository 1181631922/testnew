package com.fanyafeng.testnew.wechatrotate;

import android.os.Bundle;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.QQBrowser.MyLinearLayout;
import com.fanyafeng.testnew.QQBrowser.MyScrollView;
import com.fanyafeng.testnew.R;

public class WechatRotateActivity extends BaseActivity implements MyScrollView.OnScrollListener, MyLinearLayout.OnScrollListener,NestedScrollView.OnScrollChangeListener {
//    private MyScrollView sv_wechat;
    private NestedScrollView sv_wechat;
    private ImageView iv_wechat;
    private static int ivWechatHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_rotate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "图片上下消失";
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
//        sv_wechat = (MyScrollView) findViewById(R.id.sv_wechat);
//        sv_wechat.setOnScrollListener(this);
        sv_wechat = (NestedScrollView) findViewById(R.id.sv_wechat);
        sv_wechat.setOnScrollChangeListener(this);

        sv_wechat.bringToFront();
        iv_wechat = (ImageView) findViewById(R.id.iv_wechat);
        iv_wechat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (iv_wechat.getHeight() == 0)
                    return;
                ivWechatHeight = iv_wechat.getHeight();
                iv_wechat.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    private void initData() {
        sv_wechat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    System.out.println("移动。。。。。。");
                }
                return false;
            }
        });
    }

    @Override
    public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        System.out.println("scrollY滑动的长度：" + scrollY);
        System.out.println("oldScrollY滑动的长度：" + oldScrollY);
        if ((scrollY - oldScrollY > 0 || scrollY - oldScrollY == 0) && scrollY - oldScrollY < 5)
            if (scrollY > 0 && scrollY < ivWechatHeight) {
                ViewCompat.setTranslationY(iv_wechat, -scrollY / 2);
                ViewCompat.setTranslationY(sv_wechat, -scrollY);
            }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if ((scrollY - oldScrollY > 0 || scrollY - oldScrollY == 0) && scrollY - oldScrollY < 5)
            if (scrollY > 0 && scrollY < ivWechatHeight) {
                ViewCompat.setTranslationY(iv_wechat, -scrollY / 2);
                ViewCompat.setTranslationY(sv_wechat, -scrollY);
            }
    }
}
