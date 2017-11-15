package cn.com.ziquan.lib.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */

public class GsonUtil {

    /**
     * 私有构造方法
     */
    private GsonUtil() {
        throw new UnsupportedOperationException("u can't instantiate here!");
    }

    /**
     * 将对象转化成JOSN字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 将json转为clazz指定的对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json转为list集合
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }
}
