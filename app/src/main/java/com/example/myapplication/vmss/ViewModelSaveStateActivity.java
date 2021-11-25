package com.example.myapplication.vmss;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityViewModelSaveStateBinding;

public class ViewModelSaveStateActivity extends AppCompatActivity {
    ActivityViewModelSaveStateBinding binding;
    VmssViewModel viewModel;
    public final static String KEY_NUMBER = "my_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyData myData = new MyData(getApplicationContext());
//        myData.number = 1000;
//        myData.save();
//        int x = myData.load();
//        String TAG = "mylog";
//        Log.d(TAG, "onCreate:" + x);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_save_state);
        viewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(VmssViewModel.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);


    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}