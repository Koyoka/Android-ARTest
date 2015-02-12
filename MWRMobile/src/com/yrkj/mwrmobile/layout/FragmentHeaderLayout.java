package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class FragmentHeaderLayout extends BaseLayout{

    public static final int BtnActionBarBackId = R.id.btnActionBarBack;
    public static final int TxtActionBarTitleId = R.id.txtActionBarTitle;
    public static final int BtnActionOpsId = R.id.btnActionOps;

    protected android.widget.Button mBtnActionBarBack;
    protected android.widget.TextView mTxtActionBarTitle;
    protected android.widget.Button mBtnActionOps;

    protected Activity mCurActy;

    public FragmentHeaderLayout(Activity acty){
        mCurActy = acty;
        mBtnActionBarBack = (android.widget.Button) acty.findViewById(BtnActionBarBackId);
        mTxtActionBarTitle = (android.widget.TextView) acty.findViewById(TxtActionBarTitleId);
        mBtnActionOps = (android.widget.Button) acty.findViewById(BtnActionOpsId);
    }   

    public FragmentHeaderLayout(android.view.View acty){
        mBtnActionBarBack = (android.widget.Button) acty.findViewById(BtnActionBarBackId);
        mTxtActionBarTitle = (android.widget.TextView) acty.findViewById(TxtActionBarTitleId);
        mBtnActionOps = (android.widget.Button) acty.findViewById(BtnActionOpsId);
    }   
    public android.widget.Button getBtnActionBarBack() {
        return mBtnActionBarBack;
    }
    public android.widget.TextView getTxtActionBarTitle() {
        return mTxtActionBarTitle;
    }
    public android.widget.Button getBtnActionOps() {
        return mBtnActionOps;
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
                case BtnActionBarBackId:
                    setViewData(adp,getBtnActionBarBack(),data,joinData.formatString,joinData.data);
                    break;
                case TxtActionBarTitleId:
                    setViewData(adp,getTxtActionBarTitle(),data,joinData.formatString,joinData.data);
                    break;
                case BtnActionOpsId:
                    setViewData(adp,getBtnActionOps(),data,joinData.formatString,joinData.data);
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
                case BtnActionBarBackId:
                    setViewData(adp,getBtnActionBarBack(),data,"",path);
                    break;
                case TxtActionBarTitleId:
                    setViewData(adp,getTxtActionBarTitle(),data,"",path);
                    break;
                case BtnActionOpsId:
                    setViewData(adp,getBtnActionOps(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
