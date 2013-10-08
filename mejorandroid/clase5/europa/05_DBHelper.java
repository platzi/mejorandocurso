package com.mejorandola.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String TAG = DBHelper.class.getSimpleName();
	
    public static final String DB_NAME = "timeline.db";
    public static final int DB_VERSION = 1;
    
    public static final String TABLE = "timeline";
    public static final String C_ID = BaseColumns._ID;
    public static final String C_NAME = "name";
    public static final String C_SCREEN_NAME = "screen_name";
    public static final String C_IMAGE_PROFILE_URL = "image_profile_url";
    public static final String C_TEXT = "text";
    public static final String C_CREATED_AT = "created_at";
    
    public static final int C_ID_INDEX = 0;
    public static final int C_NAME_INDEX = 1;
    public static final int C_SCREEN_NAME_INDEX = 2;
    public static final int C_IMAGE_PROFILE_URL_INDEX = 3;
    public static final int C_TEXT_INDEX = 4;
    public static final int C_CREATED_AT_INDEX = 5;
    
    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
	
    // Called only once, first time the DB is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql = "create table " + TABLE + " (" + C_ID + " int primary key, " 
               + C_NAME + " text, " + C_SCREEN_NAME + " text, " +
        		C_IMAGE_PROFILE_URL + " text, " + C_TEXT + " text, " + C_CREATED_AT + " text)";
        db.execSQL(sql);
        Log.d(TAG, "onCreated sql: " + sql);
    }
	
    // Called whenever newVersion != oldVersion
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Typically do ALTER TABLE statements, but... we're just in development, so:
        db.execSQL("drop table if exists " + TABLE); // drops the old database
        Log.d(TAG, "onUpdated");
        onCreate(db); // run onCreate to get the new database
    }

}
