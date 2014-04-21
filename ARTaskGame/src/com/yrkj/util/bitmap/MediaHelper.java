package com.yrkj.util.bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
	
	public static final int MEDIA_IMG_PHOTO = 3;
	public static final int MEDIA_IMG_CAMERA = 5;
	public static final int MEDIA_IMG_CROP = 48;
	
//	private static final int MEDIA_IMG_CROP_FINISH = 32;
	
	private static final int BIT_LOW = 7;
	private static final int BIT_HIGH = 56;
	
	private static Activity mCurActy;
	private static int curMediaType;
	private static File mCurrentPhotoFile;
	private static final File PHOTO_DIR = 
			new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");//图片的存储目录
	
	public static void setMedia(Activity acty,int mediaType) {
		mCurActy = acty;
		curMediaType = mediaType;
		switch (curMediaType&BIT_LOW) {
		case MEDIA_IMG_PHOTO:
			getMedia_Photo();
			break;
		case MEDIA_IMG_CAMERA:
			getMedia_Camera();
			break;
		default:
			break;
		}

	}
	
	private static void getMedia_Photo(){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		intent.putExtra("return-data", true);
		
		mCurActy.startActivityForResult(Intent.createChooser(intent, "请选择"), MEDIA_IMG_PHOTO);
	}
	
	private static void getMedia_Camera(){
//		DebugTrace.Print("open camera------------");
//		mCurrentPhotoFile = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			PHOTO_DIR.mkdir();
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			mCurrentPhotoFile = new File(PHOTO_DIR, getSaveImageFileName()); // 用当前时间给取得的图片命名
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(mCurrentPhotoFile));

			mCurActy.startActivityForResult(intent, MEDIA_IMG_CROP);
		} else {
//			MessageBox.getInstance(mCurActy).Show("请插入SD卡");
			ToastUtil.show(mCurActy, "请插入SD卡");
		}

	}
	
	private static void getMedia_CropImage(Uri photoUri) {  
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
			mCurrentPhotoFile = new File(PHOTO_DIR, getSaveImageFileName());
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(mCurrentPhotoFile));
			
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

			curMediaType = MEDIA_IMG_CAMERA;
			mCurActy.startActivityForResult(intent, MEDIA_IMG_CAMERA);
		} else {
			ToastUtil.show(mCurActy, "请插入SD卡");
		}
	}
	
	public static String getMedioResultPath(Intent data){
		
		Uri uri = null;
		String imgPath = null;
		
		switch (curMediaType&BIT_LOW) {
		case MediaHelper.MEDIA_IMG_CAMERA:
			uri = Uri.fromFile(mCurrentPhotoFile);
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
			mCurrentPhotoFile = null;
			break;
		case MediaHelper.MEDIA_IMG_PHOTO:
			if(data != null){
//				DebugTrace.Print("1.------" + data.getData());
//				DebugTrace.Print("2.------" + data.getExtras());
				uri = data.getData();
				if(uri == null){
					break;
				}
    			Cursor c = mCurActy.managedQuery(uri, null, null, null, null);
//    			c = mCurActy.getContentResolver().query(uri, null, null, null, null);
//    			c.close();
//    			new android.content.CursorLoader(mCurActy).;
    			
    			if(c==null){
    				imgPath = uri.getPath();
    			}else if(c.moveToFirst()  && c.getCount() == 1){
    				int dataColIndex = c.getColumnIndexOrThrow(Media.DATA);
    				imgPath = c.getString(dataColIndex);
    				
//    				uri = Uri.fromFile(new File(imgPath));
//    				MessageBox.getInstance(mCurActy).Show(" waring:Cursor is not null check code.");
    			}else{
    				//nothing
    			}
			}
			break;
		default:
			break;
		}
		
		if((curMediaType&BIT_HIGH) 
				== MEDIA_IMG_CROP){
//			MessageBox.getInstance(mCurActy).Show("curMediaType & AND_HIGH");
			if(uri!=null){
				getMedia_CropImage(uri);
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

/*    		switch (requestCode) {
    		case MediaUtil.MEDIA_IMG_CAMERA:
				uri = MediaUtil.getCurrentPhotoFileUri();
    			if(uri == null){
    				//nothing
    			}else{
    				imgPath = uri.getPath();
    				MediaUtil.galleryAddPic(getBaseContext(), imgPath);
    			}
    			break;
    		case MediaUtil.MEDIA_IMG_PHOTO:
    			if(data != null){
    				uri = data.getData();
	    			Cursor c = managedQuery(uri, null, null, null, null);
	    			if(c==null){
	    				imgPath = uri.getPath();
	    			}else if(c.moveToFirst()  && c.getCount() == 1){
	    				int dataColIndex = c.getColumnIndexOrThrow(Media.DATA);
	    				imgPath = c.getString(dataColIndex);
	    			}else{
	    				//nothing
	    			}
    			}
    			break;
    			
    		default:
    			break;
    		}
    		
*/
