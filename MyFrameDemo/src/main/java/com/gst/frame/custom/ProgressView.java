package com.gst.frame.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {

    public ProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProgressView(Context context) {
        super(context);
        initView(context);
    }

    /** 进度条最大值 */
    private float maxCount;
    /** 进度条当前值 */
    private float currentCount;
    /** 画笔 */
    private Paint mPaint;
    private int mWidth, mHeight;

    /**
     * 进度条斜线的宽度
     */
    private int GRAYWIDTH = 5;

    private void initView(Context context) {
        setMaxCount(100);
        setCurrentCount(60);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        int round = mHeight * 9 / 10;
        System.out.println("max=" + maxCount + "  current=" + currentCount);

        mPaint.setColor(Color.rgb(255, 200, 47));// 设置画笔的颜色
        RectF rectBg = new RectF(0, 0, mWidth, mHeight);

        canvas.drawRoundRect(rectBg, round, round, mPaint);// 画出圆角矩形背景

        mPaint.setColor(Color.rgb(244, 244, 244));
        int myWidth = 5;
        RectF rectBlackBg = new RectF(myWidth, myWidth, mWidth - myWidth,
                mHeight - myWidth);

        canvas.drawRoundRect(rectBlackBg, round * 7 / 9, round * 7 / 9, mPaint);// 画出浅白色背景

        float section = currentCount / maxCount;
        RectF rectProgressBg = new RectF(myWidth, myWidth, (mWidth - myWidth)
                * section, mHeight - myWidth);

        mPaint.setColor(Color.rgb(226, 29, 0));

        canvas.drawRoundRect(rectProgressBg, round * 7 / 9, round * 7 / 9,
                mPaint);

        RectF rectProgressBg2 = new RectF(myWidth, myWidth, (mWidth - myWidth)
                * section, mHeight - myWidth);
        mPaint.setColor(Color.rgb(187, 25, 0));
        // mPaint.setColor(Color.BLACK);
        float myX = rectProgressBg2.left + myWidth;
        int length = (int) (((mWidth - myWidth) * section - myWidth) / (mHeight
                - myWidth * 2 - 2));
        if (length > 1) {

            for (int i = 0; i < length - 1; i++) {
                Path path = new Path();
                path.moveTo(myX + i * (mHeight - (mHeight - myWidth * 2 - 2))
                        - 2, rectProgressBg2.bottom);
                path.lineTo(myX + GRAYWIDTH + i
                                * (mHeight - (mHeight - myWidth * 2 - 2)) - 2,
                        rectProgressBg2.bottom);
                path.lineTo(myX + GRAYWIDTH + (i + 1)
                                * (mHeight - (mHeight - myWidth * 2 - 2)) - 2,
                        rectProgressBg2.top);
                path.lineTo(myX + (i + 1)
                                * (mHeight - (mHeight - myWidth * 2 - 2)) - 2,
                        rectProgressBg2.top);
                path.close();
                if ((myX + GRAYWIDTH + (i + 1)
                        * (mHeight - (mHeight - myWidth * 2 - 2)) - 2) > ((mWidth - myWidth)
                        * section - myWidth)) {
                    break;
                }
                canvas.drawPath(path, mPaint);
            }
        }

    }

    /**
     * dip转换为px
     *
     * @param dip
     * @return
     */
    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /***
     * 设置最大的进度值
     *
     * @param maxCount
     */
    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
    }

    /***
     * 设置当前的进度值
     *
     * @param currentCount
     */
    public void setCurrentCount(float currentCount) {
        this.currentCount = currentCount > maxCount ? maxCount : currentCount;
        invalidate();
    }

    public float getMaxCount() {
        return maxCount;
    }

    public float getCurrentCount() {
        return currentCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY
                || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = dipToPx(100);
        }
        if (heightSpecMode == MeasureSpec.AT_MOST
                || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(15);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);// 设置控件的宽高
    }

}
