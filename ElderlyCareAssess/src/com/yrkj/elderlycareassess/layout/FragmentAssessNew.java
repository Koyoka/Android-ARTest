package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessNew extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int TxtAssessCustViewId = R.id.txtAssessCustView;
    public static final int TxtAssessAddressViewId = R.id.txtAssessAddressView;
    public static final int TxtAssessNumViewId = R.id.txtAssessNumView;
    public static final int TxtAssessTypeViewId = R.id.txtAssessTypeView;
    public static final int TxtAssessCountViewId = R.id.txtAssessCountView;
    public static final int TxtAssessLastDateViewId = R.id.txtAssessLastDateView;
    public static final int TxtAssessEndDateViewId = R.id.txtAssessEndDateView;
    public static final int TxtEmployNameViewId = R.id.txtEmployNameView;
    public static final int BtnStratViewId = R.id.btnStratView;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.TextView mTxtAssessCustView;
    protected android.widget.TextView mTxtAssessAddressView;
    protected android.widget.TextView mTxtAssessNumView;
    protected android.widget.TextView mTxtAssessTypeView;
    protected android.widget.TextView mTxtAssessCountView;
    protected android.widget.TextView mTxtAssessLastDateView;
    protected android.widget.TextView mTxtAssessEndDateView;
    protected android.widget.TextView mTxtEmployNameView;
    protected android.widget.Button mBtnStratView;

    protected Activity mCurActy;

    public FragmentAssessNew(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mTxtAssessCustView = (android.widget.TextView) acty.findViewById(TxtAssessCustViewId);
        mTxtAssessAddressView = (android.widget.TextView) acty.findViewById(TxtAssessAddressViewId);
        mTxtAssessNumView = (android.widget.TextView) acty.findViewById(TxtAssessNumViewId);
        mTxtAssessTypeView = (android.widget.TextView) acty.findViewById(TxtAssessTypeViewId);
        mTxtAssessCountView = (android.widget.TextView) acty.findViewById(TxtAssessCountViewId);
        mTxtAssessLastDateView = (android.widget.TextView) acty.findViewById(TxtAssessLastDateViewId);
        mTxtAssessEndDateView = (android.widget.TextView) acty.findViewById(TxtAssessEndDateViewId);
        mTxtEmployNameView = (android.widget.TextView) acty.findViewById(TxtEmployNameViewId);
        mBtnStratView = (android.widget.Button) acty.findViewById(BtnStratViewId);
    }   

    public FragmentAssessNew(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mTxtAssessCustView = (android.widget.TextView) acty.findViewById(TxtAssessCustViewId);
        mTxtAssessAddressView = (android.widget.TextView) acty.findViewById(TxtAssessAddressViewId);
        mTxtAssessNumView = (android.widget.TextView) acty.findViewById(TxtAssessNumViewId);
        mTxtAssessTypeView = (android.widget.TextView) acty.findViewById(TxtAssessTypeViewId);
        mTxtAssessCountView = (android.widget.TextView) acty.findViewById(TxtAssessCountViewId);
        mTxtAssessLastDateView = (android.widget.TextView) acty.findViewById(TxtAssessLastDateViewId);
        mTxtAssessEndDateView = (android.widget.TextView) acty.findViewById(TxtAssessEndDateViewId);
        mTxtEmployNameView = (android.widget.TextView) acty.findViewById(TxtEmployNameViewId);
        mBtnStratView = (android.widget.Button) acty.findViewById(BtnStratViewId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.TextView getTxtAssessCustView() {
        return mTxtAssessCustView;
    }
    public android.widget.TextView getTxtAssessAddressView() {
        return mTxtAssessAddressView;
    }
    public android.widget.TextView getTxtAssessNumView() {
        return mTxtAssessNumView;
    }
    public android.widget.TextView getTxtAssessTypeView() {
        return mTxtAssessTypeView;
    }
    public android.widget.TextView getTxtAssessCountView() {
        return mTxtAssessCountView;
    }
    public android.widget.TextView getTxtAssessLastDateView() {
        return mTxtAssessLastDateView;
    }
    public android.widget.TextView getTxtAssessEndDateView() {
        return mTxtAssessEndDateView;
    }
    public android.widget.TextView getTxtEmployNameView() {
        return mTxtEmployNameView;
    }
    public android.widget.Button getBtnStratView() {
        return mBtnStratView;
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
                case TxtAssessCustViewId:
                    setViewData(adp,getTxtAssessCustView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessAddressViewId:
                    setViewData(adp,getTxtAssessAddressView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessNumViewId:
                    setViewData(adp,getTxtAssessNumView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessTypeViewId:
                    setViewData(adp,getTxtAssessTypeView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessCountViewId:
                    setViewData(adp,getTxtAssessCountView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessLastDateViewId:
                    setViewData(adp,getTxtAssessLastDateView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessEndDateViewId:
                    setViewData(adp,getTxtAssessEndDateView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtEmployNameViewId:
                    setViewData(adp,getTxtEmployNameView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnStratViewId:
                    setViewData(adp,getBtnStratView(),data,joinData.formatString,joinData.data);
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
                case TxtAssessCustViewId:
                    setViewData(adp,getTxtAssessCustView(),data,"",path);
                    break;
                case TxtAssessAddressViewId:
                    setViewData(adp,getTxtAssessAddressView(),data,"",path);
                    break;
                case TxtAssessNumViewId:
                    setViewData(adp,getTxtAssessNumView(),data,"",path);
                    break;
                case TxtAssessTypeViewId:
                    setViewData(adp,getTxtAssessTypeView(),data,"",path);
                    break;
                case TxtAssessCountViewId:
                    setViewData(adp,getTxtAssessCountView(),data,"",path);
                    break;
                case TxtAssessLastDateViewId:
                    setViewData(adp,getTxtAssessLastDateView(),data,"",path);
                    break;
                case TxtAssessEndDateViewId:
                    setViewData(adp,getTxtAssessEndDateView(),data,"",path);
                    break;
                case TxtEmployNameViewId:
                    setViewData(adp,getTxtEmployNameView(),data,"",path);
                    break;
                case BtnStratViewId:
                    setViewData(adp,getBtnStratView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
