package com.example.liuhaifeng.rongyundemo.Activity;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuhaifeng.rongyundemo.Myapplication;
import com.example.liuhaifeng.rongyundemo.R;
import com.example.liuhaifeng.rongyundemo.data.FriendGroupDao;
import com.example.liuhaifeng.rongyundemo.data.personDao;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by liuhaifeng on 2017/1/19.
 */

public class Friend_fragment extends Fragment {
    private personAdapter adapter_p;
    private ExpandableListView lv;
    private List<FriendGroupDao> list_f;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_friend,null);
        lv = (ExpandableListView) view.findViewById(R.id.lv_person);

        list_f=new ArrayList<FriendGroupDao>();
        adapter_p = new personAdapter(getActivity(),list_f);
        lv.setAdapter(adapter_p);
        getdata();

        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(list_f!=null){
                    if (RongIM.getInstance() != null){
                        RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,list_f.get(groupPosition).getList().get(childPosition).getUserid(),
                                list_f.get(groupPosition).getList().get(childPosition).getName());
                    }
                }
                return true;
            }
        });

        return view;



    }

    class personAdapter extends BaseExpandableListAdapter {
        private List<FriendGroupDao> list;
        LayoutInflater layoutInflater;
        public personAdapter(Context context, List<FriendGroupDao> list) {
            this.list=list;
            layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return list.get(groupPosition).getList().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return list.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return list.get(groupPosition).getList().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.friendgroup_item,null);
            }
            TextView name= (TextView) convertView.findViewById(R.id.friendgroup_name);
            ImageView img= (ImageView) convertView.findViewById(R.id.img);
            name.setText(list.get(groupPosition).getName());
            if(isExpanded){
                img.setImageDrawable(getResources().getDrawable(R.drawable.up));

            }else{
                img.setImageDrawable(getResources().getDrawable(R.drawable.left));
            }

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.person_item,null);
            }
            TextView name= (TextView) convertView.findViewById(R.id.person_name);
            name.setText(list.get(groupPosition).getList().get(childPosition).getName());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
    public  void getdata(){
        list_f.clear();
        db= Myapplication.friendGroupOpenHelper.getReadableDatabase();
        FriendGroupDao dao_f;
        List<personDao> list_per=new ArrayList<personDao>();
        Cursor cursor=db.query("friendgroup",null,"owenr=?",new String[]{Myapplication.name},null,null,null);
        if(cursor.moveToFirst()){
            do{
                dao_f=new FriendGroupDao();
                dao_f.setName(cursor.getString(cursor.getColumnIndex("name")));
                list_f.add(dao_f);

            }while (cursor.moveToNext());

        }
        db=Myapplication.personOpenHelper.getReadableDatabase();
        personDao dao_p;

        Cursor c=db.query("friend",null,"owenr=?",new String[]{Myapplication.name},null,null,null);
        if(c.moveToFirst()){
            do{
                dao_p=new personDao();
                dao_p.setName(c.getString(c.getColumnIndex("name")));
                dao_p.setToken(c.getString(c.getColumnIndex("token")));
                dao_p.setUserid(c.getString(c.getColumnIndex("userID")));
                dao_p.setFriendgroup(c.getString(c.getColumnIndex("friendgroup")));
                list_per.add(dao_p);

            }while (c.moveToNext());

        }
        FriendGroupDao da = null;
        List<personDao> li;
        personDao p;
        for(int i=0;i<list_f.size();i++){
            li=new ArrayList<personDao>();
            for(int o=0;o<list_per.size();o++){

                if(list_f.get(i).getName().equals(list_per.get(o).getFriendgroup())){
                    da=new FriendGroupDao();
                    p=new personDao();
                    p.setFriendgroup(list_per.get(o).getFriendgroup());
                    p.setName(list_per.get(o).getName());
                    p.setToken(list_per.get(o).getToken());
                    p.setUserid(list_per.get(o).getUserid());
                    li.add(p);
                }

            }
            da.setList(li);
            da.setName(list_f.get(i).getName());


        }
        cursor.close();
        list_f.clear();
        list_f.add(da);
        adapter_p.notifyDataSetChanged();
    }
}
