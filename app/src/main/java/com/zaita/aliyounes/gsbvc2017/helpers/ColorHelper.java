package com.zaita.aliyounes.gsbvc2017.helpers;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Lenovo on 8/13/2017.
 */

public class ColorHelper {

    private static int lastColor = 0;
    private static String[] mColors = {
            "e3ab38", //Orange
            "a1a1a1", //Grey
            "4bb2c5", //Blue
    };
    public static int getRandomColor() {
        int color;
        do {
            color = Color.parseColor("#" + mColors[new Random().nextInt(3)]);
        } while (lastColor == color);
        lastColor = color;
        return color;
    }
    public static int getRandomColor(int last) {
        int color;
        do {
            color = Color.parseColor("#" + mColors[new Random().nextInt(3)]);
        } while (last == color);
        lastColor = color;
        return color;
    }
}
