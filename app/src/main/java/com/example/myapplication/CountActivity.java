package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.myapplication.ViewModelWithCount;
import com.example.myapplication.databinding.ActivityCountBinding;

public class CountActivity extends AppCompatActivity {
    ViewModelWithCount viewModelWithCount;
    ActivityCountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_count);
        viewModelWithCount = new ViewModelProvider(this).get(ViewModelWithCount.class);
        binding.setData(viewModelWithCount);
        binding.setLifecycleOwner(this);
    }
}
