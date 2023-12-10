package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.user_settings);


        binding.buttonDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "DECONNEXION CLICK");
                Param.getInstance().setToken("");
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        binding.bottomContainer.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "HOME CLICK");
                Intent intent = new Intent(SettingsActivity.this, UserMain.class);
                startActivity(intent);
            }
        });

        binding.bottomContainer.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "ADD CLICK");
                Intent intent = new Intent(SettingsActivity.this, AdminEditPcr.class);
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
