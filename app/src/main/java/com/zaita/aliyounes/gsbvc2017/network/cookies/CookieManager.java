package com.zaita.aliyounes.gsbvc2017.network.cookies;


import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;

import java.util.Set;

import static com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils.Prefs.SESSION_ID;
import static com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils.Prefs.APP_COOKIE;

/**
 * Created by jawad.turk on 01-Sep-16.
 */
public class CookieManager {



    public static String getAppId() {

        return PrefUtils.getString(GSBApplication.getInstance(), APP_COOKIE, "");
    }

    public static void setAppId(String cookie) {
        PrefUtils.setString(GSBApplication.getInstance(), APP_COOKIE, cookie);
    }

    public static Set<String> getCookie() {

        return PrefUtils.getStringSet(GSBApplication.getInstance(), SESSION_ID);
    }

    public static void setCookie(Set<String> cookie) {
        PrefUtils.setStringSet(GSBApplication.getInstance(), SESSION_ID, cookie);
    }

    public static String getSessionId() {

        return PrefUtils.getString(GSBApplication.getInstance(), SESSION_ID, "");
    }

    public static void setSessionId(String cookie) {
        PrefUtils.setString(GSBApplication.getInstance(), SESSION_ID, cookie);
    }

public static void clearCookie()
{
    PrefUtils.clearSpecificPref(GSBApplication.getInstance(), SESSION_ID);
    PrefUtils.clearSpecificPref(GSBApplication.getInstance(), APP_COOKIE);
}

}