package com.mfbl.mofang.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/*********
 * @author: Devis 2015-2-9 下午7:40:22
 * @desc : 基于Sharedpreferences的设置缓存
 *********/
public class MySharedpreferences {

	public final static String DATA = "data";

	/**********
	 * @author: Devis 2015-2-9 下午7:35:23
	 * @desc : 校验字符串
	 **********/
	private static boolean checkString(String string) {
		if (string == null) {
			return true;
		}
		if (string.length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据key，重置value
	 */
	public static void resetValueByKey(Context context, String key) {
		if (checkString(key) || context == null) {
			return;
		}
		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor = editor.remove(key);
		editor.commit();
	}

	/**
	 * @author Devis
	 * @date 2014-12-3-下午5:22:06
	 * @des 清空缓存
	 */
	public static void clearValueByType(Context context) {
		if (context == null) {
			return;
		}
		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 获取string类型的preference值
	 */
	public static String getStringValueByKey(Context context, String key) {
		if (checkString(key) || context == null) {
			return "";
		}
		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		return settings.getString(key, "");
	}

	/**
	 * 获取string类型的preference值
	 */
	public static String getStringValueByKey(Context context, String key, String defaultString) {
		if (checkString(key) || context == null) {
			return "";
		}
		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		return settings.getString(key, defaultString);
	}

	/**
	 * @param key
	 * @param value
	 *            设置string类型的preference值
	 */
	public static void setStringValueForKey(Context context, String key, String value) {
		if (checkString(key) || checkString(value) || context == null) {
			return;
		}
		String oldValue = getStringValueByKey(context, key);
		if (oldValue == null || oldValue.compareTo(value) != 0) {
			SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
			Editor editor = settings.edit();
			editor.putString(key, value);
			editor.commit();
		}
	}

	/**
	 * @param key
	 *            设置boolean类型的preference值
	 */
	public static boolean getBooleanValueByKey(Context context, String key) {

		if (checkString(key) || context == null) {
			return false;
		}

		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		return settings.getBoolean(key, false);
	}

	public static boolean getBooleanValueByKey(Context context, String key, boolean defaultState) {

		if (checkString(key) || context == null) {
			return false;
		}

		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		return settings.getBoolean(key, defaultState);
	}

	/**
	 * @param key
	 * @param value
	 *            设置boolean类型的preference值
	 */
	public static void setBooleanValueForKey(Context context, String key, boolean value) {
		if (checkString(key) || context == null) {
			return;
		}
		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 获取int类型的perference值
	 */
	public static int getIntegerValueByKey(Context context, String key, int defaultValue) {
		if (checkString(key) || context == null) {
			return 0;
		}
		SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		return settings.getInt(key, defaultValue);
	}

	/**
	 * @param key
	 * @param value
	 *            设置int类型的preference值
	 */
	public static void setIntegerValueForKey(Context context, String key, int value) {
		if (checkString(key) || context == null) {
			return;
		}
		int oldValue = getIntegerValueByKey(context, key, 0);
		if (oldValue != value) {
			SharedPreferences settings = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
			Editor editor = settings.edit();
			editor.putInt(key, value);
			editor.commit();
		}
	}

}
