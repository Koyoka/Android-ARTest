package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAttachment extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int BtnDiseaseViewId = R.id.btnDiseaseView;
    public static final int BtnCameraViewId = R.id.btnCameraView;
    public static final int BtnSoundViewId = R.id.btnSoundView;
    public static final int LayoutImgListId = R.id.layoutImgList;
    public static final int LayoutSoundListId = R.id.layoutSoundList;
    public static final int ProgressBar1Id = R.id.progressBar1;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.Button mBtnDiseaseView;
    protected android.widget.ImageView mBtnCameraView;
    protected com.yrkj.elderlycareassess.widget.UIRecordButton mBtnSoundView;
    protected android.widget.LinearLayout mLayoutImgList;
    protected android.widget.LinearLayout mLayoutSoundList;
    protected android.widget.ProgressBar mProgressBar1;

    protected Activity mCurActy;

    public FragmentAttachment(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mBtnDiseaseView = (android.widget.Button) acty.findViewById(BtnDiseaseViewId);
        mBtnCameraView = (android.widget.ImageView) acty.findViewById(BtnCameraViewId);
        mBtnSoundView = (com.yrkj.elderlycareassess.widget.UIRecordButton) acty.findViewById(BtnSoundViewId);
        mLayoutImgList = (android.widget.LinearLayout) acty.findViewById(LayoutImgListId);
        mLayoutSoundList = (android.widget.LinearLayout) acty.findViewById(LayoutSoundListId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
    }   

    public FragmentAttachment(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mBtnDiseaseView = (android.widget.Button) acty.findViewById(BtnDiseaseViewId);
        mBtnCameraView = (android.widget.ImageView) acty.findViewById(BtnCameraViewId);
        mBtnSoundView = (com.yrkj.elderlycareassess.widget.UIRecordButton) acty.findViewById(BtnSoundViewId);
        mLayoutImgList = (android.widget.LinearLayout) acty.findViewById(LayoutImgListId);
        mLayoutSoundList = (android.widget.LinearLayout) acty.findViewById(LayoutSoundListId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.Button getBtnDiseaseView() {
        return mBtnDiseaseView;
    }
    public android.widget.ImageView getBtnCameraView() {
        return mBtnCameraView;
    }
    public com.yrkj.elderlycareassess.widget.UIRecordButton getBtnSoundView() {
        return mBtnSoundView;
    }
    public android.widget.LinearLayout getLayoutImgList() {
        return mLayoutImgList;
    }
    public android.widget.LinearLayout getLayoutSoundList() {
        return mLayoutSoundList;
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,joinData.formatString,joinData.data);
                    break;
                case BtnDiseaseViewId:
                    setViewData(adp,getBtnDiseaseView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnCameraViewId:
                    setViewData(adp,getBtnCameraView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSoundViewId:
                    setViewData(adp,getBtnSoundView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutImgListId:
                    setViewData(adp,getLayoutImgList(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutSoundListId:
                    setViewData(adp,getLayoutSoundList(),data,joinData.formatString,joinData.data);
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
                case ContainerId:
                    setViewData(adp,getContainer(),data,"",path);
                    break;
                case BtnDiseaseViewId:
                    setViewData(adp,getBtnDiseaseView(),data,"",path);
                    break;
                case BtnCameraViewId:
                    setViewData(adp,getBtnCameraView(),data,"",path);
                    break;
                case BtnSoundViewId:
                    setViewData(adp,getBtnSoundView(),data,"",path);
                    break;
                case LayoutImgListId:
                    setViewData(adp,getLayoutImgList(),data,"",path);
                    break;
                case LayoutSoundListId:
                    setViewData(adp,getLayoutSoundList(),data,"",path);
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
