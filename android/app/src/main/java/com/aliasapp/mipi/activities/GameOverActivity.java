package com.aliasapp.mipi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aliasapp.mipi.R;
import com.aliasapp.mipi.highscore.HighScore;
import com.aliasapp.mipi.models.GameState;


public class GameOverActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    private static TextView correctGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        correctGuesses = (TextView) findViewById(R.id.correct_guesses);
        correctGuesses.setText("You got " + GameState.getCorrectCount() + " correct guesses!");
        HighScore.saveScore(GameState.getCorrectCount());
    }

    public void playAgain(View view) {
        startActivity(new Intent(this, GameActivity.class));
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
