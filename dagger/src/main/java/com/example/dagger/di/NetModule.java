package com.example.dagger.di;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

//dagger模块
@Module
public class NetModule {
    @Provides
    public User provider(){
        return new User();
    }
    @Provides
    public Retrofit provideRetrofit(){
    return new Retrofit.Builder()
            .baseUrl("http://www.baidu.com")
            .build();
    }
}
