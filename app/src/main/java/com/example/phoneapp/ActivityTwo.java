package com.example.phoneapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityTwo extends Activity implements SensorEventListener{

    //Объявляем картинку для компаса
    ImageView HeaderImage;
    //Объявляем функцию поворота картинки
    float RotateDegree = 0f;

    TextView aX;
    TextView aY;
    TextView aZ;

    TextView mX;
    TextView mY;
    TextView mZ;
    TextView proximity;
    TextView light;

    SensorManager sensorManager;
    Sensor aSensor;
    Sensor mSensor;
    Sensor pSensor;
    Sensor lSensor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        aX = findViewById(R.id.textView8);
        aY = findViewById(R.id.textView9);
        aZ = findViewById(R.id.textView10);
        mX = findViewById(R.id.textView12);
        mY = findViewById(R.id.textView11);
        mZ = findViewById(R.id.textView13);
        proximity = findViewById(R.id.textView14);
        light = findViewById(R.id.textView15);
        HeaderImage =  findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            aX.setText("X: " + (event.values[0]));
            aY.setText("Y: " + (event.values[1]));
            aZ.setText("Z: " + (event.values[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            mX.setText("X: " +(event.values[0]));
            mY.setText("Y: " +(event.values[1]));
            mZ.setText("Z: " +(event.values[2]));

            float degree = Math.round(event.values[0]);
            //Создаем анимацию вращения:
            RotateAnimation rotateAnimation = new RotateAnimation(
                    RotateDegree,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f);
            //Продолжительность анимации в миллисекундах:
            rotateAnimation.setDuration(200);
            //Настраиваем анимацию после завершения подсчетных действий датчика:
            rotateAnimation.setFillAfter(true);
            //Запускаем анимацию:
            HeaderImage.startAnimation(rotateAnimation);
            RotateDegree = -degree;
        }
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            proximity.setText(Float.toString(event.values[0]));
        }
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            light.setText(Float.toString(event.values[0]));
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        sensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    public void onStop(){
        super.onStop();
        sensorManager.unregisterListener(this, aSensor);
        sensorManager.unregisterListener(this, mSensor);
        sensorManager.unregisterListener(this, pSensor);
        sensorManager.unregisterListener(this, lSensor);
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
