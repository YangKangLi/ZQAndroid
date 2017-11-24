package cn.com.ziquan.lib.push;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LocalMessageReceiver extends JPushMessageReceiver {

    private static final String TAG = LocalMessageReceiver.class.getSimpleName();

    public LocalMessageReceiver() {
        super();
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
        Log.d(TAG, "LocalMessageReceiver ---> onTagOperatorResult --> "+jPushMessage.getAlias());
        LibPush.MessageListener listener = LibPush.getInstance().getMessageListener();
        if (listener != null) {

        }
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
        Log.d(TAG, "LocalMessageReceiver ---> onCheckTagOperatorResult --> "+jPushMessage.getAlias());
        LibPush.MessageListener listener = LibPush.getInstance().getMessageListener();
        if (listener != null) {

        }
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        Log.d(TAG, "LocalMessageReceiver ---> onAliasOperatorResult --> "+jPushMessage.getAlias());
        LibPush.MessageListener listener = LibPush.getInstance().getMessageListener();
        if (listener != null) {

        }
    }
}
