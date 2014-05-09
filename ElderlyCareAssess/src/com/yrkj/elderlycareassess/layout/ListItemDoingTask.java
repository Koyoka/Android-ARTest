package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ListItemDoingTask extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int TxtTaskNumViewId = R.id.txtTaskNumView;
    public static final int TxtTaskStateViewId = R.id.txtTaskStateView;
    public static final int TxtTaskLastDoDateViewId = R.id.txtTaskLastDoDateView;
    public static final int TxtTaskLastDoDateLibViewId = R.id.txtTaskLastDoDateLibView;
    public static final int TxtTaskEndDateViewId = R.id.txtTaskEndDateView;
    public static final int TxtTaskEndDateLabViewId = R.id.txtTaskEndDateLabView;
    public static final int VLineId = R.id.vLine;
    public static final int TxtTaskUserNameViewId = R.id.txtTaskUserNameView;
    public static final int TxtTaskSexViewId = R.id.txtTaskSexView;
    public static final int ImgTaskUserNameIconViewId = R.id.imgTaskUserNameIconView;
    public static final int TxtTaskPhoneNumViewId = R.id.txtTaskPhoneNumView;
    public static final int ImgTaskPhoneIconViewId = R.id.imgTaskPhoneIconView;
    public static final int TxtTaskAddressViewId = R.id.txtTaskAddressView;
    public static final int ImgTaskAddressIconViewId = R.id.imgTaskAddressIconView;
    public static final int BtnTaskMapViewId = R.id.btnTaskMapView;

    protected android.widget.RelativeLayout mContainer;
    protected android.widget.TextView mTxtTaskNumView;
    protected android.widget.TextView mTxtTaskStateView;
    protected android.widget.TextView mTxtTaskLastDoDateView;
    protected android.widget.TextView mTxtTaskLastDoDateLibView;
    protected android.widget.TextView mTxtTaskEndDateView;
    protected android.widget.TextView mTxtTaskEndDateLabView;
    protected android.view.View mVLine;
    protected android.widget.TextView mTxtTaskUserNameView;
    protected android.widget.TextView mTxtTaskSexView;
    protected android.widget.ImageView mImgTaskUserNameIconView;
    protected android.widget.TextView mTxtTaskPhoneNumView;
    protected android.widget.ImageView mImgTaskPhoneIconView;
    protected android.widget.TextView mTxtTaskAddressView;
    protected android.widget.ImageView mImgTaskAddressIconView;
    protected android.widget.Button mBtnTaskMapView;

    protected Activity mCurActy;

    public ListItemDoingTask(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
        mTxtTaskNumView = (android.widget.TextView) acty.findViewById(TxtTaskNumViewId);
        mTxtTaskStateView = (android.widget.TextView) acty.findViewById(TxtTaskStateViewId);
        mTxtTaskLastDoDateView = (android.widget.TextView) acty.findViewById(TxtTaskLastDoDateViewId);
        mTxtTaskLastDoDateLibView = (android.widget.TextView) acty.findViewById(TxtTaskLastDoDateLibViewId);
        mTxtTaskEndDateView = (android.widget.TextView) acty.findViewById(TxtTaskEndDateViewId);
        mTxtTaskEndDateLabView = (android.widget.TextView) acty.findViewById(TxtTaskEndDateLabViewId);
        mVLine = (android.view.View) acty.findViewById(VLineId);
        mTxtTaskUserNameView = (android.widget.TextView) acty.findViewById(TxtTaskUserNameViewId);
        mTxtTaskSexView = (android.widget.TextView) acty.findViewById(TxtTaskSexViewId);
        mImgTaskUserNameIconView = (android.widget.ImageView) acty.findViewById(ImgTaskUserNameIconViewId);
        mTxtTaskPhoneNumView = (android.widget.TextView) acty.findViewById(TxtTaskPhoneNumViewId);
        mImgTaskPhoneIconView = (android.widget.ImageView) acty.findViewById(ImgTaskPhoneIconViewId);
        mTxtTaskAddressView = (android.widget.TextView) acty.findViewById(TxtTaskAddressViewId);
        mImgTaskAddressIconView = (android.widget.ImageView) acty.findViewById(ImgTaskAddressIconViewId);
        mBtnTaskMapView = (android.widget.Button) acty.findViewById(BtnTaskMapViewId);
    }   

    public ListItemDoingTask(android.view.View acty){
        mContainer = (android.widget.RelativeLayout) acty.findViewById(ContainerId);
        mTxtTaskNumView = (android.widget.TextView) acty.findViewById(TxtTaskNumViewId);
        mTxtTaskStateView = (android.widget.TextView) acty.findViewById(TxtTaskStateViewId);
        mTxtTaskLastDoDateView = (android.widget.TextView) acty.findViewById(TxtTaskLastDoDateViewId);
        mTxtTaskLastDoDateLibView = (android.widget.TextView) acty.findViewById(TxtTaskLastDoDateLibViewId);
        mTxtTaskEndDateView = (android.widget.TextView) acty.findViewById(TxtTaskEndDateViewId);
        mTxtTaskEndDateLabView = (android.widget.TextView) acty.findViewById(TxtTaskEndDateLabViewId);
        mVLine = (android.view.View) acty.findViewById(VLineId);
        mTxtTaskUserNameView = (android.widget.TextView) acty.findViewById(TxtTaskUserNameViewId);
        mTxtTaskSexView = (android.widget.TextView) acty.findViewById(TxtTaskSexViewId);
        mImgTaskUserNameIconView = (android.widget.ImageView) acty.findViewById(ImgTaskUserNameIconViewId);
        mTxtTaskPhoneNumView = (android.widget.TextView) acty.findViewById(TxtTaskPhoneNumViewId);
        mImgTaskPhoneIconView = (android.widget.ImageView) acty.findViewById(ImgTaskPhoneIconViewId);
        mTxtTaskAddressView = (android.widget.TextView) acty.findViewById(TxtTaskAddressViewId);
        mImgTaskAddressIconView = (android.widget.ImageView) acty.findViewById(ImgTaskAddressIconViewId);
        mBtnTaskMapView = (android.widget.Button) acty.findViewById(BtnTaskMapViewId);
    }   
    public android.widget.RelativeLayout getContainer() {
        return mContainer;
    }
    public android.widget.TextView getTxtTaskNumView() {
        return mTxtTaskNumView;
    }
    public android.widget.TextView getTxtTaskStateView() {
        return mTxtTaskStateView;
    }
    public android.widget.TextView getTxtTaskLastDoDateView() {
        return mTxtTaskLastDoDateView;
    }
    public android.widget.TextView getTxtTaskLastDoDateLibView() {
        return mTxtTaskLastDoDateLibView;
    }
    public android.widget.TextView getTxtTaskEndDateView() {
        return mTxtTaskEndDateView;
    }
    public android.widget.TextView getTxtTaskEndDateLabView() {
        return mTxtTaskEndDateLabView;
    }
    public android.view.View getVLine() {
        return mVLine;
    }
    public android.widget.TextView getTxtTaskUserNameView() {
        return mTxtTaskUserNameView;
    }
    public android.widget.TextView getTxtTaskSexView() {
        return mTxtTaskSexView;
    }
    public android.widget.ImageView getImgTaskUserNameIconView() {
        return mImgTaskUserNameIconView;
    }
    public android.widget.TextView getTxtTaskPhoneNumView() {
        return mTxtTaskPhoneNumView;
    }
    public android.widget.ImageView getImgTaskPhoneIconView() {
        return mImgTaskPhoneIconView;
    }
    public android.widget.TextView getTxtTaskAddressView() {
        return mTxtTaskAddressView;
    }
    public android.widget.ImageView getImgTaskAddressIconView() {
        return mImgTaskAddressIconView;
    }
    public android.widget.Button getBtnTaskMapView() {
        return mBtnTaskMapView;
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
                case TxtTaskNumViewId:
                    setViewData(adp,getTxtTaskNumView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskStateViewId:
                    setViewData(adp,getTxtTaskStateView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskLastDoDateViewId:
                    setViewData(adp,getTxtTaskLastDoDateView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskLastDoDateLibViewId:
                    setViewData(adp,getTxtTaskLastDoDateLibView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskEndDateViewId:
                    setViewData(adp,getTxtTaskEndDateView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskEndDateLabViewId:
                    setViewData(adp,getTxtTaskEndDateLabView(),data,joinData.formatString,joinData.data);
                    break;
                case VLineId:
                    setViewData(adp,getVLine(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskUserNameViewId:
                    setViewData(adp,getTxtTaskUserNameView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskSexViewId:
                    setViewData(adp,getTxtTaskSexView(),data,joinData.formatString,joinData.data);
                    break;
                case ImgTaskUserNameIconViewId:
                    setViewData(adp,getImgTaskUserNameIconView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskPhoneNumViewId:
                    setViewData(adp,getTxtTaskPhoneNumView(),data,joinData.formatString,joinData.data);
                    break;
                case ImgTaskPhoneIconViewId:
                    setViewData(adp,getImgTaskPhoneIconView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskAddressViewId:
                    setViewData(adp,getTxtTaskAddressView(),data,joinData.formatString,joinData.data);
                    break;
                case ImgTaskAddressIconViewId:
                    setViewData(adp,getImgTaskAddressIconView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnTaskMapViewId:
                    setViewData(adp,getBtnTaskMapView(),data,joinData.formatString,joinData.data);
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
                case TxtTaskNumViewId:
                    setViewData(adp,getTxtTaskNumView(),data,"",path);
                    break;
                case TxtTaskStateViewId:
                    setViewData(adp,getTxtTaskStateView(),data,"",path);
                    break;
                case TxtTaskLastDoDateViewId:
                    setViewData(adp,getTxtTaskLastDoDateView(),data,"",path);
                    break;
                case TxtTaskLastDoDateLibViewId:
                    setViewData(adp,getTxtTaskLastDoDateLibView(),data,"",path);
                    break;
                case TxtTaskEndDateViewId:
                    setViewData(adp,getTxtTaskEndDateView(),data,"",path);
                    break;
                case TxtTaskEndDateLabViewId:
                    setViewData(adp,getTxtTaskEndDateLabView(),data,"",path);
                    break;
                case VLineId:
                    setViewData(adp,getVLine(),data,"",path);
                    break;
                case TxtTaskUserNameViewId:
                    setViewData(adp,getTxtTaskUserNameView(),data,"",path);
                    break;
                case TxtTaskSexViewId:
                    setViewData(adp,getTxtTaskSexView(),data,"",path);
                    break;
                case ImgTaskUserNameIconViewId:
                    setViewData(adp,getImgTaskUserNameIconView(),data,"",path);
                    break;
                case TxtTaskPhoneNumViewId:
                    setViewData(adp,getTxtTaskPhoneNumView(),data,"",path);
                    break;
                case ImgTaskPhoneIconViewId:
                    setViewData(adp,getImgTaskPhoneIconView(),data,"",path);
                    break;
                case TxtTaskAddressViewId:
                    setViewData(adp,getTxtTaskAddressView(),data,"",path);
                    break;
                case ImgTaskAddressIconViewId:
                    setViewData(adp,getImgTaskAddressIconView(),data,"",path);
                    break;
                case BtnTaskMapViewId:
                    setViewData(adp,getBtnTaskMapView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
