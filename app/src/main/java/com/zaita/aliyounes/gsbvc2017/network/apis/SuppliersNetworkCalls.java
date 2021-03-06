package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.SuppliersService;
import com.zaita.aliyounes.gsbvc2017.pojos.Supplier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by Elie Ohanian on 8/26/2017.
 */

public class SuppliersNetworkCalls {
    public static Observable<List<Supplier>> getAllSuppliers() {
        SuppliersService service = ServiceGenerator.createService(SuppliersService.class);
        return service.getAllSuppliers(UrlManager.getAllSuppliersURL())
                .flatMap(new Function<JsonElement, Observable<List<Supplier>>>() {
                    @Override
                    public Observable<List<Supplier>> apply(JsonElement jsonElement) throws Exception {

                        if (jsonElement != null) {
                            Log.i("Get All Supplier" , "JSON: "+jsonElement.toString());
                            if(jsonElement.isJsonArray()) {
                                List<Supplier> suppliers = Supplier.SuppliersListParser.fromJsonArray(jsonElement.getAsJsonArray());
                                return Observable.just(suppliers);
                            } else {
                                return Observable.error(new Exception("Expected a JSON Array"));
                            }
                        } else {
                            return Observable.just((List<Supplier>) new ArrayList<Supplier>());
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Boolean> addSupplier(com.zaita.aliyounes.gsbvc2017.network.datamodels.Supplier supplier) {
        supplier.setSupCreationDate(JsonHelper.getDateFormatter().format(new Date()));
        SuppliersService service = ServiceGenerator.createService(SuppliersService.class);
        return service.addSupplier(UrlManager.addSupplierURL() , supplier)
                .flatMap(new Function<JsonElement, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Add Supplier" , "JSON: "+jsonElement);
                            return Observable.just(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> deleteSupplier(String code) {
        SuppliersService service = ServiceGenerator.createService(SuppliersService.class);
        return service.deleteSupplier(UrlManager.deleteSupplierURL() , code)
                .flatMap(new Function<JsonElement, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(JsonElement jsonElement) throws Exception {
                        Log.i("delete Supplier" , "JSON: "+jsonElement);
                        return Observable.just(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
