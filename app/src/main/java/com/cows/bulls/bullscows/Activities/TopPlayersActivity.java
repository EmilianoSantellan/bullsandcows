package com.cows.bulls.bullscows.Activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import com.cows.bulls.bullscows.Fragments.PlayerFragment;
import com.cows.bulls.bullscows.R;

import static com.cows.bulls.bullscows.R.id.players_container;

public class TopPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_players);

        PlayerFragment fragment = (PlayerFragment)
                getFragmentManager().findFragmentById(players_container);

        if(fragment == null) {
            fragment = PlayerFragment.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .add(players_container, fragment)
                    .commit();
        }
    }
}
