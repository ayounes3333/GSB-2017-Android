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
import com.zaita.aliyounes.gsbvc2017.adapters.SuppliersAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.network.apis.SuppliersNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Supplier;

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

public class SuppliersFragment extends Fragment {
    public static final String TAG = SuppliersFragment.class.getSimpleName();
    RecyclerView recyclerView_suppliers;
    SuppliersAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    private CompositeDisposable compositeDisposable;
    private List<Supplier> suppliers;

    public SuppliersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suppliers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        suppliers = new ArrayList<>();
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        if(GSBApplication.isDummyData()) {
            suppliers.addAll(getDummySuppliers());
            adapter.notifyDataSetChanged();
        } else {
            fetchSuppliers();
        }
    }
    private void setupViews(View rootView) {
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        recyclerView_suppliers = (RecyclerView) rootView.findViewById(R.id.recyclerView_suppliers);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noSuppliers);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new SuppliersAdapter(suppliers);
        recyclerView_suppliers.setAdapter(adapter);
        recyclerView_suppliers.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    private void fetchSuppliers() {
        suppliers.clear();
        adapter.notifyDataSetChanged();
        relativeLayout_noData.setVisibility(View.GONE);
        relativeLayout_noInternet.setVisibility(View.GONE);
        relativeLayout_serverError.setVisibility(View.GONE);
        progressBarLoadingData.setVisibility(View.VISIBLE);
        SuppliersNetworkCalls.getAllSuppliers().subscribe(new Observer<List<Supplier>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeeds
            @Override
            public void onNext(List<Supplier> value) {
                //Value is the return of the API call
                //In this case it is a list of Suppliers
                //For more info see Mohammad faour's code (ManagedObjects/SupplierController.java)
                Log.i("Get Suppliers" , value.size()+" Supplier");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    suppliers.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fails
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(View.GONE);
                Log.e("Get Suppliers" , "Error getting Suppliers" , e);
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

    //Create dummy Suppliers
    private List<Supplier> getDummySuppliers() {
        List<Supplier> dummySuppliers = new ArrayList<>();
        dummySuppliers.add(new Supplier("supplier1@example.com" , "00961 1 123 456" , "Supplier 1" , "+961 78 912 345" , "Beirut - Adr 1"));
        dummySuppliers.add(new Supplier("supplier1@example.com" , "00961 1 123 456" , "Supplier 2" , "+961 78 912 345" , "Beirut - Adr 2"));
        dummySuppliers.add(new Supplier("supplier1@example.com" , "00961 1 123 456" , "Supplier 3" , "+961 78 912 345" , "Beirut - Adr 3"));
        dummySuppliers.add(new Supplier("supplier1@example.com" , "00961 1 123 456" , "Supplier 4" , "+961 78 912 345" , "Beirut - Adr 4"));
        dummySuppliers.add(new Supplier("supplier1@example.com" , "00961 1 123 456" , "Supplier 5" , "+961 78 912 345" , "Beirut - Adr 5"));
        dummySuppliers.add(new Supplier("supplier1@example.com" , "00961 1 123 456" , "Supplier 6" , "+961 78 912 345" , "Beirut - Adr 6"));
        return dummySuppliers;
    }
    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        fetchSuppliers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
