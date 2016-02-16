package com.fanyafeng.testnew.util;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fanyafeng on 2015/11/6,0006.
 */
public class Utils {
//    压缩图片
    private static byte[] prepareWeixinThumb(Bitmap bmp) {

        int MAX_SIZE = 32000;

        int w = 150;
        int h = w * bmp.getHeight() / bmp.getWidth();

        byte[] result = null;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, w, h, true);

        int quality = 90;

        while (quality > 0) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            thumbBmp.compress(Bitmap.CompressFormat.JPEG, quality, output);

            result = output.toByteArray();
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result.length < MAX_SIZE) {
                break;
            }
            quality -= 10;
        }
        thumbBmp.recycle();
        return result;
    }

    public static int getExifOrientation(String path) {
        int d = 0;

        ExifInterface exif = null;

        try {
            exif = new ExifInterface(path);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        if (exif != null) {
            int or = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

            if (or != -1) {
                // We only recognize a subset of orientation tag values.
                switch (or) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        d = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        d = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        d = 270;
                        break;
                }
            }
        }
        return d;
    }

    public void saveMyBitmap(String bitName, Bitmap mBitmap) {
        File f = new File("/sdcard/" + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d("TAG", "在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    private boolean parseUri(String path) {
//
//        String newPath = path;
//
//        try {
//            Bitmap bitmap;
//
//            BitmapFactory.Options opts = new BitmapFactory.Options();
//            opts.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(path, opts);
//
//            opts.inSampleSize = ImageUtils.computeSampleSize(opts, -1, mScreenHeight * mScreenWidth);
//            opts.inJustDecodeBounds = false;
//            bitmap = BitmapFactory.decodeFile(path, opts);
//
//            int d = ImageUtils.getExifOrientation(path);
//
//            if (d != 0) {
//                Matrix matrix = new Matrix();
//
//                matrix.postRotate(d);
//                Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//
//                if (resizedBitmap != bitmap) {
//                    bitmap.recycle();
//                }
//
//                bitmap = resizedBitmap;
//
//                newPath = EDIR + "/coco/schedule/" + uuid + "_" + time;
//                ImageUtils.save(this, newPath, bitmap, 80);
//            }
//            if (bitmap != null) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
