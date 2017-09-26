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
import com.zaita.aliyounes.gsbvc2017.adapters.BrandsAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.network.apis.BrandsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;

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

public class BrandsFragment extends Fragment {
    public static final String TAG = BrandsFragment.class.getSimpleName();
    RecyclerView recyclerView_brands;
    BrandsAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    List<Brand> brands;
    private CompositeDisposable compositeDisposable;

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
        brands = new ArrayList<>();
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        if(GSBApplication.isDummyData()) {
            brands.addAll(getDummyBrands());
            adapter.notifyDataSetChanged();
        } else {
            fetchBrands();
        }
    }
    private void setupViews(View rootView) {
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        recyclerView_brands = (RecyclerView) rootView.findViewById(R.id.recyclerView_brands);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noBrands);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new BrandsAdapter(brands);
        recyclerView_brands.setAdapter(adapter);
        recyclerView_brands.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    private void fetchBrands() {
        progressBarLoadingData.setVisibility(View.VISIBLE);
        BrandsNetworkCalls.getAllBrands().subscribe(new Observer<List<Brand>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeed
            @Override
            public void onNext(List<Brand> value) {
                //Value is the return of the API call
                //In this case it is a list of Brands
                //For more info see Mohammad faour's code (ManagedObjects/BrandController.java)
                Log.i("Get Brands" , value.size()+" Brand");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    brands.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fail
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(View.GONE);
                Log.e("Get Brands" , "Error getting Brands" , e);
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
