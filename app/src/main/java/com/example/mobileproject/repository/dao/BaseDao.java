package com.example.mobileproject.repository.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileproject.repository.DataBaseOpenHelper;


public class BaseDao {


    protected SQLiteDatabase getReadableDatabase(Context context)
    {
        return DataBaseOpenHelper.getInstance(context).getReadableDatabase();
    }

    protected SQLiteDatabase getWritableDatabase(Context context)
    {
        return DataBaseOpenHelper.getInstance(context).getWritableDatabase();
    }
}
