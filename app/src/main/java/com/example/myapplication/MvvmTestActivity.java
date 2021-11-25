package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myapplication.databinding.ActivityMvvmTestBinding;
import com.jaeger.library.StatusBarUtil;

public class MvvmTestActivity extends AppCompatActivity {
    ActivityMvvmTestBinding mvvmTestBinding;
    ViewModelTest viewModelTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvvmTestBinding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm_test);
        viewModelTest = new ViewModelProvider(this).get(ViewModelTest.class);
        mvvmTestBinding.setData(viewModelTest);
        mvvmTestBinding.setLifecycleOwner(this);
        mvvmTestBinding.button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                viewModel.add();
//                viewModel.getDataInfoForNet();
                viewModelTest.getDataInfoForNet();
//                if(viewModel.dataInfo != null && viewModel.dataInfo.getValue() != null) {
//                    binding.editTextTextPersonName2.setText(viewModel.dataInfo.getValue().getName());
////                    Toast.makeText(RetrofitActivity.this, viewModel.dataInfo.getValue().getName(),Toast.LENGTH_SHORT).show();
//                }
            }
        });
        StatusBarUtil.setTranslucent(this, 0);
        StatusBarUtil.setLightMode(this);
    }

    public void addNumber(View view) {
        viewModelTest.addNumber(1);
    }

}