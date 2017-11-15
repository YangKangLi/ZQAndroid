package cn.com.ziquan.lib.utils;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/11/15.
 */

public class LogUtil {

    // 标记是否是Debug
    private static boolean sDebug = false;

    /**
     * 初始化
     *
     * @param isDebug
     */
    public static void init(boolean isDebug) {
        Logger.addLogAdapter(new AndroidLogAdapter());
        LogUtil.sDebug = isDebug;
    }

    public static void v(String tag, String msg) {
        if (sDebug) {
            Logger.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sDebug) {
            Logger.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sDebug) {
            Logger.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sDebug) {
            Logger.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebug) {
            Logger.e(tag, msg);
        }
    }

    /**
     * 打印异常信息
     *
     * @param t
     */
    public static void printExcption(Throwable t) {
        Logger.e(t, "Program error");
    }
}
