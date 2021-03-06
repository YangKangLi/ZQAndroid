package cn.com.ziquan.android;

import android.app.Application;

import cn.com.ziquan.lib.http.RetrofitClient;
import cn.com.ziquan.lib.push.LibPush;

/**
 * Created by Administrator on 2017/11/17.
 */

public class LocalApplication extends Application {

    private static final String BASE_URL = "http://fy.iciba.com/";
    //private static final String BASE_URL = "http://ip.taobao.com/service/";

    @Override
    public void onCreate() {
        super.onCreate();
        //LogUtil.init(true);

        RetrofitClient.init(BASE_URL);

        LibPush.init(this, false, LibPush.PLATFORM_JPUSH);

    }
}
