package com.zaita.aliyounes.gsbvc2017.helpers;

import com.google.gson.JsonElement;

/**
 * Created by Lenovo on 8/26/2017.
 */

public class JsonHelper {
    public static boolean isNull(JsonElement jsonElement, String key) {
        return jsonElement.getAsJsonObject().has(key);
    }
}
