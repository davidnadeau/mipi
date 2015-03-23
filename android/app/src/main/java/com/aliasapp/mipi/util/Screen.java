package com.aliasapp.mipi.util;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by aliasapps on 15-03-22.
 */
public class Screen {
    private static int width;
    private static int height;
    public static void init(Activity a) {
        Display display = a.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
}
