package com.cows.bulls.bullscows.data;

import android.provider.BaseColumns;

/**
 * Scheme of the database for Players
 */
public class PlayerContract {
    public static abstract class PlayerEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "player";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ATTEMPTS = "attempts";
    }
}
