package com.fanyafeng.testnew.zhongkuohao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanyafeng on 2015/11/30,0030.
 */
public class zhongkuohao extends View {
    Paint paint;
    int colcor;
    int strokeWidth;
    boolean isLeft = true;

    public zhongkuohao(Context context) {
        super(context);

    }

    public zhongkuohao(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColor(int color) {
        this.colcor = color;
        paint.setColor(color);
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        paint.setStrokeWidth(strokeWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);


//        canvas.drawLine(60, 40, 80, 40, paint);
//        canvas.drawLine(60, 40, 60, 100, paint);
//        canvas.drawLine(60,100,80,100,paint);

        int startx = getLeft();
        int endx = getRight();
        int starty = getTop();
        int endy = getBottom();
        int heitht = getHeight();
        int width = getWidth();


        canvas.drawLine(startx, starty, endx, starty, paint);
        if (isLeft) {
            canvas.drawLine(startx, starty, startx, starty + heitht, paint);
        } else {
            canvas.drawLine(endx, starty, endx, starty + heitht, paint);
        }
        canvas.drawLine(startx, starty + heitht - 1, endx, starty + heitht - 1, paint);
    }
}
