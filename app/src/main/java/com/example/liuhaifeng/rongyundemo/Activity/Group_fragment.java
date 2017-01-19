package com.example.liuhaifeng.rongyundemo.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liuhaifeng.rongyundemo.Myapplication;
import com.example.liuhaifeng.rongyundemo.R;
import com.example.liuhaifeng.rongyundemo.data.groupDao;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by liuhaifeng on 2017/1/19.
 */

public class Group_fragment extends Fragment {

    private ListView lv;
    private groupDao group;
    private List<groupDao> list;
    SQLiteDatabase db;
    private groupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, null);
        lv = (ListView) view.findViewById(R.id.group_lv);
        list = new ArrayList<groupDao>();
        adapter = new groupAdapter(list);
        lv.setAdapter(adapter);
        getdata();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.GROUP,list.get(position).getGroupID(),list.get(position).getGroup_name());
            }
        });
        return view;
    }

    class groupAdapter extends BaseAdapter {
        List<groupDao> list;

        public groupAdapter(List<groupDao> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getActivity().getLayoutInflater().inflate(R.layout.person_item, null);
            TextView name = (TextView) view.findViewById(R.id.person_name);
            name.setText(list.get(i).getGroup_name());


            return view;
        }
    }

    public void getdata() {
        list.clear();
        db = Myapplication.groupOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("group1", null, "owenr=?", new String[]{Myapplication.name}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                group = new groupDao();
                group.setGroup_name(cursor.getString(cursor.getColumnIndex("groupname")));
                group.setGroupID(cursor.getString(cursor.getColumnIndex("groupid")));
                list.add(group);
            } while (cursor.moveToNext());

        }

        adapter.notifyDataSetChanged();
    }

}
