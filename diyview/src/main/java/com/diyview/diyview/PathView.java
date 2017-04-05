package com.diyview.diyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.R.attr.max;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Right on 2017/4/1.
 */

public class PathView extends View {
    private Paint mPaint = new Paint();//画笔
    private int centerX, centerY;//原点坐标
    private float radius;//网格最大半径
    private int count = 6;//正六边形
    private float angle = (float) (Math.PI * 2 / count);//弧度
    private float angleDeg=60;//正六边形每个内角60°
    private String[] titles = {"a","b","c","d","e","f"};
    private double[] data = {95,60,20,60,70,50,10,100}; //各维度分值
    private double maxVaule=100f;
    private Paint valuePaint=new Paint();//区域画笔
    private Paint textPaint=new Paint();//文字画笔

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.8f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(centerX, centerY);//将坐标原点移动至屏幕中央
      //  canvas.scale(1,-1);//将y轴进行翻转，变为标准坐标
        mPaint.setColor(Color.RED);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(5f);              // 边框宽度 - 10
        //----------画x轴y轴
        canvas.drawLine(-centerX, 0, centerX, 0, mPaint);//x轴
        canvas.drawLine(centerX - 20, 20, centerX, 0, mPaint);//箭头下半部分
        canvas.drawLine(centerX - 20, -20, centerX, 0, mPaint);//箭头上半部分
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, -centerY, 0, centerY, mPaint);//y轴
        canvas.drawLine(20, -centerY + 20, 0, -centerY, mPaint);//箭头右半部分
        canvas.drawLine(-20, -centerY + 20, 0, -centerY, mPaint);//箭头左半部分
        //---------------------------------------
//        mPath.lineTo(200, 200);
//        mPath.lineTo(200, -400);
//        canvas.drawPath(mPath,mPaint);
//        mPaint.setColor(Color.BLACK);
//        mPath.setLastPoint(-200,200);
//        mPath.lineTo(0,0);
//        canvas.drawPath(mPath,mPaint);
//        RectF rectF=new RectF(10,10,200,300);
//        mPath.addArc(rectF,50,360);
//        canvas.drawPath(mPath,mPaint);
        //  canvas.scale(1,-1);   // <-- 注意 翻转y坐标轴
//        Path path = new Path();
//        path.lineTo(100, 100);
//        RectF oval = new RectF(0, 0, 300, 300);
//        //  path.addArc(oval,0,270);
//        path.arcTo(oval, 0, 270, false);             // <-- 和上面一句作用等价
//        canvas.drawPath(path, mPaint);
        drawPolygon(canvas);//绘制多边形
        drawLine(canvas);//连接多边形至圆心
        drawText(canvas);//绘制文字
        drawRegion(canvas);//绘制区域
    }

    /**
     * 绘制正多边形
     * double angleHude = angle * Math.PI / 180;*角度变成弧度
     * p.X = (int)(radius * Math.Cos(angleHude)) + center .X;
     * p.Y = (int)(radius * Math.Sin(angleHude)) + center .Y;
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int j = 1; j < count; j++) {
            float curR = r * j;
            path.reset();
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    path.moveTo(curR, 0);
                }
                float x = (float) (curR * Math.cos(angle * i) + 0);
                float y = (float) (curR * Math.sin(angle * i) + 0);
                path.lineTo(x, y);
            }
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    /**
     * 连接到圆心
     */
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(0, 0);
            float x = (float) (radius * Math.cos(angle * i) + 0);
            float y = (float) (radius * Math.sin(angle * i) + 0);
//            path.lineTo(x, y);
//            canvas.drawPath(path, mPaint);//两种方法
            canvas.drawLine(x, y, 0, 0, mPaint);
        }
    }
    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas){
        textPaint.setTextSize(35f);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        Log.i("TAG","------文字下降="+fontMetrics.descent+"----文字升高="+fontMetrics.ascent);
        for(int i=0;i<count;i++){
            //文字坐标
            float x = (float) (0+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (0+(radius+fontHeight/2)*Math.sin(angle*i));
            Log.i("TAG","------当前弧度="+(angle*i)+"----文字升高="+fontMetrics.ascent);
            if(angle*i>=0&&angle*i<=Math.PI/2){
                canvas.drawText(titles[i]+"第四象限", x,y,textPaint); //第4象限
            }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i]+"第三象限", x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i]+"第二象限", x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i]+"第一象限", x-dis,y,textPaint);
            }
        }
    }

    /****
     * 绘制区域
     *
     * */
    private void drawRegion(Canvas canvas){
        Path path=new Path();
        valuePaint.setAlpha(255);//完全不透明
        for (int i = 0; i <count ; i++) {
            double percent=data[i]/maxVaule;//所占百分比
            float x=(float) (0+radius*percent*Math.cos(angle*i));
            float y=(float) (0+radius*percent*Math.sin(angle*i));
            if(i==0){
                path.moveTo(x,0);
            }
            path.lineTo(x,y);
            canvas.drawCircle(x,y,10,valuePaint);///绘制小圆点
        }
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setColor(Color.RED);
        //上面绘制小圆点
        valuePaint.setAlpha(127);
//        path.close();
        canvas.drawPath(path,valuePaint);
        //绘制区域

    }

}
