package com.example.equiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String TABLE_REQ = "seanceRemarques";
    private static final String COLOR_ID = "id";
    private static final String COL_DETAILS = "details";

    public MyDB(@Nullable Context context) {
        super(context,"remarque.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_REQ + "("+
                COLOR_ID + " INTEGER PRIMARY KEY,"+
                COL_DETAILS+" TEXT NOT NULL)");
    }
    public  void insertRemarque(int id , String details){
        ContentValues row = new ContentValues();
        row.put(COLOR_ID, id);
        row.put(COL_DETAILS,details);
        getWritableDatabase().insertWithOnConflict(TABLE_REQ, null ,row,SQLiteDatabase.CONFLICT_REPLACE);
    }
    public String readRemarque(int id){
        Cursor c =  getReadableDatabase().rawQuery("SELECT "+ COL_DETAILS + " FROM " + TABLE_REQ
                + " WHERE " + COLOR_ID +"="+id,null);
        if (c != null && c.moveToFirst()){
            return  c.getString(c.getColumnIndex(COL_DETAILS));
        }
        return "";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion<2){}

    }
}
