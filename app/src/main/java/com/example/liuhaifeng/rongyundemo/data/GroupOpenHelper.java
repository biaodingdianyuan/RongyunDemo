package com.example.liuhaifeng.rongyundemo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuhaifeng on 2017/1/19.
 */

public class GroupOpenHelper extends SQLiteOpenHelper {
    public GroupOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "gr", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE group1(owenr VARCHAR(20),groupname VARCHAR(20),groupid VARCHAR(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
