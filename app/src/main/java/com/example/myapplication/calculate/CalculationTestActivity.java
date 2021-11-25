package com.example.myapplication.calculate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.myapplication.R;

public class CalculationTestActivity extends AppCompatActivity {
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_test);
        navController = Navigation.findNavController(this,R.id.fragment2);
        NavigationUI.setupActionBarWithNavController(this,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(navController.getCurrentDestination().getId() == R.id.questionFragment){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.quit_dialog_title);
            builder.setPositiveButton(R.string.dialog_positive_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    navController.navigateUp();
                }
            });
            builder.setNegativeButton(R.string.dialog_negative_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    navController.navigateUp();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(navController.getCurrentDestination().getId() == R.id.titleFragment){
            finish();
        }else{
            navController.navigate(R.id.titleFragment);
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }
}