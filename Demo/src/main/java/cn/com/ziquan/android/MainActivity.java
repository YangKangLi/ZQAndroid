package cn.com.ziquan.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import cn.com.ziquan.lib.dialog.DialogAgent;
import cn.com.ziquan.lib.dialog.LibDialog;
import cn.com.ziquan.lib.http.RetrofitClient;
import cn.com.ziquan.lib.utils.GsonUtil;
import cn.com.ziquan.lib.utils.LogUtil;
import cn.com.ziquan.lib.utils.UIUtil;
import cn.com.ziquan.lib.webview.WebViewAgent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //private static final String URL = "file:///android_asset/index.html";
    //private static final String URL = "https://www.jd.com/";
    private static final String URL = "https://yangkangli.github.io/hui/";

    private WebView wvHomePage;

    private WebViewAgent mWebViewAgent;

    private EditText etTestEdit;

    private Button btnCallJs;

    private DialogAgent dialogAgent;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.init(true);

        mWebViewAgent = WebViewAgent.with(this, (WebView) findViewById(R.id.wv_homepage)).ready().go(URL);


        this.btnCallJs = (Button) findViewById(R.id.btn_call_javascript);
        this.etTestEdit = (EditText) findViewById(R.id.et_test_edit);
        btnCallJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mWebViewAgent.callJavascript("calledByJava", "\"1\"", "\"2\"");

                // 验证发送通知
                /*
                int id = UIUtil.sendNotification(MainActivity.this,
                        R.mipmap.ic_launcher,
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
                        "作为USB存储设备使用",
                        "触摸课显示其他USB选项。");
                */


                // 验证Retrofit:使用BaseApiServer接口
                /*
                Map<String, Object> param = new HashMap<>();
                param.put("ip", "222.44.81.23");
                Call<ResponseBody> objectCall = RetrofitClient.getInstance()
                        .executeGet("getIpInfo.php", param);

                objectCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            UIUtil.showAlert(MainActivity.this, response.body().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        UIUtil.showAlert(MainActivity.this, "失败");
                    }
                });
                */
                // 验证Retrofit：使用ApiServer
                RetrofitClient.getInstance().createServer(ApiServer.class).test2("fy", "auto", "auto", "hello, world")
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String json = response.body().string();
                                    Test test = GsonUtil.jsonToObject(json, Test.class);
                                    UIUtil.showAlert(MainActivity.this, test.getContent().getOut());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mWebViewAgent != null) {
            if (!mWebViewAgent.onPressBack()) {
                UIUtil.showConfirm(MainActivity.this, "确认要退出程序吗？", new LibDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogAgent agent, int which) {
                        MainActivity.this.finish();
                    }
                });
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebViewAgent != null) {
            mWebViewAgent.onOwnerDestroy();
        }
        super.onDestroy();
    }
}
