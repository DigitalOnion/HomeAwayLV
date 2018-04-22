package com.outerspace.homeawaylv.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoriteDBHelper extends SQLiteOpenHelper {

    public FavoriteDBHelper(Context context) {
        super(context,
                FavoriteContract.DB_NAME,
                null,
                FavoriteContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( FavoriteContract.SQL_CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FavoriteContract.SQL_DROP_FAVORITES_TABLE);
        onCreate( db );
    }
}
