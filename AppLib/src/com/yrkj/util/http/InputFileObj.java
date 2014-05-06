package com.yrkj.util.http;

import java.io.InputStream;

public class InputFileObj {
	public InputFileObj(String fileName,InputStream is){
		FileName = fileName;
		FileIS = is;
	}
	public String FileName = "";
	public InputStream FileIS = null;
}
