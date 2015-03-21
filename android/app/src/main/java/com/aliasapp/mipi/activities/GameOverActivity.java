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
import com.aliasapp.mipi.models.GameState;


public class GameOverActivity extends Activity {
    private static final String TAG = "MainActivity";

    private static TextView correctGuesses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        correctGuesses = (TextView) findViewById(R.id.correct_guesses);
        correctGuesses.setText("You got " + GameState.getCorrectCount() + " correct guesses!");
    }

    public void playAgain(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }


}
