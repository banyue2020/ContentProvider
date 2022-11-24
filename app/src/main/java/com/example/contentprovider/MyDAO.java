package com.example.contentprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyDAO {

    private final Context context;
    private  SQLiteDatabase database;

    public MyDAO(Context context) {
        this.context = context;
        MyDBHelper dbHelper = new MyDBHelper(context,"fwjDB",null,1);
        database =dbHelper.getWritableDatabase();

    }

    public Uri DAOinsert(ContentValues values){
        long rowid = database.insert("student",null,values);

        Uri uri = Uri.parse("content://fwj.ContentProvider/student");

        Uri inserturi= ContentUris.withAppendedId(uri,rowid);

        context.getContentResolver().notifyChange(inserturi,null);
        return inserturi;
    }
}