package com.zaita.aliyounes.gsbvc2017.network.services;

import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.Client;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Lenovo on 8/26/2017.
 */

public interface UserService {

    @POST
    Observable<JsonElement> login(@Url String url, @Body User client);
}
