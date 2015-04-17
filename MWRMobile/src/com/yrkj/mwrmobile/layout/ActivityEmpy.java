package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityEmpy extends BaseLayout{

    public static final int TxtWSCodeId = R.id.txtWSCode;
    public static final int BtnSetHostViewId = R.id.btnSetHostView;
    public static final int TxtTextViewId = R.id.txtTextView;
    public static final int TxtNetStateId = R.id.txtNetState;

    protected android.widget.TextView mTxtWSCode;
    protected android.widget.TextView mBtnSetHostView;
    protected android.widget.TextView mTxtTextView;
    protected android.widget.TextView mTxtNetState;

    protected Activity mCurActy;

    public ActivityEmpy(Activity acty){
        mCurActy = acty;
        mTxtWSCode = (android.widget.TextView) acty.findViewById(TxtWSCodeId);
        mBtnSetHostView = (android.widget.TextView) acty.findViewById(BtnSetHostViewId);
        mTxtTextView = (android.widget.TextView) acty.findViewById(TxtTextViewId);
        mTxtNetState = (android.widget.TextView) acty.findViewById(TxtNetStateId);
    }   

    public ActivityEmpy(android.view.View acty){
        mTxtWSCode = (android.widget.TextView) acty.findViewById(TxtWSCodeId);
        mBtnSetHostView = (android.widget.TextView) acty.findViewById(BtnSetHostViewId);
        mTxtTextView = (android.widget.TextView) acty.findViewById(TxtTextViewId);
        mTxtNetState = (android.widget.TextView) acty.findViewById(TxtNetStateId);
    }   
    public android.widget.TextView getTxtWSCode() {
        return mTxtWSCode;
    }
    public android.widget.TextView getBtnSetHostView() {
        return mBtnSetHostView;
    }
    public android.widget.TextView getTxtTextView() {
        return mTxtTextView;
    }
    public android.widget.TextView getTxtNetState() {
        return mTxtNetState;
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
                case TxtWSCodeId:
                    setViewData(adp,getTxtWSCode(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSetHostViewId:
                    setViewData(adp,getBtnSetHostView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTextViewId:
                    setViewData(adp,getTxtTextView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtNetStateId:
                    setViewData(adp,getTxtNetState(),data,joinData.formatString,joinData.data);
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
                case TxtWSCodeId:
                    setViewData(adp,getTxtWSCode(),data,"",path);
                    break;
                case BtnSetHostViewId:
                    setViewData(adp,getBtnSetHostView(),data,"",path);
                    break;
                case TxtTextViewId:
                    setViewData(adp,getTxtTextView(),data,"",path);
                    break;
                case TxtNetStateId:
                    setViewData(adp,getTxtNetState(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
