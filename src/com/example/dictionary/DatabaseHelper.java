package com.example.dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME="sozluk";
	public static final String TABLE_NAME="kelime";
	public static final String COLUMN_ID="_id";
	public static final String COLUMN_AD="ad";
	public static final String COLUMN_ACIKLAMA="aciklama";
	public static final int DATABASE_VERSION=1;
	
	public static final String DATABASE_CREATE="TABLE CREATE"+TABLE_NAME+"("+COLUMN_ID+"INTEGER PRIMARY KEY "
			+ "AUTOINCREMENT,"+COLUMN_AD+"TEXT NOT NULL,"+COLUMN_ACIKLAMA+" TEXT)";
	
	public static final String DATABASE_DROP="DROP TABLE IF EXISTS"+TABLE_NAME;
	
	public DatabaseHelper(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DATABASE_DROP);
		onCreate(db);
	}
}
