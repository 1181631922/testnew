package com.fanyafeng.testnew.playvideo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by fanyafeng on 2015/12/2,0002.
 */
public class VideoViewFullScreen extends VideoView {

    public VideoViewFullScreen(Context context) {
        super(context);
    }

    public VideoViewFullScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoViewFullScreen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
        MediaController mediaController = new MediaController(getContext());
        mediaController.setVisibility(GONE);
        this.setMediaController(mediaController);
    }
}
