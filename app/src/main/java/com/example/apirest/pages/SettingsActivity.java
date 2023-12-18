package com.example.apirest.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.admin.AdminMain;
import com.example.apirest.classes.Param;
import com.example.apirest.databinding.ActivitySettingsBinding;
import com.example.apirest.user.UserMain;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.user_settings);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id_user", -1);
        String userRole = sharedPreferences.getString("role", "");


        binding.buttonDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "DECONNEXION CLICK");
                Param.getInstance().setToken("");
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "PROFILE CLICK");
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        binding.bottomContainer.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userRole.equals("user")){
                    Intent intent = new Intent(SettingsActivity.this, UserMain.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SettingsActivity.this, AdminMain.class);
                    startActivity(intent);
                }
            }
        });

        binding.bottomContainer.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        binding.bottomContainer.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "SETT CLICK");
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }



}
