package com.cows.bulls.bullscows.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.cows.bulls.bullscows.Fragments.AddPlayerFragment;
import com.cows.bulls.bullscows.R;

import static com.cows.bulls.bullscows.R.id.add_edit_lawyer_container;

public class AddPlayerActivity extends AppCompatActivity {

    private String attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        setTitle(R.string.winner_player);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            attempts = extras.getString("key");
        }

        AddPlayerFragment fragment = (AddPlayerFragment)
                getFragmentManager().findFragmentById(add_edit_lawyer_container);

        if(fragment == null) {
            fragment = AddPlayerFragment.newInstance(attempts);
            getFragmentManager()
                    .beginTransaction()
                    .add(add_edit_lawyer_container, fragment)
                    .commit();
        }
    }
}
