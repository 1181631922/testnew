package com.fanyafeng.testnew.testdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.fanyafeng.testnew.util.MyUtils;

/**
 * Created by fanyafeng on 2015/11/11,0011.
 */
public class DrawCircle extends View {

    public DrawCircle(Context context) {
        super(context);
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        画最外围的圆形
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
//        包住第一圈的圆，使其称为圆环
//        Paint paint1 = new Paint();
//        paint1.setColor(Color.WHITE);
//        paint1.setAntiAlias(true);
//        canvas.drawCircle(MyUtils.getScreenWidth(getContext()) / 2, MyUtils.getScreenHeight(getContext()) / 2, 230, paint1);

        // 设置个新的长方形，扫描测量
        RectF oval1 = new RectF(0, 0, MyUtils.getScreenWidth(getContext()), MyUtils.getScreenWidth(getContext()));
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        canvas.drawArc(oval1, 15, 30, false, paint);
        canvas.drawArc(oval1, 75, 30, false, paint);
        canvas.drawArc(oval1, 135, 30, false, paint);
        canvas.drawArc(oval1, 195, 30, false, paint);
        canvas.drawArc(oval1, 255, 30, false, paint);
        canvas.drawArc(oval1, 315, 30, false, paint);

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setAntiAlias(true);
        RectF oval2 = new RectF(5, 5, MyUtils.getScreenWidth(getContext()) - 5, MyUtils.getScreenWidth(getContext()) - 5);
        canvas.drawArc(oval2, 15, 30, true, paint2);
        canvas.drawArc(oval2, 75, 30, true, paint2);
        canvas.drawArc(oval2, 135, 30, true, paint2);
        canvas.drawArc(oval2, 195, 30, true, paint2);
        canvas.drawArc(oval2, 255, 30, true, paint2);
        canvas.drawArc(oval2, 315, 30, true, paint2);

        Paint paint3 = new Paint();
        paint3.setColor(Color.BLUE);
        paint3.setAntiAlias(true);
        RectF oval3 = new RectF(7, 7, MyUtils.getScreenWidth(getContext()) - 7, MyUtils.getScreenWidth(getContext()) - 7);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        canvas.drawArc(oval3, 15, 30, false, paint3);
        canvas.drawArc(oval3, 75, 30, false, paint3);
        canvas.drawArc(oval3, 135, 30, false, paint3);
        canvas.drawArc(oval3, 195, 30, false, paint3);
        canvas.drawArc(oval3, 255, 30, false, paint3);
        canvas.drawArc(oval3, 315, 30, false, paint3);

        Paint paint4 = new Paint();
        paint4.setColor(Color.WHITE);
        paint4.setAntiAlias(true);
        RectF oval4 = new RectF(12, 12, MyUtils.getScreenWidth(getContext()) - 5, MyUtils.getScreenWidth(getContext()) - 5);
        canvas.drawArc(oval4, 15, 30, true, paint4);
        canvas.drawArc(oval4, 75, 30, true, paint4);
        canvas.drawArc(oval4, 135, 30, true, paint4);
        canvas.drawArc(oval4, 195, 30, true, paint4);
        canvas.drawArc(oval4, 255, 30, true, paint4);
        canvas.drawArc(oval4, 315, 30, true, paint4);


////        开始画第二圈
//        Paint paint2 = new Paint();
//        paint2.setColor(Color.BLUE);
//        paint2.setAntiAlias(true);
//        canvas.drawCircle(MyUtils.getScreenWidth(getContext()) / 2, MyUtils.getScreenHeight(getContext()) / 2, 225, paint2);
////        包住第二圈
//        Paint paint3 = new Paint();
//        paint3.setColor(Color.WHITE);
//        paint3.setAntiAlias(true);
//        canvas.drawCircle(MyUtils.getScreenWidth(getContext()) / 2, MyUtils.getScreenHeight(getContext()) / 2, 215, paint3);
//
////        开始画第三圈
//        Paint paint4 = new Paint();
//        paint4.setColor(Color.BLUE);
//        paint4.setAntiAlias(true);
//        canvas.drawCircle(MyUtils.getScreenWidth(getContext()) / 2, MyUtils.getScreenHeight(getContext()) / 2, 210, paint4);
////        包住第三圈
//        Paint paint5 = new Paint();
//        paint5.setColor(Color.WHITE);
//        paint5.setAntiAlias(true);
//        canvas.drawCircle(MyUtils.getScreenWidth(getContext()) / 2, MyUtils.getScreenHeight(getContext()) / 2, 200, paint5);


    }
}
