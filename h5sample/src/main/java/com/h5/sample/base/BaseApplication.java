package com.h5.sample.base;

import android.app.Application;
import android.util.Log;

import com.h5.sample.MainActivity;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Right on 2017/2/24.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QbSdk.initX5Environment(getApplicationContext(), cb);
        Log.i("TAG", "预加载中...");
    }
    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onViewInitFinished(boolean arg0) {
            Log.i("TAG","onViewInitFinished----------------"+arg0);
        }
        @Override
        public void onCoreInitFinished() {
          Log.i("TAG","X5内核加载完成----------------");

        }
    };
}
