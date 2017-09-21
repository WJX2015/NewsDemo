package com.example.lenovo_g50_70.newsdemo.activity;

import android.graphics.PixelFormat;
import android.view.KeyEvent;

import com.example.lenovo_g50_70.newsdemo.R;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class NewsDetailActivity extends BaseActivity {

    private com.tencent.smtt.sdk.WebView mWebView;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        //视频为了避免闪屏和透明问题
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl(getIntent().getStringExtra("URL"));
    }

    @Override
    public void initListener() {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                mWebView.loadUrl(s);
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
