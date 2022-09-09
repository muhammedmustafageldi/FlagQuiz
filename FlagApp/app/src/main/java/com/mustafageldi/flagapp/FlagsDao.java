package com.mustafageldi.flagapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDao {


    public ArrayList<Flags> random5get(FlagDataBaseHelper helper){
        ArrayList<Flags> myFlags = new ArrayList<>();

        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 6",null);

        while(c.moveToNext()){

            @SuppressLint("Range") Flags flag = new Flags(c.getInt(c.getColumnIndex("bayrak_id")),c.getString(c.getColumnIndex("bayrak_ad")),c.getString(c.getColumnIndex("bayrak_resim")));
            myFlags.add(flag);
        }

        c.close();
        return myFlags;
    }



    public ArrayList<Flags> false3get(FlagDataBaseHelper helper, int flag_id) {

        ArrayList<Flags> falseAnswers = new ArrayList<>();
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor c = database.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != "+flag_id+" ORDER BY RANDOM() LIMIT 3",null);

        while (c.moveToNext()){

            @SuppressLint("Range") Flags flag = new Flags(c.getInt(c.getColumnIndex("bayrak_id")),c.getString(c.getColumnIndex("bayrak_ad")),c.getString(c.getColumnIndex("bayrak_resim")));
            falseAnswers.add(flag);
        }

        c.close();
        return  falseAnswers;
    }






}
