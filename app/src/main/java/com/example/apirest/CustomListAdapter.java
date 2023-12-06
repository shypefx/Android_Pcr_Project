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
            holder.name = (TextView) convertView.findViewById(R.id.textIdPcr);
            holder.type = (TextView) convertView.findViewById(R.id.textStatut);
            holder.carburant = (TextView) convertView.findViewById(R.id.textDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.v("position",""+position);
        }

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(150,245,170));
        }

        PCR voiture = this.listData.get(position);
        holder.id.setText(""+voiture.getId());
        holder.name.setText(voiture.getId_pcr());
        holder.type.setText(voiture.getStatut());
        holder.carburant.setText(voiture.getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView id;
        TextView name;
        TextView type;
        TextView carburant;
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
