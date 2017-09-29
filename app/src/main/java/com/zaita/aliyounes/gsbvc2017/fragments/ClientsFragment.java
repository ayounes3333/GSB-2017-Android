package com.zaita.aliyounes.gsbvc2017.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.adapters.ClientsAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.network.apis.ClientsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Client;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Chawach on 8/14/2017.
 */

public class ClientsFragment extends Fragment {
    public static final String TAG = ClientsFragment.class.getSimpleName();
    RecyclerView recyclerView_clients;
    ClientsAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    CompositeDisposable compositeDisposable;
    List<Client> clients;
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
        clients = new ArrayList<>();
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        if (GSBApplication.isDummyData()) {
            clients.addAll(getDummyClients());
            adapter.notifyDataSetChanged();
        } else {
            fetchClients();
        }
    }
    private void setupViews(View rootView) {
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        recyclerView_clients = (RecyclerView) rootView.findViewById(R.id.recyclerView_clients);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noClients);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new ClientsAdapter(clients);
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
    //Get All Clients from the server
    private void fetchClients() {
        clients.clear();
        adapter.notifyDataSetChanged();
        relativeLayout_noData.setVisibility(View.GONE);
        relativeLayout_noInternet.setVisibility(View.GONE);
        relativeLayout_serverError.setVisibility(View.GONE);
        progressBarLoadingData.setVisibility(View.VISIBLE);
        ClientsNetworkCalls.getAllClients().subscribe(new Observer<List<Client>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeed
            @Override
            public void onNext(List<Client> value) {
                //Value is the return of the API call
                //In this case it is a list of clients
                //For more info see Mohammad faour's code (ManagedObjects/ClientController.java)
                Log.i("Get Clients" , value.size()+" Client");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    clients.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fail
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(View.GONE);
                Log.e("Get Clients" , "Error getting clients" , e);
                if(e instanceof SocketException || e instanceof IOException) {
                    Toast.makeText(getContext() , R.string.no_internet , Toast.LENGTH_SHORT).show();
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.VISIBLE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext() , e.getMessage() , Toast.LENGTH_LONG).show();
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onComplete() {
                progressBarLoadingData.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        fetchClients();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
