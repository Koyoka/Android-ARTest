package com.yrkj.util.basedao;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.yrkj.util.http.HttpMng;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.log.DebugTrace;


public abstract  class BaseTaskDao<T1, T2, T3> extends AsyncTask<T1,T2,T3> {

	protected String mURLPath = "";
	protected final String COMM_ERRMSG = "·þÎñÆ÷Òì³£";
	protected HttpRequestValue mReqValues = null;
	protected HttpRequestValue mReqPostValues = null;
	protected HttpRequestValue mReqFileValues = null;
	protected int mTaskId = 0;

	protected <T> ArrayList<T> doBeanSon(BeanSon<T> bs,JSONArray jsonAry) throws Exception{
		return  bs.convertJsonToArrayBean(jsonAry);
	}
	
	protected <T> T doBeanSon(BeanSon<T> bs,JSONObject jsonObj) throws Exception{
		return bs.convertJsonToBean(jsonObj);
	}
	
	@Override
	protected T3 doInBackground(T1... params) {
		// TODO Auto-generated method stub
		return doBack();
	}
	
	protected String doHttp() throws Exception{
		
		if(mReqValues!= null){
			String s = "getvalue ";
			for(NameValuePair v : mReqValues.GetValus()){
				s += v.getName()+"="+v.getValue()+"&";
			}
			DebugTrace.Print(s);
		}
		
		if(mReqPostValues!= null){
			String s = "postvalue ";
			for(NameValuePair v : mReqPostValues.GetValus()){
				s += v.getName()+"="+v.getValue()+"&";
			}
			DebugTrace.Print(s);
		}
		
		if(mReqPostValues != null || mReqFileValues != null){
			return HttpMng.GetHttpClientHelper().
					FilePostRequest(mURLPath, mReqValues, mReqPostValues, mReqFileValues);
		}else{
		
			return HttpMng.GetHttpClientHelper()
	                .GetRequest(mURLPath, mReqValues.GetValus());
		}
	}
	
	protected void setPostParams(HttpRequestValue v){
		mReqPostValues = v;
	}
	
	protected void setFileParams(HttpRequestValue v){
		mReqFileValues = v;
	}
	
	protected void setGetParams(HttpRequestValue v){
		mReqValues = v;
	}
	
	protected void setId(int id){
		mTaskId = id;
	}
	
	protected abstract T3 doBack();
}
