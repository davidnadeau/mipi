package com.piq2015.piQ.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.piq2015.piQ.R;
import com.piq2015.piQ.highscore.HighScore;
import com.piq2015.piQ.models.GameState;


public class GameOverActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private static TextView correctGuesses;
    private static TextView gameOver;
    private static TextView toMenuText;
    private static TextView playAgainText;
    Typeface chalkFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        chalkFont = Typeface.createFromAsset(getAssets(), "fonts/chalkduster.ttf");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        correctGuesses = (TextView) findViewById(R.id.correct_guesses);
        gameOver = (TextView) findViewById(R.id.game_over);
        toMenuText = (TextView) findViewById(R.id.to_menu_text);
        playAgainText = (TextView) findViewById(R.id.play_again_text);
        correctGuesses.setText("You got " + GameState.getCorrectCount() + " correct guesses!");
        correctGuesses.setTypeface(chalkFont);
        gameOver.setTypeface(chalkFont);
        toMenuText.setTypeface(chalkFont);
        playAgainText.setTypeface(chalkFont);
        HighScore.saveScore(GameState.getCorrectCount());
    }

    public void playAgain(View view) {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

    public void toMenu(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
