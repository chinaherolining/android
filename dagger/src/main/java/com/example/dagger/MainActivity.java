package com.example.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dagger.di.DaggerApplicationComponent;
import com.example.dagger.di.User;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @Inject
    User user;
    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //执行依赖注入动作
        DaggerApplicationComponent.create().inject(this);
        System.out.println("user="+user);
        System.out.println("retrofit="+retrofit);
    }
}