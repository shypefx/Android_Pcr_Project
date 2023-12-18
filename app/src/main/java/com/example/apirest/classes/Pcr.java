package com.example.apirest.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Pcr {
    private int id;
    private int id_pcr;
    private int id_pharmacie;
    private int id_user;
    private String statut;
    private String date;


    public Pcr(JSONObject jsonObject) {
        id = jsonObject.optInt("id", -1);
        id_pcr = jsonObject.optInt("id_pcr");
        id_pharmacie = jsonObject.optInt("id_pharmacie", -1);
        id_user = jsonObject.optInt("id_user", -1);
        statut = jsonObject.optString("statut");
        date = jsonObject.optString("date");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pcr() {
        return id_pcr;
    }

    public void setId_pcr(int id_pcr) {
        this.id_pcr = id_pcr;
    }

    public int getId_pharmacie() {
        return id_pharmacie;
    }

    public void setId_pharmacie(int id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public static Pcr fromJson(String jsonString) throws JSONException {
       return new Pcr(new JSONObject(jsonString));
    }

    // Other methods...
}
