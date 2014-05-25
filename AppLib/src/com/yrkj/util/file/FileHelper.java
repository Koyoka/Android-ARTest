package com.yrkj.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

public class FileHelper {
	public static boolean CopyFile(InputStream inputStream,OutputStream outputStream){
		byte[] inputBuffer = new byte[1024];
		int length = 0;
		try {
			while((length = inputStream.read(inputBuffer)) > 0){
				outputStream.write(inputBuffer, 0, length);
			}
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String readAssessFile(Context c,String fileName){
		String s = "";
		try {
			InputStream is = c.getResources().getAssets().open(fileName);
			int len = is.available();
			byte[] buffer = new byte[len];
			is.read(buffer);
			
			s = EncodingUtils.getString(buffer, "utf-8");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}
}
