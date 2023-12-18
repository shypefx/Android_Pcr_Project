package com.example.apirest.custom;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apirest.R;
import com.example.apirest.classes.Pcr;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Pcr> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, ArrayList<Pcr> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.pcr_custom_list_view, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textHeaderId);
            holder.id_pharmacie = (TextView) convertView.findViewById(R.id.textHeaderIdPcr);
            holder.statut = (TextView) convertView.findViewById(R.id.textHeaderStatut);
            holder.date = (TextView) convertView.findViewById(R.id.textHeaderDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.v("position",""+position);
        }

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(220,220,220));
        }

        Pcr test_pcr = this.listData.get(position);
        holder.id.setText(""+test_pcr.getId_pcr());
        holder.id_pharmacie.setText(""+test_pcr.getId_pharmacie());
        holder.statut.setText(test_pcr.getStatut());
        holder.date.setText(test_pcr.getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView id;
        TextView id_pharmacie;
        TextView statut;
        TextView date;
        TextView price;
    }

    public int getCount() {
        return (listData!=null)?listData.size():0;
    }
    public Object getItem(int position) {
        return listData.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
}
