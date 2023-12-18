package com.example.apirest.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.example.apirest.api.ApiManager;
import com.example.apirest.R;
import com.example.apirest.classes.Users;
import com.example.apirest.databinding.ActivityProfileBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private int userId;
    private String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id_user", -1);
        userRole = sharedPreferences.getString("role", "");

        Users u = getUser(userId);

        EditText etIdPharmacie = (EditText)findViewById(R.id.editIdPharmacie);
        etIdPharmacie.setText(u.getId_pharmacie()+"");

        EditText etNom = (EditText)findViewById(R.id.editNom);
        etNom.setText(u.getNom());

        EditText etPrenom = (EditText)findViewById(R.id.editPrenom);
        etPrenom.setText(u.getPrenom());

        EditText etEmail = (EditText)findViewById(R.id.editEmail);
        etEmail.setText(u.getEmail());

        EditText etPassword = (EditText)findViewById(R.id.textPassword);
        etPassword.setText(u.getPassword());
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idPharmacie = Integer.parseInt(etIdPharmacie.getText().toString());
                String nom = etNom.getText().toString();
                String prenom = etPrenom.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                try {
                    ApiManager connectionRest = new ApiManager();

                    JSONObject user = new JSONObject();
                    user.put("id", u.getId());
                    user.put("id_user", userId);
                    user.put("id_pharmacie", idPharmacie);
                    user.put("role", u.getRole());
                    user.put("nom", nom);
                    user.put("prenom", prenom);
                    user.put("email", email);
                    user.put("password", password);
                    connectionRest.setObj(user);
                    connectionRest.setAction("users");
                    Log.d("Generated JSON", user.toString());

                    if(userId!=0) { // Modification
                        Log.v("Edit","PUT");
                        connectionRest.execute("PUT");
                    }else{ // Creation
                        Log.v("Edit","POST");
                        connectionRest.execute("POST");
                    }

                    Toast.makeText(ProfileActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileActivity.this, "JSON Exception", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public Users getUser(int id) {
        List<Users> users = getUsersList();
        for (Users user : users) {
            if (user.getId_user() == id) {
                return user;
            }
        }
        return null;
    }


    public ArrayList<Users> getUsersList() {
        try {
            ApiManager connectionRest = new ApiManager();
            connectionRest.setAction("users"); // Set the action to "users"
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();

            if (listJsonObjs != null) {
                connectionRest.onPostExecute("list of object : " + listJsonObjs);
                ArrayList<Users> users = parseUsersData(listJsonObjs);
                return users;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Users> parseUsersData(final String json) {
        try {
            final ArrayList<Users> users = new ArrayList<>();
            final JSONArray jUserArray = new JSONArray(json);
            for (int i = 0; i < jUserArray.length(); i++) {
                JSONObject jUser = jUserArray.optJSONObject(i);
                Users user = Users.fromJson(jUser.toString());
                users.add(user);
            }
            return users;
        } catch (JSONException e) {
            Log.v("TAG", "[JSONException] e : " + e.getMessage());
        }
        return null;
    }


}