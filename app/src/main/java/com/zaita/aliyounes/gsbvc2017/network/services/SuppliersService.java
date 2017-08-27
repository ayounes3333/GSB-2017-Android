package com.zaita.aliyounes.gsbvc2017.network.services;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Branch;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Supplier;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Lenovo on 8/26/2017.
 */

public interface SuppliersService {
    @GET
    Observable<JsonElement> getAllSuppliers(@Url String url);

    @POST
    @FormUrlEncoded
    Observable<JsonElement> addSupplier(@Url String url, @Body Supplier supplier);
}
