package com.example.apirest.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.ListView;

import com.example.apirest.admin.AdminEditPcr;
import com.example.apirest.api.ApiManager;
import com.example.apirest.custom.CustomListAdapter;
import com.example.apirest.R;
import com.example.apirest.pages.SettingsActivity;
import com.example.apirest.classes.Pcr;
import com.example.apirest.databinding.UserMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserMain extends AppCompatActivity {
    private UserMainBinding binding;
    private int userId;
    private String type_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id_user", -1);
        type_role = sharedPreferences.getString("role", "");
        Log.v("usermain","id_user = "+ userId);

        binding = UserMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Pcr> listData = getListData(userId);
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

    public ArrayList<Pcr> getListData(int user_id) {
        try {
            ApiManager connectionRest = new ApiManager();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            Log.v("listjson =","data : "+listJsonObjs.toString());
            if(listJsonObjs != null) {
                    connectionRest.onPostExecute(" list of object : "+ listJsonObjs.toString());
                    return connectionRest.parse(listJsonObjs.toString(), type_role, user_id);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}