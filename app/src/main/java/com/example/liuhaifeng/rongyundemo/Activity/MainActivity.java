package com.example.liuhaifeng.rongyundemo.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.liuhaifeng.rongyundemo.R;

import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private LinearLayout conversation_lin, contact_lin;
    private Toolbar toolbar;
    private TextView title;
    private  ImageView add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        contact_lin = (LinearLayout) findViewById(R.id.contact);
        conversation_lin = (LinearLayout) findViewById(R.id.conversation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        add= (ImageView) findViewById(R.id.add);
        title = (TextView) findViewById(R.id.title);
        title.setText("会话");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new ConversationList_Fragment()).commit();
        add.setVisibility(View.GONE);
        contact_lin.setOnClickListener(this);
        conversation_lin.setOnClickListener(this);
       add.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.conversation:
                toolbar.getMenu().clear();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new ConversationList_Fragment()).commit();
                title.setText("会话");
                add.setVisibility(View.GONE);
                break;
            case R.id.contact:
                title.setText("联系人");
                toolbar.getMenu().clear();
                add.setVisibility(view.VISIBLE);
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new ContactFragment()).commit();
                break;
            case R.id.add:
                inpopupwindow(view);
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {


            final AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
            alterDialog.setMessage("确定退出应用？");
            alterDialog.setCancelable(true);

            alterDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (RongIM.getInstance() != null)
                        RongIM.getInstance().disconnect(true);

                    finish();
                }
            });
            alterDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alterDialog.show();
        }

        return false;
    }


    private void inpopupwindow(View v){

                   View view=this.getLayoutInflater().inflate(R.layout.popupwindow_contact,null,false);



        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;

            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量

        popWindow.showAsDropDown(v,v.getWidth(),7);



    }
}
