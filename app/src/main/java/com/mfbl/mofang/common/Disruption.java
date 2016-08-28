/******
 * @FileName: Constant.java
 * @Description: 
 * @Author: Libin
 * @CreateDate: 2013-12-18
 ******/
package com.mfbl.mofang.common;

/*********
 * @author: Devis 2015-2-8 下午7:19:31
 * @desc : 魔方打乱数组
 * 
 *********/
public class Disruption {
	// 魔方种类
	public final static String[] CHOSE_TYPE_STRINGS = { "二阶", "三阶", "四阶" };
	public final static int TYPE_DEFAULT = 1;// 默认选中三阶
	// 2阶打乱
	public final static String[] DISRUPTION_2_SIDE = { "F", "B", "L", "R", "U", "D" };
	public final static String[] DISRUPTION_2_DIR = { "  ", "'  ", "2  " };
	public static int DISRUPTION_2_COUNT = 9;
	// 3阶打乱
	public final static String[] DISRUPTION_3_SIDE = { "F", "B", "L", "R", "U", "D" };
	public final static String[] DISRUPTION_3_DIR = { "  ", "'  ", "2  " };
	public static int DISRUPTION_3_COUNT = 25;
	// 4阶打乱
	public final static String[] DISRUPTION_4_SIDE = { "F", "B", "L", "R", "U", "D" };
	public final static String[] DISRUPTION_4_MID = { "", "w" };
	public final static String[] DISRUPTION_4_DIR = { "  ", "'  ", "2  " };
	public static int DISRUPTION_4_COUNT = 40;
	// 阶数
	public static int CURRENT_TYPE = 1;

	// 获取打乱步骤
	public static String getDisruptionString() {
		String disruptionString = "";// 打乱公式字符串
		String disruptionLastSideString = "";// 记录上一个选转边，用于去重
		int count = 0;
		String[] sideStrings = null;
		String[] midStrings = null;
		String[] dirStrings = null;
		switch (CURRENT_TYPE) {
		case 0:// 2阶
			count = DISRUPTION_2_COUNT;
			sideStrings = DISRUPTION_2_SIDE;
			dirStrings = DISRUPTION_2_DIR;

			for (int i = 0; i < count; i++) {
				// 旋转边
				String sideString = "";
				// 循环去重
				do {
					sideString = (sideStrings[(int) (Math.random() * sideStrings.length)]);
				} while (sideString.equals(disruptionLastSideString));

				disruptionLastSideString = sideString;
				disruptionString += sideString;

				// 转向和次数
				disruptionString += (dirStrings[(int) (Math.random() * dirStrings.length)]);
			}

			return disruptionString;
		case 1:// 3阶
			count = DISRUPTION_3_COUNT;
			sideStrings = DISRUPTION_3_SIDE;
			dirStrings = DISRUPTION_3_DIR;
			for (int i = 0; i < count; i++) {
				// 旋转边
				String sideString = "";
				// 循环去重
				do {
					sideString = (sideStrings[(int) (Math.random() * sideStrings.length)]);
				} while (sideString.equals(disruptionLastSideString));

				disruptionLastSideString = sideString;
				disruptionString += sideString;

				// 转向和次数
				disruptionString += (dirStrings[(int) (Math.random() * dirStrings.length)]);
			}

			return disruptionString;

		case 2:// 4阶
			count = DISRUPTION_4_COUNT;
			sideStrings = DISRUPTION_4_SIDE;
			midStrings = DISRUPTION_4_MID;
			dirStrings = DISRUPTION_4_DIR;
			for (int i = 0; i < count; i++) {
				// 旋转边
				String sideString = "";
				// 循环去重
				do {
					sideString = (sideStrings[(int) (Math.random() * sideStrings.length)]);
				} while (sideString.equals(disruptionLastSideString));

				disruptionLastSideString = sideString;
				disruptionString += sideString;

				// 层数
				disruptionString += (midStrings[(int) (Math.random() * midStrings.length)]);
				// 转向和次数
				disruptionString += (dirStrings[(int) (Math.random() * dirStrings.length)]);
			}

			return disruptionString;
		default:
			return "";
		}

	}

}
