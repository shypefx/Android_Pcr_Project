package com.example.apirest;

import org.json.JSONObject;

public class PCR {

    private final String id_pcr;
    private final String id_pharmacie;
    private final String statut;
    private final String date;

    public PCR(JSONObject jObject) {
        this.id_pcr = jObject.optString("id_pcr");
        this.id_pharmacie = jObject.optString("id_pharmacie"); // Added id_pharmacie field
        this.statut = jObject.optString("statut"); // Added statut field
        this.date = jObject.optString("date");
    }


    public String getId_pcr() { return id_pcr; }
    public String getId_pharmacie() { return id_pharmacie; } // Added getter method for id_pharmacie field
    public String getStatut() { return statut; } // Added getter method for statut field
    public String getDate() { return date; }
}
