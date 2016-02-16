package com.fanyafeng.testnew.FrascoOkhttp.PhotoView;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.FrascoOkhttp.PhotoView.util.fresco.subscaleview.SubsamplingScaleImageView;
import com.fanyafeng.testnew.R;

public class SubsamplingActivityActivity extends BaseActivity {
private SubsamplingScaleImageView sub_sca_imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsampling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "加载超大图片";
        isShowEmail=true;
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

    private void initView(){
        sub_sca_imageview=(SubsamplingScaleImageView)findViewById(R.id.sub_sca_imageview);
    }
    private void initData(){
        sub_sca_imageview.setImageUri("https://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg");
//        sub_sca_imageview.setImageUri("http://g.hiphotos.baidu.com/zhidao/pic/item/a71ea8d3fd1f41344103eb57261f95cad1c85ee7.jpg");
    }

}
