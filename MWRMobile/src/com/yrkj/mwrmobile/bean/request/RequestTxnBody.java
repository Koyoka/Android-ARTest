package com.yrkj.mwrmobile.bean.request;

import java.util.ArrayList;

import com.yrkj.mwrmobile.DemoMWTxnDetail;
import com.yrkj.mwrmobile.bean.TxnDetailData;

public class RequestTxnBody {
	  public String drvier = "";
	  public String drivercode = "";
	  public String inspector = "";
	  public String inspectorcode = "";
	  public String mwscode = "";
	  public String carcode = "";
	
	 public ArrayList<TxnDetailData> txndetaillist = new ArrayList<TxnDetailData>();
}
