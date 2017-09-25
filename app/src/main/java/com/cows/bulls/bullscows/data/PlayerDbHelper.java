package com.cows.bulls.bullscows.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cows.bulls.bullscows.data.Player;
import static com.cows.bulls.bullscows.data.PlayerContract.PlayerEntry;

public class PlayerDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BullsandCows.db";

    // constructor
    public PlayerDbHelper(Context _context)
    {
        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PlayerEntry.TABLE_NAME + " ("
                + PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PlayerEntry.ID + " TEXT NOT NULL,"
                + PlayerEntry.NAME + " TEXT NOT NULL,"
                + PlayerEntry.ATTEMPTS + " TEXT NOT NULL,"
                + "UNIQUE (" + PlayerEntry.ID + "))"
        );

        // A modo de desarrollo vamos a tener player top precargados
        //mockPlayerData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    private void mockPlayerData(SQLiteDatabase sqLiteDatabase)
    {
        mockPlayer(sqLiteDatabase, new Player("Emiliano", "10"));
        mockPlayer(sqLiteDatabase, new Player("Matias", "13"));
        mockPlayer(sqLiteDatabase, new Player("Pepe", "20"));
        mockPlayer(sqLiteDatabase, new Player("Ana", "20"));
    }

    private long mockPlayer(SQLiteDatabase db, Player player)
    {
        return db.insert(
                PlayerEntry.TABLE_NAME,
                null,
                player.toContentValues()
        );
    }

    public long savePlayer(Player player)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                PlayerEntry.TABLE_NAME,
                null,
                player.toContentValues());
    }

    public Cursor getTopPlayers()
    {
        return getReadableDatabase().query(
                PlayerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                PlayerEntry.ATTEMPTS + " ASC",
                "3"
        );
    }

    public Cursor getPlayerById(String playerId)
    {
        Cursor c = getReadableDatabase().query(
                PlayerEntry.TABLE_NAME,
                null,
                PlayerEntry.ID + " LIKE ?",
                new String[]{playerId},
                null,
                null,
                null);
        return c;
    }
}
