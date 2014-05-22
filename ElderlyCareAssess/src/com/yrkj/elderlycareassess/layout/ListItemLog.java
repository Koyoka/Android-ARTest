package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ListItemLog extends BaseLayout{

    public static final int TxtLogTitleViewId = R.id.txtLogTitleView;
    public static final int TxtLogDescViewId = R.id.txtLogDescView;

    protected android.widget.TextView mTxtLogTitleView;
    protected android.widget.TextView mTxtLogDescView;

    protected Activity mCurActy;

    public ListItemLog(Activity acty){
        mCurActy = acty;
        mTxtLogTitleView = (android.widget.TextView) acty.findViewById(TxtLogTitleViewId);
        mTxtLogDescView = (android.widget.TextView) acty.findViewById(TxtLogDescViewId);
    }   

    public ListItemLog(android.view.View acty){
        mTxtLogTitleView = (android.widget.TextView) acty.findViewById(TxtLogTitleViewId);
        mTxtLogDescView = (android.widget.TextView) acty.findViewById(TxtLogDescViewId);
    }   
    public android.widget.TextView getTxtLogTitleView() {
        return mTxtLogTitleView;
    }
    public android.widget.TextView getTxtLogDescView() {
        return mTxtLogDescView;
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
                case TxtLogTitleViewId:
                    setViewData(adp,getTxtLogTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtLogDescViewId:
                    setViewData(adp,getTxtLogDescView(),data,joinData.formatString,joinData.data);
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
                case TxtLogTitleViewId:
                    setViewData(adp,getTxtLogTitleView(),data,"",path);
                    break;
                case TxtLogDescViewId:
                    setViewData(adp,getTxtLogDescView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
