package com.example.liuhaifeng.rongyundemo.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.liuhaifeng.rongyundemo.DemoContext;
import com.example.liuhaifeng.rongyundemo.Myapplication;
import com.example.liuhaifeng.rongyundemo.NetUtils;
import com.example.liuhaifeng.rongyundemo.R;
import com.example.liuhaifeng.rongyundemo.data.groupDao;
import com.example.liuhaifeng.rongyundemo.data.groupData;
import com.example.liuhaifeng.rongyundemo.data.personDao;
import com.example.liuhaifeng.rongyundemo.data.personData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText username;
    private List<personDao> list;
    personData data_p = new personData();
    groupData data_g=new groupData();
    String token;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.user_id);
        list = new ArrayList<personDao>();
        list = data_p.getdata();

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserid().equals(username.getText().toString())) {
                        token = list.get(i).getToken();
                        RongIM.getInstance().getRongIMClient().connect(token, new RongIMClient.ConnectCallback() {
                            /**
                             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                             */
                            @Override
                            public void onTokenIncorrect() {
                            }

                            /**
                             * 连接融云成功
                             *
                             * @param userid 当前 token
                             */
                            @Override
                            public void onSuccess(String userid) {



                                    inSql();

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Myapplication.name = username.getText().toString();
                                finish();
                            }

                            /**
                             * 连接融云失败
                             *
                             * @param errorCode 错误码，可到官网 查看错误码对应的注释
                             */
                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {
                            }
                        });
                    }
                }
            }
        });
    }

    public void inSql(){
        List<groupDao> list_g;
        List<personDao> list_p;
        groupDao dao_g;
        personDao dao_p;
        list_g=data_g.getgroupdata();
        list_p=data_p.getdata();
        db=Myapplication.personOpenHelper.getWritableDatabase();
        Cursor cursor=db.query("friend",null,"owenr=?",new String[]{username.getText().toString()},null,null,null);
        if(cursor.getCount()>0){

        }else{

        ContentValues values;
        for(int i=0;i<list_p.size();i++){
            values=new ContentValues();
            values.put("name",list_p.get(i).getName());
            values.put("token",list_p.get(i).getToken());
            values.put("userID",list_p.get(i).getUserid());
            values.put("friendgroup",list_p.get(i).getFriendgroup());
            values.put("owenr",username.getText().toString());
            db.insert("friend",null,values);
        }}

      db=Myapplication.groupOpenHelper.getWritableDatabase();
        Cursor c=db.query("group1",null,"owenr=?",new String[]{username.getText().toString()},null,null,null);
       if(c.getCount()>0){

       }else {

           ContentValues v;
           for (int y = 0; y < list_g.size(); y++) {
               v = new ContentValues();
               v.put("groupname", list_g.get(y).getGroup_name());
               v.put("groupid", list_g.get(y).getGroupID());
               v.put("owenr", username.getText().toString());
               db.insert("group1", null, v);
           }
       }
        db=Myapplication.friendGroupOpenHelper.getWritableDatabase();
        Cursor cu=db.query("friendgroup",null,"owenr=?",new String[]{username.getText().toString()},null,null,null);
        if(cu.getCount()>0){

        }else{
            ContentValues va=new ContentValues();
            va.put("owenr",username.getText().toString());
            va.put("name","我的好友");
            db.insert("friendgroup",null,va);
        }


    }

}

