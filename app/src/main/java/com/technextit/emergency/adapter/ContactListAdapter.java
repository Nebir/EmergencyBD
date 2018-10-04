package com.technextit.emergency.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technextit.emergency.MainActivity;
import com.technextit.emergency.MapsActivity;
import com.technextit.emergency.R;
import com.technextit.emergency.model.Contact;

import java.util.List;

/**
 * Created by DELL on 7/7/2015.
 */
public class ContactListAdapter extends BaseAdapter {

    Activity context;
    private List<Contact> contacts;

    public ContactListAdapter(Activity context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
            viewHolder.contactName = (TextView) convertView.findViewById(R.id.contactName);
            viewHolder.contactNumber = (TextView) convertView.findViewById(R.id.contactNumber);
            viewHolder.callButton = (Button) convertView.findViewById(R.id.callButton);
            viewHolder.locationButton = (Button) convertView.findViewById(R.id.locationButton);

            int width = MainActivity.SCREEN_WIDTH / 8;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            viewHolder.callButton.setLayoutParams(params);
            viewHolder.locationButton.setLayoutParams(params);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = (Contact) getItem(position);

        viewHolder.contactName.setText(contact.getName());
        viewHolder.contactNumber.setText(contact.getPhone_number());
        viewHolder.callButton.setBackgroundResource(R.drawable.contacts_icon);
        viewHolder.locationButton.setBackgroundResource(R.drawable.addresses_icon);

        viewHolder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = (Contact) getItem(position);

                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + contact.getPhone_number()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });

        viewHolder.locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = (Contact) getItem(position);
                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?mode=driving&daddr="+contact.getLat()+","+contact.getLon());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(mapIntent);
                }
            }
        });


        return convertView;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public class ViewHolder {
        public TextView contactName;
        public TextView contactNumber;
        public Button callButton;
        public Button locationButton;
    }


}
