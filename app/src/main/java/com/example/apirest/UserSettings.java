package com.example.apirest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.apirest.databinding.UserSettingsBinding;

public class UserSettings extends AppCompatActivity {
    private UserSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = UserSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.user_settings);


        binding.bottomContainer.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "HOME CLICK");
                Intent intent = new Intent(UserSettings.this, UserMain.class);
                startActivity(intent);
            }
        });

        binding.bottomContainer.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "ADD CLICK");
                Intent intent = new Intent(UserSettings.this, AdminEditPcr.class);
                startActivity(intent);
            }
        });

        binding.bottomContainer.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserSettings", "SETT CLICK");
                Intent intent = new Intent(UserSettings.this, UserSettings.class);
                startActivity(intent);
            }
        });


    }



}
