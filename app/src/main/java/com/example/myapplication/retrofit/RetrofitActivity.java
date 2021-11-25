package com.example.myapplication.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRetrofitBinding;

public class RetrofitActivity extends AppCompatActivity {
    ActivityRetrofitBinding binding;
    RetrofitViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit);
        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);
        binding.button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                viewModel.add();
//                viewModel.getDataInfoForNet();
                viewModel.getDataInfoForNet();
                if(viewModel.dataInfo != null && viewModel.dataInfo.getValue() != null) {
                    binding.editTextTextPersonName2.setText(viewModel.dataInfo.getValue().getName());
//                    Toast.makeText(RetrofitActivity.this, viewModel.dataInfo.getValue().getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.button17.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                viewModel.postDataInfoForNet();
            }
        });
    }
}