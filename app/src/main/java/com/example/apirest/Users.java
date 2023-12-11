package com.example.apirest;

import org.json.JSONException;
import org.json.JSONObject;

public class Users {
    private int id_user;
    private int id_pharmacie;
    private String role;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public Users(JSONObject jsonObject) {
        id_user = jsonObject.optInt("id_user");
        id_pharmacie = jsonObject.optInt("id_pharmacie");
        role = jsonObject.optString("role");
        nom = jsonObject.optString("nom");
        prenom = jsonObject.optString("prenom");
        email = jsonObject.optString("email");
        password = jsonObject.optString("password");
    }

    public int getId_user() {
        return id_user;
    }
    public int getId_pharmacie() {
        return id_pharmacie;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public static Users fromJson(String jsonString) throws JSONException {
        return new Users(new JSONObject(jsonString));
    }
}
