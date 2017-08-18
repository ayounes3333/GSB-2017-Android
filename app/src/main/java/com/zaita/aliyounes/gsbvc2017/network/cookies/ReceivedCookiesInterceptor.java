package com.zaita.aliyounes.gsbvc2017.network.cookies;

// Original written by tsuharesu
// Adapted to create a "drop it in and watch it work" approach by Nikhil Jha.
// Just add your package statement and drop it in the folder with all your other classes.

import android.preference.PreferenceManager;

import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String TAG = ReceivedCookiesInterceptor.class.getSimpleName();
//    private Context context;

//    public ReceivedCookiesInterceptor(Context context) {
//        this.context = context;
//    } // AddCookiesInterceptor()

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookies = (HashSet<String>) PreferenceManager
                    .getDefaultSharedPreferences(GSBApplication.getInstance())
                    .getStringSet("PREF_COOKIES", new HashSet<String>());

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
//            Log.d(TAG, "original headers" + originalResponse.headers("Set-Cookie").toString());

//            Object[] array = originalResponse.headers("Set-Cookie").toArray();
//
//            String cookie = TextUtils.join(";", array);

//            Log.d(TAG, "Cookie   " + cookie);
//            Log.d(TAG, "cookie retrieve" + CookieManager.getCookie());
            if (CookieManager.getCookie().isEmpty()) {
//                Log.d(TAG, "cookie after" + cookie);
                CookieManager.setCookie(cookies);
            }


        }

        return originalResponse;
    }

}