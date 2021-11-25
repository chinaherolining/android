package com.example.dagger.di;

import com.example.dagger.MainActivity;

import dagger.Component;

@Component(modules = NetModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
