package cn.com.ziquan.lib.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.com.ziquan.lib.dialog.DialogAgent;
import cn.com.ziquan.lib.dialog.LibDialog;
import cn.com.ziquan.lib.utils.LogUtil;
import cn.com.ziquan.lib.utils.UIUtil;

/**
 * Created by Administrator on 2017/11/1.
 */

public class WebViewAgent {

    // 上下文
    private Activity mContext;

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
    private WebViewAgent(Activity mContext, WebView mWebView) {
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
        // 获得WebSettings对象
        WebSettings webSettings = mWebView.getSettings();
        // 如果访问的页面中要与Javascript交互，则WebView必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭WebView中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


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

    /**
     * 当WebView的拥有者销毁的时候调用
     */
    public void onOwnerDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d("TAG", "开始加载页面");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("TAG", "页面加载结束");
        }

//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            view.loadUrl(request.getUrl().toString());
//            return true;
//        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            LogUtil.d("WebAgent", "网页标题：" + title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            UIUtil.showAlert(mContext, message);
            result.cancel();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            UIUtil.showConfirm(mContext, message, new LibDialog.OnClickListener() {
                @Override
                public void onClick(DialogAgent agent, int which) {
                    agent.dismiss();
                }
            });
            result.confirm();
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    };
}
