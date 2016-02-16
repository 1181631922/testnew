package com.fanyafeng.testnew.FrascoOkhttp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.MyApplication.FrescoConfig;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.util.MyUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SingleImageActivity extends BaseActivity {
    private LinearLayout layout_single_image;
    private String imageUri = "http://www.apkbus.com/data/attachment/forum/201402/27/154958qgczo5a17ia3u3c4.png";
    private SimpleDraweeView image_fresco_one, image_fresco_1, image_fresco_2, image_fresco_3, image_fresco_4, image_fresco_5, image_fresco_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试单张图片效果";
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
        layout_single_image = (LinearLayout) findViewById(R.id.layout_single_image);
//        图片默认从中间截取
        image_fresco_one = (SimpleDraweeView) findViewById(R.id.image_fresco_one);
        image_fresco_one.setImageURI(Uri.parse(imageUri));
        image_fresco_1 = (SimpleDraweeView) findViewById(R.id.image_fresco_1);
        image_fresco_1.setImageURI(Uri.parse(imageUri));
        image_fresco_2 = (SimpleDraweeView) findViewById(R.id.image_fresco_2);
        image_fresco_2.setImageURI(Uri.parse(imageUri));
        image_fresco_3 = (SimpleDraweeView) findViewById(R.id.image_fresco_3);
        image_fresco_3.setImageURI(Uri.parse("http://static.shanxiu365.com/images/activity/128/568_AegKf.jpg"));
//        高清巨图加载不出来
        image_fresco_5 = (SimpleDraweeView) findViewById(R.id.image_fresco_5);
        image_fresco_5.setImageURI(Uri.parse("https://upload.wikimedia.org/wikipedia/commons/3/33/Physical_Political_World_Map.jpg"));
        image_fresco_4 = (SimpleDraweeView) findViewById(R.id.image_fresco_4);
        ImageRequest imageRequest = FrescoConfig.getDraweeViewImageRequest(image_fresco_4, imageUri);
        DraweeController draweeController = FrescoConfig.getSimpleDraweeController(imageRequest, image_fresco_4);
        image_fresco_4.setController(draweeController);

        image_fresco_6 = (SimpleDraweeView) findViewById(R.id.image_fresco_6);
        image_fresco_6.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.image_fresco_6:
                getWellCompressImage("/sdcard/bluetooth/"+File.separator+"20120101_083225.jpg");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
//            imgUri = data.getData();
//            加载本地图片
            Log.d("TAG",data.getData().toString());
//            getWellCompressImage(Environment.getExternalStorageState()+File.separator+"20120101_083218.jpg");
            image_fresco_6.setImageURI(data.getData());


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static Bitmap getWellCompressImage(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);

        int height = options.outHeight;
        int width = options.outWidth;
        int reqHeight = 1280;
        int reqWidth = 720;

        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round((float) height / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);
            options.inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        options.inJustDecodeBounds = false;
        Long bitname = System.currentTimeMillis() / 1000;
        saveMyBitmap(bitname.toString(), BitmapFactory.decodeFile(imagePath, options));
        return BitmapFactory.decodeFile(imagePath, options);
    }

    public static void saveMyBitmap(String bitName, Bitmap mBitmap) {
        File file = new File("/sdcard/prayer/" + bitName + ".png");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.d("TAG", "在保存图片时出错：" + e.toString());
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        try {
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void myselfTest() {
        Fresco.shutDown();


        Fresco.getImagePipeline().clearDiskCaches();
        Fresco.getImagePipeline().clearMemoryCaches();
        Fresco.getImagePipeline().clearCaches();
//重载 （封装，继承，多态）
//        Fresco.getImagePipeline().evictFromDiskCache(new ImageRequest());
//        Fresco.getImagePipeline().evictFromDiskCache(new Uri);
//        Fresco.getImagePipeline().evictFromMemoryCache(new Uri);
//        Fresco.getImagePipeline().evictFromCache(new Uri);
    }

    /**
     * TODO readme 关于一些常用问题
     * 1.框架加载的话一般都会有缓存，一般都会想到三级缓存，网络，内存，硬盘，（bitmap缓存）
     *  那么开发者肯定会想自己实时的去控制这三个缓存，那么fresco作为图片加载框架的话根据什么去寻找相应的图片
     *  可以根据list的键值对形式去对应，相应的key便是网络地址，那么网络地址相同所对应的图片资源一定相同，所以value可以理解为图片资源
     *  那么首先，为了有效方式oom，最起码的就是如果一个activity不用的话，对于程序员来说，便是希望它所用到的资源都去进行释放
     *  再有就是如果手机内存告急一般都会调用相应的onlowmemory周期，这算是发出一个警告吧，那么，这时候必须去清除一些没用的缓存（内存中的）
     *  最后便是程序员必须遵守的，我用了用户多少硬盘的资源，用户想清理的话都可以进行清理，便是清除硬盘的资源
     *  ---可能会有疏漏
     *
     *  fresco这些都考虑到了，首先最简单的，清除硬盘的缓存
     *   Fresco.getImagePipeline().clearDiskCaches();
     *
     *  然后是内存资源
     *  Fresco.getImagePipeline().clearMemoryCaches();
     *
     *  内存和硬盘资源
     *  Fresco.getImagePipeline().clearCaches();
     *
     * 2.这时候有可能会有出去某个图片的缓存的情况
     *
     *  fresco这些也都考虑到了，首先最简单的，清除硬盘的缓存
     *  Fresco.getImagePipeline().evictFromDiskCache(new ImageRequest());
     *  Fresco.getImagePipeline().evictFromDiskCache(new Uri);
     *
     *  然后是内存资源
     *  Fresco.getImagePipeline().evictFromMemoryCache(new Uri);
     *
     *  内存和硬盘资源
     *  Fresco.getImagePipeline().evictFromCache(new Uri);
     *
     *
     */


}
