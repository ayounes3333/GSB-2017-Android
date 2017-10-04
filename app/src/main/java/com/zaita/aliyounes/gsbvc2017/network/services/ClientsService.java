package com.zaita.aliyounes.gsbvc2017.network.services;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Client;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Lenovo on 8/26/2017.
 */

public interface ClientsService {
    @GET
    Observable<JsonElement> getAllClients(@Url String url);

    @POST
    Observable<JsonElement> addClient(@Url String url , @Body Client client);

    @POST
    Observable<JsonElement> deleteClient(@Url String url, @Body String code);
}
