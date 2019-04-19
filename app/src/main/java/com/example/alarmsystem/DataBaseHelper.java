package com.example.alarmsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "alarm_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "time";
    private static final String COL4 = "tone";

    public DataBaseHelper( Context context) {
        super(context, "ALARM_DB",null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTable = "CREATE TABLE alarm_table( ID INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR , time VARCHAR , tone INTEGER);";
            db.execSQL(createTable);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlTable = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sqlTable);

        onCreate(db);

    }

    public boolean addAlarm(String name,String time,int tone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,time);
        contentValues.put(COL4,tone);

        Log.d(TAG,"addData:adding"+name+"to"+TABLE_NAME);

        try {
            long result = db.insert(TABLE_NAME, null, contentValues);
            if(result>0){
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return  false;

    }
    public Cursor fetch()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * from alarm_table",null);
        return  cursor;
    }
}
