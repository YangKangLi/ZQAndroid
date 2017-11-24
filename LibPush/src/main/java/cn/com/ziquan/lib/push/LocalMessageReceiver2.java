package cn.com.ziquan.lib.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LocalMessageReceiver2 extends BroadcastReceiver {

    private static final String TAG = LocalMessageReceiver2.class.getSimpleName();

    public LocalMessageReceiver2() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "LocalMessageReceiver2 ---> onReceive");
//        switch (intent.getAction()){
//
//        }
        Log.d(TAG, intent.getAction());

    }
}
