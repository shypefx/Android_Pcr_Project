package com.example.apirest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest.databinding.AdminMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AdminMain extends AppCompatActivity {
    private AdminMainBinding binding;
    private int pharmaId;
    private String type_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        pharmaId = sharedPreferences.getInt("id_pharmacie", -1);
        type_role = sharedPreferences.getString("role", "");
        Log.v("adminmain","id_pharmacie = "+ pharmaId);

        binding = AdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<PCR> listData = getListData(pharmaId);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, listData));

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                PCR upload = (PCR) o;
                Intent intent = new Intent(AdminMain.this, AdminEditPcr.class);
                intent.putExtra("id_pcr", upload.getId_pcr());
                intent.putExtra("id_pharmacie", upload.getId_pharmacie());
                intent.putExtra("statut", upload.getStatut());
                intent.putExtra("date", upload.getDate());
                startActivity(intent);
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, AdminEditPcr.class);
                startActivity(intent);
            }
        });

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, AdminMain.class);
                startActivity(intent);
            }
        });

        binding.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMain.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    public ArrayList<PCR> getListData(int id){
        try{
            ApiManager connectionRest = new ApiManager();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            if(listJsonObjs != null) {
                    connectionRest.onPostExecute(" list of object : "+ listJsonObjs);
                    return connectionRest.parse(listJsonObjs, type_role, id);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}