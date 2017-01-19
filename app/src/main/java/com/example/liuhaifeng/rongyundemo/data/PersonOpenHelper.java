package com.example.liuhaifeng.rongyundemo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuhaifeng on 2017/1/19.
 */

public class PersonOpenHelper extends SQLiteOpenHelper {
    public PersonOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "fr", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE friend(owenr VARCHAR(20),name VARCHAR(20),userID VARCHAR(20),token VARCHAR(20),friendgroup VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
