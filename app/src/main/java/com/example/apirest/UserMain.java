package com.example.apirest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.ListView;

import com.example.apirest.databinding.UserMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserMain extends AppCompatActivity {
    private UserMainBinding binding;
    private int userId = 1;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //userId = sharedPreferences.getInt("user_id", -1);

        binding = UserMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<PCR> listData = getListData(userId);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, listData));

        binding.bottomContainer.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMain.this, UserMain.class);
                startActivity(intent);
            }
        });

        binding.bottomContainer.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMain.this, AdminEditPcr.class);
                startActivity(intent);
            }
        });

        binding.bottomContainer.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("UserMain", "SETT CLICK");
                Intent intent = new Intent(UserMain.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }

    public ArrayList<PCR> getListData(int userId) {
        try {
            ApiManager connectionRest = new ApiManager();
            //connectionRest.setUrl("?userId=" + userId); // Add user ID as a query parameter
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            if (listJsonObjs != null) {
                connectionRest.onPostExecute(" list of object: " + listJsonObjs);
                return connectionRest.parse(listJsonObjs, userId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}