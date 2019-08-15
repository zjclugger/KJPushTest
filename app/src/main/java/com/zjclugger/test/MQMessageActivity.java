package com.zjclugger.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cn.jpush.android.api.InstrumentedActivity;

/**
 * 消息显示
 */
public class MQMessageActivity extends InstrumentedActivity {

    private TextView mMQMessageView;

    public static boolean isForeground = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mq_message);
        mMQMessageView = findViewById(R.id.tv_message);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        if (getIntent() != null) {
            mMQMessageView.setText("您有【" + getIntent().getIntExtra(MQConstants.KEY_BADGER_COUNT,
                    0) + "】条新消息，即将被阅读!");
            if (!getIntent().getBooleanExtra(MQConstants.KEY_FROM_NOTIFICATION, false)) {
                //若打开不是来自通知栏时，清除所有通知
                ShortcutBadgerUtils.resetCount(this);
            }
        }
        super.onResume();
    }
}