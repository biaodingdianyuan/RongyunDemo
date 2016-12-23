package com.example.liuhaifeng.rongyundemo.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.liuhaifeng.rongyundemo.data.personDao;
import com.example.liuhaifeng.rongyundemo.data.personData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText userid;
    private List<personDao> list;
    personData data=new personData();
    String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userid= (EditText) findViewById(R.id.user_id);
        list=new ArrayList<personDao>();
        list=data.getdata();

        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getUserid().equals(userid.getText().toString())){
                        token=list.get(i).getToken();
                        RongIM.getInstance().getRongIMClient().connect(token, new RongIMClient.ConnectCallback() {

                            /**
                             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                             */
                            @Override
                            public void onTokenIncorrect() {

                                Log.d("LoginActivity", "--onTokenIncorrect");
                            }

                            /**
                             * 连接融云成功
                             *
                             * @param userid 当前 token
                             */
                            @Override
                            public void onSuccess(String userid) {
                  startActivity(new Intent(LoginActivity.this,MainActivity.class));


                                finish();
                                Log.d("LoginActivity", "--onSuccess---" + userid);
                            }

                            /**
                             * 连接融云失败
                             *
                             * @param errorCode 错误码，可到官网 查看错误码对应的注释
                             */
                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {

                                Log.d("LoginActivity", "--onError" + errorCode);
                            }
                        });



                    }

                }







            }
        });
    }





}

