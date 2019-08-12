package com.zjclugger.test;

import android.app.Notification;
import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class MyJPushMessageReceiver extends JPushMessageReceiver {

    private String TAG ="KZJCLUGGER";
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.d(TAG, "[收到] onMessage - " + customMessage.toString());
    }

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        Log.d(TAG, "[注册] Register=" + s);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        Log.d(TAG, "[收到] onReceive -MSGID=" + notificationMessage.toString());
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
        Log.d(TAG, "[通知] 通知被点击=" + notificationMessage.toString());
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageDismiss(context, notificationMessage);
        Log.d(TAG, "[通知] 通知消失++=" + notificationMessage.toString());
    }

    @Override
    public Notification getNotification(Context context, NotificationMessage notificationMessage) {
        Log.d(TAG, "[通知] 获取通知@@" + notificationMessage.toString());
        return super.getNotification(context, notificationMessage);
    }


}
