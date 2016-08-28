package com.mfbl.mofang.util;

import android.content.Context;
import android.os.Vibrator;

/**
 * @author Devis
 * @date 2014-12-27-下午4:07:06
 * @des 震动工具类
 */
public class MyVibratorer {
	private static Vibrator vibrator;

	public static void vibrate(Context context, int time) {
		if (vibrator == null) {
			vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		}
		vibrator.vibrate(time);
	}

	public static void vibrate(Context context, long[] pattern, int repeat) {
		if (vibrator == null) {
			vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		}
		vibrator.vibrate(pattern, repeat);
	}
}
