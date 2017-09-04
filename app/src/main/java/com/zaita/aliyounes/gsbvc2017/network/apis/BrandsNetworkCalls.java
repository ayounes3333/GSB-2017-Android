package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.BrandsService;
import com.zaita.aliyounes.gsbvc2017.pojos.Brand;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Elie Ohanian on 8/26/2017.
 */




public class BrandsNetworkCalls {
    public static Observable<List<Brand>> getAllBrands() {
            BrandsService service = ServiceGenerator.createService(BrandsService.class);
            return service.getAllBrands(UrlManager.getAllBrandsURL())
                    .flatMap(new Function<JsonElement, Observable<List<Brand>>>() {
                        @Override
                        public Observable<List<Brand>> apply(JsonElement jsonElement) throws Exception {
                            Log.i("Get All Brands" , "JSON: "+jsonElement.toString());
                            if(jsonElement.isJsonArray()) {
                                List<Brand> brands = Brand.BrandsListParser.fromJsonArray(jsonElement.getAsJsonArray());
                                return Observable.just(brands);
                            } else {
                                return Observable.error(new Exception("Expected a JSON Array"));
                            }
                        }
                    }).observeOn(AndroidSchedulers.mainThread());
        }
    public static Observable<Integer> addBrand(com.zaita.aliyounes.gsbvc2017.network.datamodels.Brand brand) {
        BrandsService service = ServiceGenerator.createService(BrandsService.class);
        return service.addBrand(UrlManager.addBrandURL() , brand)
                .flatMap(new Function<JsonElement, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Add Brand" , "JSON: "+jsonElement);
                        if(!JsonHelper.isNull(jsonElement , "brdCode")) {
                            return Observable.just(jsonElement.getAsJsonObject().get("brdCode").getAsInt());
                        } else {
                            return Observable.error(new Exception("Invalid Brand code format"));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
