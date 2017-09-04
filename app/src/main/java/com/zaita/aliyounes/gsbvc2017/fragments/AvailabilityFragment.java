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
import com.zaita.aliyounes.gsbvc2017.adapters.AvailabilitiesAdapter;
import com.zaita.aliyounes.gsbvc2017.pojos.Availability;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailabilityFragment extends Fragment {

    public static final String TAG = AvailabilityFragment.class.getSimpleName();
    RecyclerView recyclerView_availabilities;
    AvailabilitiesAdapter adapter;
    public AvailabilityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_availabilities, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }
    private void setupViews(View rootView) {
        recyclerView_availabilities = (RecyclerView) rootView.findViewById(R.id.recyclerView_availabilities);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new AvailabilitiesAdapter(getDummyAvailabilities());
        recyclerView_availabilities.setAdapter(adapter);
        recyclerView_availabilities.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    //Create dummy branches
    private List<Availability> getDummyAvailabilities() {
        List<Availability> dummyAvailabilities = new ArrayList<>();
        dummyAvailabilities.add(new Availability("Branch 1" , "Product 1" , 123));
        dummyAvailabilities.add(new Availability("Branch 2" , "Product 2" , 123));
        dummyAvailabilities.add(new Availability("Branch 3" , "Product 3" , 123));
        dummyAvailabilities.add(new Availability("Branch 4" , "Product 4" , 123));
        dummyAvailabilities.add(new Availability("Branch 5" , "Product 5" , 123));
        dummyAvailabilities.add(new Availability("Branch 6" , "Product 6" , 123));
        return dummyAvailabilities;
    }
}
