package com.example.myapplication.retrofit;


import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.retrofit.entity.Data;
import com.example.myapplication.retrofit.entity.Info;
import com.example.myapplication.retrofit.entity.Translation;
import com.example.myapplication.retrofit.http.Api;
import com.example.myapplication.retrofit.http.HttpEngine;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitViewModel extends AndroidViewModel {
    MutableLiveData<Integer> data;
    MutableLiveData<Data> dataT;
    MutableLiveData<Info> dataInfo;
    MutableLiveData<String> dataPostString;

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getData(){
        if(data == null){
            data = new MutableLiveData<Integer>();
            data.setValue(0);
        }
        return data;
    }
    public MutableLiveData<Info> getDataInfo(){
        if(dataInfo == null){
            Info info = new Info();
            dataInfo = new MutableLiveData<Info>();
            dataInfo.setValue(info);
        }
        return dataInfo;
    }
    public MutableLiveData<String> getDataPostString(){
        if(dataPostString == null){
            dataPostString = new MutableLiveData<String>();
        }
        return dataPostString;
    }
    public void add(){
            data.setValue(data.getValue()+1);
    }
    public void getDataInfoForNet(){
        if(dataInfo == null){
            Info info = new Info();
            dataInfo = new MutableLiveData<Info>();
            dataInfo.setValue(info);
        }
        //对发送请求进行封装
        Call<Data<Info>> dataCall = HttpEngine.getInstance(getApplication().getApplicationContext()).getApi().getJsonData("新歌榜", "json");
        dataCall.enqueue(new Callback<Data<Info>>() {
            @Override
            public void onResponse(Call<Data<Info>> call, Response<Data<Info>> response) {

                Data<Info> body = response.body();
                if (body == null) return;
                Info info = body.getData();
                if (info == null) return;
                dataInfo.setValue(info);
            }

            @Override
            public void onFailure(Call<Data<Info>> call, Throwable t) {
                t.getMessage();
            }
        });
    }
    public void postDataInfoForNet(){
//对发送请求进行封装
        Call<Object> dataCall = HttpEngine.getInstance(getApplication().getApplicationContext()).getApi().postDataCall( "JSON");
        dataCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Object body = response.body();
                if (body == null) return;
                dataPostString.setValue(body.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}
