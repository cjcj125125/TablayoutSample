package com.diyview.diyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.icu.math.BigDecimal;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Right on 2017/3/28.
 */

public class LineView extends View {
    private Context context;
    private Paint mPaint = new Paint();
    // 1.创建Picture
    private Picture mPicture = new Picture();

    private Path path = new Path();
    //private Handler mHandler;

    private int mWidth, mHeight;
    private float sx, sy;//指针坐标秒针
    private float sx1, sy1;//指针坐标分针
    private double jiao = 0d;//当前角度秒针
    private double jiao1 = 0d;//当前角度分针
    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }
    public  Handler  mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            jiao += 6d;
            jiao1+=0.1d;
            invalidate();
        }
    };
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //将坐标原点移到视图中间（屏幕）
        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setStrokeWidth(5f);
        //画Y轴
        float[] yPoint = {0, -mWidth, 0, mWidth};
        mPaint.setColor(Color.RED);
        canvas.drawLines(yPoint, mPaint);
        //画X轴
        float[] xPoint = {-mWidth, 0, mWidth, 0};
        mPaint.setColor(Color.BLACK);
        canvas.drawLines(xPoint, mPaint);
        //画圆
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(0, 0, (mWidth / 8), mPaint);
//        canvas.save();
//        canvas.scale(-2, -2);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawCircle(50, 50, (mWidth / 8), mPaint);
//        canvas.restore();
//        canvas.save();
//        canvas.scale(2, 2);
//        RectF rectF = new RectF(100, 100, 300, 300);
//        canvas.drawRect(rectF, mPaint);
//        canvas.restore();
//        RectF rectF1 = new RectF(-200, -200, 200, 200);
//        for (int i = 0; i <20; i++) {
//            if(i%2==0){
//                mPaint.setStrokeWidth(20f);
//                mPaint.setColor(Color.BLUE);
//            }
//            else{
//                mPaint.setStrokeWidth(10f);
//                mPaint.setColor(Color.RED);
//            }
//            canvas.scale(0.9f,0.9f);
//            canvas.drawRect(rectF1,mPaint);
//        }
//        mPaint.setStrokeWidth(20f);
//          mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rectF1,mPaint);
//        canvas.rotate(30,200,200);
//        canvas.drawRect(rectF1,mPaint);
        float radius = mWidth / 2 - 50;//外层圆半径
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0, 0, radius, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, radius - 20, mPaint);
        for (int i = 0; i <= 359; i++) {
            // 绘制圆形之间的连接线
            if (i % 5 == 0) {//一大格
                mPaint.setColor(Color.RED);
                mPaint.setStrokeWidth(10f);
                canvas.drawLine(0, radius, 0, radius - 30, mPaint);
            } else {//一小格
                mPaint.setColor(Color.BLACK);
                mPaint.setStrokeWidth(5f);
                canvas.drawLine(0, radius, 0, radius - 20, mPaint);
            }
            canvas.rotate(6f);
        }
//        RectF rectF = new RectF(-200, -200, 200, 200);
//        canvas.drawRect(rectF,mPaint);
//        canvas.skew(0,1);//垂直错切45度（tan45°=1）
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(rectF,mPaint);
//        canvas.skew(1,0);//水平错切45度（tan45°=1）
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rectF,mPaint);
        //-------Picture操作
//          drawPicture();
//          mPicture.draw(canvas);//方法一 ：这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用。
//        RectF rectF = new RectF(0, 0, 100, 100);//这个矩形来确定绘制图片的区域
//        canvas.drawPicture(mPicture, rectF);//方法二：
//        PictureDrawable drawable = new PictureDrawable(mPicture);//包装成drawable
//        drawable.setBounds(0, 0, 200, 200);// 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
//        drawable.draw(canvas);//绘制
//        drawBitmap(canvas);//绘制Bitmap
        Paint textPaint = new Paint();
        textPaint.setTextSize(30f);
        textPaint.setTextAlign(Paint.Align.CENTER);
      //  canvas.drawText("圆内写字", 0, 0, textPaint);
//        String str = "JAOOP";
//        canvas.drawPosText(str,new float[]{
//                100,100,    // 第一个字符位置
//                200,200,    // 第二个字符位置
//                300,300,    // ...
//                400,400,
//                500,500
//        },textPaint);
        Double t = (Math.pow(radius, 2) * 2);//a²+b²=c²
        //Math.sqrt(t);开方
        //   path.lineTo((float)(Math.sqrt(t)/2),(float)(Math.sqrt(t)/2));
        // canvas.drawPath(path, mPaint);

//        则圆上任一点为：（x1,y1）x0,y0为圆心ao角度
//        x1   =   x0   +   r   *   cos(ao   *   3.14   /180   )
//        y1   =   y0   +   r   *   sin(ao   *   3.14   /180   )
        sx = (float) (0 + radius * Math.cos(jiao * Math.PI / 180));
        sy =(float)( 0 + radius * Math.sin(jiao * Math.PI / 180));
        canvas.drawLine(0, 0, sx, sy, mPaint);//秒针
        sx1 = (float) (0 + radius * Math.cos(jiao1 * Math.PI / 180));
        sy1 =(float)( 0 + radius * Math.sin(jiao1 * Math.PI / 180));
        canvas.drawLine(0, 0, sx1-40, sy1-40, mPaint);//分针
        Log.i("TAG","秒针角度="+jiao+"---分针角度="+jiao1);
        mHandler.sendEmptyMessageDelayed(0,1000);
    }


    private void drawPicture() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 200, paint);
        mPicture.endRecording();

    }

    private void drawBitmap(Canvas canvas) {
        // Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.raw.bitmap);
        //资源文件(drawable/mipmap/raw):读取图片
        //  Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bitmap.png");
        //内存中读取图片
        Bitmap bitmap = null;
        try {
            InputStream is = context.getAssets().open("warn.png");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap == null) return;
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());//一种方法
    }


//    public static void main(String[] args) {
//        for (int i = 0; i <= 65535; i++) {
//            System.out.print((char) i + "  ");
//           if (i % 10 == 0) System.out.println();
//
//        }
//    }
}
