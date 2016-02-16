package com.fanyafeng.testnew.testnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fanyafeng.testnew.BaseActivity;
import com.fanyafeng.testnew.R;
import com.fanyafeng.testnew.testtaskActivity.SingleTopActivity;

public class TestNotificationActivity extends BaseActivity {

    private Button btn_show_notification, btn_show_two, btn_test_task, open_app_activity;
    private static final int MESSAGE_NOTIF_ID = 1239874;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = "测试notification";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        btn_show_notification = (Button) findViewById(R.id.btn_show_notification);
        btn_show_notification.setOnClickListener(this);
        btn_show_two = (Button) findViewById(R.id.btn_show_two);
        btn_show_two.setOnClickListener(this);
        btn_test_task = (Button) findViewById(R.id.btn_test_task);
        btn_test_task.setOnClickListener(this);
        open_app_activity = (Button) findViewById(R.id.open_app_activity);
        open_app_activity.setOnClickListener(this);

    }

    private void initData() {

    }

    private void showNotifycation(int i, Context context, String title, String content) {
        Log.d("TAG", "收到的推送的标题" + title);
        Log.d("TAG", "收到的推送的内容" + content);
//        Log.d("TAG", "收到的json串" + customContent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        try {
            Bundle bundle = new Bundle();
            switch (i) {

                case 1:
                    intent.setClass(context, NotifiBaseActivity.class);
                    bundle.putString("activityId", "TestTaskActivity");
                    bundle.putString("data1", "data1");
                    bundle.putString("data2", "data2");
                    bundle.putString("data3", "data3");
                    intent.putExtras(bundle);
                    break;
                case 2:
                    intent.setClass(context, SingleTopActivity.class);
                    intent.putExtra("isclear", "yes");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
                case 3:
                    intent.setClass(context, TestTaskActivity.class);
                    break;
                case 4:
                    ComponentName componentName = new ComponentName(
                            "com.prayer.android",
                            "com.prayer.android.MainActivity");
                    intent.setComponent(componentName);
                    intent.putExtra("url", "http://www.shanxiu365.com/event/eventDetail.do?isDirect=1&serviceType=5&eventID=5");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        PendingIntent contentIntent = PendingIntent.getActivity(context, MESSAGE_NOTIF_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(title)
                .setContentText(content)
                .setContentIntent(contentIntent)
                .setTicker("测试推送消息")
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher));
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(MESSAGE_NOTIF_ID, notification);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_show_notification:
                showNotifycation(1, this, "测试标题1", "测试内容1");
                break;
            case R.id.btn_show_two:
                showNotifycation(2, this, "测试标题2", "测试内容2");
                break;
            case R.id.btn_test_task:
                showNotifycation(3, this, "测试进入应用进程", "在此进入应用进程会不会杀死之前的");
                break;
            case R.id.open_app_activity:
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName(
                        "com.prayer.android",
                        "com.prayer.android.MainActivity");
                intent.setComponent(componentName);
                intent.putExtra("activityId", "WebviewActivity");
                intent.putExtra("eventUrl", "http://www.shanxiu365.com/event/eventDetail.do?isDirect=1&serviceType=5&eventID=5");
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED |
                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

//                showNotifycation(4, this, "66666666666666666666", "6666666666666666666666666666666666666666666666");
                break;
        }
    }


}
