package com.zaita.aliyounes.gsbvc2017.network.apis;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;
import com.zaita.aliyounes.gsbvc2017.network.UrlManager;
import com.zaita.aliyounes.gsbvc2017.network.datamodels.User;
import com.zaita.aliyounes.gsbvc2017.network.services.UserService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by Lenovo on 8/26/2017.
 */

public class UserNetworkCalls {
    public static Observable<User> loginUser(final String username , String password) {
        UserService service = ServiceGenerator.createService(UserService.class);
        return service.login(UrlManager.loginURL() , new User(username , password))
                .flatMap(new Function<JsonElement, Observable<User>>() {
                    @Override
                    public Observable<User> apply(JsonElement jsonElement) throws Exception {

                        if (jsonElement != null) {
                            Log.i("Login User" , "JSON: "+jsonElement.toString());
                            if(jsonElement.isJsonObject()) {
                                User user = (new Gson()).fromJson(jsonElement.getAsJsonObject() , User.class);
                                PrefUtils.setUsername(user.getUsrFullname());
                                PrefUtils.setUserEmail(user.getUsrUsername());
                                return Observable.just(user);
                            } else {
                                return Observable.error(new Exception("Expected a JSON Object"));
                            }
                        } else {
                            return Observable.error(new Exception("Login Failed"));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread());
    }
}
