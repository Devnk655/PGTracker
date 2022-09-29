package com.example.pgtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


    public class DataBaseHandler extends SQLiteOpenHelper {
        public DataBaseHandler(@Nullable Context context) {
            super(context, "PGTracker.db",null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table PGTracker(monthyear TEXT primary key, price double, things TEXT, total double)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop Table if exists PGTracker ");
        }
        public Boolean insertPGTrackerdetails(String monthyear, String price, String things, String total) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("monthyear",monthyear);
            contentValues.put("price",price);
            contentValues.put("things",things);
            contentValues.put("total",total);
            long result = db.insert("PGTracker",null,contentValues);
            if(result==-1){
                return false;
            }
            else{
                return  true;
            }
        }
        public Boolean updatePGTrackerdetails(String monthyear, String price, String things, String total) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("monthyear",monthyear);
            contentValues.put("things",things);
            contentValues.put("total",total);
            Cursor cursor = db.rawQuery("Select * from PGTracker where monthyear=?",new String[] {monthyear});
            if(cursor.getCount()>0) {
                long result = db.update("PGTracker", contentValues, "monthyear=?", new String[]{monthyear});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }else{
                return false;
            }
        }
        public Boolean deletePGTrackerdetails(String monthyear) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from PGTracker where monthyear=?",new String[] {monthyear});
            if(cursor.getCount()>0) {
                long result = db.delete("PGTracker", "monthyear=?", new String[]{monthyear});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }else{
                return false;
            }
        }
        public Cursor getPGTrackerdetails () {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from PGTracker" ,null);
            return cursor;
        }
}
