package com.aliasapp.mipi.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aliasapps on 15-03-21.
 */
public class SharedPreferenceWrapper {
    public static final String KEY = "id";
    private static final String TAG = "SharedPreferenceIO";
    private static final String PREFERENCE_NAME = "highscores";
    private static final int MODE = Context.MODE_PRIVATE;

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public SharedPreferenceWrapper() {
    }

    public static void init(Activity activity) {
        preferences = activity.getSharedPreferences(PREFERENCE_NAME, MODE);
    }

    public static String readString() {
        return preferences.getString(KEY, "not found");
    }

    public static void writeString(int value) {
        editor = preferences.edit();
        String oldVals = readString();
        if (oldVals.equalsIgnoreCase("not found"))
            oldVals = "";
        else
            oldVals += ",";
        editor.putString(SharedPreferenceWrapper.KEY, ""+oldVals+ value);
        editor.commit();
    }

    public static void clear() {
        editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
