package com.yrkj.yrkjdemo;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;

public class test {
	
	static OSSClient  mClient;
	public static void main(String[] args) {
		System.out.println("11");       
		initOSS();
		listObjects("eleven-bucket");
	}
	
	private static void initOSS(){
		String accessKeyId = "Rru0fC84n5OWyew0";
		String accessKeySecret = "6smSECdwPWNZ0m9pHrOzFtQMQU8wAJ";
		mClient = new OSSClient(accessKeyId, accessKeySecret);
		
	}
	public static void listObjects(String bucketName) {

		//http://eleven-bucket.oss-cn-hangzhou.aliyuncs.com/video_creator.mp4
		//http://eleven-bucket.oss-cn-hangzhou.aliyuncs.com/1.m4v
	    // 初始化OSSClient
	    OSSClient client = mClient;

	    // 获取指定bucket下的所有Object信息
	    ObjectListing listing = client.listObjects(bucketName);

	    // 遍历所有Object
	    for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
	    	
	        System.out.println(objectSummary.getKey() + " " +objectSummary.getETag() + " " 
	        		+ objectSummary.toString());
	    }
	}
}
