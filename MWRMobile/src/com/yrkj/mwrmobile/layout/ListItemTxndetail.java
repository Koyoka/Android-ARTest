package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ListItemTxndetail extends BaseLayout{

    public static final int TxtVendorId = R.id.txtVendor;
    public static final int TxtWasteId = R.id.txtWaste;
    public static final int TxtCrateCodeId = R.id.txtCrateCode;
    public static final int TxtWeightId = R.id.txtWeight;

    protected android.widget.TextView mTxtVendor;
    protected android.widget.TextView mTxtWaste;
    protected android.widget.TextView mTxtCrateCode;
    protected android.widget.TextView mTxtWeight;

    protected Activity mCurActy;

    public ListItemTxndetail(Activity acty){
        mCurActy = acty;
        mTxtVendor = (android.widget.TextView) acty.findViewById(TxtVendorId);
        mTxtWaste = (android.widget.TextView) acty.findViewById(TxtWasteId);
        mTxtCrateCode = (android.widget.TextView) acty.findViewById(TxtCrateCodeId);
        mTxtWeight = (android.widget.TextView) acty.findViewById(TxtWeightId);
    }   

    public ListItemTxndetail(android.view.View acty){
        mTxtVendor = (android.widget.TextView) acty.findViewById(TxtVendorId);
        mTxtWaste = (android.widget.TextView) acty.findViewById(TxtWasteId);
        mTxtCrateCode = (android.widget.TextView) acty.findViewById(TxtCrateCodeId);
        mTxtWeight = (android.widget.TextView) acty.findViewById(TxtWeightId);
    }   
    public android.widget.TextView getTxtVendor() {
        return mTxtVendor;
    }
    public android.widget.TextView getTxtWaste() {
        return mTxtWaste;
    }
    public android.widget.TextView getTxtCrateCode() {
        return mTxtCrateCode;
    }
    public android.widget.TextView getTxtWeight() {
        return mTxtWeight;
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
                case TxtVendorId:
                    setViewData(adp,getTxtVendor(),data,joinData.formatString,joinData.data);
                    break;
                case TxtWasteId:
                    setViewData(adp,getTxtWaste(),data,joinData.formatString,joinData.data);
                    break;
                case TxtCrateCodeId:
                    setViewData(adp,getTxtCrateCode(),data,joinData.formatString,joinData.data);
                    break;
                case TxtWeightId:
                    setViewData(adp,getTxtWeight(),data,joinData.formatString,joinData.data);
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
                case TxtVendorId:
                    setViewData(adp,getTxtVendor(),data,"",path);
                    break;
                case TxtWasteId:
                    setViewData(adp,getTxtWaste(),data,"",path);
                    break;
                case TxtCrateCodeId:
                    setViewData(adp,getTxtCrateCode(),data,"",path);
                    break;
                case TxtWeightId:
                    setViewData(adp,getTxtWeight(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
