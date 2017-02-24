package com.h5.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.h5.sample.utils.StatusUtil;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mWebview)
    WebView mWebview;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //   StatusUtil.setStatusTranslucent(this);//完全沉浸
        StatusUtil.setStatusColorByResource(this, R.color.toolbar);//状态栏改色
        if (!QbSdk.isTbsCoreInited()) {
            // preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
            QbSdk.preInit(MainActivity.this, null);// 设置X5初始化完成的回调接口
            Log.i("TAG", "加载了一次000000");
        }
        setWebView();
    }

    private void setWebView() {
//        toolbar.setTitle("首页");
//        setSupportActionBar(toolbar);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//自适应屏幕
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDefaultTextEncodingName("utf-8");
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.addJavascriptInterface(new WebAppInterFace(this), "app");
        mWebview.loadUrl("file:///android_asset/page/index.html");

    }


    @Override
    protected void onDestroy() {
        ButterKnife.bind(this).unbind();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            // 返回上一页面  调用无缓存模式
            mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            mWebview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class WebAppInterFace {
        private Context context;

        public WebAppInterFace(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void sayHello(String name) {
            if (name.equals("")) {
                Toast.makeText(context, "还没点本地按钮", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
