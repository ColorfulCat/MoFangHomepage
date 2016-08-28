package com.mfbl.mofang.util;

import android.util.Log;

/**
 * @author libin -- 上午11:16:28
 * @desc Log工具
 */
public class MyLog {
    public final static String TAG = MyConfig.getContext().getClass().getPackage().getName() + "-TTT";
    public static boolean debug = true;// LOG开关

    public static void e(String content) {
        if (debug) {
            if (MyStringUtils.isAvailable(content)) {
                Log.e(TAG, content);
            } else {
                Log.e(TAG, "log failed");
            }
        }
    }

    public static void i(String content) {
        if (debug) {
            if (MyStringUtils.isAvailable(content)) {
                Log.i(TAG, content);
            } else {
                Log.i(TAG, "log failed");
            }
        }
    }

    public static void d(String content) {
        if (debug) {
            if (MyStringUtils.isAvailable(content)) {
                Log.d(TAG, content);
            } else {
                Log.d(TAG, "log failed");
            }
        }
    }

}
