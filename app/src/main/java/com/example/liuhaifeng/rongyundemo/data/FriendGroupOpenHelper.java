package com.example.liuhaifeng.rongyundemo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuhaifeng on 2017/1/19.
 */

public class FriendGroupOpenHelper extends SQLiteOpenHelper {
    public FriendGroupOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "frgr", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE friendgroup(owenr VARCHAR(20),name VARCHAR(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
