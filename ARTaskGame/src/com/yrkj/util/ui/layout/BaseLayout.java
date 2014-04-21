package com.yrkj.util.ui.layout;

import java.text.MessageFormat;

import com.yrkj.util.date.DateHelper;


import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;


public class BaseLayout {
	
	private String getBindDataType(String path){
    	
    	int splitLen = path.indexOf("#"); 
    	if(splitLen == -1){
    		return path;
    	}
    	
    	if(splitLen+1 > path.length()){
    		return "";
    	}
    	return path.substring(splitLen+1,path.length());
    }
	
    private String getBindDataPath(String path){
    	int splitLen = path.indexOf("#"); 
    	if(splitLen == -1){
    		return path;
    	}
    	
    	return path.substring(0,splitLen);
    }
	
	private String getBeanData(com.yrkj.util.basedao.BaseBean bean,String format,String... paths){
		
		if(TextUtils.isEmpty(format)){
			String reStr = "";
			for(int i = 0; i<paths.length;i++){
				reStr+=bean.getData(getBindDataPath(paths[i]));
			}
			return reStr;
		}else{
			String[] defineBeanData = new String[paths.length];
			for(int i = 0; i<paths.length;i++){
				defineBeanData[i] = bean.getData(getBindDataPath(paths[i]));
			}
//			MessageFormat.format(format, args)
//			return String.format(format, args);
			return MessageFormat.format(format, defineBeanData);
		}
	}
    
	public void setViewData(LayoutDataAdapter adp, View v,
			com.yrkj.util.basedao.BaseBean bean, String format, String... paths) {

	}
	
//    public void setViewData(LayoutDataAdapter adp,
//    		com.yrkj.util.ui.TextViewFixTouchConsume v,
//    		com.yrkj.util.basedao.BaseBean bean,
//    		String format,
//			String... paths){
//    	if(paths == null){
//			return;
//		}
//		String type = getBindDataType(paths[paths.length-1]);
//		String beanData = getBeanData(bean,format,paths);
//		
//		
//		//use data
//    	v.setText(Html.fromHtml(beanData));
//		UiCtrlUtil.SetTextViewClickSpan(adp.mActy,v, 
//				Color.argb(255, 30, 123, 158), 
//				beanData,
//				null
//		);
//    }
    
    public void setViewData(LayoutDataAdapter adp,android.widget.
    		TextView v,
    		com.yrkj.util.basedao.BaseBean bean,
    		String format,
			String... paths){
    	if(paths == null){
			return;
		}
		String type = getBindDataType(paths[paths.length-1]);
		String beanData = getBeanData(bean,format,paths);
		
//		if(type.contains("Len")){
//			String defineLen = type.replace("Len:", "");
//			int len = Integer.parseInt(defineLen, 10);
//			if(len != 0){
//				Text
//			}
//		}
		if(type.equals("DateTime") && !TextUtils.isEmpty(beanData)){
			v.setText(DateHelper.convertToNeerTimeOrDate(beanData));
			return;
		}
		
		//use data
    	v.setText(beanData);
    }
    
//    public void setViewData(LayoutDataAdapter adp,
//    		ImageView v,
//    		com.yrkj.util.basedao.BaseBean bean,
//    		String format,
//			String... paths){
//    	if(paths == null){
//			return;
//		}
//		String type = getBindDataType(paths[paths.length-1]);
//		String beanData = getBeanData(bean,format,paths);
////		MessageBox.getInstance(adp.mActy).Show(beanData);
//		//use data
//    	if(type.equals("S_Avatar")){
//    		adp.mHttpAsyncLoadImg.bindSmallAvatarPic(v, beanData,false);
//    	}else if(type.equals("S_Blog")){
//    		adp.mHttpAsyncLoadImg.bindSmallWeiboPic(v, beanData,false);
//    	}
//    	else{
//    		adp.mHttpAsyncLoadImg.bindSmallPhotoPic(v, beanData,false);
//    	}
//	}
    
    public void setViewData(LayoutDataAdapter adp,
    		android.widget.RatingBar v,
    		com.yrkj.util.basedao.BaseBean bean,
    		String format,
			String... paths){
    	if(paths == null){
			return;
		}
		String type = getBindDataType(paths[paths.length-1]);
		String beanData = getBeanData(bean,format,paths);
		
		
		if(TextUtils.isEmpty(beanData)){
			v.setRating(0f);
			return;
		}
		try{
			float count = Float.parseFloat(beanData);
			v.setRating(count);
		}catch(Exception e){
			v.setRating(0f);
		}
		
    	
    }
    
    
}
