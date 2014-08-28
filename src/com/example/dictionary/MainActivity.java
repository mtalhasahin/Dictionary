package com.example.dictionary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private DatabaseHelper helper=new DatabaseHelper(this);
	private String[] projection=new String[]{DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_AD,
			DatabaseHelper.COLUMN_ACIKLAMA};
	private Cursor butunkelimeleriSorgula(){
		SQLiteDatabase db=helper.getReadableDatabase();
		return db.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null, null);
	}
	
	private Cursor kelimeSorgula(String ad){
		
		String where=DatabaseHelper.COLUMN_AD+"=?";
		String [] whereArgs=new String[]{ad};
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor cursor=db.query(DatabaseHelper.TABLE_NAME, projection, where, whereArgs, null, null, null);
	
		 return cursor;
	}
	private int getKelimeId(String ad){
		
		Cursor cursor=kelimeSorgula(ad);
		int count=cursor.getCount();
		cursor.moveToNext();
		int Idindex=cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
		return cursor.getInt(Idindex);
	}
	private long kelimeEkle(String ad, String aciklama){
		ContentValues satir=new ContentValues();
		satir.put("ad", ad);
		satir.put("aciklama", aciklama);
		
		SQLiteDatabase db=helper.getWritableDatabase();
		long eklenenKelimeId=db.insert(DatabaseHelper.TABLE_NAME,null,satir);
		return eklenenKelimeId;
	}
	private void kelimeGuncelle(String ad, String aciklama){
		ContentValues guncelSatir=new ContentValues();
		guncelSatir.put("ad", ad);
		guncelSatir.put("aciklama", aciklama);
		
		int kelimeId=getKelimeId(ad);
		SQLiteDatabase db=helper.getWritableDatabase();
		String where=DatabaseHelper.COLUMN_ID+"="+kelimeId;
		db.update(DatabaseHelper.TABLE_NAME, guncelSatir, where, null);
	}
	
	private void kelimeSil(String ad){
		int kelimeId=getKelimeId(ad);
		SQLiteDatabase db=helper.getWritableDatabase();
		String where=DatabaseHelper.COLUMN_ID+"="+kelimeId;
		db.delete(DatabaseHelper.TABLE_NAME, where, null);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dmain);
	}
}
