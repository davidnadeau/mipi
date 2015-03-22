package com.aliasapp.mipi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliasapp.mipi.KeypadAdapter;
import com.aliasapp.mipi.NumberScroller;
import com.aliasapp.mipi.PiChecker;
import com.aliasapp.mipi.R;
import com.aliasapp.mipi.models.GameState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private static PiChecker checker;
    private static GridView gridview;
    private static RelativeLayout livesLayout;
    private static TextView lives;
    private static TextView currentScore;
    private static NumberScroller scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        livesLayout = (RelativeLayout) findViewById(R.id.lives_layout);
        checker = new PiChecker();
        lives = (TextView) findViewById(R.id.lives);
        currentScore = (TextView) findViewById(R.id.current_score);
        currentScore.setText("0");
        lives.setText(""+GameState.getLives());
        scroller = new NumberScroller(new TextView[]{
                (TextView) findViewById(R.id.number0),
                (TextView) findViewById(R.id.number1),
                (TextView) findViewById(R.id.number2),
                (TextView) findViewById(R.id.number3),
                (TextView) findViewById(R.id.number4),
                (TextView) findViewById(R.id.number5),
                (TextView) findViewById(R.id.number6)
        });
        scroller.init();

//        piDisplay = (TextView) findViewById(R.id.pi_display);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new KeypadAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (checker.guess(position)) {
                    case CORRECT:
                        scroller.shift();
                        currentScore.setText(""+GameState.getCorrectCount());
//                        flashColor(R.color.correct_answer_background);
                        break;
                    case WRONG:
                        lives.setText(""+GameState.getLives());
                        shake();
//                        flashColor(R.color.wrong_answer_background);
                        Log.v(TAG, "GOT ONE WRONG");
                        break;
                    case GAMEOVER:
                        startActivity(new Intent(GameActivity.this, GameOverActivity.class));
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void shake() {
        final Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.shake);
        livesLayout.setAnimation(animation);
        livesLayout.animate();
        animation.start();
        exec.schedule(new Runnable() {
            @Override
            public void run() {
                animation.cancel();
            }
        }, 300, TimeUnit.MILLISECONDS);

    }
    final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);


}
