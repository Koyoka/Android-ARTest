package com.yrkj.util.bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapHelper {

	public static InputStream Bitmap2IS(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
		return sbs;
	}
	
//	public static InputStreanm ImageFile2IS(){
//		
//	}
	
	public static Bitmap getImageFromAssetsFile(Context c,String fileName) {
		Bitmap image = null;
		AssetManager am = c.getResources().getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}

	public static Bitmap decodeSampledBitmapFromStream(InputStream is,int reqWidth, int reqHeight){
		final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    Bitmap mBitmap = BitmapFactory.decodeStream(is, null, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return mBitmap = BitmapFactory.decodeStream(is, null, options);
	}
	
	public static Bitmap decodeSampledBitmapFromByte1(byte[] bs, 
	        int reqWidth, int reqHeight) {
		try {
			// First decode with inJustDecodeBounds=true to check dimensions
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap mBitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length,
					options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth,
					reqHeight);
			// DebugTrace.Print("inSampleSize---------------"+options.inSampleSize);
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			return mBitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length,
					options);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Bitmap decodeSampledBitmapFromLacolPath(String mImgPath, 
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    Bitmap mBitmap = BitmapFactory.decodeFile(mImgPath,options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return mBitmap = BitmapFactory.decodeFile(mImgPath,options);
	}
	
	public static Bitmap decodeSampledBitmapFromByte(byte[] bs, 
	        int reqWidth, int reqHeight) {
		try {
			// First decode with inJustDecodeBounds=true to check dimensions
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap mBitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length,
					options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth,
					reqHeight);
			// DebugTrace.Print("inSampleSize---------------"+options.inSampleSize);
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			return mBitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length,
					options);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}
}
