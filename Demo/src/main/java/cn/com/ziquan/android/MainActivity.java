package cn.com.ziquan.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import cn.com.ziquan.lib.dialog.DialogAgent;
import cn.com.ziquan.lib.dialog.LibDialog;
import cn.com.ziquan.lib.utils.DTUtil;
import cn.com.ziquan.lib.utils.GsonUtil;
import cn.com.ziquan.lib.utils.LogUtil;
import cn.com.ziquan.lib.utils.UIUtil;
import cn.com.ziquan.lib.webview.WebViewAgent;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "file:///android_asset/index.html";
    //private static final String URL = "https://www.jd.com/";

    private WebView wvHomePage;

    private WebViewAgent mWebViewAgent;

    private EditText etTestEdit;

    private Button btnCallJs;

    private DialogAgent dialogAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.init(true);


        this.btnCallJs = (Button) findViewById(R.id.btn_call_javascript);
        this.etTestEdit = (EditText) findViewById(R.id.et_test_edit);
        btnCallJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mWebViewAgent.callJavascript("calledByJava", "\"1\"", "\"2\"");

                String content = GsonUtil.toJson(new Person("Adam", 32, true));

                dialogAgent = UIUtil.showAlert(MainActivity.this, content);


            }
        });


        this.wvHomePage = (WebView) findViewById(R.id.wv_homepage);

        mWebViewAgent = WebViewAgent.with(this, wvHomePage)
                .ready()
                .go(URL);
    }

    @Override
    public void onBackPressed() {
        if (!mWebViewAgent.onPressBack()) {
            UIUtil.showConfirm(MainActivity.this, "确认要退出程序吗？", new LibDialog.OnClickListener() {
                @Override
                public void onClick(DialogAgent agent, int which) {
                    MainActivity.this.finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        mWebViewAgent.onOwnerDestroy();
        super.onDestroy();
    }
}
