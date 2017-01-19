package com.example.liuhaifeng.rongyundemo.data;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by liuhaifeng on 2016/12/15.
 */

public class personDao {
    private String name;
    private String userid;
    private String token;

    public String getFriendgroup() {
        return friendgroup;
    }

    public void setFriendgroup(String friendgroup) {
        this.friendgroup = friendgroup;
    }

    private String friendgroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
