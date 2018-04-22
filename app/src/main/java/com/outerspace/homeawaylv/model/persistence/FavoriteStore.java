package com.outerspace.homeawaylv.model.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.outerspace.homeawaylv.model.persistence.FavoriteContract.SQL_COUNT_FAVORITE;

public class FavoriteStore {
    private static final String NUMBER_FORMAT = "%d";

    private static FavoriteStore instance = new FavoriteStore();
    private static FavoriteDBHelper dbHelper;

    private FavoriteStore() { }

    public static FavoriteStore getInstance(Context context) {
        dbHelper = new FavoriteDBHelper(context);
        return instance;
    }

    public static FavoriteStore getInstance() {
        if( dbHelper == null )  {
            return null;
        }
        return instance;
    }

    public boolean isFavorite(String venueId) {
        SQLiteDatabase db = instance.dbHelper.getReadableDatabase();

        String[] projection = {
                FavoriteContract.COUNT_VENUE_ID
        };

        String[] selectionArgs = { venueId };
        Cursor cursor = db.rawQuery(SQL_COUNT_FAVORITE, selectionArgs);
        boolean bFavorite = false;
        int colIdx = cursor.getColumnIndex(FavoriteContract.COUNT_VENUE_ID);
        while( cursor.moveToNext() ) {
            bFavorite |= cursor.getInt(colIdx) != 0;
        }
        return bFavorite;
    }

    public void markAsFavorite(String venueId) {
        SQLiteDatabase db = instance.dbHelper.getWritableDatabase();
        String[] arguments = { venueId };
        db.execSQL(FavoriteContract.SQL_INSERT_FAVORITE, arguments);
    }

    public void removeFavorite(String venueId) {
        SQLiteDatabase db = instance.dbHelper.getWritableDatabase();
        String[] arguments = { venueId };
        db.execSQL(FavoriteContract.SQL_DELETE_FAVORITE, arguments);
    }
}
