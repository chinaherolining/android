package com.example.myapplication.navigation1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationViewModel2 extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add(int x){
        number.setValue(number.getValue()+x);
        if(number.getValue() < 0){
            number.setValue(0);
        }
    }
}
