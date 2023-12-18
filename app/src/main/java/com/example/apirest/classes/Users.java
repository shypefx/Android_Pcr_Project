package com.example.apirest.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Users {

    private int id;
    private int id_user;
    private int id_pharmacie;
    private String role;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public Users(JSONObject jsonObject) {
        id = jsonObject.optInt("id",-1);
        id_user = jsonObject.optInt("id_user",-1);
        id_pharmacie = jsonObject.optInt("id_pharmacie",-1);
        role = jsonObject.optString("role");
        nom = jsonObject.optString("nom");
        prenom = jsonObject.optString("prenom");
        email = jsonObject.optString("email");
        password = jsonObject.optString("password");
    }

    public Users(int id_user, int id_pharmacie, String role, String nom, String prenom, String email, String password) {
        this.id = id_user;
        this.id_user = id_user;
        this.id_pharmacie = id_pharmacie;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
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

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_user", id_user);
        jsonObject.put("id_pharmacie", id_pharmacie);
        jsonObject.put("role", role);
        jsonObject.put("nom", nom);
        jsonObject.put("prenom", prenom);
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_pharmacie=" + id_pharmacie +
                ", role='" + role + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
