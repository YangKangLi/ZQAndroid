package cn.com.ziquan.lib.utils;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ToastUtil {

    // Toast对象
    private static Toast sToast;

    private ToastUtil() {
        // DO NOTHING
    }

    public static void show(Context paramContext, int paramInt) {
        show(paramContext, paramContext.getResources().getText(paramInt), 0);
    }

    public static void show(Context paramContext, int paramInt1, int paramInt2) {
        show(paramContext, paramContext.getResources().getText(paramInt1), paramInt2);
    }

    public static void show(Context paramContext, CharSequence paramCharSequence) {
        show(paramContext, paramCharSequence, 0);
    }

    /**
     * 显示Toast（短时间）
     *
     * @param context
     * @param content
     */
    public static void showShort(Context context, CharSequence content) {
        ToastUtil.show(context, content, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast（短时间）
     *
     * @param context
     * @param contentId
     */
    public static void showShort(Context context, @StringRes int contentId) {
        ToastUtil.show(context, context.getResources().getText(contentId), Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast（长时间）
     *
     * @param context
     * @param content
     */
    public static void showLong(Context context, CharSequence content) {
        ToastUtil.show(context, content, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast（长时间）
     *
     * @param context
     * @param contentId
     */
    public static void showLong(Context context, @StringRes int contentId) {
        ToastUtil.show(context, context.getResources().getText(contentId), Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param content
     * @param duration
     */
    public static void show(Context context, CharSequence content, int duration) {
        if (sToast != null) {
            sToast.setText(content);
            sToast.setDuration(duration);
        } else {
            sToast = Toast.makeText(context, content, duration);
        }
        sToast.show();
    }

    /**
     * 取消显示Toast
     */
    public static void cancle() {
        if (sToast != null)
            sToast.cancel();
    }
}
