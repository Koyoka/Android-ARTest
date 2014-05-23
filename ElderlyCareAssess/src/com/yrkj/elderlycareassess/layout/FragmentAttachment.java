package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAttachment extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int LayoutQuestionBarViewId = R.id.layoutQuestionBarView;
    public static final int TxtQuestionTitleViewId = R.id.txtQuestionTitleView;
    public static final int BtnShowDiseaseContentViewId = R.id.btnShowDiseaseContentView;
    public static final int BtnShowDiseaseViewId = R.id.btnShowDiseaseView;
    public static final int LayoutContentViewId = R.id.layoutContentView;
    public static final int BtnDiseaseViewId = R.id.btnDiseaseView;
    public static final int BtnSaveDiseaseInfoViewId = R.id.btnSaveDiseaseInfoView;
    public static final int TxtDiseaseListViewId = R.id.txtDiseaseListView;
    public static final int BtnDiseaseDateViewId = R.id.btnDiseaseDateView;
    public static final int RdoIsMedicationViewId = R.id.rdoIsMedicationView;
    public static final int RdoMedicationYViewId = R.id.rdoMedicationYView;
    public static final int RdoMedicationNViewId = R.id.rdoMedicationNView;
    public static final int TxtDiseaseDescViewId = R.id.txtDiseaseDescView;
    public static final int BtnCameraViewId = R.id.btnCameraView;
    public static final int LayoutImgListId = R.id.layoutImgList;
    public static final int BtnSoundViewId = R.id.btnSoundView;
    public static final int LayoutSoundListId = R.id.layoutSoundList;
    public static final int ProgressBar1Id = R.id.progressBar1;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.RelativeLayout mLayoutQuestionBarView;
    protected android.widget.TextView mTxtQuestionTitleView;
    protected android.widget.RelativeLayout mBtnShowDiseaseContentView;
    protected android.widget.CheckBox mBtnShowDiseaseView;
    protected android.widget.LinearLayout mLayoutContentView;
    protected android.widget.Button mBtnDiseaseView;
    protected android.widget.Button mBtnSaveDiseaseInfoView;
    protected android.widget.TextView mTxtDiseaseListView;
    protected android.widget.Button mBtnDiseaseDateView;
    protected android.widget.RadioGroup mRdoIsMedicationView;
    protected android.widget.RadioButton mRdoMedicationYView;
    protected android.widget.RadioButton mRdoMedicationNView;
    protected android.widget.EditText mTxtDiseaseDescView;
    protected android.widget.ImageView mBtnCameraView;
    protected android.widget.LinearLayout mLayoutImgList;
    protected android.widget.Button mBtnSoundView;
    protected android.widget.LinearLayout mLayoutSoundList;
    protected android.widget.ProgressBar mProgressBar1;

    protected Activity mCurActy;

    public FragmentAttachment(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mLayoutQuestionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBarViewId);
        mTxtQuestionTitleView = (android.widget.TextView) acty.findViewById(TxtQuestionTitleViewId);
        mBtnShowDiseaseContentView = (android.widget.RelativeLayout) acty.findViewById(BtnShowDiseaseContentViewId);
        mBtnShowDiseaseView = (android.widget.CheckBox) acty.findViewById(BtnShowDiseaseViewId);
        mLayoutContentView = (android.widget.LinearLayout) acty.findViewById(LayoutContentViewId);
        mBtnDiseaseView = (android.widget.Button) acty.findViewById(BtnDiseaseViewId);
        mBtnSaveDiseaseInfoView = (android.widget.Button) acty.findViewById(BtnSaveDiseaseInfoViewId);
        mTxtDiseaseListView = (android.widget.TextView) acty.findViewById(TxtDiseaseListViewId);
        mBtnDiseaseDateView = (android.widget.Button) acty.findViewById(BtnDiseaseDateViewId);
        mRdoIsMedicationView = (android.widget.RadioGroup) acty.findViewById(RdoIsMedicationViewId);
        mRdoMedicationYView = (android.widget.RadioButton) acty.findViewById(RdoMedicationYViewId);
        mRdoMedicationNView = (android.widget.RadioButton) acty.findViewById(RdoMedicationNViewId);
        mTxtDiseaseDescView = (android.widget.EditText) acty.findViewById(TxtDiseaseDescViewId);
        mBtnCameraView = (android.widget.ImageView) acty.findViewById(BtnCameraViewId);
        mLayoutImgList = (android.widget.LinearLayout) acty.findViewById(LayoutImgListId);
        mBtnSoundView = (android.widget.Button) acty.findViewById(BtnSoundViewId);
        mLayoutSoundList = (android.widget.LinearLayout) acty.findViewById(LayoutSoundListId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
    }   

    public FragmentAttachment(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mLayoutQuestionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBarViewId);
        mTxtQuestionTitleView = (android.widget.TextView) acty.findViewById(TxtQuestionTitleViewId);
        mBtnShowDiseaseContentView = (android.widget.RelativeLayout) acty.findViewById(BtnShowDiseaseContentViewId);
        mBtnShowDiseaseView = (android.widget.CheckBox) acty.findViewById(BtnShowDiseaseViewId);
        mLayoutContentView = (android.widget.LinearLayout) acty.findViewById(LayoutContentViewId);
        mBtnDiseaseView = (android.widget.Button) acty.findViewById(BtnDiseaseViewId);
        mBtnSaveDiseaseInfoView = (android.widget.Button) acty.findViewById(BtnSaveDiseaseInfoViewId);
        mTxtDiseaseListView = (android.widget.TextView) acty.findViewById(TxtDiseaseListViewId);
        mBtnDiseaseDateView = (android.widget.Button) acty.findViewById(BtnDiseaseDateViewId);
        mRdoIsMedicationView = (android.widget.RadioGroup) acty.findViewById(RdoIsMedicationViewId);
        mRdoMedicationYView = (android.widget.RadioButton) acty.findViewById(RdoMedicationYViewId);
        mRdoMedicationNView = (android.widget.RadioButton) acty.findViewById(RdoMedicationNViewId);
        mTxtDiseaseDescView = (android.widget.EditText) acty.findViewById(TxtDiseaseDescViewId);
        mBtnCameraView = (android.widget.ImageView) acty.findViewById(BtnCameraViewId);
        mLayoutImgList = (android.widget.LinearLayout) acty.findViewById(LayoutImgListId);
        mBtnSoundView = (android.widget.Button) acty.findViewById(BtnSoundViewId);
        mLayoutSoundList = (android.widget.LinearLayout) acty.findViewById(LayoutSoundListId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.RelativeLayout getLayoutQuestionBarView() {
        return mLayoutQuestionBarView;
    }
    public android.widget.TextView getTxtQuestionTitleView() {
        return mTxtQuestionTitleView;
    }
    public android.widget.RelativeLayout getBtnShowDiseaseContentView() {
        return mBtnShowDiseaseContentView;
    }
    public android.widget.CheckBox getBtnShowDiseaseView() {
        return mBtnShowDiseaseView;
    }
    public android.widget.LinearLayout getLayoutContentView() {
        return mLayoutContentView;
    }
    public android.widget.Button getBtnDiseaseView() {
        return mBtnDiseaseView;
    }
    public android.widget.Button getBtnSaveDiseaseInfoView() {
        return mBtnSaveDiseaseInfoView;
    }
    public android.widget.TextView getTxtDiseaseListView() {
        return mTxtDiseaseListView;
    }
    public android.widget.Button getBtnDiseaseDateView() {
        return mBtnDiseaseDateView;
    }
    public android.widget.RadioGroup getRdoIsMedicationView() {
        return mRdoIsMedicationView;
    }
    public android.widget.RadioButton getRdoMedicationYView() {
        return mRdoMedicationYView;
    }
    public android.widget.RadioButton getRdoMedicationNView() {
        return mRdoMedicationNView;
    }
    public android.widget.EditText getTxtDiseaseDescView() {
        return mTxtDiseaseDescView;
    }
    public android.widget.ImageView getBtnCameraView() {
        return mBtnCameraView;
    }
    public android.widget.LinearLayout getLayoutImgList() {
        return mLayoutImgList;
    }
    public android.widget.Button getBtnSoundView() {
        return mBtnSoundView;
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
                case LayoutQuestionBarViewId:
                    setViewData(adp,getLayoutQuestionBarView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtQuestionTitleViewId:
                    setViewData(adp,getTxtQuestionTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnShowDiseaseContentViewId:
                    setViewData(adp,getBtnShowDiseaseContentView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnShowDiseaseViewId:
                    setViewData(adp,getBtnShowDiseaseView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutContentViewId:
                    setViewData(adp,getLayoutContentView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnDiseaseViewId:
                    setViewData(adp,getBtnDiseaseView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSaveDiseaseInfoViewId:
                    setViewData(adp,getBtnSaveDiseaseInfoView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDiseaseListViewId:
                    setViewData(adp,getTxtDiseaseListView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnDiseaseDateViewId:
                    setViewData(adp,getBtnDiseaseDateView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoIsMedicationViewId:
                    setViewData(adp,getRdoIsMedicationView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoMedicationYViewId:
                    setViewData(adp,getRdoMedicationYView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoMedicationNViewId:
                    setViewData(adp,getRdoMedicationNView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDiseaseDescViewId:
                    setViewData(adp,getTxtDiseaseDescView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnCameraViewId:
                    setViewData(adp,getBtnCameraView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutImgListId:
                    setViewData(adp,getLayoutImgList(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSoundViewId:
                    setViewData(adp,getBtnSoundView(),data,joinData.formatString,joinData.data);
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
                case LayoutQuestionBarViewId:
                    setViewData(adp,getLayoutQuestionBarView(),data,"",path);
                    break;
                case TxtQuestionTitleViewId:
                    setViewData(adp,getTxtQuestionTitleView(),data,"",path);
                    break;
                case BtnShowDiseaseContentViewId:
                    setViewData(adp,getBtnShowDiseaseContentView(),data,"",path);
                    break;
                case BtnShowDiseaseViewId:
                    setViewData(adp,getBtnShowDiseaseView(),data,"",path);
                    break;
                case LayoutContentViewId:
                    setViewData(adp,getLayoutContentView(),data,"",path);
                    break;
                case BtnDiseaseViewId:
                    setViewData(adp,getBtnDiseaseView(),data,"",path);
                    break;
                case BtnSaveDiseaseInfoViewId:
                    setViewData(adp,getBtnSaveDiseaseInfoView(),data,"",path);
                    break;
                case TxtDiseaseListViewId:
                    setViewData(adp,getTxtDiseaseListView(),data,"",path);
                    break;
                case BtnDiseaseDateViewId:
                    setViewData(adp,getBtnDiseaseDateView(),data,"",path);
                    break;
                case RdoIsMedicationViewId:
                    setViewData(adp,getRdoIsMedicationView(),data,"",path);
                    break;
                case RdoMedicationYViewId:
                    setViewData(adp,getRdoMedicationYView(),data,"",path);
                    break;
                case RdoMedicationNViewId:
                    setViewData(adp,getRdoMedicationNView(),data,"",path);
                    break;
                case TxtDiseaseDescViewId:
                    setViewData(adp,getTxtDiseaseDescView(),data,"",path);
                    break;
                case BtnCameraViewId:
                    setViewData(adp,getBtnCameraView(),data,"",path);
                    break;
                case LayoutImgListId:
                    setViewData(adp,getLayoutImgList(),data,"",path);
                    break;
                case BtnSoundViewId:
                    setViewData(adp,getBtnSoundView(),data,"",path);
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
