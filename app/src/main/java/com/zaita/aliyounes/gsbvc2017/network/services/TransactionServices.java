package com.zaita.aliyounes.gsbvc2017.network.services;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Order;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Chawach on 8/29/2017.
 */

public interface TransactionServices {
    @GET
    Observable<JsonElement> getAllOrders(@Url String url);

    @POST
    @FormUrlEncoded
    Observable<JsonElement> addOrder(@Url String url, @Body Order order);
}
