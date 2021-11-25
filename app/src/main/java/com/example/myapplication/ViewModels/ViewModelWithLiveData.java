package com.example.myapplication.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {
    private MutableLiveData<Integer> LikeNumber;

    public MutableLiveData<Integer> getLikeNumber() {
        if(LikeNumber == null){
            LikeNumber = new MutableLiveData<Integer>();
            LikeNumber.setValue(0);
        }
        return LikeNumber;
    }
    public void addLikeNumber(int n){
        LikeNumber.setValue(LikeNumber.getValue()+n);
    }
}
