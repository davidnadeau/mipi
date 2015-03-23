package com.aliasapp.mipi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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
import com.aliasapp.mipi.util.Screen;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private static PiChecker checker;
    private static GridView gridview;
    private static RelativeLayout livesLayout;
    private static TextView lives;
    private static TextView peeks;
    private static TextView currentScore;
    private static NumberScroller scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Screen.init(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        livesLayout = (RelativeLayout) findViewById(R.id.lives_layout);
        checker = new PiChecker();
        lives = (TextView) findViewById(R.id.lives);
        peeks = (TextView) findViewById(R.id.peeks);
        currentScore = (TextView) findViewById(R.id.current_score);
        currentScore.setText("Correct: 0");
        lives.setText("" + GameState.getLives());
        scroller = new NumberScroller(new TextView[]{
                (TextView) findViewById(R.id.number0),
                (TextView) findViewById(R.id.number1),
                (TextView) findViewById(R.id.number2),
                (TextView) findViewById(R.id.number3),
                (TextView) findViewById(R.id.number4),
                (TextView) findViewById(R.id.number5),
                (TextView) findViewById(R.id.number6),
                (TextView) findViewById(R.id.number7),
                (TextView) findViewById(R.id.number8)
        }, this);
        scroller.init();

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new KeypadAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (checker.isPeeking(position)) {
                    boolean canPeek = GameState.peek();
                    if (canPeek) {
                        Log.v(TAG, "PEEKING");
                        scroller.peek();
                        peeks.setText("" + GameState.getPeeks());
                    } else {
                        Log.v(TAG, "SHAKE");
                        shake();
                    }
                    return;
                }
                switch (checker.guess(position)) {
                    case CORRECT:
                        scroller.shift();
                        currentScore.setText("Correct: " + GameState.getCorrectCount());
                        break;
                    case WRONG:
                        lives.setText("" + GameState.getLives());
                        shake();
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
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
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
