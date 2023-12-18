package com.example.apirest.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apirest.api.ApiManager;
import com.example.apirest.custom.CustomSearchList;
import com.example.apirest.R;
import com.example.apirest.classes.Pcr;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchPcrActivity extends AppCompatActivity {

    private ArrayList<Pcr> listData;

    private EditText searchText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchText = findViewById(R.id.editTextSearchPcr);
        Button btnSearch = findViewById(R.id.btnSearchPcr);
        listView = findViewById(R.id.listViewPcr);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPcrById();
            }
        });

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Pcr selectedPcr = listData.get(position);
        });
    }

    private void searchPcrById() {
        if (searchText.getText().length() > 0) {
            try {
                int pcrId = Integer.parseInt(searchText.getText().toString());
                listData = getPcrById(pcrId);
                displayPcrResults();
            } catch (NumberFormatException e) {
                // Handle invalid input (non-integer)
                e.printStackTrace();
            }
        }
    }

    private void displayPcrResults() {
        CustomSearchList customListAdapter = new CustomSearchList(this, listData);
        listView.setAdapter(customListAdapter);
    }

    private ArrayList<Pcr> getPcrById(int pcrId) {
        try {
            ApiManager connectionRest = new ApiManager();
            connectionRest.setAction("pcr");
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();

            if (listJsonObjs != null) {
                return parsePcrData(listJsonObjs, pcrId);
            }

            return new ArrayList<>();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Pcr> parsePcrData(String json, int pcrId) {
        ArrayList<Pcr> pcrList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Pcr pcr = new Pcr(jsonObject);
                if (pcr.getId_pcr() == pcrId) {
                    pcrList.add(pcr);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pcrList;
    }
}
