package com.example.phoneapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;

public class ActivityFour extends AppCompatActivity implements View.OnTouchListener {

    boolean inTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int actionMask = event.getActionMasked();
        Canvas canvas = null;

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: // первое касание
                inTouch = true;


            case MotionEvent.ACTION_UP: // прерывание последнего касания
                inTouch = false;


            case MotionEvent.ACTION_MOVE: // движение

                for (int i = 0; i < 2; i++) {
                    //canvas.drawLine(event.getX(i),event.getY(i),event.getX(i) + 10,event.getY(i)+ 10);

                }
                break;
        }
        return true;
    }

    class DrawView extends View {
        Paint paint;
        Rect rect;


    public DrawView(Context context){
        super(context);
        paint = new Paint();
        rect = new Rect();
        }
        @Override
        protected void onDraw(Canvas canvas){
        canvas.drawRGB(80,102,204);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
        }

    }

}
