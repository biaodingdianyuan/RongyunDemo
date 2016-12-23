package com.example.liuhaifeng.rongyundemo.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liuhaifeng.rongyundemo.R;
import com.example.liuhaifeng.rongyundemo.data.groupDao;
import com.example.liuhaifeng.rongyundemo.data.groupData;
import com.example.liuhaifeng.rongyundemo.data.personDao;
import com.example.liuhaifeng.rongyundemo.data.personData;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static com.example.liuhaifeng.rongyundemo.R.id.line1;
import static com.example.liuhaifeng.rongyundemo.R.id.list;


public class ContactFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv;
    private personData data_p;
    private groupData data_g;
    private personAdapter adapter_p;
    private groupAdapter adapter_g;
    private LinearLayout person_lin, group_lin;
    private List<personDao> list_p=new ArrayList<personDao>();
    private List<groupDao> list_g=new ArrayList<groupDao>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, null);
        lv = (ListView) view.findViewById(R.id.lv_person);
        person_lin = (LinearLayout) view.findViewById(R.id.person);
        group_lin = (LinearLayout) view.findViewById(R.id.group);
        person_lin.setOnClickListener(this);
        group_lin.setOnClickListener(this);
        lv.setOnItemClickListener(this);
        data_p = new personData();
        list_p = data_p.getdata();
        adapter_p = new personAdapter(list_p);
        lv.setAdapter(adapter_p);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person:
                list_g.clear();
                data_p = new personData();
                list_p = data_p.getdata();
                adapter_p = new personAdapter(list_p);
                lv.setAdapter(adapter_p);

                break;
            case R.id.group:
                //显示群组列表
                list_p.clear();
                data_g=new groupData();
                list_g=data_g.getgroupdata();
                adapter_g=new groupAdapter(list_g);
                lv.setAdapter(adapter_g);

                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(list_g!=null){
        if (RongIM.getInstance() != null)
            RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.GROUP,list_g.get(i).getGroupID(),list_g.get(i).getGroup_name());

    }else if(list_p!=null){
            if (RongIM.getInstance() != null)
                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,list_p.get(i).getUserid(),list_p.get(i).getName());
        }
    }

    class personAdapter extends BaseAdapter {
        private List<personDao> list;

        public personAdapter(List<personDao> list) {
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
            name.setText(list.get(i).getName());


            return view;
        }
    }
    class  groupAdapter extends BaseAdapter{
        List<groupDao> list;
        public groupAdapter(List<groupDao> list) {
            this.list=list;
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


}
