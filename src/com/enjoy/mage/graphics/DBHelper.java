package com.enjoy.mage.graphics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	
	private static final int DB_VERSION= 1;
	
	//��ݿ��ļ�Ŀ����·��ΪϵͳĬ��λ�ã�cn.arthur.examples ����İ���
    private static String DB_PATH = "/data/data/com.gameyself.www/databases/";

    private static String DB_NAME         = "test.db";
    private static String ASSETS_NAME     = "test.db";
    
    private SQLiteDatabase myDataBase    = null;
    private final Context myContext;
    
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.myContext=context;
	}
		    
	public DBHelper(Context context, String name, int version){
	    this(context,name,null,version);
	}
	
	public DBHelper(Context context, String name){
	     this(context,name,DB_VERSION);
	}
	    
	public DBHelper (Context context) {
	     this(context, DB_PATH + DB_NAME);
	}     

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void creatDataBase()
	{
		if(!checkDataBase())
		{
			try{
			File dir=new File(DB_PATH);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			File dbf=new File(DB_PATH+DB_NAME);
			if(dbf.exists())
			{
				dbf.delete();
			}
			copyDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean checkDataBase()
	{
	   SQLiteDatabase checkDB = null;
	   String myPath = DB_PATH + DB_NAME;
	   try{           
		   checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
		             //database does't exist yet.
		}
		if(checkDB != null){
		   checkDB.close();
		}
	    return checkDB != null ? true : false;
	}
	
	private void copyDataBase() throws IOException
	{
		InputStream input=this.myContext.getAssets().open(ASSETS_NAME);
		String outFileName=DB_PATH+ASSETS_NAME;
		OutputStream out=new FileOutputStream(outFileName);
		byte []buffer=new byte[1024];
		int length=0;
		while((length=input.read(buffer))>0)
		{
			out.write(buffer,0,length);
		}
		out.flush();
		out.close();
		input.close();
		
	}
	
    @Override
	public synchronized void close() {
	 if(myDataBase != null){
	    myDataBase.close();
	 }
	  super.close();
	 }
	
}
