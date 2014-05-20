package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityEdit extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int LayoutActionBarViewId = R.id.layoutActionBarView;
    public static final int BtnBackViewId = R.id.btnBackView;
    public static final int TxtOldPwdViewId = R.id.txtOldPwdView;
    public static final int TxtNewPwdViewId = R.id.txtNewPwdView;
    public static final int TxtConfirmPwdViewId = R.id.txtConfirmPwdView;
    public static final int BtnOkViewId = R.id.btnOkView;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.RelativeLayout mLayoutActionBarView;
    protected android.widget.ImageButton mBtnBackView;
    protected android.widget.EditText mTxtOldPwdView;
    protected android.widget.EditText mTxtNewPwdView;
    protected android.widget.EditText mTxtConfirmPwdView;
    protected android.widget.Button mBtnOkView;

    protected Activity mCurActy;

    public ActivityEdit(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnBackView = (android.widget.ImageButton) acty.findViewById(BtnBackViewId);
        mTxtOldPwdView = (android.widget.EditText) acty.findViewById(TxtOldPwdViewId);
        mTxtNewPwdView = (android.widget.EditText) acty.findViewById(TxtNewPwdViewId);
        mTxtConfirmPwdView = (android.widget.EditText) acty.findViewById(TxtConfirmPwdViewId);
        mBtnOkView = (android.widget.Button) acty.findViewById(BtnOkViewId);
    }   

    public ActivityEdit(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mLayoutActionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutActionBarViewId);
        mBtnBackView = (android.widget.ImageButton) acty.findViewById(BtnBackViewId);
        mTxtOldPwdView = (android.widget.EditText) acty.findViewById(TxtOldPwdViewId);
        mTxtNewPwdView = (android.widget.EditText) acty.findViewById(TxtNewPwdViewId);
        mTxtConfirmPwdView = (android.widget.EditText) acty.findViewById(TxtConfirmPwdViewId);
        mBtnOkView = (android.widget.Button) acty.findViewById(BtnOkViewId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.RelativeLayout getLayoutActionBarView() {
        return mLayoutActionBarView;
    }
    public android.widget.ImageButton getBtnBackView() {
        return mBtnBackView;
    }
    public android.widget.EditText getTxtOldPwdView() {
        return mTxtOldPwdView;
    }
    public android.widget.EditText getTxtNewPwdView() {
        return mTxtNewPwdView;
    }
    public android.widget.EditText getTxtConfirmPwdView() {
        return mTxtConfirmPwdView;
    }
    public android.widget.Button getBtnOkView() {
        return mBtnOkView;
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtOldPwdViewId:
                    setViewData(adp,getTxtOldPwdView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtNewPwdViewId:
                    setViewData(adp,getTxtNewPwdView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtConfirmPwdViewId:
                    setViewData(adp,getTxtConfirmPwdView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnOkViewId:
                    setViewData(adp,getBtnOkView(),data,joinData.formatString,joinData.data);
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,"",path);
                    break;
                case LayoutActionBarViewId:
                    setViewData(adp,getLayoutActionBarView(),data,"",path);
                    break;
                case BtnBackViewId:
                    setViewData(adp,getBtnBackView(),data,"",path);
                    break;
                case TxtOldPwdViewId:
                    setViewData(adp,getTxtOldPwdView(),data,"",path);
                    break;
                case TxtNewPwdViewId:
                    setViewData(adp,getTxtNewPwdView(),data,"",path);
                    break;
                case TxtConfirmPwdViewId:
                    setViewData(adp,getTxtConfirmPwdView(),data,"",path);
                    break;
                case BtnOkViewId:
                    setViewData(adp,getBtnOkView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
