package com.zaita.aliyounes.gsbvc2017.helpers;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Lenovo on 8/13/2017.
 */

public class ColorHelper {

    private static int lastColor = 0;
    private static String[] mColors = {
            "F44336", //Red
            "E91E63", //Pink
            "9C27B0", //Purple
            "673AB7", //Deep Purple
            "3F51B5", //Indigo
            "2196F3", //Blue
            "03A9F4", //Light Blue
            "00BCD4", //Cyan
            "009688", //Teal
            "FF9800", //Orange
    };
    public static int getRandomColor() {
        int color;
        do {
            color = Color.parseColor("#" + mColors[new Random().nextInt(9)]);
        } while (lastColor == color);
        lastColor = color;
        return color;
    }
    public static int getRandomColor(int last) {
        int color;
        do {
            color = Color.parseColor("#" + mColors[new Random().nextInt(254)]);
        } while (last == color);
        lastColor = color;
        return color;
    }
}
