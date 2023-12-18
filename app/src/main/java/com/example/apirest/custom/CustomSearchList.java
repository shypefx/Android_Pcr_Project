package com.example.apirest.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.apirest.R;
import com.example.apirest.classes.Pcr;

import java.util.ArrayList;

public class CustomSearchList extends ArrayAdapter<Pcr> {

    private final Context context;
    private final ArrayList<Pcr> pcrList;

    public CustomSearchList(Context context, ArrayList<Pcr> pcrList) {
        super(context, R.layout.custom_pcr_list, pcrList);
        this.context = context;
        this.pcrList = pcrList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_pcr_list, parent, false);

        TextView idTextView = rowView.findViewById(R.id.idTextView);
        TextView statusTextView = rowView.findViewById(R.id.statusTextView);
        TextView dateTextView = rowView.findViewById(R.id.dateTextView);

        Pcr pcr = pcrList.get(position);

        idTextView.setText("ID: " + pcr.getId_pcr());
        statusTextView.setText("Status: " + pcr.getStatut());
        dateTextView.setText("Date: " + pcr.getDate());

        // Change row background color based on status (example: red for "POSITIF")
        if ("POSITIF".equals(pcr.getStatut())) {
            rowView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        }

        return rowView;
    }

    public void updateData(ArrayList<Pcr> pcrList) {
        this.pcrList.clear();
        this.pcrList.addAll(pcrList);
        notifyDataSetChanged();
    }
}
