package com.fanyafeng.testnew.FrascoOkhttp;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.FrascoOkhttp.PhotoView.PhotoViewActivity;
import com.fanyafeng.testnew.FrascoOkhttp.PhotoView.SubsamplingActivityActivity;
import com.fanyafeng.testnew.MyApplication.FrescoConfig;
import com.fanyafeng.testnew.MyApplication.PermissionControl;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.MyUtils;

import java.util.Arrays;
import java.util.List;

public class FrescoSampleActivity extends AppCompatActivity implements MyListView.OnScrollListener {

    private MyListView listview_frasco;
    private List<FrescoBean> frescoBeanList;
    private FrescoAdapter frescoAdapter;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private boolean isTop = true, isAnimator = false;
    private int newPosition, oldPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        首先需要初始化
//        Fresco.initialize(this);
        setContentView(R.layout.activity_frasco_sample);

        if (PermissionControl.isGetPermissionFor(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Fresco.initialize(this, FrescoConfig.getsImagePipelineConfig(this));
            initView();
            initData();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        title = "Fresco图片加载";
//        isShowEmail = true;
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                清除内存和硬盘所有缓存
                Fresco.getImagePipeline().clearCaches();
//                Fresco.getImagePipeline().clearMemoryCaches();清除内存缓存
//                Fresco.getImagePipeline().clearDiskCaches();清除硬盘缓存
                Snackbar.make(view, "清除缓存成功", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        initView();
//        initData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionControl.WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Fresco.initialize(this, FrescoConfig.getsImagePipelineConfig(this));
                    initView();
                    initData();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    private void initView() {
        listview_frasco = (MyListView) findViewById(R.id.listview_frasco);
        listview_frasco.setPadding(0, 0, 0, MyUtils.getNavigationBarHeight(this));
        listview_frasco.setOnScrollListener(this);
        listview_frasco.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldPosition = listview_frasco.getFirstVisiblePosition();
                        break;
                    case MotionEvent.ACTION_UP:
                        newPosition = listview_frasco.getFirstVisiblePosition();
                        if (newPosition > oldPosition && isTop && !isAnimator) {
                            System.out.println("用户向上滑动");
                            fromLeft2Right(fab, 40, 0, 300, true);
                        } else if (newPosition < oldPosition && !isTop && !isAnimator) {
                            fromLeft2Right(fab, 40, 0, 300, false);
                            System.out.println("用户向下滑动");
                        }
                        break;
                }
                return false;
            }
        });

    }

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
        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setInterpolator(new AccelerateInterpolator(2f));
//        valueAnimator.setInterpolator(new BounceInterpolator());
//        仿钉钉
//        valueAnimator.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.anim.overshoot_interpolator));
//        valueAnimator.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.anticipate_overshoot_interpolator));
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

    private void initData() {
        FrescoBean[] frescoBeanArray = new FrescoBean[]{
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142428.27146212_500.jpg"),
                new FrescoBean("https://www.google.com/logos/doodles/2015/81st-anniversary-of-the-loch-ness-monsters-most-famous-photograph-4847834381680640-hp.gif"),
                new FrescoBean("http://g.hiphotos.baidu.com/baike/w%3D268/sign=66d17ed667380cd7e61ea5eb9945ad14/e61190ef76c6a7ef18b42940fffaaf51f2de66c2.jpg"),
                new FrescoBean("http://s1.dwstatic.com/group1/M00/B7/A5/9e17c82bae2fc22df427b09ae317eaaa.gif"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142420.11265268_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142352.84233298_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142234.22690934_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142140.58842929_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142204.46977964_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142406.96541771_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142315.72377310_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142303.81804449_500.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142251.40035406_500.jpg"),
                new FrescoBean("http://img1.cache.netease.com/catchpic/6/65/65C2B02DEB31A82125E1E6C9E4021AC2.jpg"),
                new FrescoBean("http://img32.mtime.cn/up/2013/07/20/142329.63833494_500.jpg")

        };
        Arrays.sort(frescoBeanArray);
        frescoBeanList = Arrays.asList(frescoBeanArray);
        frescoAdapter = new FrescoAdapter(this, frescoBeanList);
        listview_frasco.setAdapter(frescoAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fresco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.test_0001:
                startActivity(new Intent(this, SingleImageActivity.class));
                break;
            case R.id.test_0002:
                startActivity(new Intent(this, PhotoViewActivity.class));
                break;
            case R.id.test_0003:
                startActivity(new Intent(this, SubsamplingActivityActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        System.out.println("用户滑动的距离scrollY:" + scrollY);
//        System.out.println("用户滑动的距离oldScrollY:" + oldScrollY);

    }


}
