package com.example.apirest;

import org.json.JSONException;
import org.json.JSONObject;

public class Users {
    private int id_user;
    private String role;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int id_pcr;
    private String statut;
    private String date;

    public Users(JSONObject jsonObject) {
        id_user = jsonObject.optInt("id_user");
        role = jsonObject.optString("role");
        nom = jsonObject.optString("nom");
        prenom = jsonObject.optString("prenom");
        email = jsonObject.optString("email");
        password = jsonObject.optString("password");
        id_pcr = jsonObject.optInt("id_pcr");
        statut = jsonObject.optString("statut");
        date = jsonObject.optString("date");
    }

    public int getId_user() {
        return id_user;
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

    public int getId_pcr() {
        return id_pcr;
    }

    public void setId_pcr(int id_pcr) {
        this.id_pcr = id_pcr;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static Users fromJson(String jsonString) throws JSONException {
        return new Users(new JSONObject(jsonString));
    }
}
