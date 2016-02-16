package com.fanyafeng.testnew.testnavigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by fanyafeng on 2015/10/28,0028.
 */
public class CircleImageView extends ImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getHeight() == 0 || getWidth() == 0) {
            return;
        }

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth();
        Bitmap circleBitmap = getCroppedBitmap(mBitmap, w);
        canvas.drawBitmap(circleBitmap, 0, 0, null);
    }

    public static Bitmap getCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap mBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius) {
            mBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        } else {
            mBitmap = bitmap;
        }

        Bitmap outPut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outPut);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(mBitmap.getWidth() / 2 + 0.7f, mBitmap.getHeight() / 2 + 0.7f, mBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, rect, rect, paint);
        return outPut;
    }
}
