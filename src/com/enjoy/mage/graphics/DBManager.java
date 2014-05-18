package com.enjoy.mage.graphics;

import org.andengine.util.debug.Debug;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {
	public static void Test(Context context)
	{
		DBHelper dbHelper=new DBHelper(context);
		dbHelper.creatDataBase();
		
		SQLiteDatabase db=dbHelper.getWritableDatabase();
	    Cursor cursor=db.rawQuery("select * from test", null);
	    
	    cursor.moveToFirst();
	    int id=cursor.getInt(0);    
	    Debug.w(id+" ");             
	   	    
	}
}
