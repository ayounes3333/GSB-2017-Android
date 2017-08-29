package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.BranchesService;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Elie Ohanian on 8/26/2017.
 */



public class BranchesNetworkCalls {
    public static Observable<List<Branch>> getAllBranches() {
        BranchesService service = ServiceGenerator.createService(BranchesService.class);
        return service.getAllBranches(UrlManager.getAllBranchesURL())
                .flatMap(new Function<JsonElement, Observable<List<Branch>>>() {
                    @Override
                    public Observable<List<Branch>> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Get All Branches" , "JSON: "+jsonElement.toString());
                        if(jsonElement.isJsonArray()) {
                            List<Branch> branches = Branch.BranchesListParser.fromJsonArray(jsonElement.getAsJsonArray());
                            return Observable.just(branches);
                        } else {
                            return Observable.error(new Exception("Expected a JSON Array"));
                        }
                    }
                }).observeOn(Schedulers.io());
    }
    public static Observable<Integer> addBranch(com.zaita.aliyounes.gsbvc2017.network.datamodels.Branch branch ) {
        BranchesService service = ServiceGenerator.createService(BranchesService.class);
        return service.addBranch(UrlManager.addBranchURL() , branch)
                .flatMap(new Function<JsonElement, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Add Branch" , "JSON: "+jsonElement);
                        if(!JsonHelper.isNull(jsonElement , "brCode")) {
                            return Observable.just(jsonElement.getAsJsonObject().get("brCode").getAsInt());
                        } else {
                            return Observable.error(new Exception("Invalid Branch code format"));
                        }
                    }
                }).observeOn(Schedulers.io());
    }
}
