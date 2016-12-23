package com.example.liuhaifeng.rongyundemo.data;

import java.util.ArrayList;
import java.util.List;

/**
 *构造测试数据
 */

public class personData {
    String[] name={"小明","小亮","小红"};
    String []userID={"1001","1003","1004"};
    String [] token={
           "IPogxD8CFYC3ycpQYwuNn8kw6NwCnGCkgc85gkUmVDEfOmBQMqBKVSbG6EXev8KWdN6aZxTigNskSugwx4o84Q=="
            ,"9xWdH062N6iCrmDZ5/HiDckw6NwCnGCkgc85gkUmVDEfOmBQMqBKVVAh7NjkcWEeKlZeLq2gmTVJ5zylVRJFdg=="
    ,"m6wOzy71rDnM2Uh2ljnx8skw6NwCnGCkgc85gkUmVDEfOmBQMqBKVTkiDJToLhCt4/xE1ji0gIf+Y9fJkIV6FA=="};

    public List<personDao> getdata(){
        List<personDao> list=new ArrayList<personDao>();
        personDao person;
        for(int i=0;i<name.length;i++){
            person=new personDao();
            person.setName(name[i]);
            person.setUserid(userID[i]);
            person.setToken(token[i]);
            list.add(person);


        }
        return list;

}

}
