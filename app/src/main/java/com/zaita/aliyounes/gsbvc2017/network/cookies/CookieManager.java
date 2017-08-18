package com.zaita.aliyounes.gsbvc2017.network.cookies;


import com.zaita.aliyounes.gsbvc2017.application.GSBApplication;
import com.zaita.aliyounes.gsbvc2017.helpers.PrefUtils;

import java.util.Set;

/**
 * Created by jawad.turk on 01-Sep-16.
 */
public class CookieManager {
    private static final String KEY_COOKIE = "SessionId";
    private static final String Key_AppCookie = "AppCookie";



    public static String getAppId() {

        return PrefUtils.getString(GSBApplication.getInstance(), Key_AppCookie, "");
    }

    public static void setAppId(String cookie) {
        PrefUtils.setString(GSBApplication.getInstance(), Key_AppCookie, cookie);
    }

    public static Set<String> getCookie() {

        return PrefUtils.getStringSet(GSBApplication.getInstance(), KEY_COOKIE);
    }

    public static void setCookie(Set<String> cookie) {
        PrefUtils.setStringSet(GSBApplication.getInstance(), KEY_COOKIE, cookie);
    }

    public static String getSessionId() {

        return PrefUtils.getString(GSBApplication.getInstance(), KEY_COOKIE, "");
    }

    public static void setSessionId(String cookie) {
        PrefUtils.setString(GSBApplication.getInstance(), KEY_COOKIE, cookie);
    }

public static void clearCookie()
{
    PrefUtils.clearSpecificPref(GSBApplication.getInstance(),KEY_COOKIE);
    PrefUtils.clearSpecificPref(GSBApplication.getInstance(), Key_AppCookie);
}

}