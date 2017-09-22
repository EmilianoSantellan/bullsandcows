package com.cows.bulls.bullscows.Activities;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cows.bulls.bullscows.Activities.HelpActivity;
import com.cows.bulls.bullscows.Activities.StartGameActivity;
import com.cows.bulls.bullscows.Activities.TopPlayersActivity;
import com.cows.bulls.bullscows.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view)
    {
        Intent gameActivityIntent = new Intent(this, StartGameActivity.class);
        startActivity(gameActivityIntent);
    }

    public void topPlayers(View view)
    {
        Intent topPlayerActivityIntent = new Intent(this, TopPlayersActivity.class);
        startActivity(topPlayerActivityIntent);
    }

    public void help(View view)
    {
        Intent helpActivityIntent = new Intent(this, HelpActivity.class);
        startActivity(helpActivityIntent);
    }
}
