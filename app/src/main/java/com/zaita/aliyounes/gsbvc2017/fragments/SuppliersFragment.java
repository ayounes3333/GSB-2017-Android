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
import com.zaita.aliyounes.gsbvc2017.adapters.SuppliersAdapter;
import com.zaita.aliyounes.gsbvc2017.pojos.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chawach on 8/14/2017.
 */

public class SuppliersFragment extends Fragment {
    public static final String TAG = SuppliersFragment.class.getSimpleName();
    RecyclerView recyclerView_suppliers;
    SuppliersAdapter adapter;
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
        setupViews(view);
    }
    private void setupViews(View rootView) {
        recyclerView_suppliers = (RecyclerView) rootView.findViewById(R.id.recyclerView_suppliers);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new SuppliersAdapter(getDummySuppliers());
        recyclerView_suppliers.setAdapter(adapter);
        recyclerView_suppliers.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
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
}
