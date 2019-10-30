package com.example.phoneapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;

public class ActivityFour extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingView(this));
    }

    protected Paint mPaint;
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    public static final float TOUCH_STROKE_WIDTH = 5;
    protected float mStartX;
    protected float mStartY;

    protected float mx;
    protected float my;
    protected boolean isDrawing = false;
    protected Paint mPaintFinal;

    public class DrawingView extends View {

        public DrawingView(Context context) {
            super(context);
            init();
        }

        protected void init() {
            mPaint = new Paint(Paint.DITHER_FLAG);
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(TOUCH_STROKE_WIDTH);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            mx = event.getX();
            my = event.getY();
                    onTouchEventCircle(event);
            return true;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);

                       // onDrawCircle(canvas);
            DrawStar(canvas);
        }

        private void DrawStar(Canvas canvas){
            canvas.drawLine(300 ,300 ,500 ,500,mPaint);
            canvas.drawLine(500,500,700,300,mPaint);
            canvas.drawLine(700,300,650,100,mPaint);
            canvas.drawLine(650,100,350,100,mPaint);
            canvas.drawLine(350,100,300,300,mPaint);
        }

        private void onDrawCircle(Canvas canvas) {
            canvas.drawCircle(mStartX, mStartY, calculateRadius(mStartX, mStartY, mx, my), mPaint);

        }

        private void onTouchEventCircle(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDrawing = true;
                    mStartX = mx;
                    mStartY = my;
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    isDrawing = false;
                    mCanvas.drawCircle(mStartX, mStartY,
                            calculateRadius(mStartX,mStartY,mx,my), mPaintFinal);
                    invalidate();
                    break;
            }
        }

        protected float calculateRadius(float x1, float y1, float x2, float y2) {
            return (float) Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) );
        }

    }


}
