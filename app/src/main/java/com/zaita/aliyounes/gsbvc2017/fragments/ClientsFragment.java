package com.zaita.aliyounes.gsbvc2017.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.adapters.ClientsAdapter;
import com.zaita.aliyounes.gsbvc2017.pojos.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chawach on 8/14/2017.
 */

public class ClientsFragment extends Fragment {
    public static final String TAG = ClientsFragment.class.getSimpleName();
    RecyclerView recyclerView_clients;
    ClientsAdapter adapter;
    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }
    private void setupViews(View rootView) {
        recyclerView_clients = (RecyclerView) rootView.findViewById(R.id.recyclerView_clients);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new ClientsAdapter(getDummyClients());
        recyclerView_clients.setAdapter(adapter);
        recyclerView_clients.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    //Create dummy Clients
    private List<Client> getDummyClients() {
        List<Client> dummyClients = new ArrayList<>();
        dummyClients.add(new Client("Client 1" , "00961 1 123456" , "+961 76 123 456" , "Mr" , "Beirut - Hamra"      , "client1@example.com"));
        dummyClients.add(new Client("Client 2" , "00961 1 123456" , "+961 76 123 456" , "Mr" , "Beirut - Verdun"     , "client2@example.com"));
        dummyClients.add(new Client("Client 3" , "00961 1 123456" , "+961 76 123 456" , "Mr" , "Beirut - Ashrafiyeh" , "client3@example.com"));
        dummyClients.add(new Client("Client 4" , "00961 1 123456" , "+961 76 123 456" , "Mr" , "Beirut - Mazra'a"    , "client4@example.com"));
        dummyClients.add(new Client("Client 5" , "00961 1 123456" , "+961 76 123 456" , "Mr" , "Beirut - Dahye"      , "client5@example.com"));
        dummyClients.add(new Client("Client 6" , "00961 1 123456" , "+961 76 123 456" , "Mr" , "Beirut - Sen El Fil" , "client6@example.com"));
        return dummyClients;
    }
}
