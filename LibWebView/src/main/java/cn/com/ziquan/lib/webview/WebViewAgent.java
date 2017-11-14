package cn.com.ziquan.lib.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.com.ziquan.lib.dialog.DialogAgent;
import cn.com.ziquan.lib.dialog.LibDialog;

/**
 * Created by Administrator on 2017/11/1.
 */

public class WebViewAgent {

    // 上下文
    private Context mContext;

    // WebView对象
    private WebView mWebView;

    /**
     * @param activity
     * @return
     */
    public static WebViewAgent with(Activity activity, WebView webView) {
        return new WebViewAgent(activity, webView);
    }

    /**
     * @param fragment
     * @return
     */
    public static WebViewAgent with(Fragment fragment, WebView webView) {
        return new WebViewAgent(fragment.getActivity(), webView);
    }

    /**
     * 构造方法
     *
     * @param mContext
     * @param mWebView
     */
    @TargetApi(Build.VERSION_CODES.M)
    private WebViewAgent(Context mContext, WebView mWebView) {
        this.mContext = mContext;
        this.mWebView = mWebView;
        this.mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
            }
        });
    }

    /**
     * 准备:做初始化操作
     *
     * @return
     */
    public WebViewAgent ready() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 不会使用浏览器打开超链接
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        return this;
    }

    /**
     * 打开页面
     *
     * @param url
     * @return
     */
    public WebViewAgent go(String url) {
        this.mWebView.loadUrl(url);
        return this;
    }

    /**
     * 调用javascript脚本
     *
     * @param method
     */
    public void callJavascript(String method) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + method + "()");
        this.mWebView.loadUrl(sb.toString());
    }

    /**
     * 调用Javascript
     *
     * @param method
     * @param params
     */
    public void callJavascript(String method, String... params) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + method);
        sb.append("(");
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                String param = params[i];
                sb.append(param);

                if (i != params.length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append(")");
        this.mWebView.loadUrl(sb.toString());
    }

    /**
     * 调用Javascript
     *
     * @param method
     * @param callback
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void callJavascript(String method, ValueCallback<String> callback) {
        this.mWebView.evaluateJavascript(method, callback);
    }

    /**
     * 调用Javascript
     *
     * @param method
     * @param callback
     * @param params
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void callJavascript(String method, ValueCallback<String> callback, String... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        if (params != null && params.length > 0) {
            sb.append("(");
            for (int i = 0; i < params.length; i++) {
                String param = params[i];
                sb.append(param);

                if (i != params.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
        }
        this.mWebView.evaluateJavascript(sb.toString(), callback);
    }

    public boolean onPressBack() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        return false;
    }

    private WebViewClient mWebViewClient = new WebViewClient() {

    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            DialogAgent.getInstance(mContext)
                    .create(LibDialog.DIALOG_TYPE_ALERT)
                    .setContent(title)
                    .showDialog();
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            DialogAgent.getInstance(mContext)
                    .create(LibDialog.DIALOG_TYPE_ALERT)
                    .setContent(message)
                    .showDialog();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            DialogAgent.getInstance(mContext)
                    .create(LibDialog.DIALOG_TYPE_CONFIRM)
                    .setContent(message)
                    .showDialog();
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    };
}
