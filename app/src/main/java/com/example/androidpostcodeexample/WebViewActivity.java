package com.example.androidpostcodeexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        init_webView();
        handler = new Handler();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init_webView(){
        webView = findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true); //javascript 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); // window.open 허용
        webView.addJavascriptInterface(new AndroidBridge(), "android");
        webView.setWebChromeClient(new WebChromeClient());

        // assets 안에 있는 index.html 을 반드시 웹 상에서 구현하여 아래 에 URL 을 삽입 해야함
        webView.loadUrl("file:///android_asset/www/index.html");

    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("Args", "arg1 : " + arg1 + ", arg2 : " + arg2 + ", arg3 : " + arg3);
                    init_webView();
                }
            });
        }
    }
}
