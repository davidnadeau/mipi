package com.aliasapp.mipi.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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


public class PracticeActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private static PiChecker checker;
    private static GridView gridview;
    private static RelativeLayout livesLayout;
    private static TextView currentScore;
    private static NumberScroller scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        livesLayout = (RelativeLayout) findViewById(R.id.lives_layout);
        checker = new PiChecker();
        currentScore = (TextView) findViewById(R.id.current_score);
        currentScore.setText("0");
        scroller = new NumberScroller(new TextView[]{
                (TextView) findViewById(R.id.number0),
                (TextView) findViewById(R.id.number1),
                (TextView) findViewById(R.id.number2),
                (TextView) findViewById(R.id.number3),
                (TextView) findViewById(R.id.number4),
                (TextView) findViewById(R.id.number5),
                (TextView) findViewById(R.id.number6)
        }, this);
        scroller.initPractice();

//        piDisplay = (TextView) findViewById(R.id.pi_display);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new KeypadAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (checker.guess(position)) {
                    case CORRECT:
                        scroller.shiftPractice();
                        currentScore.setText("" + GameState.getCorrectCount());
//                        flashColor(R.color.correct_answer_background);
                        break;
                    case WRONG:
                        shake();
//                        flashColor(R.color.wrong_answer_background);
                        break;
                    case GAMEOVER:
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
