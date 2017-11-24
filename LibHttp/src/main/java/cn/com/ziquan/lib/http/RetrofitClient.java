package cn.com.ziquan.lib.http;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/16.
 */

public class RetrofitClient {

    // 默认的超时时间(秒)
    private static final int DEFAULT_TIMEOUT = 25;

    /**
     * 静态内部类，RetrofitClient持有类
     */
    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    /**
     * 获得RetrofitClient实例对象
     *
     * @return
     */
    public static final RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }


    // 超时时间
    private long mTimeout = DEFAULT_TIMEOUT;

    private String mBaseUrl;

    private OkHttpClient.Builder mOkHttpBuilder;

    private Retrofit mRetrofit;

    private BaseApiServer mDefaultServer;


    /**
     * 私有构造方法
     */
    private RetrofitClient() {

    }

    /**
     * 初始化
     *
     * @param baseUrl
     */
    public static void init(String baseUrl) {
        init(baseUrl, DEFAULT_TIMEOUT);
    }

    /**
     * 初始化
     *
     * @param baseUrl
     * @param timeout
     */
    public static void init(String baseUrl, int timeout) {
        init(baseUrl, timeout, null);
    }

    /**
     * 初始化
     *
     * @param baseUrl
     * @param timeout
     * @param interceptor
     */
    public static void init(String baseUrl, int timeout, Interceptor interceptor) {
        getInstance().initialize(baseUrl, timeout, interceptor);
    }

    /**
     * 初始化
     *
     * @param baseUrl
     * @param timeout
     * @param interceptor
     */
    private void initialize(String baseUrl, int timeout, Interceptor interceptor) {
        // 初始化BaseUrl和超时时间
        this.mBaseUrl = baseUrl;
        this.mTimeout = timeout;

        // 配置网络参数
        mOkHttpBuilder = new OkHttpClient.Builder();
        if (interceptor != null) {
            mOkHttpBuilder.addInterceptor(interceptor);
        }
        mOkHttpBuilder.readTimeout(mTimeout, TimeUnit.SECONDS);
        mOkHttpBuilder.connectTimeout(mTimeout, TimeUnit.SECONDS);

        // 创建Retrofit实例
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpBuilder.build())//设置OKHttpClient
                .baseUrl(mBaseUrl)//baseUrl
                //.addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mDefaultServer = mRetrofit.create(BaseApiServer.class);
    }

    /**
     * 创建Server
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createServer(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    /**
     * Get请求
     *
     * @param url
     * @return
     */
    public Call executeGet(String url) {
        return mDefaultServer.executeGet(url);
    }

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @return
     */
    public Call<ResponseBody> executeGet(String url, Map<String, Object> params) {
        return mDefaultServer.executeGet(url, params);
    }
}
