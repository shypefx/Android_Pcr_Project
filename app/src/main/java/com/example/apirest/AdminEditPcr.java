package com.example.apirest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AdminEditPcr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_pcr);
        final int id = getIntent().getIntExtra("id", 0);
        String id_pcr = getIntent().getStringExtra("id_pcr");
        String statut = getIntent().getStringExtra("statut");
        String date = getIntent().getStringExtra("date");
        final EditText IdPcrTxt = (EditText) findViewById(R.id.id_pcr_edited);
        final EditText typeTxt = (EditText) findViewById(R.id.statut_pcr_edited);
        final EditText dateEditTxt = (EditText) findViewById(R.id.date_pcr_edited);
        TextView idTxt = (TextView) findViewById(R.id.textview_id);
        Button buttonCancel = (Button) findViewById(R.id.button_cancel);
        Button buttonOk = (Button) findViewById(R.id.button_ok);

        if(id!=0){
            idTxt.setText(""+id);
            IdPcrTxt.setText(id_pcr);
            typeTxt.setText(statut);
            dateEditTxt.setText(date);
            buttonCancel.setText("Supprimer");
            buttonOk.setText("Modifier");
        }

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){ // Suppression
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
                Intent intent = new Intent(AdminEditPcr.this, UserMain.class);
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ApiManager connectionRest = new ApiManager();
                    JSONObject product = new JSONObject();
                    if(id!=0) {
                        product.put("id", id);
                    }
                    product.put("id_pcr", IdPcrTxt.getText().toString());
                    product.put("statut", typeTxt.getText().toString());
                    product.put("date", dateEditTxt.getText().toString());
                    connectionRest.setObj(product);

                    if(id!=0) { // Modification
                        connectionRest.execute("PUT");
                    }else{ // Creation
                        connectionRest.execute("POST");
                    }
                    Intent intent = new Intent(AdminEditPcr.this, UserMain.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}