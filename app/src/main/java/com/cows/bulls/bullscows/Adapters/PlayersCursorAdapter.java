package com.cows.bulls.bullscows.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.cows.bulls.bullscows.R;
import com.cows.bulls.bullscows.data.PlayerContract.PlayerEntry;

/**
 * Created by Emiliano Santellan on 22/9/17.
 */

/**
 * Adapters to Players
 */
public class PlayersCursorAdapter extends CursorAdapter {
    public PlayersCursorAdapter(Context context, Cursor c)
    {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_player, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor)
    {
        // Referencias UI
        TextView nameText = (TextView) view.findViewById(R.id.textViewName);
        TextView attemptsText = (TextView) view.findViewById(R.id.textViewAttempts);

        // Get Valores
        String name = cursor.getString(cursor.getColumnIndex(PlayerEntry.NAME));
        String attempts = cursor.getString(cursor.getColumnIndex(PlayerEntry.ATTEMPTS)) + " Intentos";

        // Setup
        nameText.setText(name);
        attemptsText.setText(attempts);
    }
}
