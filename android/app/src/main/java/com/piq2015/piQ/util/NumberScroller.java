package com.piq2015.piQ.util;

import android.app.Activity;
import android.os.Handler;
import android.widget.TextView;

import com.piq2015.piQ.models.C;
import com.piq2015.piQ.models.GameState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by aliasapps on 15-03-21.
 */
public class NumberScroller {
    private static TextView[] textViews;
    private static Activity ctx;
    private int scrollerSize;
    private int middleView;

    public NumberScroller(TextView[] inputTextViews, Activity ctx) {
        textViews = new TextView[inputTextViews.length];
        System.arraycopy(inputTextViews, 0, textViews, 0, inputTextViews.length);
        scrollerSize = textViews.length;
        middleView = scrollerSize / 2;
    }

    public int size() {
        return scrollerSize;
    }

    public void init() {
        for (int i = 0; i < textViews.length; i++) {
            if ((middleView - i) > 0) textViews[i].setText("");
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


    public void initPractice() {
        for (int i = 0; i < textViews.length; i++) {
            if ((middleView - i) > 0) textViews[i].setText("");
            else if (middleView == i) textViews[i].setText("?");
            else textViews[i].setText(getPiChar(i));
        }
    }

    public void shiftPractice() {
        for (int i = 0; i < textViews.length; i++) {
            if (middleView - GameState.getCorrectCount() >= 0) {
                if (GameState.getCorrectCount() < (1 + middleView - i)) continue;
                textViews[i].setText(getPiChar(i));
            } else {
                textViews[i].setText(getPiChar(i));
            }
        }
    }

    private String getPiChar(int i) {
        return "" + C.PI.charAt(GameState.getCorrectCount() + (i - middleView - 1));
    }

    public void peek() {
        for (int i = middleView + 1; i < textViews.length; i++)
            textViews[i].setText(getPiChar(i));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = middleView + 1; i < textViews.length; i++)
                    textViews[i].setText("?");
            }
        }, 1000);

    }

    final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

}
