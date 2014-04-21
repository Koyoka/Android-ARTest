package com.yrkj.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
}
