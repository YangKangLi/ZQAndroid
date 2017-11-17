package cn.com.ziquan.lib.http;

import android.content.Context;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Administrator on 2017/11/16.
 */

public class BaseSubscriber<T> implements Subscriber<T> {

    // 上下文
    private Context mContext;

    /**
     * 构造方法
     *
     * @param context
     */
    public BaseSubscriber(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
