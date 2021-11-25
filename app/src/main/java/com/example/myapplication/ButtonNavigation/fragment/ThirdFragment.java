package com.example.myapplication.ButtonNavigation.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.Random;

public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    ImageView imageView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThirdViewModel.class);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"x",0,0);
        objectAnimator.setDuration(500);
        imageView.setX(imageView.getX()+mViewModel.dX);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!objectAnimator.isRunning()){
                    float dx = new Random().nextBoolean()?100:-100;
                    objectAnimator.setFloatValues(imageView.getX(),imageView.getX()+dx);
                    mViewModel.dX += dx;
                    objectAnimator.start();
                }

            }
        });
    }

}