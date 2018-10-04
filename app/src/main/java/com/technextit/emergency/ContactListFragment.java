package com.technextit.emergency;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.technextit.emergency.adapter.ContactListAdapter;
import com.technextit.emergency.app.AppController;
import com.technextit.emergency.http.Client;
import com.technextit.emergency.listener.VolleyResponseHandler;
import com.technextit.emergency.model.Contact;
import com.technextit.emergency.model.Contacts;
import com.technextit.emergency.model.Division;
import com.technextit.emergency.model.Service;
import com.technextit.emergency.utils.URLUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment implements ActionBar.TabListener{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String SELECTED_SERVICE_POS = "selected_service_pos";

    public ContactListAdapter contactListAdapter;
    public List<Contact> contacts;
    public ListView listView;


    public static ContactListFragment newInstance(int selectedDivision, int selectedService) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, selectedDivision);
        args.putInt(SELECTED_SERVICE_POS, selectedService);
        fragment.setArguments(args);
        return fragment;
    }



    public ContactListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

//        int servicePos = savedInstanceState.getInt(SELECTED_SERVICE_POS);
//        int divisionPos = savedInstanceState.getInt(ARG_SECTION_NUMBER);

        listView = (ListView) view.findViewById(R.id.contactListView);


        contacts = new ArrayList<Contact>();

        contactListAdapter = new ContactListAdapter(getActivity(), contacts);
        listView.setAdapter(contactListAdapter);

        HashMap<String, String> params = new HashMap<String, String>();

//        Service service = AppController.getInstance().getServices().get(servicePos);
//        Division division = AppController.getInstance().getDivisions().get(divisionPos);
//
//        params.put("serviceId", ""+service.getId());
//        params.put("divisionId", ""+division.getId());


        Client.post(URLUtils.URL_CONTACTS, params, Contacts.class, null, new VolleyResponseHandler<Contacts>() {
            @Override
            public void onSuccess(Contacts response) {
                if (response.contacts != null) {
                    contactListAdapter.setContacts(response.contacts);
                }

                Log.e("Key", "SIze of divisions-- " + response.contacts.size());
                Toast.makeText(getActivity(), "Test--> " + response.contacts.size(), Toast.LENGTH_SHORT).show();
                contactListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getActivity(), "Test--> " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Key", "error-- " + error.getMessage());
            }
        });

        return view;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
