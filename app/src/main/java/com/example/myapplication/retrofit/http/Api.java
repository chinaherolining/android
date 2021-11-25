package com.example.myapplication.retrofit.http;


import com.example.myapplication.retrofit.entity.Data;
import com.example.myapplication.retrofit.entity.Info;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    //get请求
    @GET("api/rand.music")
    Call<Data<Info>> getJsonData(@Query("sort")String sort, @Query("format")String format);
    //post请求，如果有参数需要添加 @FormUrlEncoded注解，即和@Field配合使用
    @FormUrlEncoded
    @POST("api/comments.163")
    Call<Object> postDataCall(@Field("format") String format);

}
