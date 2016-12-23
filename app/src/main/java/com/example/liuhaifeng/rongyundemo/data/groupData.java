package com.example.liuhaifeng.rongyundemo.data;

import java.util.ArrayList;
import java.util.List;



/**
 * 创建群组调试数据
 * groupID  群组ID
 * groupname 群组名称
 * number    群内人员数量
 *
 */

public class groupData {
    List<groupDao> list=new ArrayList<groupDao>();
    String[] groupID={"1102"};
    String[] groupname={"1111111"};



    public List<groupDao> getgroupdata(){
        groupDao dao;
        for(int i=0;i<groupID.length;i++){
            dao=new groupDao();
            dao.setGroup_name(groupname[i]);
            dao.setGroupID(groupID[i]);

            list.add(dao);

        }
        return list;
    }


}
