package com.cows.bulls.bullscows.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.text.TextUtils;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import com.cows.bulls.bullscows.Activities.TopPlayersActivity;
import com.cows.bulls.bullscows.R;
import com.cows.bulls.bullscows.data.PlayerDbHelper;
import com.cows.bulls.bullscows.data.Player;

/**
 * Create View to Top Players
 */
public class AddPlayerFragment extends Fragment {
    private static final String ARG_ATTEMPTS = "arg_attempts";

    private String mAttempts;

    private PlayerDbHelper mPlayerDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mNameField;
    private TextInputLayout mNameLabel;

    public AddPlayerFragment() {
        // Required empty public constructor
    }

    public static AddPlayerFragment newInstance(String attempts) {
        AddPlayerFragment fragment = new AddPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ATTEMPTS, attempts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAttempts = getArguments().getString(ARG_ATTEMPTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_player, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) root.findViewById(R.id.fab);
        mNameField = (TextInputEditText) root.findViewById(R.id.et_name);
        mNameLabel = (TextInputLayout) root.findViewById(R.id.til_name);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPlayer();
            }
        });

        mPlayerDbHelper = new PlayerDbHelper(getActivity());

        return root;
    }

    private void addNewPlayer() {
        boolean error = false;

        String name = mNameField.getText().toString();

        if (TextUtils.isEmpty(name)) {
            mNameLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (error) {
            return;
        }

        Player player = new Player(name, mAttempts);

        new AddPlayerTask().execute(player);
    }

    private class AddPlayerTask extends AsyncTask<Player, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Player... players) {
            return mPlayerDbHelper.savePlayer(players[0]) > 0;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            showTopPlayersScreen(result);
        }

    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showTopPlayersScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
        } else {
            ShowTopPlayers();
        }

        getActivity().finish();
    }

    private void ShowTopPlayers() {
        Intent intent = new Intent(getActivity(), TopPlayersActivity.class);
        startActivityForResult(intent, Activity.RESULT_OK);
    }
}
