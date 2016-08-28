package com.mfbl.mofang.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * BitmapUtils
 * 
 * @author yanmin
 */
public final class BitmapUtils {
	public static final int HIGH_QUALITY = 90;

	public static final int MAX_PIXELS_LRG = 1024 * 1024 * 2; // 2 MegaPixels
	public static final int MAX_UPLOAD_SIZE = 720; // max width or height of 720
													// pixels

	public static final int MAX_WIDTH = 640;
	public static final int MAX_HEIGHT = 4096;

	private BitmapUtils() {
		throw new UnsupportedOperationException("Sorry, you cannot instantiate an utility class!");
	}

	public static Bitmap readBitmapFromFile(String absoluteFileName, Config config) {
		return readBitmapFromFile(absoluteFileName, config, 1);
	}

	/**
	 * Read a bitmap from disk
	 * 
	 * @param absoluteFileName
	 * @param config
	 *            optional Bitmap.Config. If null, the default ARGB_8888 will be
	 *            used.
	 * @return
	 */
	public static Bitmap readBitmapFromFile(String absoluteFileName, Config config, int sampleSize) {
		File file = new File(absoluteFileName);
		if (!file.exists()) {
			return null;
		}
		Options opts = new Options();
		opts.inPreferredConfig = config == null ? Config.RGB_565 : config;
		opts.inDither = true;
		opts.inSampleSize = sampleSize;
		try {
			return BitmapFactory.decodeFile(absoluteFileName, opts);
		} catch (OutOfMemoryError e1) {
			try {
				opts.inPreferredConfig = Config.RGB_565;
				return BitmapFactory.decodeFile(absoluteFileName, opts);
			} catch (OutOfMemoryError e2) {
				throw e2;
			}
		}
	}

	/**
	 * 按比例压缩图片大小
	 * 
	 * @return
	 */
	public static byte[] compressImage(File file) {
		Log.e("TTT", "BitmapUtils compressImage");
		if (file == null) {
			return null;
		}
		try {
			Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
			int options = 100;
			while (baos.toByteArray().length / 1024 > 120) { // 循环判断如果压缩后图片是否大于120kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				options -= 10;// 每次都减少10
			}
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	private static Paint sScalePaint = new Paint(Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);

	public static Bitmap createScaledBitmap1(Bitmap src, int scaledWidth, int scaledHeight) {
		Bitmap scaledBitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, src.getConfig());
		Canvas canvas = new Canvas(scaledBitmap);
		canvas.drawBitmap(src, new Rect(0, 0, scaledWidth, scaledHeight), new Rect(0, 0, scaledWidth, scaledHeight),
				sScalePaint);
		return scaledBitmap;
	}

	public static Bitmap createScaledBitmap(Bitmap src, int scaledWidth, int scaledHeight) {
		Bitmap scaledBitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, src.getConfig());
		Canvas canvas = new Canvas(scaledBitmap);
		int srcHeight = (int) (src.getWidth() * ((float) scaledHeight / (float) scaledWidth));
		if (srcHeight > src.getHeight()) {
			return src;
		}
		canvas.drawBitmap(src, new Rect(0, 0, src.getWidth(), srcHeight), new Rect(0, 0, scaledWidth, scaledHeight),
				sScalePaint);
		return scaledBitmap;
	}

	public static Bitmap createScaledBitmap(Bitmap src) {
		Bitmap scaledBitmap = Bitmap.createBitmap(160, 267, src.getConfig());
		int width = src.getWidth(), height = src.getHeight();
		float scale = (float) 1.5;
		if (height / width > scale) {
			Canvas canvas = new Canvas(scaledBitmap);
			canvas.drawBitmap(src, new Rect(0, 0, width, (int) (width * scale)), new Rect(0, 0, 160, 267), sScalePaint);
			return scaledBitmap;
		}
		return src;
	}

	/**
	 * 图片显示成为圆形
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmapByDp(int height, int width, Bitmap bitmap, int dp) {
		// if (bitmap.getHeight() > bitmap.getWidth()) {
		bitmap = createScaledBitmap1(bitmap, bitmap.getWidth(), bitmap.getWidth());
		// }
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight() + 10);
		final RectF rectF = new RectF(rect);
		final float roundPx = dp;// (float) (bitmap.getHeight() / 6.85)
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// 截取图片上半部分
		final Rect cropRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight() - 10);
		canvas.drawBitmap(bitmap, cropRect, rect, paint);
		return output;
	}

	/**
	 * 图片显示成为圆形
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		if (bitmap.getHeight() > bitmap.getWidth()) {
			bitmap = createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getWidth());
		}
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = rect.width() / 2;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 图片显示成为圆形 指定图片大小
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getRoundedCornerSizeBitmap(Bitmap bitmap, int height, int width) {
		// if (bitmap.getHeight() > bitmap.getWidth()) {
		bitmap = createScaledBitmap(bitmap, height, width);
		// }
		Bitmap output = Bitmap.createBitmap(height, width, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, height, width);
		final RectF rectF = new RectF(rect);
		final float roundPx = rect.width() / 2;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Bitmap getRoundedBitmap(Bitmap bitmap, int height, int width) {
		if (height == 0) {
			height = bitmap.getHeight();
		}
		if (width == 0) {
			width = bitmap.getWidth();
		}
		bitmap = createScaledBitmap(bitmap, height, width);
		Bitmap output = Bitmap.createBitmap(height, width, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, height, width);
		final RectF rectF = new RectF(rect);
		final float roundPx = rect.width() / 6;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static byte[] bmpToByteArray(final Bitmap bitmap, final boolean paramBoolean) {
		Bitmap localBitmap = Bitmap.createBitmap(80, 80, Config.RGB_565);
		Canvas localCanvas = new Canvas(localBitmap);
		int i;
		int j;
		if (bitmap.getHeight() > bitmap.getWidth()) {
			i = bitmap.getWidth();
			j = bitmap.getWidth();
		} else {
			i = bitmap.getHeight();
			j = bitmap.getHeight();
		}
		while (true) {
			localCanvas.drawBitmap(bitmap, new Rect(0, 0, i, j), new Rect(0, 0, 80, 80), null);
			if (paramBoolean)
				bitmap.recycle();
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
			localBitmap.recycle();
			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			try {
				localByteArrayOutputStream.close();
				return arrayOfByte;
			} catch (Exception e) {
				// F.out(e);
			}
			i = bitmap.getHeight();
			j = bitmap.getHeight();
		}
	}

	// public static InputStream streamForUrl(String path) {
	// URL url = null;
	// InputStream is = null;
	// try {
	// url = new URL(path);
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// conn.setDoInput(true);
	// conn.connect();
	// is = conn.getInputStream();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return is;
	// }

	/**
	 * 以最省内存的方式读取本地资源的图片
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		Options opt = new Options();
		opt.inPreferredConfig = Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}




}
