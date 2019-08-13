package com.zjclugger.test;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ShortcutBadgerUtils {
    private final static String TAG = "KINGZJCS";
    private final static String KEY_BADGER_COUNT = "badger_count";

    /**
     * 消息数+1
     *
     * @param context
     * @param notification
     */
    public static void incrementBadgerCount(Context context, Notification notification) {
        PreferencesUtil.putInt(context, KEY_BADGER_COUNT, getBadgerCount(context) + 1);
        setShortcutBadger(context, notification);
    }

    /**
     * 消息数-1
     *
     * @param context
     * @param notification
     */
    public static void decrementBadgerCount(Context context, Notification notification) {
        int value = getBadgerCount(context);
        PreferencesUtil.putInt(context, KEY_BADGER_COUNT, value == 0 ? 0 : value - 1);
        setShortcutBadger(context, notification);
    }

    /**
     * 一次清除全部消息数
     *
     * @param context
     * @param notification
     */
    public static void clearBadgerCount(Context context, Notification notification) {
        PreferencesUtil.putInt(context, KEY_BADGER_COUNT, 0);
        setShortcutBadger(context, notification);
    }

    /**
     * 应用退出时，消息数置为0
     *
     * @param context
     */
    public static void resetCount(Context context) {
        PreferencesUtil.putInt(context, KEY_BADGER_COUNT, 0);
        ShortcutBadger.removeCount(context);
    }

    /**
     * 设置角标
     *
     * @param context
     * @param notification
     */
    public static void setShortcutBadger(Context context, Notification notification) {
        boolean success = false;
        int badgerCount = getBadgerCount(context);
        Log.d(TAG, "count is ==44==" + badgerCount);

        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            ShortcutBadger.applyNotification(context, notification, badgerCount);
        } else {
            success = ShortcutBadger.applyCount(context, badgerCount);
        }

        Toast.makeText(context, "Set count=" + badgerCount + ", success=" + success,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 当然消息数
     *
     * @param context
     * @return
     */
    public static int getBadgerCount(Context context) {
        return PreferencesUtil.getInt(context, KEY_BADGER_COUNT, 0);
    }
}
