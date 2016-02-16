package com.fanyafeng.testnew.blurImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.FastBlur;
import com.fanyafeng.testnew.util.MyUtils;

public class BlurActivity extends BaseActivity {
private ImageView iv_blur;
//    private TextView btn_needtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试磨砂效果";
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
        iv_blur=(ImageView)findViewById(R.id.iv_blur);
//        btn_needtime=(TextView)findViewById(R.id.btn_needtime);
    }

    private void blur(Bitmap bitmap,View view){
        long startMs=System.currentTimeMillis();
        float scaleFactor =8;
        float radius=2;

        Bitmap overlay=Bitmap.createBitmap((int)(view.getMeasuredWidth()/scaleFactor),(int)(view.getMeasuredHeight()/scaleFactor),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint=new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        overlay= FastBlur.doBlur(overlay,(int)radius,true);
        view.setBackground(new BitmapDrawable(getResources(),overlay));
        System.out.println(System.currentTimeMillis() - startMs + "ms");
//        btn_needtime.setText(System.currentTimeMillis() - startMs + "ms");
    }

    private void initData() {
        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.img_1);
        iv_blur.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                blur(bitmap, iv_blur);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        final Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.img_1);
//        iv_blur.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                blur(bitmap, iv_blur);
//                iv_blur.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//            }
//        });
    }
}
