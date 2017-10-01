package com.zaita.aliyounes.gsbvc2017.network.services;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Branch;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Brand;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Lenovo on 8/26/2017.
 */

public interface BrandsService {
    @GET
    Observable<JsonElement> getAllBrands(@Url String url);

    @POST
    Observable<JsonElement> addBrand(@Url String url, @Body Brand brand);

    @POST
    Observable<JsonElement> deleteBrand(@Url String url, @Body String code);
}
