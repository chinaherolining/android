package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.util.StringUtil;

import com.example.myapplication.retrofit.entity.Data;
import com.example.myapplication.retrofit.entity.Info;
import com.example.myapplication.retrofit.http.HttpEngine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelTest extends AndroidViewModel {
    private MutableLiveData<Integer> number;
    private MutableLiveData<Info> dataInfo;

    public ViewModelTest(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getNumber() {
        if(number == null){
            number = new MutableLiveData<Integer>();
            number.setValue(0);
        }
        return number;
    }

    public void addNumber(int n) {
        this.number.setValue(number.getValue()+n);
    }

    public MutableLiveData<Info> getDataInfo(){
        if(dataInfo == null){
            Info info = new Info();
            dataInfo = new MutableLiveData<Info>();
            dataInfo.setValue(info);
        }
        return dataInfo;
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
}
