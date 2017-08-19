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
import com.zaita.aliyounes.gsbvc2017.adapters.BrandsAdapter;
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chawach on 8/14/2017.
 */

public class BrandsFragment extends Fragment {
    public static final String TAG = BrandsFragment.class.getSimpleName();
    RecyclerView recyclerView_brands;
    BrandsAdapter adapter;
    public BrandsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brands, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }
    private void setupViews(View rootView) {
        recyclerView_brands = (RecyclerView) rootView.findViewById(R.id.recyclerView_brands);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new BrandsAdapter(getDummyBrands());
        recyclerView_brands.setAdapter(adapter);
        recyclerView_brands.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    //Create dummy Brands
    private List<Brand> getDummyBrands() {
        List<Brand> dummyBrands = new ArrayList<>();
        dummyBrands.add(new Brand("Brand 1"));
        dummyBrands.add(new Brand("Brand 2"));
        dummyBrands.add(new Brand("Brand 3"));
        dummyBrands.add(new Brand("Brand 4"));
        dummyBrands.add(new Brand("Brand 5"));
        dummyBrands.add(new Brand("Brand 6"));
        return dummyBrands;
    }
}
