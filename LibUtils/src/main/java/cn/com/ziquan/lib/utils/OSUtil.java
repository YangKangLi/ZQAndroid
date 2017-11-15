package cn.com.ziquan.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/15.
 */

public class OSUtil {

    /**
     * 私有构造方法
     */
    private OSUtil() {
        throw new UnsupportedOperationException("u can't instantiate here!");
    }

    /**
     * 获取UUID，必须声明
     * {@link android.permission.READ_PHONE_STATE} permission in its manifest.
     *
     * @param context
     * @return
     */
    public static String getUUID(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString().toUpperCase();
    }

    /**
     * 获取app版本名
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取app版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * 判断网络是否连接，必须申明
     * {@link android.permission.ACCESS_NETWORK_STATE} permission in its manifest.
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接，必须申明
     * {@link android.permission.ACCESS_NETWORK_STATE} permission in its manifest.
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打开系统设置界面
     *
     * @param activity
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 打开Wifi设置界面
     *
     * @param activity
     */
    public static void openWifiSetting(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 打开照相机（生成的照片使用默认的命名方式：IMG_yyyyMMdd_HHmmss.jpg，如IMG_20171115_132909.jpg）
     *
     * @param activity
     * @param path
     * @param requestCode
     */
    public static void openCamera(Activity activity, String path, int requestCode) {
        openCamera(activity, path, "IMG_" + DTUtil.formatDate(new Date(), "yyyyMMdd_HHmmss") + ".jpg", requestCode);
    }

    /**
     * 打开照相机
     *
     * @param activity
     * @param path
     * @param fileName
     * @param requestCode
     */
    public static void openCamera(Activity activity, String path, String fileName, int requestCode) {
        // TODO 创建存放照片的文件夹
        // FileUtils.makeDirs(path);
        File cameraFile = new File(path, fileName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
        activity.startActivityForResult(intent, requestCode);
    }





    /**
     * 打开软键盘
     *
     * @param editText
     * @param context
     */
    public static void openKeybord(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param editText
     * @param context
     */
    public static void closeKeybord(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }



    /**
     * 检查是否授予了指定的权限
     *
     * @param context
     * @param permName
     * @return
     */
    public static boolean checkPermission(Context context, String permName) {
        int perm = context.checkCallingOrSelfPermission(permName);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
