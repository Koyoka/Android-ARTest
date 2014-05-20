package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class LayoutDisease extends BaseLayout{

    public static final int BtnDiseaseViewId = R.id.btnDiseaseView;
    public static final int BtnSaveDiseaseInfoViewId = R.id.btnSaveDiseaseInfoView;
    public static final int TxtDiseaseListViewId = R.id.txtDiseaseListView;
    public static final int BtnDiseaseDateViewId = R.id.btnDiseaseDateView;
    public static final int RdoIsMedicationViewId = R.id.rdoIsMedicationView;
    public static final int RdoMedicationYViewId = R.id.rdoMedicationYView;
    public static final int RdoMedicationNViewId = R.id.rdoMedicationNView;
    public static final int TxtDiseaseDescViewId = R.id.txtDiseaseDescView;

    protected android.widget.Button mBtnDiseaseView;
    protected android.widget.Button mBtnSaveDiseaseInfoView;
    protected android.widget.TextView mTxtDiseaseListView;
    protected android.widget.Button mBtnDiseaseDateView;
    protected android.widget.RadioGroup mRdoIsMedicationView;
    protected android.widget.RadioButton mRdoMedicationYView;
    protected android.widget.RadioButton mRdoMedicationNView;
    protected android.widget.EditText mTxtDiseaseDescView;

    protected Activity mCurActy;

    public LayoutDisease(Activity acty){
        mCurActy = acty;
        mBtnDiseaseView = (android.widget.Button) acty.findViewById(BtnDiseaseViewId);
        mBtnSaveDiseaseInfoView = (android.widget.Button) acty.findViewById(BtnSaveDiseaseInfoViewId);
        mTxtDiseaseListView = (android.widget.TextView) acty.findViewById(TxtDiseaseListViewId);
        mBtnDiseaseDateView = (android.widget.Button) acty.findViewById(BtnDiseaseDateViewId);
        mRdoIsMedicationView = (android.widget.RadioGroup) acty.findViewById(RdoIsMedicationViewId);
        mRdoMedicationYView = (android.widget.RadioButton) acty.findViewById(RdoMedicationYViewId);
        mRdoMedicationNView = (android.widget.RadioButton) acty.findViewById(RdoMedicationNViewId);
        mTxtDiseaseDescView = (android.widget.EditText) acty.findViewById(TxtDiseaseDescViewId);
    }   

    public LayoutDisease(android.view.View acty){
        mBtnDiseaseView = (android.widget.Button) acty.findViewById(BtnDiseaseViewId);
        mBtnSaveDiseaseInfoView = (android.widget.Button) acty.findViewById(BtnSaveDiseaseInfoViewId);
        mTxtDiseaseListView = (android.widget.TextView) acty.findViewById(TxtDiseaseListViewId);
        mBtnDiseaseDateView = (android.widget.Button) acty.findViewById(BtnDiseaseDateViewId);
        mRdoIsMedicationView = (android.widget.RadioGroup) acty.findViewById(RdoIsMedicationViewId);
        mRdoMedicationYView = (android.widget.RadioButton) acty.findViewById(RdoMedicationYViewId);
        mRdoMedicationNView = (android.widget.RadioButton) acty.findViewById(RdoMedicationNViewId);
        mTxtDiseaseDescView = (android.widget.EditText) acty.findViewById(TxtDiseaseDescViewId);
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
                    
                default:
                    break;
                }
            }
         }
    }
}
