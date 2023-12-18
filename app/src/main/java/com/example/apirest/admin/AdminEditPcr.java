package com.example.apirest.admin;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.apirest.R;
import com.example.apirest.api.ApiManager;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminEditPcr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_pcr);

        final int id = getIntent().getIntExtra("id", 0);
        int id_pcr = getIntent().getIntExtra("id_pcr",0);
        int id_pharmacie = getIntent().getIntExtra("id_pharmacie",0);
        int id_user = getIntent().getIntExtra("id_user",0);
        String statut = getIntent().getStringExtra("statut");
        String date = getIntent().getStringExtra("date");

        final EditText idPcrTxt = findViewById(R.id.id_pcr_edited);
        final EditText idPharmacieTxt = findViewById(R.id.id_pharmacie_edited);
        final EditText statutTxt = findViewById(R.id.statut_pcr_edited);
        final EditText dateTxt = findViewById(R.id.date_pcr_edited);
        TextView idTxt = findViewById(R.id.textview_id);

        Button buttonCancel = findViewById(R.id.button_cancel);
        Button buttonOk = findViewById(R.id.button_ok);

        if (id != 0) {
            idTxt.setText(String.valueOf(id));
            idPcrTxt.setText(id_pcr+"");
            idPharmacieTxt.setText(id_pharmacie+"");
            statutTxt.setText(statut+"");
            dateTxt.setText(date+"");
            buttonCancel.setText("Supprimer");
            buttonOk.setText("Modifier");
        }

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != 0) { // Suppression
                    try {
                        ApiManager connectionRest = new ApiManager();
                        JSONObject product = new JSONObject();
                        product.put("id", id);
                        connectionRest.setObj(product);
                        connectionRest.execute("DELETE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(AdminEditPcr.this, AdminMain.class);
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ApiManager connectionRest = new ApiManager();
                    JSONObject product = new JSONObject();
                    Log.v("data",""+id);
                    Log.v("data",""+idPcrTxt.getText().toString());
                    Log.v("data",""+idPharmacieTxt.getText().toString());
                    Log.v("data",""+statutTxt.getText().toString());
                    Log.v("data",""+dateTxt.getText().toString());
                    if (id != 0) {
                        product.put("id", id);
                    }
                    product.put("id_pcr", Integer.parseInt(idPcrTxt.getText().toString()));
                    product.put("id_pharmacie", Integer.parseInt(idPharmacieTxt.getText().toString()));
                    product.put("id_user", id_user);
                    product.put("statut", statutTxt.getText().toString());
                    product.put("date", dateTxt.getText().toString());
                    connectionRest.setObj(product);

                    if (id != 0) { // Modification
                        connectionRest.execute("PUT");
                    } else { // Creation
                        connectionRest.execute("POST");
                    }

                    Intent intent = new Intent(AdminEditPcr.this, AdminMain.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
