package com.cows.bulls.bullscows;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
