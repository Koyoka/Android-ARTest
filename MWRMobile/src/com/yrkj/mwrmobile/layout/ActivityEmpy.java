package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityEmpy extends BaseLayout{

    public static final int TxtTextViewId = R.id.txtTextView;

    protected android.widget.TextView mTxtTextView;

    protected Activity mCurActy;

    public ActivityEmpy(Activity acty){
        mCurActy = acty;
        mTxtTextView = (android.widget.TextView) acty.findViewById(TxtTextViewId);
    }   

    public ActivityEmpy(android.view.View acty){
        mTxtTextView = (android.widget.TextView) acty.findViewById(TxtTextViewId);
    }   
    public android.widget.TextView getTxtTextView() {
        return mTxtTextView;
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
                case TxtTextViewId:
                    setViewData(adp,getTxtTextView(),data,joinData.formatString,joinData.data);
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
                case TxtTextViewId:
                    setViewData(adp,getTxtTextView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
