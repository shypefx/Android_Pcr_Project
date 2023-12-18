package com.example.apirest.admin;// com.example.apirest.admin.AdminAddPcr

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apirest.R;
import com.example.apirest.api.ApiManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminAddPcrTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText idPcrTxt = findViewById(R.id.id_pcr_add);
        final EditText statutTxt = findViewById(R.id.statut_pcr_add);
        final EditText dateTxt = findViewById(R.id.date_pcr_add);
        final EditText userIdTxt = findViewById(R.id.user_id_pcr_add);
        final EditText pharmacieIdTxt = findViewById(R.id.pharmacie_id_pcr_add);
        Button buttonCancel = findViewById(R.id.button_cancel_add);
        Button buttonOk = findViewById(R.id.button_ok_add);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddPcrTest.this, AdminMain.class);
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Create a JSON object with PCR data
                    JSONObject pcrData = new JSONObject();
                    pcrData.put("id_pcr", Integer.parseInt(idPcrTxt.getText().toString()));
                    pcrData.put("id_pharmacie", Integer.parseInt(pharmacieIdTxt.getText().toString()));
                    pcrData.put("id_user", Integer.parseInt(userIdTxt.getText().toString()));
                    pcrData.put("statut", statutTxt.getText().toString());
                    pcrData.put("date", dateTxt.getText().toString());
                    // Send the data to the API server
                    ApiManager connectionRest = new ApiManager();
                    connectionRest.setObj(pcrData);
                    connectionRest.execute("POST");

                    // Navigate back to the main admin activity after adding the PCR
                    Intent intent = new Intent(AdminAddPcrTest.this, AdminMain.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
