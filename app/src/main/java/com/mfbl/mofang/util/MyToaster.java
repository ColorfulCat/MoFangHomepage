package com.mfbl.mofang.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ***
 *
 * @Description: Toast管理类
 * @Author: Libin
 * @CreateDate: 2013-12-24
 * ****
 */
public class MyToaster {

    private static Toast toast = null;
    private static Context mContext;

    private MyToaster() {
    }

    /**
     * ***
     *
     * @MethodName: cancelToast
     * @Description: 取消当前toast
     * @Author: Libin
     * @CreateDate: 2013-12-24
     * ****
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * ***
     *
     * @MethodName: showToast
     * @Description: 显示系统默认toast
     * @Author: Libin
     * @CreateDate: 2013-12-24
     * ****
     */
    public static void showToast(String string) {
        if (mContext == null) {
            mContext = MyConfig.getContext();
        }
        cancelToast();
        toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);//
        toast.show();
    }

    /**
     * ***
     *
     * @MethodName: showToast
     * @Description: 显示系统默认toast 可改时间
     * @Author: Libin
     * @CreateDate: 2013-12-24
     * ****
     */
    public static void showToast(String string, int time) {
        if (mContext == null) {
            mContext = MyConfig.getContext();
        }
        cancelToast();
        toast = Toast.makeText(mContext, string, time);
        toast.show();
    }

    /**
     * @author Devis
     * @date 2015-1-4-下午4:28:01
     * @des 彩色toast
     */
    public static void showColorToast(String string, int color) { // yOffset表示对话框相对Y偏移量
        if (mContext == null) {
            mContext = MyConfig.getContext();
        }
        cancelToast();
        // 自定义的Toast
        TextView textView = new TextView(mContext);
        textView.setPadding(30, 10, 30, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(color);
        textView.setTextColor(mContext.getResources().getColor(android.R.color.white));
        textView.setText(string);
        if (toast == null) {
            toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, yOffset); // 位置，向右，向下
        }
        toast.setView(textView);
        toast.show();
    }

    public static void runOnUiThreadToast(Context context, final String content){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(content);
            }
        });
    }

}
