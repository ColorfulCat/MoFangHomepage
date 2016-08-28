package com.mfbl.mofang.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * ******
 *
 * @author: Devis 2015-3-18 上午11:37:59
 * @desc : 初始化配置
 * *******
 */
public class MyConfig {

    //leancloud
    public static final String LEANCLOUD_ID = "k32nwrumufbu1bbo3fbnb0ylk1txskpy5k3judc8vnjj02k7";
    public static final String LEANCLOUD_KEY = "1n29xiaq968copjbbz61j9ujvbtdw39enxjoumg0a9xl9e9w";



    private static Context mContext;

    // 屏幕分辨率
    private static float density;
    private static DisplayMetrics metrics;
    // 屏幕宽度
    private static int widthPixels;
    // 屏幕高度
    private static int heightPixels;

    /**
     * *******
     *
     * @author: Devis 2015-3-18 上午11:38:26
     * @desc : 初始化
     * ********
     */
    public static void initConfig(Context context) {
        mContext = context;
    }

    /**
     * *******
     *
     * @author: Devis 2015-3-18 上午11:38:30
     * @desc : 获取全局上下文
     * ********
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * *******
     *
     * @author: Devis 2015-3-27 上午10:06:34
     * @desc : 切换到全屏
     * ********
     */
    public static void toFullScreen(Activity context) {
        // 切换到全屏
        context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * *******
     *
     * @author: Devis 2015-3-27 上午10:06:41
     * @desc : 切换到非全屏
     * ********
     */
    public static void toNotFullScreen(Activity context) {
        // 切换到非全屏
        context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    /**
     * *******
     *
     * @author: Devis 2015-3-27 上午10:07:14
     * @desc : dp转换成px
     * ********
     */
    public static int dipToPX(final Context ctx, float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }

    /**
     * *******
     *
     * @author: Devis 2015-3-27 上午10:13:05
     * @desc : 获取状态栏高度
     * ********
     */
    public static int getStatusBarHeight1(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    //获取设备编号
    public static String getDeviceId(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tm != null){
            return tm.getDeviceId();
        }
        return "default";
    }
    //获取设备型号
    public static String getDeviceType(){
        Build bd = new Build();
        String model = bd.MODEL;
        return model;
    }

    //获取设备信息
    public static String getDeviceInfo(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tm != null){
            StringBuilder sb = new StringBuilder();
            sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());
            sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
            sb.append("\nLine1Number = " + tm.getLine1Number());
            sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
            sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
            sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
            sb.append("\nNetworkType = " + tm.getNetworkType());
            sb.append("\nPhoneType = " + tm.getPhoneType());
            sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
            sb.append("\nSimOperator = " + tm.getSimOperator());
            sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
            sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
            sb.append("\nSimState = " + tm.getSimState());
            sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
            sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
            Log.e("info", sb.toString());
            return sb.toString();
        }


        return "default";
    }

    /**
     * *******
     *
     * @author: Devis 2015-4-20 下午9:53:34
     * @desc : 获取屏幕密度
     * ********
     */
    public static float getDensity(Context context) {
        if (density == 0) {
            if (metrics == null) {
                getDisPlayMetrics(context);
            }
            density = metrics.density;
        }
        return density;
    }

    /**
     * *******
     *
     * @author: Devis 2015-4-20 下午9:52:55
     * @desc : 获取屏幕参数
     * ********
     */
    public static DisplayMetrics getDisPlayMetrics(Context context) {
        if (metrics == null) {
            metrics = new DisplayMetrics();
            if (context instanceof Activity) {
                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            }
        }
        return metrics;
    }

    /**
     * *******
     *
     * @author: Devis 2015-4-20 下午9:53:11
     * @desc : 获取屏幕宽度
     * ********
     */
    public static int getWidthPixels(Context context) {
        if (widthPixels == 0) {
            widthPixels = getDisPlayMetrics(context).widthPixels;
        }
        return widthPixels;
    }

    /**
     * *******
     *
     * @author: Devis 2015-4-20 下午9:53:23
     * @desc : 获取屏幕高度
     * ********
     */
    public static int getHeightPixels(Context context) {
        if (heightPixels == 0) {
            heightPixels = getDisPlayMetrics(context).heightPixels;
        }
        return heightPixels;
    }

//    /**
//     * @author Devis -- 上午9:56:35
//     * @desc 获取版本号
//     */
//    public static int getVersionCode(Context context) {
//        if (context == null) {
//            return -1;
//        }
//
//        int versionCode = -1;
//        try {
//            PackageManager packageMng = context.getPackageManager();
//            if (packageMng != null) {
//                PackageInfo packageInfo = packageMng.getPackageInfo(context.getPackageName(), 0);
//                if (packageInfo != null) {
//                    versionCode = packageInfo.versionCode;
//                }
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return versionCode;
//    }

    /**
     * @author Devis -- 下午9:26:44
     * @desc 获取渠道号
     */
    public static String getChanelId() {
        String CHANNELID = "";
        try {
            ApplicationInfo ai = MyConfig.getContext().getPackageManager()
                    .getApplicationInfo(MyConfig.getContext().getPackageName(), PackageManager.GET_META_DATA);
            Object value = ai.metaData.get("UMENG_CHANNEL_VALUE");
            if (value != null) {
                CHANNELID = value.toString();
            }
        } catch (Exception e) {
        }
        return CHANNELID;
    }

    public static String getVersionName(Context context){
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return String.valueOf(versionName);
        } catch (Exception e) {
        }
        return "未知版本名";
    }

    public static String getVersionCode(Context context){
        try {
            String pkName = context.getPackageName();
            int versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return String.valueOf(versionCode);
        } catch (Exception e) {
        }
        return "未知版本号";
    }

    public static String getChannelName(Context ctx){
       return getAppMetaData(ctx, "UMENG_CHANNEL");
    }


    /**
     * 获取application中指定的meta-data
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }

    //网络是否可用
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //WIFI是否打开
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }
    //WIFI是否连接
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
