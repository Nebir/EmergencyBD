package com.technextit.emergency.adapter;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.technextit.emergency.R;
import com.technextit.emergency.model.Division;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sohel.technext on 7/6/2015.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {

    Context context;
    private List<Division> divisions;

    public NavigationDrawerListAdapter(Context context, List<Division> divisions) {
        this.context = context;
        this.divisions = divisions;
    }

    @Override
    public int getCount() {
        return divisions.size();
    }

    @Override
    public Object getItem(int position) {
        return divisions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.division_list_item,parent,false);
            viewHolder.divisionName = (TextView) convertView.findViewById(R.id.divisionTitle);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Division division = (Division) getItem(position);

        viewHolder.divisionName.setText(division.getName());

        return convertView;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    private class ViewHolder {
        public TextView divisionName;
    }
}
