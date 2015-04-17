package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class FragmentEditHost extends BaseLayout{

    public static final int TxtEditViewId = R.id.txtEditView;
    public static final int BtnConfirmViewId = R.id.btnConfirmView;

    protected android.widget.EditText mTxtEditView;
    protected android.widget.Button mBtnConfirmView;

    protected Activity mCurActy;

    public FragmentEditHost(Activity acty){
        mCurActy = acty;
        mTxtEditView = (android.widget.EditText) acty.findViewById(TxtEditViewId);
        mBtnConfirmView = (android.widget.Button) acty.findViewById(BtnConfirmViewId);
    }   

    public FragmentEditHost(android.view.View acty){
        mTxtEditView = (android.widget.EditText) acty.findViewById(TxtEditViewId);
        mBtnConfirmView = (android.widget.Button) acty.findViewById(BtnConfirmViewId);
    }   
    public android.widget.EditText getTxtEditView() {
        return mTxtEditView;
    }
    public android.widget.Button getBtnConfirmView() {
        return mBtnConfirmView;
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
                case TxtEditViewId:
                    setViewData(adp,getTxtEditView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnConfirmViewId:
                    setViewData(adp,getBtnConfirmView(),data,joinData.formatString,joinData.data);
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
                case TxtEditViewId:
                    setViewData(adp,getTxtEditView(),data,"",path);
                    break;
                case BtnConfirmViewId:
                    setViewData(adp,getBtnConfirmView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
