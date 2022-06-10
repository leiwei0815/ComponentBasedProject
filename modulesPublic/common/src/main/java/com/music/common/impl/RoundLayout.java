package com.music.common.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import android.widget.FrameLayout;


public class RoundLayout extends FrameLayout {
    int saveCount;
    private Path mPath;
    float[] radii=new float[]{0,0,0,0,0,0,0,0};
    public RoundLayout(Context context) {
        super(context);
        init();
    }

    public RoundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);
//        setWillNotDraw(false);
    }

    /**
     * 设置圆角半径
     *
     * @param radius
     */
    public void setCornerRadius(float[] radius) {
        radii=radius;
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mPath.rewind();
        mPath.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), radii, Path.Direction.CW);
        saveCount = canvas.save();
        canvas.clipPath(mPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(saveCount);
    }


}
