package com.fanyafeng.testnew.QQBrowser;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testfragmentdialog.DpPxConvert;

public class QQBrowserActivity extends BaseActivity {
    private TextView tv_qq_back, tv_qq_forward, tv_qq_menu, tv_qq_homepage, tv_qq_other;
    private Button btn_pop;
    private PopupWindow popupWindow;
    private RelativeLayout layout_father_qqbrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqbrowser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试qq浏览器页面";
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
        showPopWindow();
    }

    private void initView() {
        layout_father_qqbrowser = (RelativeLayout) findViewById(R.id.layout_father_qqbrowser);

        tv_qq_back = (TextView) findViewById(R.id.tv_qq_back);
        tv_qq_back.setOnClickListener(this);
        tv_qq_forward = (TextView) findViewById(R.id.tv_qq_forward);
        tv_qq_forward.setOnClickListener(this);
        tv_qq_menu = (TextView) findViewById(R.id.tv_qq_menu);
        tv_qq_menu.setOnClickListener(this);
        tv_qq_homepage = (TextView) findViewById(R.id.tv_qq_homepage);
        tv_qq_homepage.setOnClickListener(this);
        tv_qq_other = (TextView) findViewById(R.id.tv_qq_other);
        tv_qq_other.setOnClickListener(this);
        btn_pop = (Button) findViewById(R.id.btn_pop);
        btn_pop.setOnClickListener(this);
    }

    private void initData() {
    }

    private void showPopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.item_qq_popwindow_layout, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
//        handler.sendEmptyMessageDelayed(0, 1000);
//        popupWindow.showAtLocation(layout_father_qqbrowser, Gravity.BOTTOM, 0, (int) DpPxConvert.dip2px(this, 50));
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
//                    popupWindow.showAtLocation(layout_father_qqbrowser, Gravity.BOTTOM, 0, (int) DpPxConvert.dip2px(QQBrowserActivity.this, 50));
//                    popupWindow.update();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_qq_back:
                break;
            case R.id.tv_qq_forward:
                break;
            case R.id.tv_qq_menu:
                popupWindow.setAnimationStyle(R.style.popupAnimation);
                if (Build.VERSION.SDK_INT > 20) {
                    popupWindow.showAtLocation(layout_father_qqbrowser, Gravity.BOTTOM, 0, (int) DpPxConvert.dip2px(QQBrowserActivity.this, 50) + getNavigationBarHeight());
                } else {
                    popupWindow.showAtLocation(layout_father_qqbrowser, Gravity.BOTTOM, 0, (int) DpPxConvert.dip2px(QQBrowserActivity.this, 50));
                }
                popupWindow.showAsDropDown(v);
                break;
            case R.id.tv_qq_homepage:
                break;
            case R.id.tv_qq_other:
                break;
            case R.id.btn_pop:
//                popupWindow.showAtLocation(layout_father_qqbrowser, Gravity.NO_GRAVITY, 0, 0);
//                popupWindow.showAsDropDown(v);
                break;
        }
    }

    //    获取虚拟按键高度
    private int getNavigationBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    //    获取状态栏高度
    private int getStatusBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }


}
