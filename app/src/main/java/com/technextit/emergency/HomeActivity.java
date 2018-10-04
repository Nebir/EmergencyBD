package com.technextit.emergency;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.technextit.emergency.adapter.HomeAdapter;
import com.technextit.emergency.app.AppController;
import com.technextit.emergency.http.Client;
import com.technextit.emergency.listener.VolleyResponseHandler;
import com.technextit.emergency.model.Divisions;
import com.technextit.emergency.model.Service;
import com.technextit.emergency.model.Services;
import com.technextit.emergency.utils.URLUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 7/7/2015.
 */
public class HomeActivity extends AppCompatActivity{

    ListView serviceListView;
    public List<Service> services;
    HomeAdapter homeAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set first page viewed by user after app installed
        setContentView(R.layout.activity_home);

        // as the first page is a listView so we set this listView
        serviceListView = (ListView) findViewById(R.id.serviceList);

        // adapter is used to show custom listView.
        ArrayList<Service> services = new ArrayList<Service>();
        homeAdapter = new HomeAdapter(HomeActivity.this, services);

        serviceListView.setAdapter(homeAdapter);
//
//        serviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

         progressDialog = new ProgressDialog(HomeActivity.this);



        HashMap<String, String> params = new HashMap<String, String>();
        progressDialog.show();
        Client.post(URLUtils.URL_SERVICES, params, Services.class, null, new VolleyResponseHandler<Services>() {

            @Override
            public void onSuccess(Services response) {
                if (response.services != null){
                    homeAdapter.setServiceItems(response.services);
                    homeAdapter.notifyDataSetChanged();

                    AppController.getInstance().setServices(response.services);
                }

                progressDialog.hide();

                Log.e("Key", "SIze of divisions-- " + response.services.size());
                Toast.makeText(HomeActivity.this, "Test--> " + response.services.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(HomeActivity.this, "Test--> "+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Key", "error-- "+error.getMessage());
                progressDialog.hide();
            }
        });



        // for fragment (ex: getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new MyFragment()).commit(); )
    }

}
