package com.example.myapplication.ButtonNavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ButtonNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_navigation);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);//底部导航条
        NavController navController = Navigation.findNavController(this,R.id.fragment3);//上部显示导航后的界面区域
        //工具条设置
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //装配工具条
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        //装配
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }
}