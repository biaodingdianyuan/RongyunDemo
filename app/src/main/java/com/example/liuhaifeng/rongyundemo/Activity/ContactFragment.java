package com.example.liuhaifeng.rongyundemo.Activity;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuhaifeng.rongyundemo.Myapplication;
import com.example.liuhaifeng.rongyundemo.R;
import com.example.liuhaifeng.rongyundemo.data.FriendGroupDao;
import com.example.liuhaifeng.rongyundemo.data.groupDao;
import com.example.liuhaifeng.rongyundemo.data.groupData;
import com.example.liuhaifeng.rongyundemo.data.personDao;
import com.example.liuhaifeng.rongyundemo.data.personData;
import java.util.ArrayList;
import java.util.List;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class ContactFragment extends Fragment implements View.OnClickListener{

    private personData data_p;
    private groupData data_g;
    private View fragment;


    private LinearLayout person_lin, group_lin;
    private List<groupDao> list_g=new ArrayList<groupDao>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, null);

        person_lin = (LinearLayout) view.findViewById(R.id.person);
        group_lin = (LinearLayout) view.findViewById(R.id.group);
        person_lin.setOnClickListener(this);
        group_lin.setOnClickListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_contact,new Friend_fragment()).commit();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person:
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_contact,new Friend_fragment()).commit();
                break;
            case R.id.group:
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_contact,new Group_fragment()).commit();


                break;


        }
    }








}
