/**
 * ***
 *
 * @FileName: Constant.java
 * @Description:
 * @Author: Libin
 * @CreateDate: 2013-12-18
 * ****
 */
package com.mfbl.mofang.common;

/**
 * ***
 *
 * @File: Constant.java
 * @Description: 常量类
 * ****
 */
public class Constant {

    // 屏幕宽和高
    public static int SCREEN_WIDTH = 640;
    public static int SCREEN_HEIGHT = 960;
    public static float DENSITY = 2f;

    public final static int CANCEL = -1;
    public final static int CONFIRM = -2;

    public final static int CAMERA = 0x12;
    public final static int ALBUM = 0x13;

    public final static int MENU_LEFT = 0x14;
    public final static int MENU_RIGHT = 0x15;

    public final static int ANIMATION_START = 0x16;
    public final static int ANIMATION_STOP= 0x17;
    public final static int ANIMATION_CANCEL = 0x18;

    public final static int NEED_TO_REFRESH = 0x20;


    public final static int MENU_COLLECT = 0x21;
    public final static int MENU_REPORT = 0x22;
    public final static int MENU_DELETE = 0x23;

    public final static int COUNTER_ADD2 = 0x24;
    public final static int COUNTER_DNF = 0x25;
    public final static int COUNTER_CONFIRM = 0x26;

    public final static int ITEM_DELETE = 0x27;
    public final static int ITEM_LIKE = 0x28;
    public final static int ITEM_REFRESH = 0x29;

    public final static int SUCCESS = 0x81;// 成功
    public final static int FAIL = 0x82;// 失败
    public final static int NEED_LOGIN = 0x83;// 需要重新登录

    public final static int TIME_EXIT = 2000;// 退出时间
    public final static int TIME_FEEDBACK_VIBRATE = 30;// 反馈震动时间
    public final static int TIME_ANIMATION = 500;// 动画时间
    public final static int TIME_DELAY500 = 500;// 动画延时
    public final static int TIME_ANIMATION300 = 300;// 动画时间
    public final static int TIME_PULLTOREFRESH = 3000;// 下拉刷新时间

    public final static String DEFAULT_CACHE = "data";

    // 数据库名称
    public final static String DB_NAME = "mofang_db";

    //计时默认字体大小
    public final static int COUNTER_TEXTSIZE = 80;
    public final static int COUNTER_DISRUPTION_TEXTSIZE = 16;

    //图片选择器
    public final static int REQUEST_IMAGE = 2;

    public static final String MODE_KEY = "mode_key";
    public static final int MODE_PRACTICE = 1;
    public static final int MODE_TIMING = 2;

    public static final String TYPE_INDEX_KEY = "type_index_Key";

    public static final String[] TYPE_NAME =
            {"二阶", "三阶", "四阶", "五阶", "六阶", "七阶", "魔表", "五魔", "金字塔", "斜转", "SQ1"};

}
