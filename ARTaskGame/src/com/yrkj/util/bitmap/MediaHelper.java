package com.yrkj.util.bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.yrkj.util.log.DebugTrace;
import com.yrkj.util.log.ToastUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;

public class MediaHelper {
	
	final static String TAG = "MediaHelper";
//	final static String INTENT_KEY_MEDIA_TYEP = "currentMediaType";
	final static String INTENT_KEY_MEDIA_PHOTOFILEPATH = "photoFilePath";
	
	public static final int MEDIA_IMG_PHOTO = 3;
	public static final int MEDIA_IMG_CAMERA = 5;
	public static final int MEDIA_IMG_CROP = 48;
	
//	private static final int MEDIA_IMG_CROP_FINISH = 32;
	
	private static final int BIT_LOW = 7;
	private static final int BIT_HIGH = 56;
	
	private static Activity mCurActy1;
	private static int curMediaType1;
	private static File mCurrentPhotoFile1;
	private static final File PHOTO_DIR = 
			new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");//图片的存储目录
	
	
	
	
	public static void setMedia(Activity acty,int mediaType,String path) {
//		mCurActy = acty;
//		curMediaType = mediaType;
		switch (mediaType&BIT_LOW) {
		case MEDIA_IMG_PHOTO:
			getMedia_Photo(acty);
			break;
		case MEDIA_IMG_CAMERA:
			getMedia_Camera(acty,path);
			break;
		default:
			break;
		}

	}
	
	private static void getMedia_Photo(Activity acty){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		intent.putExtra("return-data", true);
		
		acty.startActivityForResult(Intent.createChooser(intent, "请选择"), MEDIA_IMG_PHOTO);
	}
	
	private static void getMedia_Camera(Activity acty,String path){
//		DebugTrace.Print("open camera------------");
//		mCurrentPhotoFile = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			PHOTO_DIR.mkdir();
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//			mCurrentPhotoFile = new File(PHOTO_DIR, getSaveImageFileName()); // 用当前时间给取得的图片命名
//			intent.putExtra(MediaStore.EXTRA_OUTPUT,
//					Uri.fromFile(mCurrentPhotoFile));
			File f = new File(path); // 用当前时间给取得的图片命名
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(f));

//			intent.putExtra(INTENT_KEY_MEDIA_PHOTOFILEPATH, f.getPath());
//			DebugTrace.Print(TAG, "INTENT_KEY_MEDIA_PHOTOFILEPATH ["+ f.getPath()+"]");
			
//			mCurActy.startActivityForResult(intent, MEDIA_IMG_CROP);
			acty.startActivityForResult(intent, MEDIA_IMG_CAMERA);
		} else {
//			MessageBox.getInstance(mCurActy).Show("请插入SD卡");
			ToastUtil.show(acty, "请插入SD卡");
		}

	}
	
	private static void getMedia_CropImage(Activity mCurActy,Uri photoUri,String savePath) {  
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			PHOTO_DIR.mkdir();
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(photoUri, "image/*");
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 80);
			intent.putExtra("outputY", 80);
			intent.putExtra("return-data", false);
//			mCurrentPhotoFile = new File(PHOTO_DIR, getSaveImageFileName());
//			intent.putExtra(MediaStore.EXTRA_OUTPUT,
//					Uri.fromFile(mCurrentPhotoFile));
			File f;
			if(savePath == null){
				ToastUtil.show(mCurActy,"save path is null");
				return;
			}else{
				f = new File(savePath);
			}
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(f));
//			intent.putExtra(INTENT_KEY_MEDIA_PHOTOFILEPATH, f.getPath());
//			intent.putExtra(INTENT_KEY_MEDIA_TYEP, MEDIA_IMG_CAMERA);
			/*
			 *crop	String	发送裁剪信号
				aspectX	int	X方向上的比例
				aspectY	int	Y方向上的比例
				outputX	int	裁剪区的宽
				outputY	int	裁剪区的高
				scale	boolean	是否保留比例
				return-data	boolean	是否将数据保留在Bitmap中返回
				data	Parcelable	相应的Bitmap数据
				circleCrop	String	圆形裁剪区域？
				MediaStore.EXTRA_OUTPUT ("output")	URI	将URI指向相应的file:///.
			 * */

//			curMediaType = MEDIA_IMG_CAMERA;
			mCurActy.startActivityForResult(intent, MEDIA_IMG_CAMERA);
		} else {
			ToastUtil.show(mCurActy, "请插入SD卡");
		}
	}
	
	public static String getMedioResultPath(Activity mCurActy,Intent data,int resultCode,String savePath){
		
		int curMediaType = resultCode;
//		String savePath = data.getStringExtra(INTENT_KEY_MEDIA_PHOTOFILEPATH);
		
		Uri uri = null;
		String imgPath = null;
		DebugTrace.Print(TAG, "-1. curMediaType["+curMediaType+"] BIT_LOW["+BIT_LOW+"] savePath["+savePath+"]");
		
		switch (curMediaType&BIT_LOW) {
		case MediaHelper.MEDIA_IMG_CAMERA:
			uri = Uri.fromFile(new File(savePath));
//			DebugTrace.Print("3,---------"+mCurrentPhotoFile);
//			DebugTrace.Print("4,---------"+uri);
			if(uri != null){
				if((curMediaType&BIT_HIGH) == MEDIA_IMG_CROP){
					//nothing and go on crop the iamge
				}else{
					imgPath = uri.getPath();
					galleryAddPic(mCurActy, imgPath);
				}
			}
//			mCurrentPhotoFile = null;
			break;
		case MediaHelper.MEDIA_IMG_PHOTO:
			if(data != null){
//				DebugTrace.Print("1.------" + data.getData());
//				DebugTrace.Print("2.------" + data.getExtras());
				uri = data.getData();
				if(uri == null){
					DebugTrace.Print(TAG, "0. uri == null");
					break;
				}
//    			Cursor c = mCurActy.managedQuery(uri, null, null, null, null);
    			
    			String[] filePathColumn = { MediaStore.Images.Media.DATA };

    			Cursor c = mCurActy.getContentResolver().query(uri,
    					                    filePathColumn, null, null, null);

//    			c = mCurActy.getContentResolver().query(uri, null, null, null, null);
//    			c.close();
//    			new android.content.CursorLoader(mCurActy).;
    			
    			if(c==null){
    				imgPath = uri.getPath();
    				DebugTrace.Print(TAG, "1. c == null");
    			}else if(c.moveToFirst()  && c.getCount() == 1){
    				int dataColIndex = c.getColumnIndexOrThrow(filePathColumn[0]);
    				imgPath = c.getString(dataColIndex);
    				DebugTrace.Print(TAG, "2. c.moveToFirst()  && c.getCount() == 1 imgPath["+imgPath+"]");
//    				uri = Uri.fromFile(new File(imgPath));
//    				MessageBox.getInstance(mCurActy).Show(" waring:Cursor is not null check code.");
    			}else{
    				DebugTrace.Print(TAG, "3. imgPath is null");
    				//nothing
    			}
    			c.close();
			}else{
				
				DebugTrace.Print(TAG, "4. data is null");
			}
			break;
		default:
			DebugTrace.Print(TAG, "getMedioResultPath is default");
			break;
		}
		
		if((curMediaType&BIT_HIGH) 
				== MEDIA_IMG_CROP){
//			MessageBox.getInstance(mCurActy).Show("curMediaType & AND_HIGH");
			if(uri!=null){
				getMedia_CropImage(mCurActy,uri,savePath);
			}
			
			return "";
		}
		
		return imgPath;
	}
	
	public static void galleryAddPic(Context context,String path) {
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    File f = new File(path);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    context.sendBroadcast(mediaScanIntent);
	}
	
	
	
//	public static int calculateInSampleSize(BitmapFactory.Options options,
//			int reqWidth, int reqHeight) {
//		// Raw height and width of image
//		final int height = options.outHeight;
//		final int width = options.outWidth;
//		int inSampleSize = 1;
//
//		if (height > reqHeight || width > reqWidth) {
//			if (width > height) {
//				inSampleSize = Math.round((float) height / (float) reqHeight);
//			} else {
//				inSampleSize = Math.round((float) width / (float) reqWidth);
//			}
//		}
//		return inSampleSize;
//	}
	
	private static String getSaveImageFileName(){
		Date date = new Date(System.currentTimeMillis());  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");  
	    String savePhotoFileName = dateFormat.format(date) + ".jpg";  
	    return savePhotoFileName;
	}
	
	public static String getSavePath(){
		File f = new File(PHOTO_DIR, getSaveImageFileName()); // 用当前时间给取得的图片命名
		return f.getPath();
	}
	

	
	public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
                ExifInterface exifInterface = new ExifInterface(path);
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
        } catch (IOException e) {
                e.printStackTrace();
        }
        return degree;
    }
	
}

