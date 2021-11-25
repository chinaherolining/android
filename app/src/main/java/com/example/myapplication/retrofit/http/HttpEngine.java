package com.example.myapplication.retrofit.http;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpEngine {
    public static HttpEngine instance;
    private Retrofit retrofit;

    private Api api;
    public HttpEngine(Context context) {
        //创建retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.uomg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口对象实例
        api = retrofit.create(Api.class);
    }
    //线程锁单例
    public static HttpEngine getInstance(Context context) {
        if (instance == null) {
            synchronized (HttpEngine.class) {
                if (instance == null) {
                    instance = new HttpEngine(context);
                }
            }
        }
        return instance;
    }

    public Api getApi() {
        return api;
    }
}
