package cn.com.ziquan.android;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/17.
 */

public interface ApiServer {

    @GET("getIpInfo.php")
    Call<ResponseBody> test1(@Query("ip") String ip);

    @GET("ajax.php")
        Call<ResponseBody> test2(@Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);
}
