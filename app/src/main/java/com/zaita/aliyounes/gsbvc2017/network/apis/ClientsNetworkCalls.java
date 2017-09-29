package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.JsonHelper;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.services.ClientsService;
import com.zaita.aliyounes.gsbvc2017.pojos.Client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lenovo on 8/26/2017.
 */

public class ClientsNetworkCalls {
    public static Observable<List<Client>> getAllClients() {
        ClientsService service = ServiceGenerator.createService(ClientsService.class);
        return service.getAllClients(UrlManager.getAllClientsURL())
                .flatMap(new Function<JsonElement, Observable<List<Client>>>() {
                    @Override
                    public Observable<List<Client>> apply(JsonElement jsonElement) throws Exception {
                        if(jsonElement != null) {
                            Log.i("Get All Clients" , "JSON: "+jsonElement.toString());
                            if(jsonElement.isJsonArray()) {
                                List<Client> clients = Client.ClientsListParser.fromJsonArray(jsonElement.getAsJsonArray());
                                return Observable.just(clients);
                            } else {
                                return Observable.error(new Exception("Expected a JSON Array"));
                            }
                        } else {
                            return Observable.just((List<Client>) new ArrayList<Client>());
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Boolean> addClient(com.zaita.aliyounes.gsbvc2017.network.datamodels.Client client) {
        client.setCltCreationDate(JsonHelper.getDateFormatter().format(new Date()));
        ClientsService service = ServiceGenerator.createService(ClientsService.class);
        return service.addClient(UrlManager.addClientURL() , client)
                .flatMap(new Function<JsonElement, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(JsonElement jsonElement) throws Exception {
                        Log.i("Add Client" , "JSON: "+jsonElement);
                        return Observable.just(true);
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
