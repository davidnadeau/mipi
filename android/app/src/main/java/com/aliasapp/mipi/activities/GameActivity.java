package com.aliasapp.mipi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.aliasapp.mipi.KeypadAdapter;
import com.aliasapp.mipi.PiChecker;
import com.aliasapp.mipi.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static PiChecker checker;
    private static GridView gridview;
    private static TextView piDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        checker = new PiChecker();

        piDisplay = (TextView) findViewById(R.id.pi_display);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new KeypadAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (checker.guess(position)) {
                    case CORRECT:
                        piDisplay.setText(checker.getInput());
                        flashColor(R.color.correct_answer_background);
                        break;
                    case WRONG:
                        flashColor(R.color.wrong_answer_background);
                        Log.v(TAG, "GOT ONE WRONG");
                        break;
                    case GAMEOVER:
                        startActivity(new Intent(GameActivity.this, GameOverActivity.class));
                        break;
                }
            }
        });
    }

    final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

    private void flashColor(int color) {
        piDisplay.setBackgroundColor(getResources().getColor(color));
        exec.schedule(new Runnable() {
            @Override
            public void run() {
                piDisplay.setBackgroundColor(getResources().getColor(R.color.transparent_background));
            }
        }, 100, TimeUnit.MILLISECONDS);
    }


}
