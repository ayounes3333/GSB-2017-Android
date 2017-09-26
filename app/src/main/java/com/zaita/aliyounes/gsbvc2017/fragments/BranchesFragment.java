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
import com.zaita.aliyounes.gsbvc2017.adapters.BranchesAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.network.apis.BranchesNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class BranchesFragment extends Fragment {

    public static final String TAG = BranchesFragment.class.getSimpleName();
    RecyclerView recyclerView_branches;
    BranchesAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    private List<Branch> branches;
    private CompositeDisposable compositeDisposable;

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
        compositeDisposable = new CompositeDisposable();
        branches = new ArrayList<>();
        setupViews(view);
        if (GSBApplication.isDummyData()) {
            branches.addAll(getDummyBranches());
            adapter.notifyDataSetChanged();
        } else {
            fetchBranches();
        }
    }
    private void setupViews(View rootView) {
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        recyclerView_branches = (RecyclerView) rootView.findViewById(R.id.recyclerView_branches);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noBranches);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new BranchesAdapter(branches);
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
    private void fetchBranches() {
        progressBarLoadingData.setVisibility(View.VISIBLE);
        BranchesNetworkCalls.getAllBranches().subscribe(new Observer<List<Branch>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeed
            @Override
            public void onNext(List<Branch> value) {
                //Value is the return of the API call
                //In this case it is a list of branches
                //For more info see Mohammad faour's code (ManagedObjects/ClientController.java)
                Log.i("Get Branches" , value.size()+" Branch");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    branches.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fail
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(View.GONE);
                Log.e("get Branches" , "Error getting branches" , e);
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
