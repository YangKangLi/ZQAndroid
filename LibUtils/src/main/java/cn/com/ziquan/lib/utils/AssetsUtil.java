package cn.com.ziquan.lib.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/15.
 */

public class AssetsUtil {

    /**
     * 从Assert中的文件读取JSON字符串
     *
     * @param context
     * @param filePath
     * @return
     */
    public static String getJsonStr(Context context, String filePath) {
        InputStreamReader inputStreamReader = null;
        StringBuilder stringBuilder = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(filePath), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String jsonStr;
            stringBuilder = new StringBuilder();
            while ((jsonStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }
}
