package com.example.myapplication.glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityGlideTestBinding;
import com.example.myapplication.utils.FileUtil;
import com.example.myapplication.utils.GlideRoundTransform;

import java.io.File;

public class GlideTestActivity extends AppCompatActivity {
    private ActivityGlideTestBinding binding;
    private GlideTestViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_glide_test);
        viewModel = new ViewModelProvider(this).get(GlideTestViewModel.class);
        binding.setData(viewModel);
        binding.local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocal();
            }
        });
        binding.sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSd();
            }
        });
        binding.net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNet();
            }
        });
        binding.circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCircle();
            }
        });

    }
    public void setLocal(){
        Glide.with(this)
                .load(R.drawable.title)
                .into(binding.localImageView);
    }
    public void setSd(){
        File file = new File(FileUtil.getDownloadPath(this));
        Glide.with(this)
                .load(file)
                .into(binding.localImageView);
    }
    public void setNet(){
        Glide.with(this)
                .load("https://pics2.baidu.com/feed/38dbb6fd5266d0168196846a20d3bb0e37fa35e2.jpeg?token=1a46555ed440c5b0f747b15880e6caf3")
                .into(binding.localImageView);
    }
    public void setCircle(){
        Glide.with(this)
                .load(R.drawable.h456)
                .override(188, 188)
                .placeholder(R.mipmap.ic_launcher)//加载占位图
                .transform(new GlideRoundTransform(this,5))
                .error(R.mipmap.ic_launcher)//异常占位图
                .into(binding.localImageView);
    }
}