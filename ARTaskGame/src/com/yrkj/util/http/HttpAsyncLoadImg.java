package com.yrkj.util.http;

import java.io.ByteArrayOutputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yrkj.util.bitmap.BitmapHelper;
import com.yrkj.util.http.HttpClientHelper.DownloadListener;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.widget.ImageView;


//import com.lc.sm.R;
//import com.lc.sm.base.BaseApplication;
//import com.yrkj.util.DebugTrace;
//import com.yrkj.util.MediaUtil;
//import com.yrkj.util.http.HttpClientHelper.DownloadListener;

//import com.lc.zstx.R;
//import com.lc.zstx.base.BaseApplication;
//import com.lc.zstx.http.HttpClientHelper.DownloadListener;
//import com.lc.zstx.util.DebugTrace;
//import com.lc.zstx.util.MediaUtil;

public class HttpAsyncLoadImg {
	final String TAG = "HttpAsyncLoadImg";
	
	public HttpAsyncLoadImg(){
		this(10);
	}
	
	public HttpAsyncLoadImg(int threadPoolCount){
		//任正原来的代码
		executorService = Executors.newFixedThreadPool(threadPoolCount);
		
		//modify by white
//		executorService = BaseApplication.getInstance().getPool();
	}
	
//	private static final int SOFT_CACHE_CAPACITY = 40;  
//	public static LinkedHashMap<String, SoftReference<Bitmap>> imageCache = new LinkedHashMap<String, SoftReference<Bitmap>>(SOFT_CACHE_CAPACITY,0.75f,true);
	public static Map<String, SoftReference<Bitmap>> bitmapSoftCache = new HashMap<String, SoftReference<Bitmap>>();
	
	private static final int CACHESIZW = 1024 * 1024 * 8;
	public static LruCache<String, Bitmap> bitmapHardCache = new LruCache<String, Bitmap>(CACHESIZW) {
		@Override
		protected int sizeOf(String key, Bitmap bitmap) {
			return bitmap.getRowBytes() * bitmap.getHeight();  
//			return bitmap.getRowBytes();
		}
		
		@Override
		protected void entryRemoved(boolean evicted, String key,
				Bitmap oldValue, Bitmap newValue) {
			super.entryRemoved(evicted, key, oldValue, newValue);
			if(oldValue != null && !oldValue.isRecycled()){
				oldValue.recycle();
				oldValue = null;
//				DebugTrace.Print("entryRemoved------------");
			}
//			bitmapSoftCache.put(key, new SoftReference<Bitmap>(oldValue));
		}
	};
	
	private Map<String,BitmapLoadTask> picLoadTaskMap = new  HashMap<String,BitmapLoadTask>();
	
	private ExecutorService executorService = null;//Executors.newFixedThreadPool(10); //线程池
	
//	private final Handler handler = new Handler();
	
	public void bindImageViewPic(final ImageView v,final String Url,
			final int reqWidth,
			final int reqHeight,
			final int defaultPicResId,
			final DownloadListener l,boolean isRound){
//		if(true){
//			return;
//		}
		final String imgUrlKey = Url+"_"+reqWidth+"_"+reqHeight;
		
		if(TextUtils.isEmpty(Url)){
			if(defaultPicResId == 0){
				return;
			}
			setImageView(v,defaultPicResId,isRound);
//			if(isRound){
//				v.setImageResource(R.drawable.round_shadow_9);
//				v.setBackgroundResource(defaultPicResId);
//			}else{
//				v.setImageResource(defaultPicResId);
//			}
			return;
		}
//		DebugTrace.Print("....................." + imgUrlKey);
		
		synchronized(bitmapHardCache){  
			if(bitmapHardCache.get(imgUrlKey) != null){
//				v.setImageBitmap(bitmapHardCache.get(imgUrlKey));
				setImageView(v,bitmapHardCache.get(imgUrlKey),isRound);
				picLoadTaskMap.remove(imgUrlKey);
//				DebugTrace.Print("get from cache-----------------");
				return ;
			}else{
//				if(v.getDrawable() != null){
//					DebugTrace.Print("Drawable not null-----------------");
//					v.setImageResource(defaultPicResId);
//				}
			}
		}
		
//		synchronized(bitmapSoftCache){  
//			if (bitmapSoftCache.containsKey(imgUrlKey)) {
//				SoftReference<Bitmap> softReference = bitmapSoftCache.get(imgUrlKey);
//				if (softReference.get() != null) {
//					((ImageView)v).setImageBitmap(softReference.get());
//					picLoadTaskMap.remove(imgUrlKey);
//					return;
//				}
//			}
//		}
//		if(isRound){
//			v.setImageResource(defaultPicResId);
//			v.setBackgroundDrawable(null);
//		}else{
//			v.setImageResource(defaultPicResId);
//		}
		setImageView(v,defaultPicResId,isRound);
//		v.setImageResource(defaultPicResId);
		if(picLoadTaskMap.get(imgUrlKey) != null){
//			((ImageView)v).setImageBitmap(picES.get(imgUrlKey).getBM());
//			v.setImageResource(defaultPicResId);
			picLoadTaskMap.get(imgUrlKey).addView(v);
			return;
		}
		
		BitmapLoadTask bc = new BitmapLoadTask(v,Url,reqWidth,reqHeight,isRound);
//		((ImageView)v).setImageBitmap(bc.getBM());
//		v.setImageResource(defaultPicResId);
		
		picLoadTaskMap.put(imgUrlKey, bc);
		bc.Executor(l);

	}
	
	class BitmapLoadTask {
		private String imgUrlKey = "";
		private String Url;
		private boolean isRound = false;
		private int reqWidth;
		private int reqHeight;
		private final List<WeakReference<ImageView>> viewList = new ArrayList<WeakReference<ImageView>>();
//		private Bitmap mBitmap;
		private final Handler handler = new Handler();
		
		public void addView(ImageView v){
			viewList.add(new WeakReference<ImageView>(v));
		}
		public void Executor(final DownloadListener l){
			executorService.submit(new Runnable() {
				public void run() {
//					final Bitmap b = loadImageFromNetwork(Url,reqWidth,reqHeight,l); 
//					if(b != null){
//					Bitmap b = loadImageFromNetwork_bit(imgUrlKey, Url,
//							reqWidth, reqHeight, l);
					Bitmap b = loadImageFromNetwork(Url,
							reqWidth, reqHeight, l);
//					Bitmap b = loadImageFromNetwork_LargeBitmap(Url,
//							reqWidth, reqHeight, l);
					synchronized (bitmapHardCache) {

						bitmapHardCache.put(
								imgUrlKey,
								b
								);
					}
					
					if (bitmapHardCache.get(imgUrlKey) == null) {
						return;
					}

					if (bitmapHardCache.get(imgUrlKey).isRecycled()) {
						return;
					}

					handler.post(new Runnable() {
						public void run() {
							// if(b!=null){
							// mBitmap = b;
							for (WeakReference<ImageView> view : viewList) {
								if (view != null && view.get() != null) {
									// if (canDisplay(view)) {
									// if (b != null) {
//										view.get().setImageBitmap(
//												bitmapHardCache.get(imgUrlKey));
										setImageView(view.get(),bitmapHardCache.get(imgUrlKey),isRound);
//										DebugTrace.Print("2.+++++"+imgUrlKey);
									// playImageViewAnimation(view, bitmap);
									// lruCache.put(data, bitmap);
									// } else {
									// // view.get().setImageDrawable(new
									// ColorDrawable(Color.TRANSPARENT));
									// }
									// }
								}
								// }
							}

							viewList.clear();

							if (picLoadTaskMap != null
									&& picLoadTaskMap.get(imgUrlKey) != null) {
								picLoadTaskMap.remove(imgUrlKey);
							}
						}
					});
					
				}
			});
		}
		
		public BitmapLoadTask(ImageView v,String url,int reqW,int reqH,boolean isround){
			imgUrlKey = url+"_"+reqW+"_"+reqH;
			
			Url = url;
			reqWidth = reqW;
			reqHeight = reqH;
			isRound = isround;
			viewList.add(new WeakReference<ImageView>(v));
		}
	}
	
	protected Bitmap loadImageFromNetwork(String imageUrl){
		return loadImageFromNetwork(imageUrl,100,100,null);
	}
	
	private void setImageView(ImageView view,Bitmap bit,boolean isRound){
		if(!isRound){
			view.setImageBitmap(
					bit);
		}else{
			
//			view
//			.setImageResource(R.drawable.round_shadow_9);
			
			view
			.setBackgroundDrawable(
					new BitmapDrawable(bit)
					);
		}
	}
	
	private void setImageView(ImageView view,int r,boolean isRound){
		if(isRound){
			view.setImageResource(r);
			view.setBackgroundDrawable(null);
		}else{
			view.setImageResource(r);
		}
	}
	
//	private  Bitmap mBitmapBuffer = null;
//	private ArrayList<SoftReference<Bitmap>> mBitmapBufferList = new ArrayList<SoftReference<Bitmap>>();
//	private float bufferSize = 0;
//	protected Bitmap loadImageFromNetwork_LargeBitmap(
//			String imageUrl,int reqWidth,int reqHeight,DownloadListener l){
//		
//		ByteArrayOutputStream baos = null;
//		for (int i = 0; i < 3; i++) {
//			baos = HttpMng.GetHttpClientHelper().getHttpFileStream2ByteOutStream(imageUrl, l);
//			if (baos != null) {
//				break;
//			}
//		}
//
//		if (baos == null) {
//			return null;
//		}
//		
//		byte[] bs = baos.toByteArray();
//		
////		if(bitmapHardCache.size() + bs.length >= 1024 * 1024 *5){
//////			bitmapHardCache.evictAll();
////			if (mBitmapBuffer != null && !mBitmapBuffer.isRecycled()) {
////				mBitmapBuffer.recycle();
////				mBitmapBuffer = null;
////			}
////		}
//		
////		bufferSize += bs.length;
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeByteArray(bs, 0, bs.length, options);
//		options.inSampleSize = MediaUtil.calculateInSampleSize(options,
//				reqWidth, reqHeight);
//		options.inJustDecodeBounds = false;
//
//		try
//		{
////			bitmapHardCache.
////			DebugTrace.Print("---------------"+bitmapHardCache.size() + " " + bs.length);
//			if(mBitmapBuffer == null){
//				
//				return mBitmapBuffer = BitmapFactory.decodeByteArray(bs, 0, bs.length,
//						options);
//			}else{
//				return  BitmapFactory.decodeByteArray(bs, 0, bs.length,
//						options);
//			}
//			
//		}catch(OutOfMemoryError  ex){
//			DebugTrace.Print("oom--------------------------");
//			if (mBitmapBuffer != null && !mBitmapBuffer.isRecycled()) {
//				mBitmapBuffer.recycle();
//				mBitmapBuffer = null;
//				DebugTrace.Print("recycle--------------------------");
//			}
//			return mBitmapBuffer = BitmapFactory.decodeByteArray(bs, 0, bs.length,
//					options);
//		}
//		
////		mBitmapBufferList.add(new SoftReference<Bitmap>(mBitmapBuffer));
//		
////			}
////		}
//	}
	
	protected Bitmap loadImageFromNetwork(String imageUrl,int reqWidth,int reqHeight,DownloadListener l) {

			ByteArrayOutputStream baos = null;
			for (int i = 0; i < 3; i++) {
				baos = HttpMng.GetHttpClientHelper().getHttpFileStream2ByteOutStream(imageUrl, l);
				if (baos != null) {
					break;
				}
			}

			if (baos == null) {
				return null;
			}
			
			Bitmap b = null;
			for (int i = 0; i < 2; i++) {
				b = BitmapHelper.decodeSampledBitmapFromByte(
					baos.toByteArray(), reqWidth, reqHeight);
				if(b != null){
					break;
				}
			}
			return b;
	}
	
	
	
//	public void bindSmallAvatarPic(ImageView v,String imgUrl,boolean isFling){
//		
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.samll_Avatar_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.samll_Avatar_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.default_avatar,null,true);
//			
//		}
//	}
//	
//	public void bindlargeAvatarPic(ImageView v,String imgUrl,boolean isFling){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Avatar_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Avatar_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.default_avatar,null,true);
//		}
//	}
//	
//	public void bindSmallWeiboPic(ImageView v,String imgUrl,boolean isFling){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.samll_Weibo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.samll_Weibo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,null,false);
//		}
//	}
//	
//	public void bindlargeWeiboPic(ImageView v,String imgUrl,boolean isFling){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Weibo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Weibo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,null,false);
//		}
//	}
//	public void bindlargeWeiboPicAndProgress(ImageView v,String imgUrl,boolean isFling,DownloadListener l){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Weibo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Weibo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,l,false);
//		}
//	}
//	
//	public void bindSmallPhotoPic(ImageView v,String imgUrl,boolean isFling){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.samll_Photo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.samll_Photo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,null,false);
//		}
//	}
//	
//	public void bindContentPhotoPic(ImageView v,String imgUrl,boolean isFling){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.content_Photo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.content_Photo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,null,false);
//		}
//	}
//	
//	public void bindLargePhotoPic(ImageView v,String imgUrl,boolean isFling){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Photo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Photo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,null,false);
//		}
//	}
//	public void bindLargePhotoPicAndProgress(ImageView v,String imgUrl,boolean isFling,DownloadListener l){
//		if (!isFling){
//			int width = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Photo_Width);
//			int height = BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.large_Photo_Height);
//			bindImageViewPic(v,imgUrl,width,height,R.drawable.pic_loading,l,false);
//		}
//	}
	
}
