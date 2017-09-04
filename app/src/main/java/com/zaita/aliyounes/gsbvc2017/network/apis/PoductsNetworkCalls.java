package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.ProductsService;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Elie Ohanian on 8/26/2017.
 */



public class PoductsNetworkCalls {
    public static Observable<List<Product>> getAllProducts() {
        ProductsService service = ServiceGenerator.createService(ProductsService.class);
        return service.getAllProducts(UrlManager.getAllProductsURL())
                .flatMap(new Function<JsonElement, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Get All Products" , "JSON: "+jsonElement.toString());
                        if(jsonElement.isJsonArray()) {
                            List<Product> products = Product.ProductsListParser.fromJsonArray(jsonElement.getAsJsonArray());
                            return Observable.just(products);
                        } else {
                            return Observable.error(new Exception("Expected a JSON Array"));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Integer> addProduct(com.zaita.aliyounes.gsbvc2017.network.datamodels.Product product ) {
        ProductsService service = ServiceGenerator.createService(ProductsService.class);
        return service.addProduct(UrlManager.addProductURL() , product)
                .flatMap(new Function<JsonElement, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Add Product" , "JSON: "+jsonElement);
                        if(!JsonHelper.isNull(jsonElement , "prCode")) {
                            return Observable.just(jsonElement.getAsJsonObject().get("prCode").getAsInt());
                        } else {
                            return Observable.error(new Exception("Invalid Product code format"));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
