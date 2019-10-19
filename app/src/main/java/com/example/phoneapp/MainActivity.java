package com.example.phoneapp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

    final String TAG = "States";
    Button btnActTwo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActTwo = findViewById(R.id.button);
        btnActTwo.setOnClickListener(this);
        Log.d(TAG, "MainActivity: onCreate()");
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);
    }
    public void onClickTwo(View v) {
        Intent intent = new Intent(this, ActivityThree.class);
        startActivity(intent);
    }
    public void onClickCanvas(View v){
        Intent intent = new Intent(this, ActivityFour.class);
        startActivity(intent);
    }
    public void onClickMultyTouch(View v){
        Intent intent = new Intent(this, ActivityMultiTouch.class);
        startActivity(intent);
    }

}
