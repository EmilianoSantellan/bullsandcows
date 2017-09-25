package com.cows.bulls.bullscows.data;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.UUID;

import com.cows.bulls.bullscows.data.PlayerContract.PlayerEntry;

/*
 * Entidad Player from players top
 */
public class Player {
    private String id;
    private String name;
    private String attempts;

    public Player(String name, String attempts)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.attempts = attempts;
    }

    public Player(Cursor cursor)
    {
        id = cursor.getString(cursor.getColumnIndex(PlayerEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(PlayerEntry.NAME));
        attempts = cursor.getString(cursor.getColumnIndex(PlayerEntry.ATTEMPTS));
    }

    public ContentValues toContentValues()
    {
        ContentValues values = new ContentValues();
        values.put(PlayerEntry.ID, id);
        values.put(PlayerEntry.NAME, name);
        values.put(PlayerEntry.ATTEMPTS, attempts);

        return  values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAttempts() { return  attempts; }
}
