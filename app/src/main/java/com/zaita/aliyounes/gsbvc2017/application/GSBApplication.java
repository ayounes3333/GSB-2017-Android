package com.zaita.aliyounes.gsbvc2017.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;
import com.zaita.aliyounes.gsbvc2017.network.ServiceGenerator;

/**
 * Created by Lenovo on 8/18/2017.
 */

public class GSBApplication extends Application {
    private static GSBApplication instance;
    public static GSBApplication getInstance() {
        return instance;
    }
    public static boolean hasNetwork() {
        return instance.checkIfHasNetwork();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //setDummyData();
        clearDummyData();
        ServiceGenerator.setup();
        Log.i("Setup" , "Complete");
    }

    public static boolean isDummyData() {
        return PrefUtils.getBoolean(GSBApplication.getInstance() , "isDummyData" , false);
    }

    public static void setDummyData() {
        PrefUtils.setBoolean(GSBApplication.getInstance() , "isDummyData" , true);
    }

    public static void clearDummyData() {
        PrefUtils.setBoolean(GSBApplication.getInstance() , "isDummyData" , false);
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
