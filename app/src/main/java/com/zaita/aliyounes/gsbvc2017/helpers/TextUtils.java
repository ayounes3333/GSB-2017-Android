package com.zaita.aliyounes.gsbvc2017.helpers;

import java.text.DecimalFormat;

/**
 * Created by Lenovo on 8/26/2017.
 */

public class TextUtils {
    public static String formatPrice(int price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return "$"+df.format(price);
    }
    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return "$"+df.format(price);
    }
    public static boolean isEmpty(String s) {
        if(s != null) {
            if(!s.equalsIgnoreCase("")) {
                return true;
            }
        }
        return false;
    }
}
