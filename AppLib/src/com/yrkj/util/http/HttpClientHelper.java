package com.yrkj.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.BasicManagedEntity;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.yrkj.util.http.CustomMultipartEntity.HttpProgressListener;
import com.yrkj.util.information.HttpInfor;
import com.yrkj.util.log.DLog;


//import com.lc.sm.base.BaseApplication;
//import com.lc.sm.base.Config;
//import com.yrkj.util.DebugTrace;




public class HttpClientHelper {
	private static final String TAG = "HttpClient";
	public static final String CHARSET = HTTP.UTF_8;//"UTF-8";
	
	private DefaultHttpClient _httpclient;
//	private DefaultHttpClient _imgHttpClient;
	private ThreadSafeClientConnManager _connMng;
	private String _host = "";
	private int _port = -1;
	
	public static interface DownloadListener {
        public void pushProgress(int progress, int max);
    }

	public HttpClientHelper(){
		HttpParams params = new BasicHttpParams();
		{
			ConnManagerParams.setMaxTotalConnections(params, 50);
			 /* 从连接池中取连接的超时时间 */
			ConnManagerParams.setTimeout(params, 15000);
			 /* 连接超时 */
            HttpConnectionParams.setConnectionTimeout(params, 15000);
            /* 请求超时 */
            HttpConnectionParams.setSoTimeout(params, 15000);
            
			HttpProtocolParams.setContentCharset(params, CHARSET);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			
			
			HttpProtocolParams.setUseExpectContinue(params, true);
		}
		_httpclient = new DefaultHttpClient(params);
		
	}
	public HttpClientHelper(String host,int port) {
		_port = port;
		_host = host;
		HttpParams params = new BasicHttpParams();
		{
			
			ConnManagerParams.setMaxTotalConnections(params, 50);
			 /* 从连接池中取连接的超时时间 */
			ConnManagerParams.setTimeout(params, 15000);
			 /* 连接超时 */
            HttpConnectionParams.setConnectionTimeout(params, 15000);
            /* 请求超时 */
            HttpConnectionParams.setSoTimeout(params, 15000);
            
			HttpProtocolParams.setContentCharset(params, CHARSET);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			
			
			HttpProtocolParams.setUseExpectContinue(params, true);
		}

		//prepare clientConnManager
		{
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			{
				Scheme http = new Scheme("http",
						PlainSocketFactory.getSocketFactory(), _port);
				schemeRegistry.register(http);
				
				Scheme https = new Scheme("https",
						SSLSocketFactory.getSocketFactory(), 443);
				schemeRegistry.register(https);
			}

			_connMng = new ThreadSafeClientConnManager(params,
					schemeRegistry);
			
		}
		

		_httpclient = new DefaultHttpClient(_connMng, params);
//		_imgHttpClient = new DefaultHttpClient(_connMng, params);
		
	}
	
	public static ByteArrayOutputStream getHttpFileStream2ByteOutStream(String httpPath, DownloadListener l) {
		try {
			
			 URL url = new URL(httpPath);

			 HttpURLConnection urlConnection = null;
	         urlConnection = (HttpURLConnection) url
	                 .openConnection();

	         urlConnection.setRequestMethod("GET");
	         urlConnection.setDoOutput(false);
	         urlConnection.setConnectTimeout(10000);
	         urlConnection.setReadTimeout(10000);
	         urlConnection.setRequestProperty("Connection", "Keep-Alive");
	         urlConnection.setRequestProperty("Charset", CHARSET);
	         urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");

	         urlConnection.connect();
	         
	         int status = urlConnection.getResponseCode();
	         if (status != HttpStatus.SC_OK) {
	        	 return null;
	         }
	         
	        InputStream is = urlConnection.getInputStream();
	        int bytetotal = (int) urlConnection.getContentLength();
	        
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			putInStream2OutStream(is, baos, bytetotal, l);

			if (is != null) {
				is.close();
			}

			if (baos != null) {
				baos.close();
			}
			return baos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static void putInStream2OutStream(InputStream is,OutputStream os,int byteTotal,DownloadListener l) throws IOException{
		byte[] buf = new byte[1444];
		int len = -1;
		int bytesum = 0;

		while ((len = is.read(buf)) != -1) {
			bytesum += len;
			os.write(buf, 0, len);
			if (l != null && byteTotal > 0 ) {
				
				l.pushProgress(bytesum, byteTotal);
			}
		}
	}
	
	
	public String GetRequest(String operatorPath, List<NameValuePair> qparams)
			throws Exception{

		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
				URLEncodedUtils.format(qparams, CHARSET), null);
		
		HttpGet httpget = new HttpGet(uri);
		
		HttpResponse response;
		try {
			response = _httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String s = e.getMessage();
			s = HttpInfor.HTTP_CONN_ERR;
			if(s!=null){
				
				throw new Exception(s);
			}
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String s = e.getMessage();
			s = HttpInfor.HTTP_CONN_ERR;
			if(s!=null){
				
				throw new Exception(s);
			}
			return "";
		}
		String result = "";
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream is = response.getEntity().getContent();
			result = inStream2String(is);
		}
		
		DLog.LOG(TAG,result);
		return result;

	}
	
	public JSONObject GetRequestJsonResult(String operatorPath, List<NameValuePair> qparams)
			throws Exception{
		String outResStr = GetRequest(operatorPath,qparams);
		if(outResStr.length() == 0){
			DLog.LOG(TAG,operatorPath);
			String s = "";
			for(NameValuePair item : qparams){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DLog.LOG(TAG,s);
			throw new Exception("err:return josn is empty!");
		}else{
			String s = "";
			for(NameValuePair item : qparams){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DLog.LOG(TAG,s);
		}
		return new JSONObject(outResStr);
	}
	public JSONObject GetRequestJsonResult(String operatorPath,  HttpRequestValue getValue)
			throws Exception{
		String outResStr = GetRequest(operatorPath,getValue.GetValus());
		if(outResStr.length() == 0){
			DLog.LOG(TAG,operatorPath);
			String s = "";
			for(NameValuePair item : getValue.GetValus()){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DLog.LOG(TAG,s);
			throw new Exception("err:return josn is empty! " + operatorPath);
//			return null;
		}else{
			String s = "";
			for(NameValuePair item : getValue.GetValus()){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DLog.LOG(TAG,s);
		}
		return new JSONObject(outResStr);
	}
	
	public JSONObject PostRequestJsonResult(String operatorPath, HttpRequestValue getValue,HttpRequestValue postValue)
			throws Exception{
		String outResStr = PostRequest(operatorPath,getValue.GetValus(),postValue.GetValus());
		if(outResStr.length() == 0){
			return null;
		}
		return new JSONObject(outResStr);
	}
	public JSONObject PostRequestJsonResult(String operatorPath, List<NameValuePair> getQparams,List<NameValuePair> postQparams)
			throws Exception{
		String outResStr = PostRequest(operatorPath,getQparams,postQparams);
		if(outResStr.length() == 0){
			return null;
		}
		return new JSONObject(outResStr);
	}
	public String PostRequest(String operatorPath, List<NameValuePair> getQparams,List<NameValuePair> postQparams)
	throws Exception{
		return httpRequest(operatorPath,getQparams,postQparams,null,null);
	}
	
	public String FilePostRequest(String operatorPath,
			HttpRequestValue getQparams,
			HttpRequestValue postQparams,
			HttpRequestValue fileQparams)throws Exception{
		return httpRequest(operatorPath,
				getQparams==null?null:getQparams.GetValus(),
				postQparams==null?null:postQparams.GetValus(),
				fileQparams==null?null:fileQparams.GetFileValues(),null);
	}
	public String FilePostRequest(String operatorPath,
			HttpRequestValue getQparams,
			HttpRequestValue postQparams,
			HttpRequestValue fileQparams,HttpProgressListener l)throws Exception{
		return httpRequest(operatorPath,
				getQparams==null?null:getQparams.GetValus(),
						postQparams==null?null:postQparams.GetValus(),
								fileQparams==null?null:fileQparams.GetFileValues(),l);
	}
	
	public String httpRequest(String operatorPath,
			List<NameValuePair> getQparams,
			List<NameValuePair> postQparams,
			Map<String,InputFileObj> fileQparams,HttpProgressListener l)throws Exception{
		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
				getQparams!= null?URLEncodedUtils.format(getQparams, CHARSET):null, null);
		
	    HttpPost post = new HttpPost(uri);  
		
	    if(postQparams != null || fileQparams != null){
	    	MultipartEntity multipartEntity;
	    	if(l == null){
	    		 multipartEntity = new MultipartEntity();  
	    	}else{
	    		 multipartEntity = new CustomMultipartEntity(l);
	    	}
	    	
	    	if(postQparams != null){
	    		for(NameValuePair item : postQparams){
	    			multipartEntity.addPart(item.getName(), 
	        				 new StringBody(item.getValue(), Charset.forName(CHARSET)));
	    		}
	    	}
	    	
	    	if(fileQparams != null){
	    		for (Map.Entry<String, InputFileObj> entry : fileQparams.entrySet()) {
					org.apache.http.entity.mime.content.InputStreamBody isb = 
							new InputStreamBody(
									entry.getValue().FileIS,
									entry.getValue().FileName);
					multipartEntity.addPart(entry.getKey(), isb);
	    		}
	    		
	    	}
	    	
	    	post.setEntity(multipartEntity);
	    }
	    
	    HttpResponse response = _httpclient.execute(post);
		String result = "";
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream is = response.getEntity().getContent();
			result = inStream2String(is);
		}
		
		DLog.LOG(result);
		return result;
		
	}
	
	public String httpSignatureRequestURL(String url,String accessKey,String secretKey,
			String requestBody)throws Exception{
		URI uri = new URI(url);
		
	    HttpPost post = new HttpPost(uri);  
		
	    post.setEntity(new StringEntity(requestBody.toString()));
	    
	    SignatureBuilder sb = new SignatureBuilder();
	    post.setHeader(new BasicHeader("Date", org.apache.http.impl.cookie.DateUtils.formatDate(new java.util.Date()).replaceFirst("[+]00:00$", "")));
	    post.setHeader(new BasicHeader("Content-Type", "application/json"));
	    post.setHeader("Authorization", "MWR " + accessKey + ":" + sb.tmsSignature(post, secretKey));
	    
	    HttpResponse response = _httpclient.execute(post);
		String result = "";
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream is = response.getEntity().getContent();
			result = inStream2String(is);
		}
		
//		DLog.LOG(result);
		return result;
		
	}
	public String httpSignatureRequest(String operatorPath,String accessKey,String secretKey,
			String requestBody)throws Exception{
		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,null, null);
		
	    HttpPost post = new HttpPost(uri);  
		
	    post.setEntity(new StringEntity(requestBody.toString()));
	    
	    SignatureBuilder sb = new SignatureBuilder();
	    post.setHeader(new BasicHeader("Date", org.apache.http.impl.cookie.DateUtils.formatDate(new java.util.Date()).replaceFirst("[+]00:00$", "")));
	    post.setHeader(new BasicHeader("Content-Type", "application/json"));
	    post.setHeader("Authorization", "MWR " + accessKey + ":" + sb.tmsSignature(post, secretKey));
	    
	    HttpResponse response = _httpclient.execute(post);
		String result = "";
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream is = response.getEntity().getContent();
			result = inStream2String(is);
		}
		
//		DLog.LOG(result);
		return result;
		
	}
	
	private String inStream2String(InputStream is) throws Exception {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        byte[] buf = new byte[1024];  
        int len = -1;  
        while ((len = is.read(buf)) != -1) {  
            baos.write(buf, 0, len);  
        }  
        
        String defineStr = new String(baos.toByteArray(),CHARSET);  
        
        if(is != null){
        	is.close();
        }
        
        if(baos != null){
        	baos.close();
        }
        return defineStr;
    }  
	
	
	
	
}


