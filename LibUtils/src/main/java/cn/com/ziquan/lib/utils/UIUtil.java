package cn.com.ziquan.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import cn.com.ziquan.lib.dialog.DialogAgent;
import cn.com.ziquan.lib.dialog.LibDialog;

/**
 * Created by Administrator on 2017/11/15.
 */

public class UIUtil {

    // Toast对象
    private static Toast sToast;

    /**
     * 显示Toast（短时间）
     *
     * @param context
     * @param content
     */
    public static void showShortToast(Context context, CharSequence content) {
        UIUtil.showToast(context, content, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast（长时间）
     *
     * @param context
     * @param content
     */
    public static void showLongToast(Context context, CharSequence content) {
        UIUtil.showToast(context, content, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param content
     * @param duration
     */
    public static void showToast(Context context, CharSequence content, int duration) {
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
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
        }
    }

    /**
     * 显示警告对话框
     *
     * @param activity
     * @param content
     * @return
     */
    public static DialogAgent showAlert(Activity activity, CharSequence content) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_ALERT)
                .setContent(content)
                .showDialog();
    }

    /**
     * 显示警告对话框
     *
     * @param activity
     * @param content
     * @param positiveBtnText
     * @return
     */
    public static DialogAgent showAlert(Activity activity, CharSequence content, CharSequence positiveBtnText) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_ALERT)
                .setContent(content)
                .setPositiveBtn(positiveBtnText)
                .showDialog();
    }

    /**
     * 显示警告对话框
     *
     * @param activity
     * @param content
     * @param listener
     * @return
     */
    public static DialogAgent showAlert(Activity activity, CharSequence content, LibDialog.OnClickListener listener) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_ALERT)
                .setContent(content)
                .setPositiveBtn(listener)
                .showDialog();
    }

    /**
     * 显示警告对话框
     *
     * @param activity
     * @param content
     * @param positiveBtnText
     * @param listener
     * @return
     */
    public static DialogAgent showAlert(Activity activity, CharSequence content, CharSequence positiveBtnText, LibDialog.OnClickListener listener) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_ALERT)
                .setContent(content)
                .setPositiveBtn(positiveBtnText, listener)
                .showDialog();
    }

    /**
     * 显示确认对话框
     *
     * @param activity
     * @param content
     * @param positiveBtnListener
     * @return
     */
    public static DialogAgent showConfirm(Activity activity, CharSequence content, LibDialog.OnClickListener positiveBtnListener) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_CONFIRM)
                .setContent(content)
                .setPositiveBtn(positiveBtnListener)
                .showDialog();
    }

    /**
     * 显示确认对话框
     *
     * @param activity
     * @param content
     * @param positiveBtnText
     * @param positiveBtnListener
     * @return
     */
    public static DialogAgent showConfirm(Activity activity, CharSequence content, CharSequence positiveBtnText, LibDialog.OnClickListener positiveBtnListener) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_CONFIRM)
                .setContent(content)
                .setPositiveBtn(positiveBtnText, positiveBtnListener)
                .showDialog();
    }

    /**
     * 显示确认对话框
     *
     * @param activity
     * @param content
     * @param positiveBtnText
     * @param positiveBtnListener
     * @param negativeBtnText
     * @return
     */
    public static DialogAgent showConfirm(Activity activity, CharSequence content, CharSequence positiveBtnText, LibDialog.OnClickListener positiveBtnListener, CharSequence negativeBtnText) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_CONFIRM)
                .setContent(content)
                .setPositiveBtn(positiveBtnText, positiveBtnListener)
                .setNegativeBtn(negativeBtnText)
                .showDialog();
    }

    /**
     * 显示确认对话框
     *
     * @param activity
     * @param content
     * @param positiveBtnText
     * @param positiveBtnListener
     * @param negativeBtnText
     * @param negativeBtnListener
     * @return
     */
    public static DialogAgent showConfirm(Activity activity, CharSequence content, CharSequence positiveBtnText, LibDialog.OnClickListener positiveBtnListener, CharSequence negativeBtnText, LibDialog.OnClickListener negativeBtnListener) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_CONFIRM)
                .setContent(content)
                .setPositiveBtn(positiveBtnText, positiveBtnListener)
                .setNegativeBtn(negativeBtnText, negativeBtnListener)
                .showDialog();
    }

    /**
     * 显示等待对话框
     *
     * @param activity
     * @param content
     * @return
     */
    public static DialogAgent showWaiting(Activity activity, CharSequence content) {
        return DialogAgent.getInstance(activity).create(LibDialog.DIALOG_TYPE_WAITING)
                .setContent(content)
                .showDialog();
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * DP转PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Activity context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * PX转DP
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Activity context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
