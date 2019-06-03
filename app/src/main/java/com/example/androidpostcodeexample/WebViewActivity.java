package com.example.androidpostcodeexample;

import android.annotation.SuppressLint;
import android.content.Intent;
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
        webView.getSettings().setJavaScriptEnabled(true); //javascript
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); // window.open
        webView.addJavascriptInterface(new AndroidBridge(), "android");
        webView.setWebChromeClient(new WebChromeClient());

        //(assets / www / index.html) to the web server and fetch the address of the corresponding web server.
        //(assets/www/index.html) 에 있는 파일을 웹 서버에 올려서 해당 웹서버의 주소를 가져 온다. 로컬에서는 동작하지 않으므로 반드시 웹에 올려서 테스트를 한다..
        webView.loadUrl("https://URL.html");

    }

    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("Args", "arg1 : " + arg1 + ", arg2 : " + arg2 + ", arg3 : " + arg3);
                    Intent intent = new Intent();
                    intent.putExtra("arg1",arg1);
                    intent.putExtra("arg2",arg2);
                    intent.putExtra("arg3",arg3);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }
}
