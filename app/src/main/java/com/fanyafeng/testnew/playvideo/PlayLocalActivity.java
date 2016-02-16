package com.fanyafeng.testnew.playvideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;

public class PlayLocalActivity extends BaseActivity {
    private VideoViewFullScreen vv_local;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉通知栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉 标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_local);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        title = "播放本地视频";
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
        vv_local = (VideoViewFullScreen) findViewById(R.id.vv_local);
    }

    private void initData() {
        vv_local = (VideoViewFullScreen) findViewById(R.id.vv_local);
        mediaController = new MediaController(this);
        vv_local.setMediaController(mediaController);
        //下面android.resource://是固定的，com.example.work是包名，R.raw.sw是你raw文件夹下的视频文件
        vv_local.setVideoURI(Uri.parse("android.resource://com.fanyafeng.testnew/" + R.raw.login_video));
        vv_local.start();
        vv_local.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
    }

}
