package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.BranchesService;
import com.zaita.aliyounes.gsbvc2017.pojos.Branch;

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



public class BranchesNetworkCalls {
    public static Observable<List<Branch>> getAllBranches() {
        BranchesService service = ServiceGenerator.createService(BranchesService.class);
        return service.getAllBranches(UrlManager.getAllBranchesURL())
                .flatMap(new Function<JsonElement, Observable<List<Branch>>>() {
                    @Override
                    public Observable<List<Branch>> apply(JsonElement jsonElement) throws Exception {
                        if(jsonElement != null) {
                            Log.i("Get All Branches", "JSON: " + jsonElement.toString());
                            if (jsonElement.isJsonArray()) {
                                List<Branch> branches = Branch.BranchesListParser.fromJsonArray(jsonElement.getAsJsonArray());
                                return Observable.just(branches);
                            } else {
                                return Observable.error(new Exception("Expected a JSON Array"));
                            }
                        } else {
                            return Observable.just((List<Branch>) new ArrayList<Branch>());
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Boolean> addBranch(com.zaita.aliyounes.gsbvc2017.network.datamodels.Branch branch ) {
        branch.setBrCreationDate(JsonHelper.getDateFormatter().format(new Date()));
        BranchesService service = ServiceGenerator.createService(BranchesService.class);
        return service.addBranch(UrlManager.addBranchURL() , branch)
                .flatMap(new Function<JsonElement, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Add Branch" , "JSON: "+jsonElement);
                        return Observable.just(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
