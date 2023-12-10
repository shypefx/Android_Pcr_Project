package com.example.apirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = (EditText)findViewById(R.id.user_email);
        userPassword = (EditText)findViewById(R.id.user_password);
        buttonLogin = (Button)findViewById(R.id.button_login);

        userEmail.setText("");
        userPassword.setText("");

        try{
            JSONObject jAuth = new JSONObject();
            jAuth.put("email", "tristan.beaudoin@esme.fr");
            jAuth.put("password", "85qLSAf5Md");
            jAuth.put("app", "MNA");
            ApiManager connectionRest = new ApiManager();
            connectionRest.setObj(jAuth);
            connectionRest.setAction("auth");
            connectionRest.execute("POST");
             token = connectionRest.get();
            Param.getInstance().setToken(token);
        }catch (ExecutionException | JSONException e){
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Users> usersMap = new HashMap<>();
                ArrayList<Users> usersList = getUsersList();
                for (Users user : usersList) {
                    usersMap.put(user.getEmail(), user);
                }
                String enteredEmail = userEmail.getText().toString();
                String enteredPassword = userPassword.getText().toString();
                Users user = usersMap.get(enteredEmail);

                if (user != null && enteredPassword.equals(user.getPassword())) {

                    if (user.getRole().equals("user")) {
                        Param.getInstance().setToken(token);
                        Intent intent = new Intent(LoginActivity.this, UserMain.class);
                        startActivity(intent);
                    } else if (user.getRole().equals("admin")) {
                        Param.getInstance().setToken(token);
                        Intent intent = new Intent(LoginActivity.this, AdminMain.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

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