package com.zaita.aliyounes.gsbvc2017.network.cookies;

// Original written by tsuharesu
// Adapted to create a "drop it in and watch it work" approach by Nikhil Jha.
// Just add your package statement and drop it in the folder with all your other classes.

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <a>https://gist.github.com/nikhiljha/52d45ca69a8415c6990d2a63f61184ff</a>
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences may ary, but this will work 99% of the time.
 */
public class AddCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    private static final String TAG = AddCookiesInterceptor.class.getSimpleName();
    // We're storing our stuff in a database made just for cookies called PREF_COOKIES.
    // I reccomend you do this, and don't change this default value.
//    private Context context;

//    public AddCookiesInterceptor(Context context) {
//        this.context = context;
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        if (!CookieManager.getCookie().isEmpty()) {
            builder.removeHeader("Cookie");
            builder.addHeader("Cookie", TextUtils.join(";", CookieManager.getCookie()));
        }

//        Log.d(TAG, "Cookie String " + CookieManager.getCookie());


        return chain.proceed(builder.build());
    }
}