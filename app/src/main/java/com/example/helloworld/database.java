package com.example.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.helloworld.Constants.CNAME;
import static com.example.helloworld.Constants.DBNAME;
import static com.example.helloworld.Constants.TNAME;
import static com.example.helloworld.Constants.VC;

public class database extends SQLiteOpenHelper {
    private static final String TAG = "database";

    public database(Context context) {
        super(context, DBNAME, null, VC);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建时的回调
        Log.d(TAG,"创建数据库");
        //sql: create table name(name varchar(45),tem integer,sex varchar,time varchar,place varchar);
        String sql="create table "+TNAME+"(maincode varchar,code varchar,name varchar,cla varchar,tem varchar,time varchar,place varchar,side varchar)";
        String spl="create table "+CNAME+"(name varchar,code varchar,cla varchar,tel varchar)";
        db.execSQL(sql);
        db.execSQL(spl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级时回调
        Log.d(TAG,"升级数据库");
        String sql="create table "+TNAME+"(maincode varchar,code varchar,name varchar,cla varchar,tem varchar,time varchar,place varchar,side varchar)";
        String spl="create table "+CNAME+"(name varchar,code varchar,cla varchar,tel varchar)";
        db.execSQL(sql);
        db.execSQL(spl);
    }
}
