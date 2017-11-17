package cn.com.ziquan.lib.http;

import java.util.Map;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/11/16.
 */

public interface BaseApiServer {

    @GET("{url}")
    <T> Call<T> executeGet(@Path("url") String url);

    @GET("{url}")
    Call<ResponseBody> executeGet(@Path("url") String url, @QueryMap Map<String, Object> params);
}
