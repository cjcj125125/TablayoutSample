package com.h5.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.h5.sample.R;

/**
 * 状态栏工具
 * Created by libo on 2017/1/22.
 */

public class StatusUtils {
    //状态栏状态,分为透明和彩色两种(statusState的参数)
    public static int STATUS_STATE_COLOR = 0x01;
    public static int STATUS_STATE_TRANSPARENT = 0x02;
    public static int sStatusState = STATUS_STATE_COLOR;

    public static int TRANSPARENT = 0x00000000;

    public static void setStatus(Activity activity, int statusState, int color) {
        Window window = activity.getWindow();
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        ViewGroup parenView = (ViewGroup) decorView.getChildAt(0);
        View statusView = new View(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusHeight(activity));
        statusView.setLayoutParams(params);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //开启4.4的透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (parenView != null) {
                parenView.addView(statusView, 0);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上，状态栏默认为半透明的，此处将状态栏改为透明的
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (parenView != null) {
                ViewCompat.setFitsSystemWindows(parenView, false);
            }
            window.setStatusBarColor(TRANSPARENT);
        }
        if (statusState == STATUS_STATE_TRANSPARENT) {
            statusView.setVisibility(View.GONE);
        } else {
            statusView.setVisibility(View.VISIBLE);
            statusView.setBackgroundColor(color);
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusHeight(Context context) {
        int statusBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
