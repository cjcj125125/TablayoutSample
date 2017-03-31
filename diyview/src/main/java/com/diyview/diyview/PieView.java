package com.diyview.diyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.diyview.entity.PieData;

import java.util.List;

/**
 * Created by Right on 2017/3/27.
 * 圆形统计图
 */

public class PieView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private float mStartAngle = 0;//圆饼图初始化角度
    private List<PieData> pieDataList;//初始化数据
    private int mWidth, mHeight;
    private Paint mPaint = new Paint();


    //文字色块部分
    // private PointF mStartPoint = new PointF(20, 20);
//    private PointF mCurrentPoint = new PointF(mStartPoint.x, mStartPoint.y);
//    private float mColorRectSideLength = 20;
    private float mTextInterval = 10;
    private float mRowMaxLength;


    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//抗锯齿
        // mPaint.setDither(true);//防抖动边界柔和
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("TAG","宽度----"+w);
        Log.i("TAG","高度----"+h);//得到的是控件的宽高
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pieDataList == null)
            return;
        //  canvas.drawRoundRect(100, 100, 800, 400, 30, 30, mPaint);
        RectF rectRound = new RectF(200, 200, 400, 400);
        // canvas.drawRoundRect(rectRound, 100, 100, mPaint);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(rectRound,mPaint);
//        canvas.drawLine(200,200,600,200,mPaint);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawLine(400,400,600,200,mPaint);
        canvas.save();//储存状态
        canvas.translate(100,100);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 100, mPaint);
       canvas.restore();//恢复状态

        //-----------------------------------------
        float currentStartAngle = mStartAngle;//当前其实角度
        canvas.translate(mWidth / 2, mHeight / 2);//画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);//饼状图半径
        RectF rectF = new RectF(-r, -r, r, r);
        for (PieData pieData : pieDataList) {
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();
            //   canvas.save();
            //canvas.translate(-mWidth / 2, -mHeight / 2);
            //  RectF colorRectF = new RectF(mCurrentPoint.x, mCurrentPoint.y, mCurrentPoint.x + mColorRectSideLength, mCurrentPoint.y + mColorRectSideLength);
            //  canvas.restore();
        }
    }

    public void setStartAngle(int startAngle) {
        this.mStartAngle = startAngle;
        invalidate();//刷新数据
    }

    public void setData(List<PieData> pieData) {
        this.pieDataList = pieData;
        initData(pieDataList);
        invalidate();//刷新
    }

    public void initData(List<PieData> pieDataList) {
        if (pieDataList == null || pieDataList.isEmpty())
            return;
        float sumValue = 0;
        int i = 0;
        for (PieData pieData : pieDataList) {
            sumValue += pieData.getValue();//求和操作
            int j = i % mColors.length;       //设置颜色
            pieData.setColor(mColors[j]);
            i++;
            if (i > pieDataList.size()) {
                i = 0;
            }
        }
        float sumAngle = 0;//角度
        for (PieData pieData : pieDataList) {
            float percentage = pieData.getValue() / sumValue;//百分比
            float angle = percentage * 360;//对应的角度
            pieData.setAngle(angle);//角度大小
            sumAngle += angle;

        }

    }
}
