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
import com.zaita.aliyounes.gsbvc2017.adapters.ProductsAdapter;
import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.network.apis.ClientsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.network.apis.PoductsNetworkCalls;
import com.zaita.aliyounes.gsbvc2017.pojos.Client;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ali Younes on 8/14/2017.
 */

public class ProductsFragment extends Fragment {
    public static final String TAG = ProductsFragment.class.getSimpleName();
    RecyclerView recyclerView_products;
    ProductsAdapter adapter;
    ProgressBar progressBarLoadingData;
    RelativeLayout relativeLayout_noInternet;
    RelativeLayout relativeLayout_serverError;
    RelativeLayout relativeLayout_noData;
    List<Product> products = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        products = new ArrayList<>();
        setupViews(view);
        compositeDisposable = new CompositeDisposable();
        if(GSBApplication.isDummyData()) {
            products.addAll(getDummyProducts());
            adapter.notifyDataSetChanged();
        } else {
            fetchProducts();
        }
    }
    private void setupViews(View rootView) {
        progressBarLoadingData = (ProgressBar) rootView.findViewById(R.id.progressBar_loadingData);
        recyclerView_products = (RecyclerView) rootView.findViewById(R.id.recyclerView_products);
        relativeLayout_noInternet  = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noInternet);
        relativeLayout_serverError = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_serverError);
        relativeLayout_noData      = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_noProducts);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new ProductsAdapter(products);
        recyclerView_products.setAdapter(adapter);
        recyclerView_products.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }
    private void fetchProducts() {
        products.clear();
        adapter.notifyDataSetChanged();
        relativeLayout_noData.setVisibility(View.GONE);
        relativeLayout_noInternet.setVisibility(View.GONE);
        relativeLayout_serverError.setVisibility(View.GONE);
        progressBarLoadingData.setVisibility(View.VISIBLE);
        PoductsNetworkCalls.getAllProducts().subscribe(new Observer<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            //Called when the request succeed
            @Override
            public void onNext(List<Product> value) {
                Log.i("Get Products" , value.size()+" Product");
                if(value.size() == 0) {
                    relativeLayout_noData.setVisibility(View.VISIBLE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                } else {
                    relativeLayout_noData.setVisibility(View.GONE);
                    relativeLayout_noInternet.setVisibility(View.GONE);
                    relativeLayout_serverError.setVisibility(View.GONE);
                    products.addAll(value);
                    adapter.notifyDataSetChanged();
                }
            }

            //Called if the request fail
            @Override
            public void onError(Throwable e) {
                progressBarLoadingData.setVisibility(View.GONE);
                Log.e("Get Products" , "Error getting products" , e);
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
    //Create dummy Products
    private List<Product> getDummyProducts() {
        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(new Product("Product 1" , 1 , "Famille 1" , 10 , 11 , "Automne"));
        dummyProducts.add(new Product("Product 2" , 1 , "Famille 1" , 10 , 11 , "Automne"));
        dummyProducts.add(new Product("Product 3" , 1 , "Famille 1" , 10 , 11 , "Automne"));
        dummyProducts.add(new Product("Product 4" , 1 , "Famille 1" , 10 , 11 , "Automne"));
        dummyProducts.add(new Product("Product 5" , 1 , "Famille 1" , 10 , 11 , "Automne"));
        dummyProducts.add(new Product("Product 6" , 1 , "Famille 1" , 10 , 11 , "Automne"));
        return dummyProducts;
    }
    @Override
    public void onResume() {
        super.onResume();
        compositeDisposable = new CompositeDisposable();
        fetchProducts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
