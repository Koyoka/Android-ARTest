package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class Loading extends BaseLayout{

    public static final int ProgressBar1Id = R.id.progressBar1;
    public static final int TxtLoadingMsgViewId = R.id.txtLoadingMsgView;

    protected android.widget.ProgressBar mProgressBar1;
    protected android.widget.TextView mTxtLoadingMsgView;

    protected Activity mCurActy;

    public Loading(Activity acty){
        mCurActy = acty;
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
        mTxtLoadingMsgView = (android.widget.TextView) acty.findViewById(TxtLoadingMsgViewId);
    }   

    public Loading(android.view.View acty){
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
        mTxtLoadingMsgView = (android.widget.TextView) acty.findViewById(TxtLoadingMsgViewId);
    }   
    public android.widget.ProgressBar getProgressBar1() {
        return mProgressBar1;
    }
    public android.widget.TextView getTxtLoadingMsgView() {
        return mTxtLoadingMsgView;
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
                case ProgressBar1Id:
                    setViewData(adp,getProgressBar1(),data,joinData.formatString,joinData.data);
                    break;
                case TxtLoadingMsgViewId:
                    setViewData(adp,getTxtLoadingMsgView(),data,joinData.formatString,joinData.data);
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
                case ProgressBar1Id:
                    setViewData(adp,getProgressBar1(),data,"",path);
                    break;
                case TxtLoadingMsgViewId:
                    setViewData(adp,getTxtLoadingMsgView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
