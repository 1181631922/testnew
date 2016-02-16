package com.fanyafeng.testnew.QQBrowser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by fanyafeng on 2015/12/16,0016.
 */
public class MyScrollView extends ScrollView {
    private OnScrollListener onScrollListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.onScrollListener = scrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.OnScroll(l, t, oldl, oldt);
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
    }

    public interface OnScrollListener {
        public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }



}
