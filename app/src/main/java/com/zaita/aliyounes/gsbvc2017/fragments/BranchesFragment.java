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
import com.zaita.aliyounes.gsbvc2017.adapters.BranchesAdapter;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchesFragment extends Fragment {

    public static final String TAG = BranchesFragment.class.getSimpleName();
    RecyclerView recyclerView_branches;
    BranchesAdapter adapter;
    public BranchesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_branches, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }
    private void setupViews(View rootView) {
        recyclerView_branches = (RecyclerView) rootView.findViewById(R.id.recyclerView_branches);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new BranchesAdapter(getDummyBranches());
        recyclerView_branches.setAdapter(adapter);
        recyclerView_branches.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    //Create dummy branches
    private List<Branch> getDummyBranches() {
        List<Branch> dummyBranches = new ArrayList<>();
        dummyBranches.add(new Branch("Branch 1" , "00961 1 123 456" , "Beirut - Hamra"));
        dummyBranches.add(new Branch("Branch 2" , "00961 1 123 456" , "Beirut - Verdun"));
        dummyBranches.add(new Branch("Branch 3" , "00961 1 123 456" , "Beirut - Ashrafiyeh"));
        dummyBranches.add(new Branch("Branch 4" , "00961 1 123 456" , "Beirut - Mazra'a"));
        dummyBranches.add(new Branch("Branch 5" , "00961 1 123 456" , "Beirut - Dahye"));
        dummyBranches.add(new Branch("Branch 6" , "00961 1 123 456" , "Beirut - Sen El Fil"));
        return dummyBranches;
    }
}
