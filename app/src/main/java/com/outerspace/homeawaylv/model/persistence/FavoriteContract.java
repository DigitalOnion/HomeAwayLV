package com.outerspace.homeawaylv.model.persistence;

import android.provider.BaseColumns;

public class FavoriteContract {

    private FavoriteContract() { } // prevent instantiation

    public static final String DB_NAME = "Favorite.db";
    public static final Integer DB_VERSION = 3;

    public static class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite_table";
        public static final String COLUMN_NAME_VENUE_ID = "venue_id";
    }

    public static final String COUNT_VENUE_ID = "count_venue_id";

    public static final String SQL_CREATE_FAVORITES_TABLE =
            "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                    FavoriteEntry._ID + " INTEGER PRIMARY KEY," +
                    FavoriteEntry.COLUMN_NAME_VENUE_ID + " TEXT)";

    public static final String SQL_DROP_FAVORITES_TABLE =
            "DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME;

    public static final String SQL_DELETE_FAVORITE =
            "DELETE FROM " + FavoriteEntry.TABLE_NAME + " WHERE " +
                    FavoriteEntry.COLUMN_NAME_VENUE_ID + " LIKE ? ";

    public static final String SQL_INSERT_FAVORITE =
            "INSERT INTO " + FavoriteEntry.TABLE_NAME +
                    " (" + FavoriteEntry.COLUMN_NAME_VENUE_ID + ") " +
                    " VALUES ( ? )";

    public static final String SQL_COUNT_FAVORITE =
            "SELECT count(" + FavoriteEntry.COLUMN_NAME_VENUE_ID + ")"
                    + " AS " + FavoriteContract.COUNT_VENUE_ID
                    + " FROM " + FavoriteEntry.TABLE_NAME
                    + " WHERE " + FavoriteEntry.COLUMN_NAME_VENUE_ID
                    + " LIKE ? ";
}

