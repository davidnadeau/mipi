package com.aliasapp.mipi.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aliasapp.mipi.R;
import com.aliasapp.mipi.highscore.HighScore;


public class HighScoresActivity extends ActionBarActivity {
    private static final String TAG = "HighScores";
    private static TextView[] highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        highScores = new TextView[]{
                (TextView) findViewById(R.id.highscore0),
                (TextView) findViewById(R.id.highscore1),
                (TextView) findViewById(R.id.highscore2),
                (TextView) findViewById(R.id.highscore3),
                (TextView) findViewById(R.id.highscore4),
                (TextView) findViewById(R.id.highscore5),
                (TextView) findViewById(R.id.highscore6),
                (TextView) findViewById(R.id.highscore7),
                (TextView) findViewById(R.id.highscore8),
                (TextView) findViewById(R.id.highscore9)
        };

        loadBoard();
    }

    public void loadBoard() {
        String[] topTen = HighScore.topTen();
        for (int i = 0; i < highScores.length; i++)
            highScores[i].setText(topTen[i]);
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


}
