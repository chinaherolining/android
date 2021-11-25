package com.example.myapplication.lifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;

public class LifeCyclesActivity extends AppCompatActivity {
    MyChronometer myChronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycles);
//        myChronometer = findViewById(R.id.myChronometer);
//        getLifecycle().addObserver(myChronometer);
    }
}