package com.yrkj.mwrmobile.bean.response;

import java.util.ArrayList;

import com.yrkj.mwrmobile.bean.VendorData;
import com.yrkj.mwrmobile.bean.WasteCategoryData;

public class ResponseInitMWSSubmitBody {
	public String WSCode = "";
    public String CrateMask = "";
    public String AssessKey = "";
    public String SecretKey = "";
    
    public ArrayList<VendorData> VendorList = null;
    public ArrayList<WasteCategoryData> WasteList = null;
}
