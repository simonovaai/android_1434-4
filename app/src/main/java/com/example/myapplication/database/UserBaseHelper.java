package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.User;

import static com.example.myapplication.database.UserDbSchema.*;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db"; // для создания БД
    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ UserTable.NAME+" ("+
                "_id integer primary key autoincrement," +
                UserTable.cols.UUID+"," +
                UserTable.cols.USERNAME+"," +
                UserTable.cols.USERLASTNAME+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
