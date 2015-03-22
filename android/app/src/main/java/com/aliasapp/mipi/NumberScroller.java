package com.aliasapp.mipi;

import android.widget.TextView;

import com.aliasapp.mipi.models.GameState;

/**
 * Created by aliasapps on 15-03-21.
 */
public class NumberScroller {
    private static TextView[] textViews;
    private int scrollerSize;
    private int middleView;

    public NumberScroller(TextView[] inputTextViews) {
        textViews = new TextView[inputTextViews.length];
        System.arraycopy(inputTextViews, 0, textViews, 0, inputTextViews.length);
        scrollerSize = textViews.length;
        middleView = scrollerSize / 2;
    }

    public int size() {
        return scrollerSize;
    }

    public void init() {
        for (int i = 0; i < textViews.length;i++) {
            if ((middleView-i) > 0) textViews[i].setText("");
            else textViews[i].setText("?");
        }
    }

    public void shift() {
        for (int i = 0; i <= middleView; i++) {
            if (middleView - GameState.getCorrectCount() >= 0) {
                if (GameState.getCorrectCount() < (1 + middleView - i)) continue;
                textViews[i].setText(getPiChar(i));
            } else {
                textViews[i].setText(getPiChar(i));
            }
        }
    }

    private String getPiChar(int i){
        return "" + C.PI.charAt(GameState.getCorrectCount() + (i - middleView - 1));
    }
}
