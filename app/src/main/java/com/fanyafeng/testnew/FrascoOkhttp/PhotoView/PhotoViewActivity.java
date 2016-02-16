package com.fanyafeng.testnew.FrascoOkhttp.PhotoView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.FrascoOkhttp.FrescoBean;
import com.fanyafeng.testnew.FrascoOkhttp.PhotoView.util.fresco.photoview.HackyViewPager;
import com.fanyafeng.testnew.FrascoOkhttp.PhotoView.util.fresco.photoview.PhotoView;
import com.fanyafeng.testnew.R;

import java.util.Arrays;
import java.util.List;

public class PhotoViewActivity extends BaseActivity {
    private static final String ISLOCKED_ARG = "isLocked";
    private HackyViewPager hackyviewpager;
    private int position;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试图片浏览";
        isShowEmail = true;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "当前图片位置" + position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();

        if (getIntent() != null) {
            position = getIntent().getIntExtra("position", 0);
            System.out.println("获得的当前的图片位置：" + position);
            hackyviewpager.setCurrentItem(position);
        }

        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG, false);
            hackyviewpager.setLocked(isLocked);
        }


    }


    private void initView() {
        initData();
        hackyviewpager = (HackyViewPager) findViewById(R.id.hackyviewpager);
        hackyviewpager.setAdapter(new SamplePagerAdapter(dataList));
        hackyviewpager.setCurrentItem(0);
        hackyviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        String[] frescoBeanArray = new String[]{
                "http://pic2.ooopic.com/01/03/51/25b1OOOPIC19.jpg",
                "http://img3.imgtn.bdimg.com/it/u=1183223528,3058066243&fm=21&gp=0.jpg",
                "http://baike.soso.com/p/20090711/20090711101754-314944703.jpg",
                "http://img2.3lian.com/img2007/19/33/005.jpg",
                "http://pica.nipic.com/2007-11-09/200711912453162_2.jpg",
                "http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg",
                "http://pica.nipic.com/2008-03-19/2008319183523380_2.jpg",
                "http://pic28.nipic.com/20130402/9252150_190139450381_2.jpg",
                "http://imgk.zol.com.cn/dcbbs/2342/a2341460.jpg",
                "http://img2.3lian.com/img2007/10/28/123.jpg",
                "http://pic.nipic.com/2007-11-09/200711912230489_2.jpg",
                "http://imgsrc.baidu.com/forum/pic/item/3ac79f3df8dcd1004e9102b8728b4710b9122f1e.jpg",
                "http://pic1.nipic.com/2008-08-12/200881211331729_2.jpg",
                "http://img4.3lian.com/sucai/img6/230/29.jpg",
                "http://pic9.nipic.com/20100812/3289547_144304019987_2.jpg",
                "http://img3.imgtn.bdimg.com/it/u=1284416964,1606791513&fm=21&gp=0.jpg",
                "http://a2.att.hudong.com/38/59/300001054794129041591416974.jpg",
                "http://pic1a.nipic.com/2008-11-26/200811268173650_2.jpg"
        };
        Arrays.sort(frescoBeanArray);
        dataList = Arrays.asList(frescoBeanArray);
    }

    static class SamplePagerAdapter extends PagerAdapter {

        List<String> list;

        public SamplePagerAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageUri(list.get(position));

            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private boolean isViewPagerActive() {
        return (hackyviewpager != null && hackyviewpager instanceof HackyViewPager);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(ISLOCKED_ARG, ((HackyViewPager) hackyviewpager).isLocked());
        }
        super.onSaveInstanceState(outState);
    }

}
