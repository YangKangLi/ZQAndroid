package cn.com.ziquan.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.com.ziquan.lib.webview.WebViewAgent;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "file:///android_asset/index.html";
    //private static final String URL = "https://www.jd.com/";

    private WebView wvHomePage;

    private WebViewAgent mWebViewAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.wvHomePage = (WebView) findViewById(R.id.wv_homepage);

        mWebViewAgent = WebViewAgent.with(this, wvHomePage)
                .ready()
                .go(URL);
    }
}
