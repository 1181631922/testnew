package com.fanyafeng.testnew.testdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.CancellationException;

/**
 * Created by zgw on 15/11/11 下午2:40.
 */
public class BaGua extends View {

    RectF[] circle = new RectF[3];

    static Paint[] highlightPaint = new Paint[2];

    private int currentFrame = 0;

    static {
        highlightPaint[0] = new Paint();
        highlightPaint[0].setColor(Color.WHITE);
        highlightPaint[0].setStyle(Paint.Style.STROKE);
        highlightPaint[0].setStrokeWidth(6f);
        highlightPaint[0].setAntiAlias(true);
        highlightPaint[0].setDither(true);

        highlightPaint[1] = new Paint();
        highlightPaint[1].setColor(Color.parseColor("#6f6057"));
        highlightPaint[1].setStyle(Paint.Style.STROKE);
        highlightPaint[1].setStrokeWidth(6f);
        highlightPaint[1].setAntiAlias(true);
        highlightPaint[1].setDither(true);
    }

    public BaGua(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public BaGua(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public BaGua(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    private static float ARC_LEN = 31f;

    private static float SHORT_ARC_LEN = 12.5f;

    private float start = -90f - ARC_LEN / 2;

    static int[][] pos = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    private boolean[][] dash = new boolean[][]{
            {true, true, true},
            {true, true, false},
            {false, true, false},
            {true, false, false},
            {false, false, false},
            {false, false, true},
            {true, false, true},
            {false, true, true}
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                drawAcr(canvas, j, start, i);
            }

            start += 45f;
        }
        postDelayed(refresh, 90);
    }

    private void drawAcr(Canvas canvas, int q, float angle, int p) {
        if (dash[p][q]) {
            canvas.drawArc(circle[q], angle, ARC_LEN, false, highlightPaint[pos[currentFrame][p]]);
        } else {
            canvas.drawArc(circle[q], angle, SHORT_ARC_LEN , false, highlightPaint[pos[currentFrame][p]]);
            canvas.drawArc(circle[q], angle + ARC_LEN - SHORT_ARC_LEN, SHORT_ARC_LEN , false, highlightPaint[pos[currentFrame][p]]);
        }
    }

    private Runnable refresh = new Runnable() {
        @Override
        public void run() {
            currentFrame++;
            if (currentFrame == pos.length) {
                currentFrame = 0;
            }
            invalidate();
        }
    };

    private static int STEP = 15;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        for (int i = 0; i < circle.length; i++) {
            if (circle[i] == null) {
                circle[i] = new RectF();
            }
        }
        int halfSize = (Math.min(w, h) >> 1) - STEP;
        circle[0].set((w >> 1) - halfSize, (h >> 1) - halfSize, (w >> 1) + halfSize, (h >> 1) + halfSize);

        halfSize -= STEP;
        circle[1].set((w >> 1) - halfSize, (h >> 1) - halfSize, (w >> 1) + halfSize, (h >> 1) + halfSize);

        halfSize -= STEP;
        circle[2].set((w >> 1) - halfSize, (h >> 1) - halfSize, (w >> 1) + halfSize, (h >> 1) + halfSize);

    }
}
