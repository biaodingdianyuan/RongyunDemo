package com.example.liuhaifeng.rongyundemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.liuhaifeng.rongyundemo.data.FriendGroupOpenHelper;
import com.example.liuhaifeng.rongyundemo.data.GroupOpenHelper;
import com.example.liuhaifeng.rongyundemo.data.PersonOpenHelper;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.widget.adapter.MessageListAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by liuhaifeng on 2016/12/13.
 */

public class Myapplication extends Application implements RongIMClient.OnReceiveMessageListener {
    private String token;
    public static FriendGroupOpenHelper friendGroupOpenHelper;
    public static GroupOpenHelper groupOpenHelper;
    public static PersonOpenHelper personOpenHelper;
    public static String name;
    @Override
    public void onCreate() {
        super.onCreate();
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
                RongIM.init(this);
                RongIM.getInstance().getRongIMClient().setOnReceiveMessageListener(this);

        }
        friendGroupOpenHelper=new FriendGroupOpenHelper(getApplicationContext(),"frgr",null,1);
        groupOpenHelper=new GroupOpenHelper(getApplicationContext(),"gr",null,1);
        personOpenHelper=new PersonOpenHelper(getApplicationContext(),"fr",null,1);



    }
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
    @Override
    public boolean onReceived(Message message, int i) {
        return false;
    }
}
