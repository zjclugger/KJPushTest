package com.zjclugger.test;

import android.app.Notification;
import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class MyJPushMessageReceiver extends JPushMessageReceiver {

    private String TAG = "KINGZJC";
    private Notification mNotification;

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.d(TAG, "[收到] onMessage - " + customMessage.messageId);
    }

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        Log.d(TAG, "[注册] Register=" + s);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        //TODO：显示角标+1
        ShortcutBadgerUtils.incrementBadgerCount(context, mNotification);
        Log.d(TAG,
                "[通知-到达]=" + notificationMessage.toString() + ",count=" + getBadgerCount(context));
    }

    private int getBadgerCount(Context context) {
        return ShortcutBadgerUtils.getBadgerCount(context);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
        ShortcutBadgerUtils.decrementBadgerCount(context, mNotification);
        Log.d(TAG, "[通知-被点击]=" + notificationMessage.msgId + ",count=" + getBadgerCount(context));
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageDismiss(context, notificationMessage);
        Log.d(TAG, "[通知-被关闭]" + notificationMessage.msgId);
        //ShortcutBadgerUtils.getInstance().badgerClear();
        //TODO：用户忽略通知，角标-1？
       /* if (ShortcutBadger.removeCount(context)) {
            Log.d(TAG, "[错误] 清除角标数时出错");
        }*/
    }

    @Override
    public Notification getNotification(Context context, NotificationMessage notificationMessage) {
        Log.d(TAG, "[通知-新通知]" + notificationMessage.msgId + ",count=" + getBadgerCount(context));
        mNotification = super.getNotification(context, notificationMessage);
        return mNotification;
    }
}
