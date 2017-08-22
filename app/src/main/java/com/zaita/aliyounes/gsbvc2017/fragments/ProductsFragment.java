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
import com.zaita.aliyounes.gsbvc2017.adapters.ProductsAdapter;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Younes on 8/14/2017.
 */

public class ProductsFragment extends Fragment {
    public static final String TAG = ProductsFragment.class.getSimpleName();
    RecyclerView recyclerView_products;
    ProductsAdapter adapter;
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
        setupViews(view);
    }
    private void setupViews(View rootView) {
        recyclerView_products = (RecyclerView) rootView.findViewById(R.id.recyclerView_products);
        setupRecyclerView();
    }

    //Setup the list
    private void setupRecyclerView() {
        adapter = new ProductsAdapter(getDummyProducts());
        recyclerView_products.setAdapter(adapter);
        recyclerView_products.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
    }

    //Create dummy Products
    private List<Product> getDummyProducts() {
        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(new Product("Product 1" , "type product 1" , "Famille 1" , "$0.00" , "$0.00" , "Automne"));
        dummyProducts.add(new Product("Product 2" , "type product 1" , "Famille 1" , "$0.00" , "$0.00" , "Automne"));
        dummyProducts.add(new Product("Product 3" , "type product 1" , "Famille 1" , "$0.00" , "$0.00" , "Automne"));
        dummyProducts.add(new Product("Product 4" , "type product 1" , "Famille 1" , "$0.00" , "$0.00" , "Automne"));
        dummyProducts.add(new Product("Product 5" , "type product 1" , "Famille 1" , "$0.00" , "$0.00" , "Automne"));
        dummyProducts.add(new Product("Product 6" , "type product 1" , "Famille 1" , "$0.00" , "$0.00" , "Automne"));
        return dummyProducts;
    }
}
