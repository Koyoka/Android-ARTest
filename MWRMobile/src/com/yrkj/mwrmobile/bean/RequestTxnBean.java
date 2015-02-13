package com.yrkj.mwrmobile.bean;

import java.util.ArrayList;

import com.yrkj.mwrmobile.DemoMWTxnDetail;

public class RequestTxnBean {
	  public String drvier = "";
	  public String drivercode = "";
	  public String inspector = "";
	  public String inspectorcode = "";
	  public String mwscode = "";
	  public String carcode = "";
	
	 public ArrayList<TxnDetailData> txndetaillist = new ArrayList<TxnDetailData>();
}
