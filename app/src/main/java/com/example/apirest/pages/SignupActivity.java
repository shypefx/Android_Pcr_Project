package com.example.apirest.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.apirest.api.ApiManager;
import com.example.apirest.R;
import com.example.apirest.classes.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class SignupActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private Button buttonLogin;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button btnRegister = findViewById(R.id.btn_register);
        ImageButton btnBack = findViewById(R.id.iv_backButton);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idUser = ThreadLocalRandom.current().nextInt(100000, 999995 + 1);

                EditText etIdPharmacie = findViewById(R.id.et_id_pharmacie);
                String idPharmacieStr = etIdPharmacie.getText().toString();
                int idPharmacie = Integer.parseInt(idPharmacieStr);


                EditText spRole = findViewById(R.id.et_role);
                String role = spRole.getText().toString();

                EditText etNom = findViewById(R.id.et_nom);
                String nom = etNom.getText().toString();

                EditText etPrenom = findViewById(R.id.et_prenom);
                String prenom = etPrenom.getText().toString();

                EditText etEmail = findViewById(R.id.et_email);
                String email = etEmail.getText().toString();

                EditText etPassword = findViewById(R.id.et_password);
                String password = etPassword.getText().toString();

                Users user = new Users(idUser, idPharmacie, role, nom, prenom, email, password);
                JSONObject jsonObject = null;
                try {
                    jsonObject = user.toJson();
                    ApiManager connectionRest = new ApiManager();
                    connectionRest.execute("POST");
                    connectionRest.setAction("users");
                    connectionRest.setObj(jsonObject);
                    String listJsonObjs = connectionRest.get();
                } catch (JSONException | ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }








}