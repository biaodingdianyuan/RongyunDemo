package com.example.liuhaifeng.rongyundemo.data;

import java.util.List;

/**
 * Created by liuhaifeng on 2017/1/19.
 */

public class FriendGroupDao {
    private String name;
    private List<personDao> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<personDao> getList() {
        return list;
    }

    public void setList(List<personDao> list) {
        this.list = list;
    }
}
