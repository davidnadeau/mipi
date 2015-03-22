package com.aliasapp.mipi.highscore;

import com.aliasapp.mipi.util.SharedPreferenceWrapper;

/**
 * Created by aliasapps on 15-03-21.
 */
public class HighScore {
    public static void saveScore(int score) {
        SharedPreferenceWrapper.writeString(score);
    }

    public static String readScores() {
        return SharedPreferenceWrapper.readString();
    }
}
