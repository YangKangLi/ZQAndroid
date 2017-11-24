package cn.com.ziquan.lib.push;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LibPush {

    private static final String TAG = "LibPush";

    /**
     * 极光推送平台
     */
    public static final int PLATFORM_JPUSH = 0x01;

    /**
     * 友盟推送平台
     */
    public static final int PLATFORM_UMENG = 0x02;

    /**
     * 小米推送平台
     */
    public static final int PLATFORM_XIAOMI = 0x03;

    /**
     * 华为推送平台
     */
    public static final int PLATFORM_HUAWEI = 0x04;


    private static class SingletonHolder {
        private static LibPush INSTANCE = new LibPush();
    }

    protected static final LibPush getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     * @param isDebug
     * @param platforms
     */
    public static final void init(Context context, boolean isDebug, int... platforms) {
        getInstance().initialize(context, isDebug, platforms);
    }


    // 上下文
    private Context mContext;

    // 标记是否Bebug模式，开启后会打印日志
    private boolean mDebug;

    // 使用的推送平台
    private int[] mPlatforms;

    // 消失接收监听器
    private MessageListener mMessageListener;

    /**
     * 初始化LibPush
     *
     * @param context
     * @param isDebug
     * @param platforms
     */
    private void initialize(Context context, boolean isDebug, int... platforms) {
        this.mContext = context;
        this.mDebug = isDebug;
        this.mPlatforms = platforms;

        for (int i = 0; i < platforms.length; i++) {
            switch (platforms[i]) {
                case PLATFORM_JPUSH:
                    JPushInterface.setDebugMode(isDebug);
                    JPushInterface.init(context);
                    JPushInterface.setAlias(context, 1, "Adam");
                    break;
                case PLATFORM_UMENG:
                    break;
                case PLATFORM_XIAOMI:
                    break;
                case PLATFORM_HUAWEI:
                    break;
            }
        }
    }

    /**
     * 获得消息监听器
     *
     * @return
     */
    protected MessageListener getMessageListener() {
        return mMessageListener;
    }

    /**
     * 推送消息监听器
     */
    interface MessageListener {

        /**
         * 初始化成功回调方法
         *
         * @param platform 第三方推送平台
         */
        void onInitSuccess(int platform);

        /**
         * 初始化失败
         *
         * @param platform  第三方推送平台
         * @param errorCode 错误码
         */
        void onInitFailed(int platform, int errorCode);

        void onReceiveMessage(int platform);
    }
}
