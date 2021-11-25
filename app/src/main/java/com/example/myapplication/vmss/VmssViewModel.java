package com.example.myapplication.vmss;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;

public class VmssViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.data_key);
    String shpName = getApplication().getResources().getString(R.string.shp_name);
    String TAG = "data:";
    public VmssViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        Log.d(TAG,"123123");
        if(!this.handle.contains(key)){
            Log.d(TAG,"有"+key);
            load();
        }
    }
    void load(){
        SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
        int x = shp.getInt(key,getApplication().getResources().getInteger(R.integer.defValue));
        Log.d(TAG,"加载key:"+x);
        handle.set(key,x);
    }
    public void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(shpName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key,getNumber().getValue());
        editor.apply();
    }
    public LiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }

    public void add(int x) {
        handle.set(key,getNumber().getValue() + x);
        save();
    }
}
