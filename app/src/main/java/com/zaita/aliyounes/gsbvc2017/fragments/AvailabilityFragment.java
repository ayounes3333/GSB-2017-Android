package com.zaita.aliyounes.gsbvc2017.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.zaita.aliyounes.gsbvc2017.R;
import com.zaita.aliyounes.gsbvc2017.adapters.AvailabilitiesAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.pojos.Availability;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailabilityFragment extends Fragment {

    public static final String TAG = AvailabilityFragment.class.getSimpleName();
    RecyclerView recyclerView_availabilities;
    AvailabilitiesAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    private List<Availability> availabilities;
    private CompositeDisposable compositeDisposable;

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
        availabilities = new ArrayList<>();
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        if(GSBApplication.isDummyData()) {
            availabilities.addAll(getDummyAvailabilities());
            adapter.notifyDataSetChanged();
        } else {
            fetchAvailabilities();
        }
    }
    private void setupViews(View rootView) {
        recyclerView_availabilities = (RecyclerView) rootView.findViewById(R.id.recyclerView_availabilities);
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noAvailabilities);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new AvailabilitiesAdapter(availabilities);
        recyclerView_availabilities.setAdapter(adapter);
        recyclerView_availabilities.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    private void fetchAvailabilities() {
        /*progressBarLoadingData.setVisibility(View.VISIBLE);
          AvailabilitiesNetworkCalls.getAllAvailabilities().subscribe(new Observer<List<Availability>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeed
            @Override
            public void onNext(List<Availability> value) {
                //Value is the return of the API call
                //In this case it is a list of availabilities
                //For more info see Mohammad faour's code (ManagedObjects/AvailabilityController.java)
                Log.i("Get availabilities" , value.size()+" Availability");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    availabilities.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fail
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(Vew.GONE);
                Log.e("Get availabilities" , "Error getting availabilities" , e);
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
                progressBarLoadingData.setVisibility(Vew.GONE);
            }
        });*/
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
