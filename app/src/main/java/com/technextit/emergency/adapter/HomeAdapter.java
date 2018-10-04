package com.technextit.emergency.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.technextit.emergency.MainActivity;
import com.technextit.emergency.R;
import com.technextit.emergency.model.Service;
//import com.technextit.emergencybangladesh.emergency.data.EmergencyActivity;

import java.util.ArrayList;

/**
 * Created by DELL on 6/26/2015.
 */
public class HomeAdapter extends BaseAdapter {

    Activity context;
    private ArrayList<Service> serviceItems;

    public HomeAdapter(Activity context, ArrayList<Service> serviceItems){
        this.context = context;
        this.serviceItems = serviceItems;
    }

    @Override
    public int getCount() {
        return serviceItems.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.service_list_item,parent,false);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.serviceIcon);
            viewHolder.titleText = (TextView) convertView.findViewById(R.id.serviceTitle);
            viewHolder.descriptionText = (TextView) convertView.findViewById(R.id.serviceDescription);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Service service = (Service) getItem(position);

        viewHolder.titleText.setText(service.getName());
        viewHolder.descriptionText.setText(service.getDescription());
        viewHolder.icon.setImageResource(R.drawable.ambulance_service_icon);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("servicePosition", position);
                context.startActivity(intent);
            }
        });

        return convertView;

    }

    private class ViewHolder{
        public ImageView icon;
        public TextView titleText;
        public TextView descriptionText;
    }

    public ArrayList<Service> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(ArrayList<Service> serviceItems) {
        this.serviceItems = serviceItems;
    }
}
