package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityRecover extends BaseLayout{

    public static final int TxtTxnHeaderId = R.id.txtTxnHeader;

    protected android.widget.TextView mTxtTxnHeader;

    protected Activity mCurActy;

    public ActivityRecover(Activity acty){
        mCurActy = acty;
        mTxtTxnHeader = (android.widget.TextView) acty.findViewById(TxtTxnHeaderId);
    }   

    public ActivityRecover(android.view.View acty){
        mTxtTxnHeader = (android.widget.TextView) acty.findViewById(TxtTxnHeaderId);
    }   
    public android.widget.TextView getTxtTxnHeader() {
        return mTxtTxnHeader;
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
                case TxtTxnHeaderId:
                    setViewData(adp,getTxtTxnHeader(),data,joinData.formatString,joinData.data);
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
                case TxtTxnHeaderId:
                    setViewData(adp,getTxtTxnHeader(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
