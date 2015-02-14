package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityLaunch extends BaseLayout{

    public static final int TxtLoginInfoId = R.id.txtLoginInfo;
    public static final int BtnScanId = R.id.btnScan;
    public static final int BtnExitId = R.id.btnExit;

    protected android.widget.TextView mTxtLoginInfo;
    protected android.widget.Button mBtnScan;
    protected android.widget.Button mBtnExit;

    protected Activity mCurActy;

    public ActivityLaunch(Activity acty){
        mCurActy = acty;
        mTxtLoginInfo = (android.widget.TextView) acty.findViewById(TxtLoginInfoId);
        mBtnScan = (android.widget.Button) acty.findViewById(BtnScanId);
        mBtnExit = (android.widget.Button) acty.findViewById(BtnExitId);
    }   

    public ActivityLaunch(android.view.View acty){
        mTxtLoginInfo = (android.widget.TextView) acty.findViewById(TxtLoginInfoId);
        mBtnScan = (android.widget.Button) acty.findViewById(BtnScanId);
        mBtnExit = (android.widget.Button) acty.findViewById(BtnExitId);
    }   
    public android.widget.TextView getTxtLoginInfo() {
        return mTxtLoginInfo;
    }
    public android.widget.Button getBtnScan() {
        return mBtnScan;
    }
    public android.widget.Button getBtnExit() {
        return mBtnExit;
    }

    public void bindData(LayoutDataAdapter adp,BaseBean data){
        if(adp == null || data == null){
            return;
        }
        
        if(adp.BindJoinFiledList != null){
            java.util.Iterator iter = adp.BindJoinFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                LayoutDataAdapter.JoinData joinData = (LayoutDataAdapter.JoinData) val;
                
                switch (viewKey) {
                case TxtLoginInfoId:
                    setViewData(adp,getTxtLoginInfo(),data,joinData.formatString,joinData.data);
                    break;
                case BtnScanId:
                    setViewData(adp,getBtnScan(),data,joinData.formatString,joinData.data);
                    break;
                case BtnExitId:
                    setViewData(adp,getBtnExit(),data,joinData.formatString,joinData.data);
                    break;
                }
            }
        }
        
        if(adp.BindFiledList != null){
            java.util.Iterator iter = adp.BindFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                String path = (String) val;
                switch (viewKey) {
                case TxtLoginInfoId:
                    setViewData(adp,getTxtLoginInfo(),data,"",path);
                    break;
                case BtnScanId:
                    setViewData(adp,getBtnScan(),data,"",path);
                    break;
                case BtnExitId:
                    setViewData(adp,getBtnExit(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
