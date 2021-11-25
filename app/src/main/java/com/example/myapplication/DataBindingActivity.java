package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {
    ViewModelWithDataBinding viewModelWithDataBinding;
    ActivityDataBindingBinding bindingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding);//绑定
        viewModelWithDataBinding = new ViewModelProvider(this).get(ViewModelWithDataBinding.class);
        bindingBinding.setData(viewModelWithDataBinding);//设置viewmodel
        bindingBinding.setLifecycleOwner(this);//设置观察者
    }
}
