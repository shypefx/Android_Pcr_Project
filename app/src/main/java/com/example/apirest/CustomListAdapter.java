package com.example.apirest;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<PCR> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, ArrayList<PCR> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.pcr_custom_list_view, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textId);
            holder.id_pharmacie = (TextView) convertView.findViewById(R.id.textIdPcr);
            holder.statut = (TextView) convertView.findViewById(R.id.textStatut);
            holder.date = (TextView) convertView.findViewById(R.id.textDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.v("position",""+position);
        }

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(15,200,28));
        }

        PCR test_pcr = this.listData.get(position);
        holder.id.setText(""+test_pcr.getId_pcr());
        holder.id_pharmacie.setText(test_pcr.getId_pharmacie());
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
