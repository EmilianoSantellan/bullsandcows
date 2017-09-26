package com.cows.bulls.bullscows.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.net.Uri;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

import com.cows.bulls.bullscows.Adapters.PlayersCursorAdapter;
import com.cows.bulls.bullscows.R;
import com.cows.bulls.bullscows.data.PlayerContract;
import com.cows.bulls.bullscows.data.PlayerDbHelper;

/**
 * List View to Player
 */
public class PlayerFragment extends Fragment {

    private PlayerDbHelper mPlayerDbHelper;

    private ListView mPlayerList;
    private PlayersCursorAdapter mPlayerAdapter;

    public PlayerFragment() {
        // Required empty public constructor
    }

    public static  PlayerFragment newInstance() {
        return new PlayerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_player, container, false);

        // References UI
        mPlayerList = (ListView) root.findViewById(R.id.players_list);
        mPlayerAdapter = new PlayersCursorAdapter(getActivity(), null);

        // Setup
        mPlayerList.setAdapter(mPlayerAdapter);

        getActivity().deleteDatabase(PlayerContract.PlayerEntry.TABLE_NAME);

        // Instance helper
        mPlayerDbHelper = new PlayerDbHelper(getActivity());

        getActivity().setTitle(R.string.top_players);

        // Load Data
        loadPlayers();

        return root;
    }

    private void loadPlayers()
    {
        new PlayersLoadTask().execute();
    }

    private class PlayersLoadTask extends AsyncTask<Void, Void, Cursor>
    {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return mPlayerDbHelper.getTopPlayers();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if(cursor != null && cursor.getCount() > 0) {
                mPlayerAdapter.swapCursor(cursor);
            } else {

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            showSuccessfullSavedMessage();
            loadPlayers();
        }
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                R.string.player_succefull, Toast.LENGTH_SHORT).show();
    }
}
