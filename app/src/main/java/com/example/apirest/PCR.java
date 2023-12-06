package com.example.apirest;

import org.json.JSONObject;

public class PCR {

    private final int id;
    private final String id_pcr;
    private final String statut;
    private final String date;

    public PCR(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.id_pcr = jObject.optString("id_pcr");
        this.statut = jObject.optString("statut");
        this.date = jObject.optString("date");
    }

    public int getId() { return id; }
    public String getId_pcr() { return id_pcr; }
    public String getStatut() { return statut; }
    public String getDate() { return date; }
}
