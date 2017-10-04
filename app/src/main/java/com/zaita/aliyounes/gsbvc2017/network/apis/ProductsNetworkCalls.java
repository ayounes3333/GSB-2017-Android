package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.ProductsService;
import com.zaita.aliyounes.gsbvc2017.pojos.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Elie Ohanian on 8/26/2017.
 */


public class ProductsNetworkCalls {
    public static Observable<List<Product>> getAllProducts() {
        ProductsService service = ServiceGenerator.createService(ProductsService.class);
        return service.getAllProducts(UrlManager.getAllProductsURL())
                .flatMap(new Function<JsonElement, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> apply(JsonElement jsonElement) throws Exception {
                        if (jsonElement != null) {
                            Log.i("Get All Products", "JSON: " + jsonElement.toString());
                            if (jsonElement.isJsonArray()) {
                                List<Product> products = Product.ProductsListParser.fromJsonArray(jsonElement.getAsJsonArray());
                                return Observable.just(products);
                            } else {
                                return Observable.error(new Exception("Expected a JSON Array"));
                            }
                        } else {
                            return Observable.just((List<Product>) new ArrayList<Product>());
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> addProduct(com.zaita.aliyounes.gsbvc2017.network.datamodels.Product product) {
        product.setPrCreationDate(JsonHelper.getDateFormatter().format(new Date()));
        ProductsService service = ServiceGenerator.createService(ProductsService.class);
        return service.addProduct(UrlManager.addProductURL(), product)
                .flatMap(new Function<JsonElement, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(JsonElement jsonElement) throws Exception {

                        Log.i("Add Product", "JSON: " + jsonElement);
                        return Observable.just(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> deleteProduct(String code) {
        ProductsService service = ServiceGenerator.createService(ProductsService.class);
        return service.deleteProduct(UrlManager.deleteProductURL() , code)
                .flatMap(new Function<JsonElement, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(JsonElement jsonElement) throws Exception {
                        Log.i("delete Product" , "JSON: "+jsonElement);
                        return Observable.just(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
