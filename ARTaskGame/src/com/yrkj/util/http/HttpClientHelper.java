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
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.yrkj.config.SysConfig;
import com.yrkj.util.information.HttpInfor;
import com.yrkj.util.log.DebugTrace;


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

	public HttpClientHelper(String host) {
		
		_host = host;
//		_port = Config.SERVICE_HTTP_PORT;
		//prepare httpclient params
		HttpParams params = new BasicHttpParams();
		{
			
			ConnManagerParams.setMaxTotalConnections(params, 50);
			 /* 从连接池中取连接的超时时间 */
			ConnManagerParams.setTimeout(params, 10000);
			 /* 连接超时 */
            HttpConnectionParams.setConnectionTimeout(params, 10000);
            /* 请求超时 */
            HttpConnectionParams.setSoTimeout(params, 10000);
            
			HttpProtocolParams.setContentCharset(params, CHARSET);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			
			HttpProtocolParams.setUseExpectContinue(params, true);
		}

		//prepare clientConnManager
		{
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			{
				
//				Scheme http80 = new Scheme("http",
//						PlainSocketFactory.getSocketFactory(), 80);
//				schemeRegistry.register(http80);
				
				Scheme http = new Scheme("http",
						PlainSocketFactory.getSocketFactory(), SysConfig.SERVICE_HTTP_PORT);
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
	
	public HttpClientHelper(String host,int port)  
	{
		this(host);
		_port = port;	
	}
	
	public ByteArrayOutputStream getHttpFileStream2ByteOutStream(String httpPath, DownloadListener l) {
		try {
//			BasicManagedEntity isEntity = GetHttpFileStream(httpPath);
//			if (isEntity == null) {
//				return null;
//			}
//
//			InputStream is = isEntity.getContent();
//			int bytetotal = (int) isEntity.getContentLength();
			
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
	
	private void putInStream2OutStream(InputStream is,OutputStream os,int byteTotal,DownloadListener l) throws IOException{
		byte[] buf = new byte[1444];
		int len = -1;
		int bytesum = 0;
//		int byteread = 0;

		while ((len = is.read(buf)) != -1) {
			bytesum += len;//byteread;
			os.write(buf, 0, len);
			if (l != null && byteTotal > 0 ) {
				
				l.pushProgress(bytesum, byteTotal);
			}
		}
	}
	
//	private BasicManagedEntity GetHttpFileStream1(String httpPath)
//			throws Exception{
//		
//		
//		HttpURLConnection urlConnection = null;
//		
//		 URL url = new URL(httpPath);
//
//         urlConnection = (HttpURLConnection) url
//                 .openConnection();
//
//         urlConnection.setRequestMethod("GET");
//         urlConnection.setDoOutput(false);
//         urlConnection.setConnectTimeout(5000);
//         urlConnection.setReadTimeout(8000);
//         urlConnection.setRequestProperty("Connection", "Keep-Alive");
//         urlConnection.setRequestProperty("Charset", "UTF-8");
//         urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//
//         urlConnection.connect();
//         
//         int status = urlConnection.getResponseCode();
//         if (status == HttpStatus.SC_OK) {
////        	 urlConnection.getInputStream();
////        	 urlConnection.get
//        	 BasicManagedEntity e = null;
//        	 return e;
//         }
//         return null;
////         if (status != 200) {
////             return false;
////         }
//		
//		
//	}
	
	private BasicManagedEntity GetHttpFileStream(String httpPath)
			throws Exception{

//		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
//				URLEncodedUtils.format(qparams, CHARSET), null);
		URI uri = new URI(httpPath);
		HttpGet httpget = new HttpGet(uri);

		HttpResponse response;
		try {
			response = _httpclient.execute(httpget);
//			response = _imgHttpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String s = e.getMessage();
			if(s!=null){
				throw new Exception(s);
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String s = e.getMessage();
			if(s!=null){
				throw new Exception(s);
			}
			return null;
		}
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//			InputStream is = response.getEntity().getContent();
//			response.getEntity().get
			BasicManagedEntity e = (BasicManagedEntity) response.getEntity();
//			org.apache.http.conn.BasicManagedEntity ee;
//			response.getEntity().
			return e;
//			return Drawable.createFromStream(is,"src");
			
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//	        byte[] buf = new byte[1024];  
//	        int len = -1;  
//	        while ((len = is.read(buf)) != -1) { 
////	        	DebugTrace.Print("load img----- " + len + "  " +httpPath);
//	            baos.write(buf, 0, len);  
//	        }  
//	        Bitmap b = MediaUtil.decodeSampledBitmapFromByte(baos.toByteArray(), 40, 40);
//	        
//	        if(is != null){
//	        	is.close();
//	        }
//	        
//	        if(baos != null){
//	        	baos.close();
//	        }
//	        
//	        return b;
		}
		return null;

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
		
		DebugTrace.Print(TAG,result);
		return result;

	}
	public JSONObject GetRequestJsonResult(String operatorPath, List<NameValuePair> qparams)
			throws Exception{
		String outResStr = GetRequest(operatorPath,qparams);
		if(outResStr.length() == 0){
			DebugTrace.Print(TAG,operatorPath);
			String s = "";
			for(NameValuePair item : qparams){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DebugTrace.Print(TAG,s);
			throw new Exception("err:return josn is empty!");
//			return null;
		}else{
			String s = "";
			for(NameValuePair item : qparams){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DebugTrace.Print(TAG,s);
		}
		return new JSONObject(outResStr);
	}
	public JSONObject GetRequestJsonResult(String operatorPath,  HttpRequestValue getValue)
			throws Exception{
		String outResStr = GetRequest(operatorPath,getValue.GetValus());
//		DebugTrace.Print(TAG,"1.----"+outResStr);
		if(outResStr.length() == 0){
			DebugTrace.Print(TAG,operatorPath);
			String s = "";
			for(NameValuePair item : getValue.GetValus()){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DebugTrace.Print(TAG,s);
			throw new Exception("err:return josn is empty! " + operatorPath);
//			return null;
		}else{
			String s = "";
			for(NameValuePair item : getValue.GetValus()){
				s += item.getName() + "=" + item.getValue()+"&";
				
			}
			DebugTrace.Print(TAG,s);
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
		return httpRequest(operatorPath,getQparams,postQparams,null);
	}
	
	public String FilePostRequest(String operatorPath,
			HttpRequestValue getQparams,
			HttpRequestValue postQparams,
			HttpRequestValue fileQparams)throws Exception{
		return httpRequest(operatorPath,
				getQparams==null?null:getQparams.GetValus(),
				postQparams==null?null:postQparams.GetValus(),
				fileQparams==null?null:fileQparams.GetFileValues());
	}
	
	public String httpRequest(String operatorPath,
			List<NameValuePair> getQparams,
			List<NameValuePair> postQparams,
			Map<String,InputFileObj> fileQparams)throws Exception{
		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
				getQparams!= null?URLEncodedUtils.format(getQparams, CHARSET):null, null);
		
	    HttpPost post = new HttpPost(uri);  
		
	    if(postQparams != null || fileQparams != null){
	    	MultipartEntity multipartEntity = new MultipartEntity();  
	    	
	    	if(postQparams != null){
	    		for(NameValuePair item : postQparams){
	    			multipartEntity.addPart(item.getName(), 
	        				 new StringBody(item.getValue(), Charset.forName(CHARSET)));
	    		}
	    	}
	    	
	    	if(fileQparams != null){
	    		for (Map.Entry<String, InputFileObj> entry : fileQparams.entrySet()) {
//	    			String key = entry.getKey();
//	    			InputStream val = entry.getValue().FileIS;
					org.apache.http.entity.mime.content.InputStreamBody isb = 
							new InputStreamBody(
									entry.getValue().FileIS,
									entry.getValue().FileName);
					multipartEntity.addPart(entry.getKey(), isb);
	    		}
	    		
//	    		for(NameValuePair item : fileQparams){
//	    			File f = new File(item.getValue());
//	    			if(f.exists()){
//	    				multipartEntity.addPart(item.getName(), 
//		    					 new FileBody(f));
////	    				org.apache.http.entity.mime.content.InputStreamBody is = 
////	    						new InputStreamBody(null, "");
////	    				multipartEntity.addPart("", contentBody)
//	    			}else{
//	    				throw new Exception("item.getName() file item.getValue() unExist!!!!!!!!----------");
////	    				new Exception("item.getName() file item.getValue() unExist!!!!!!!!----------").printStackTrace();
//	    			}
//	    		}
	    	}
	    	
	    	post.setEntity(multipartEntity);
	    }
	    
	    HttpResponse response = _httpclient.execute(post);
		String result = "";
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream is = response.getEntity().getContent();
			result = inStream2String(is);
		}
		
		DebugTrace.Print(result);
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
	
	
//	public String FilePostRequest1(String operatorPath,
//			HttpRequestValue getQparams,
//			HttpRequestValue postQparams,
//			HttpRequestValue fileQparams)throws Exception{
//		return httpRequest1(operatorPath,
//				getQparams==null?null:getQparams.GetValus(),
//				postQparams==null?null:postQparams.GetValus(),
//				fileQparams==null?null:fileQparams.GetValus());
//	}
	
//	public String httpRequest1(String operatorPath,
//			InputStream inputStream,String fileName)throws Exception{
//		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
//				null, null);
//		
//	    HttpPost post = new HttpPost(uri);  
//	    MultipartEntity multipartEntity = new MultipartEntity();  
////	    FileInputStream  inStream = null;
////	    inputStream = inStream.
////	    new InputStreamBody()
//	    org.apache.http.entity.mime.content.InputStreamBody isb = 
//				new InputStreamBody(inputStream, fileName);
//	    multipartEntity.addPart("imgfile", isb);
//	    post.setEntity(multipartEntity);
//	    
//	    HttpResponse response = _httpclient.execute(post);
//		String result = "";
//		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//			InputStream is = response.getEntity().getContent();
//			result = inStream2String(is);
//		}
//		return result;
//		
//	}
	
	
}

//multipartEntity.addPart("PostImageFile", new FileBody(
//new File(filePath)));

//HttpEntity formEntity = new UrlEncodedFormEntity(postQparams,CHARSET); 
//HttpEntity form1Entity = new UrlEncodedFormEntity(getQparams,CHARSET); 
//HttpEntity f1Entity = new UriEncodeFormEntity(postQparams);
//HttpEntity fEntity = new org.apache.http.entity.StringEntity("");
//org.apache.http.client.entity.UrlEncodedFormEntity
//org.apache.http.client.utils.
//HttpEntity formEntity = new org.apache.http.entity.StringEntity(URLEncodedUtils.format(getQparams, CHARSET));
//for(NameValuePair item : postQparams){
//HttpEntity formEntity = new org.apache.http.entity.StringEntity(item.getName()+"="+item.getValue(), CHARSET);
//
//
//post.setEntity(formEntity);  
//}
//HttpEntity fntity = new org.apache.http.entity.StringEntity(s, CHARSET);
//DebugTrace.Print(TAG,formEntity.toString());
//post.setEntity(formEntity);  
//post.setEntity(form1Entity);  


//public String PostRequest(String operatorPath, List<NameValuePair> getQparams,List<NameValuePair> postQparams)
//		throws Exception{
//			URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
//					getQparams!= null?URLEncodedUtils.format(getQparams, CHARSET):null, null);
//	        HttpPost post = new HttpPost(uri);  
//	        
//	        MultipartEntity multipartEntity = new MultipartEntity();  
//	        if(postQparams != null){
//	        	 for(NameValuePair item : postQparams){
//	        		 multipartEntity.addPart(item.getName(), 
//	        				 new StringBody(item.getValue(), Charset.forName(CHARSET)));
//	        	 }
//	        }
//	        post.setEntity(multipartEntity);
//	        
//	        
//	        HttpResponse response = _httpclient.execute(post);
//			String result = "";
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				InputStream is = response.getEntity().getContent();
//				result = inStream2String(is);
//			}
//			return result;
//		}
//		

//public String FilePostRequest(String operatorPath, List<NameValuePair> qparams, String filePath){
//	
//	try{
//		URI uri = URIUtils.createURI("http", _host, _port, "/"+operatorPath,
//				URLEncodedUtils.format(qparams, CHARSET), null);
//        HttpClient client = new DefaultHttpClient();  
//        HttpPost post = new HttpPost(uri);  
//          
//        MultipartEntity multipartEntity = new MultipartEntity();  
//        
//        multipartEntity.addPart("PostImageFile", new FileBody(
//                new File(filePath)));
//        post.setEntity(multipartEntity);  
//        HttpResponse response = client.execute(post);  
//        
//        String result = "";
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
//            InputStream is = response.getEntity().getContent();
//			result = inStream2String(is);
//        }  
//        
//        return result;
//	}
//	catch(Exception e){
//		return e.getMessage();
//	}
//
//	 
//}

