package com.aliasapp.mipi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aliasapp.mipi.R;
import com.aliasapp.mipi.highscore.HighScore;
import com.aliasapp.mipi.util.SharedPreferenceWrapper;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.actionbar_logo);
        SharedPreferenceWrapper.init(this);
        Log.v(TAG, "SCORES");
        Log.v(TAG, HighScore.readScores());
    }

    public void startGame(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }


    public void viewHighScores(View view) {
        startActivity(new Intent(this, HighScoresActivity.class));
    }


    public void practice(View view) {
        startActivity(new Intent(this, PracticeActivity.class));
    }

    public void showPi(View view) {
        startActivity(new Intent(this, ShowPiActivity.class));
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
