package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentSyncItem extends BaseLayout{

    public static final int TxtTestDescViewId = R.id.txtTestDescView;
    public static final int ProgressBar1Id = R.id.progressBar1;

    protected android.widget.TextView mTxtTestDescView;
    protected android.widget.ProgressBar mProgressBar1;

    protected Activity mCurActy;

    public FragmentSyncItem(Activity acty){
        mCurActy = acty;
        mTxtTestDescView = (android.widget.TextView) acty.findViewById(TxtTestDescViewId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
    }   

    public FragmentSyncItem(android.view.View acty){
        mTxtTestDescView = (android.widget.TextView) acty.findViewById(TxtTestDescViewId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
    }   
    public android.widget.TextView getTxtTestDescView() {
        return mTxtTestDescView;
    }
    public android.widget.ProgressBar getProgressBar1() {
        return mProgressBar1;
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
                case TxtTestDescViewId:
                    setViewData(adp,getTxtTestDescView(),data,joinData.formatString,joinData.data);
                    break;
                case ProgressBar1Id:
                    setViewData(adp,getProgressBar1(),data,joinData.formatString,joinData.data);
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
                case TxtTestDescViewId:
                    setViewData(adp,getTxtTestDescView(),data,"",path);
                    break;
                case ProgressBar1Id:
                    setViewData(adp,getProgressBar1(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
