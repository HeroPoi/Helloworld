package com.example.helloworld;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;

import static com.example.helloworld.Constants.CNAME;
import static com.example.helloworld.Constants.TNAME;

public class dao{
    private final database helper;
    public dao(Context context){
        helper=new database(context);                       //创建数据库
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(System.currentTimeMillis());
        String p=simpleDateFormat.format(date);
        return p;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getsTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String p=simpleDateFormat.format(date);
        return p;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insert(String name, String tem, String time, String place,String code,String side,String cla){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="insert into "+TNAME+"(maincode,code,name,cla,tem,time,place,side) values(?,?,?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{getTime()+code,code,name,cla,tem,time,place,side});
        db.close();
    }

    public void insuser(String name,String code,String cla,String phone){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="insert into "+CNAME+"(name,code,cla,tel) values(?,?,?,?)";
        db.execSQL(sql,new Object[]{name,code,cla,phone});
        db.close();
    }

    public ArrayList<info> ser(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME;
        Cursor cursor=db.rawQuery(sql,null);
        ArrayList<info> put= new ArrayList<info>();
        while(cursor.moveToNext()){
            info i=new info();
            i.setMaincode(cursor.getString(cursor.getColumnIndex("maincode")));
            i.setCla(cursor.getString(cursor.getColumnIndex("cla")));
            i.setCode(cursor.getString(cursor.getColumnIndex("code")));
            i.setName(cursor.getString(cursor.getColumnIndex("name")));
            i.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            i.setTem(cursor.getString(cursor.getColumnIndex("tem")));
            i.setTime(cursor.getString(cursor.getColumnIndex("time")));
            i.setSide(cursor.getString(cursor.getColumnIndex("side")));
            put.add(i);
        }
        db.close();
        return put;
    }

    public ArrayList<info> sere(String place){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME+" where place like '%"+place+"%'";
        Cursor cursor=db.rawQuery(sql,null);
        ArrayList<info> put= new ArrayList<info>();
        while(cursor.moveToNext()){
            info i=new info();
            i.setMaincode(cursor.getString(cursor.getColumnIndex("maincode")));
            i.setCla(cursor.getString(cursor.getColumnIndex("cla")));
            i.setCode(cursor.getString(cursor.getColumnIndex("code")));
            i.setName(cursor.getString(cursor.getColumnIndex("name")));
            i.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            i.setTem(cursor.getString(cursor.getColumnIndex("tem")));
            i.setTime(cursor.getString(cursor.getColumnIndex("time")));
            i.setSide(cursor.getString(cursor.getColumnIndex("side")));
            put.add(i);
        }
        db.close();
        return put;
    }

    public ArrayList<info> sered(String place){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME+" where place like '%"+place+"%' and tem > '37'";
        Cursor cursor=db.rawQuery(sql,null);
        ArrayList<info> put= new ArrayList<info>();
        while(cursor.moveToNext()){
            info i=new info();
            i.setMaincode(cursor.getString(cursor.getColumnIndex("maincode")));
            i.setCla(cursor.getString(cursor.getColumnIndex("cla")));
            i.setCode(cursor.getString(cursor.getColumnIndex("code")));
            i.setName(cursor.getString(cursor.getColumnIndex("name")));
            i.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            i.setTem(cursor.getString(cursor.getColumnIndex("tem")));
            i.setTime(cursor.getString(cursor.getColumnIndex("time")));
            i.setSide(cursor.getString(cursor.getColumnIndex("side")));
            put.add(i);
        }
        db.close();
        return put;
    }

    public ArrayList<info> serme(String code){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME+" where code='"+code+"'";
        Cursor cursor=db.rawQuery(sql,null);
        ArrayList<info> put= new ArrayList<info>();
        for(int p=0;cursor.moveToNext()&&p<14;p++){
            info i=new info();
            i.setMaincode(cursor.getString(cursor.getColumnIndex("maincode")));
            i.setCla(cursor.getString(cursor.getColumnIndex("cla")));
            i.setCode(cursor.getString(cursor.getColumnIndex("code")));
            i.setName(cursor.getString(cursor.getColumnIndex("name")));
            i.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            i.setTem(cursor.getString(cursor.getColumnIndex("tem")));
            i.setTime(cursor.getString(cursor.getColumnIndex("time")));
            i.setSide(cursor.getString(cursor.getColumnIndex("side")));
            put.add(i);
        }
        db.close();
        return put;
    }

    public ArrayList<info> sermer(String code){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME+" where code='"+code+"'";
        Cursor cursor=db.rawQuery(sql,null);
        ArrayList<info> put= new ArrayList<info>();
        for(int p=0;cursor.moveToNext()&&p<7;p++){
            info i=new info();
            i.setMaincode(cursor.getString(cursor.getColumnIndex("maincode")));
            i.setCla(cursor.getString(cursor.getColumnIndex("cla")));
            i.setCode(cursor.getString(cursor.getColumnIndex("code")));
            i.setName(cursor.getString(cursor.getColumnIndex("name")));
            i.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            i.setTem(cursor.getString(cursor.getColumnIndex("tem")));
            i.setTime(cursor.getString(cursor.getColumnIndex("time")));
            i.setSide(cursor.getString(cursor.getColumnIndex("side")));
            put.add(i);
        }
        db.close();
        return put;
    }

    public ArrayList<infoc> serevery(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+CNAME;
        Cursor cursor=db.rawQuery(sql,null);
        ArrayList<infoc> put= new ArrayList<infoc>();
        while(cursor.moveToNext()){
            infoc i=new infoc();
            i.setCla(cursor.getString(cursor.getColumnIndex("cla")));
            i.setCode(cursor.getString(cursor.getColumnIndex("code")));
            i.setName(cursor.getString(cursor.getColumnIndex("name")));
            i.setTel(cursor.getString(cursor.getColumnIndex("tel")));
            put.add(i);
        }
        db.close();
        return put;
    }

    public int serc(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+CNAME;
        Cursor cursor=db.rawQuery(sql,null);
        int se=0;
        ArrayList<String> put= new ArrayList<String>();
        while(cursor.moveToNext()){
            info i=new info();
            put.add(cursor.getString(cursor.getColumnIndex("code")));
        }
        db.close();
        se=put.size();
        return se;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int sert(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+ TNAME+" where maincode like '%"+getTime()+"%'";
        Cursor cursor=db.rawQuery(sql,null);
        int se=0;
        ArrayList<String> put= new ArrayList<String>();
        while(cursor.moveToNext()){
            info i=new info();
            put.add(cursor.getString(cursor.getColumnIndex("code")));
        }
        db.close();
        se=put.size();
        return se;
    }

    public int serp(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+CNAME;
        Cursor cursor=db.rawQuery(sql,null);
        int se=0;
        ArrayList<String> put= new ArrayList<String>();
        while(cursor.moveToNext()){
            info i=new info();
            put.add(cursor.getString(cursor.getColumnIndex("code")));
        }
        db.close();
        if(put.isEmpty()) {
            se=0;
        }else {
            se = 1;
        }
        return se;
    }

    public int serq(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME+" where tem >='37'";
        Cursor cursor=db.rawQuery(sql,null);
        int se=0;
        ArrayList<String> put= new ArrayList<String>();
        for(;cursor.moveToNext();se++){
            info i=new info();
            put.add(cursor.getString(cursor.getColumnIndex("code")));
        }
        db.close();
        return se;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int repeat(String code){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from "+TNAME+" where maincode='"+getTime()+code+"'";
        Cursor cursor=db.rawQuery(sql,null);
        int se=0;
        ArrayList<String> put= new ArrayList<String>();
        while(cursor.moveToNext()){
            info i=new info();
            put.add(cursor.getString(cursor.getColumnIndex("maincode")));
        }
        db.close();
        if(put.isEmpty()) {
            se=0;
        }else {
            se=1;
        }
        return se;

    }


}
