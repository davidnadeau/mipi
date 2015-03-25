package com.piq2015.piQ.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.piq2015.piQ.R;
import com.piq2015.piQ.highscore.HighScore;


public class HighScoresActivity extends ActionBarActivity {
    private static final String TAG = "HighScores";
    private static TextView highScores;
    Typeface chalkFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        chalkFont = Typeface.createFromAsset(getAssets(), "fonts/chalkduster.ttf");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((TextView) findViewById(R.id.high_scores_screen_text)).setTypeface(chalkFont);
        highScores = (TextView) findViewById(R.id.highscore);
        highScores.setTypeface(chalkFont);
        loadBoard();
    }

    public void loadBoard() {
        String[] topTen = HighScore.topTen();
        for (int i = 0; i < topTen.length; i++) {
            String score = topTen[i] == null ? "" + 0 : topTen[i];
            highScores.setText(highScores.getText().toString() + (i + 1) + ". " + score + '\n');
        }
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
