package com.example.myapplication.lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.jar.Attributes;

class MyChronometer extends Chronometer implements LifecycleObserver {
    private static long elapsedTime;
    public MyChronometer(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void pauseMeter(){
        elapsedTime = SystemClock.elapsedRealtime()-getBase();
        stop();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void startMeter(){
        setBase(SystemClock.elapsedRealtime()-elapsedTime);
        start();
    }
}
