package com.fanyafeng.testnew.webjs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class WebviewActivity extends BaseActivity {
    private WebView wv_test_js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试js调用webview";
        isShowEmail = true;
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
        wv_test_js = (WebView) findViewById(R.id.wv_test_js);
    }

    private void initData() {
        wv_test_js.getSettings().setUseWideViewPort(true);
        wv_test_js.getSettings().setLoadWithOverviewMode(true);
        wv_test_js.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        wv_test_js.getSettings().setJavaScriptEnabled(true);
//        wv_test_js.loadUrl("file:///android_asset/first.html");
        wv_test_js.loadUrl("http://video.mtime.com/58382/?mid=225804");
    }

}
