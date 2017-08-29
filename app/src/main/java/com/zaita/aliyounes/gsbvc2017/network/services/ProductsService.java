package com.zaita.aliyounes.gsbvc2017.network.services;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Product;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Lenovo on 8/26/2017.
 */

public interface ProductsService {
    @GET
    Observable<JsonElement> getAllProducts(@Url String url);

    @POST
    @FormUrlEncoded
    Observable<JsonElement> addProduct(@Url String url, @Body Product product);
}
