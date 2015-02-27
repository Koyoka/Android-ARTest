package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityMain extends BaseLayout{

    public static final int TxtNetStateId = R.id.txtNetState;
    public static final int TxtWSCodeId = R.id.txtWSCode;
    public static final int TxtInspectorId = R.id.txtInspector;
    public static final int TxtDriverId = R.id.txtDriver;
    public static final int TxtCarCodeId = R.id.txtCarCode;
    public static final int TxtTotalCountId = R.id.txtTotalCount;
    public static final int TxtTotalWeightId = R.id.txtTotalWeight;
    public static final int BtnTestId = R.id.btnTest;
    public static final int BtnRecoverCrateId = R.id.btnRecoverCrate;
    public static final int BtnRecoverToInventroyId = R.id.btnRecoverToInventroy;
    public static final int BtnRecoverToDestroyId = R.id.btnRecoverToDestroy;
    public static final int BtnSettingId = R.id.btnSetting;
    public static final int BtnLogoutId = R.id.btnLogout;

    protected android.widget.TextView mTxtNetState;
    protected android.widget.TextView mTxtWSCode;
    protected android.widget.TextView mTxtInspector;
    protected android.widget.TextView mTxtDriver;
    protected android.widget.TextView mTxtCarCode;
    protected android.widget.TextView mTxtTotalCount;
    protected android.widget.TextView mTxtTotalWeight;
    protected android.widget.Button mBtnTest;
    protected android.widget.RelativeLayout mBtnRecoverCrate;
    protected android.widget.RelativeLayout mBtnRecoverToInventroy;
    protected android.widget.RelativeLayout mBtnRecoverToDestroy;
    protected android.widget.RelativeLayout mBtnSetting;
    protected android.widget.RelativeLayout mBtnLogout;

    protected Activity mCurActy;

    public ActivityMain(Activity acty){
        mCurActy = acty;
        mTxtNetState = (android.widget.TextView) acty.findViewById(TxtNetStateId);
        mTxtWSCode = (android.widget.TextView) acty.findViewById(TxtWSCodeId);
        mTxtInspector = (android.widget.TextView) acty.findViewById(TxtInspectorId);
        mTxtDriver = (android.widget.TextView) acty.findViewById(TxtDriverId);
        mTxtCarCode = (android.widget.TextView) acty.findViewById(TxtCarCodeId);
        mTxtTotalCount = (android.widget.TextView) acty.findViewById(TxtTotalCountId);
        mTxtTotalWeight = (android.widget.TextView) acty.findViewById(TxtTotalWeightId);
        mBtnTest = (android.widget.Button) acty.findViewById(BtnTestId);
        mBtnRecoverCrate = (android.widget.RelativeLayout) acty.findViewById(BtnRecoverCrateId);
        mBtnRecoverToInventroy = (android.widget.RelativeLayout) acty.findViewById(BtnRecoverToInventroyId);
        mBtnRecoverToDestroy = (android.widget.RelativeLayout) acty.findViewById(BtnRecoverToDestroyId);
        mBtnSetting = (android.widget.RelativeLayout) acty.findViewById(BtnSettingId);
        mBtnLogout = (android.widget.RelativeLayout) acty.findViewById(BtnLogoutId);
    }   

    public ActivityMain(android.view.View acty){
        mTxtNetState = (android.widget.TextView) acty.findViewById(TxtNetStateId);
        mTxtWSCode = (android.widget.TextView) acty.findViewById(TxtWSCodeId);
        mTxtInspector = (android.widget.TextView) acty.findViewById(TxtInspectorId);
        mTxtDriver = (android.widget.TextView) acty.findViewById(TxtDriverId);
        mTxtCarCode = (android.widget.TextView) acty.findViewById(TxtCarCodeId);
        mTxtTotalCount = (android.widget.TextView) acty.findViewById(TxtTotalCountId);
        mTxtTotalWeight = (android.widget.TextView) acty.findViewById(TxtTotalWeightId);
        mBtnTest = (android.widget.Button) acty.findViewById(BtnTestId);
        mBtnRecoverCrate = (android.widget.RelativeLayout) acty.findViewById(BtnRecoverCrateId);
        mBtnRecoverToInventroy = (android.widget.RelativeLayout) acty.findViewById(BtnRecoverToInventroyId);
        mBtnRecoverToDestroy = (android.widget.RelativeLayout) acty.findViewById(BtnRecoverToDestroyId);
        mBtnSetting = (android.widget.RelativeLayout) acty.findViewById(BtnSettingId);
        mBtnLogout = (android.widget.RelativeLayout) acty.findViewById(BtnLogoutId);
    }   
    public android.widget.TextView getTxtNetState() {
        return mTxtNetState;
    }
    public android.widget.TextView getTxtWSCode() {
        return mTxtWSCode;
    }
    public android.widget.TextView getTxtInspector() {
        return mTxtInspector;
    }
    public android.widget.TextView getTxtDriver() {
        return mTxtDriver;
    }
    public android.widget.TextView getTxtCarCode() {
        return mTxtCarCode;
    }
    public android.widget.TextView getTxtTotalCount() {
        return mTxtTotalCount;
    }
    public android.widget.TextView getTxtTotalWeight() {
        return mTxtTotalWeight;
    }
    public android.widget.Button getBtnTest() {
        return mBtnTest;
    }
    public android.widget.RelativeLayout getBtnRecoverCrate() {
        return mBtnRecoverCrate;
    }
    public android.widget.RelativeLayout getBtnRecoverToInventroy() {
        return mBtnRecoverToInventroy;
    }
    public android.widget.RelativeLayout getBtnRecoverToDestroy() {
        return mBtnRecoverToDestroy;
    }
    public android.widget.RelativeLayout getBtnSetting() {
        return mBtnSetting;
    }
    public android.widget.RelativeLayout getBtnLogout() {
        return mBtnLogout;
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
                case TxtNetStateId:
                    setViewData(adp,getTxtNetState(),data,joinData.formatString,joinData.data);
                    break;
                case TxtWSCodeId:
                    setViewData(adp,getTxtWSCode(),data,joinData.formatString,joinData.data);
                    break;
                case TxtInspectorId:
                    setViewData(adp,getTxtInspector(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDriverId:
                    setViewData(adp,getTxtDriver(),data,joinData.formatString,joinData.data);
                    break;
                case TxtCarCodeId:
                    setViewData(adp,getTxtCarCode(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTotalCountId:
                    setViewData(adp,getTxtTotalCount(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTotalWeightId:
                    setViewData(adp,getTxtTotalWeight(),data,joinData.formatString,joinData.data);
                    break;
                case BtnTestId:
                    setViewData(adp,getBtnTest(),data,joinData.formatString,joinData.data);
                    break;
                case BtnRecoverCrateId:
                    setViewData(adp,getBtnRecoverCrate(),data,joinData.formatString,joinData.data);
                    break;
                case BtnRecoverToInventroyId:
                    setViewData(adp,getBtnRecoverToInventroy(),data,joinData.formatString,joinData.data);
                    break;
                case BtnRecoverToDestroyId:
                    setViewData(adp,getBtnRecoverToDestroy(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSettingId:
                    setViewData(adp,getBtnSetting(),data,joinData.formatString,joinData.data);
                    break;
                case BtnLogoutId:
                    setViewData(adp,getBtnLogout(),data,joinData.formatString,joinData.data);
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
                case TxtNetStateId:
                    setViewData(adp,getTxtNetState(),data,"",path);
                    break;
                case TxtWSCodeId:
                    setViewData(adp,getTxtWSCode(),data,"",path);
                    break;
                case TxtInspectorId:
                    setViewData(adp,getTxtInspector(),data,"",path);
                    break;
                case TxtDriverId:
                    setViewData(adp,getTxtDriver(),data,"",path);
                    break;
                case TxtCarCodeId:
                    setViewData(adp,getTxtCarCode(),data,"",path);
                    break;
                case TxtTotalCountId:
                    setViewData(adp,getTxtTotalCount(),data,"",path);
                    break;
                case TxtTotalWeightId:
                    setViewData(adp,getTxtTotalWeight(),data,"",path);
                    break;
                case BtnTestId:
                    setViewData(adp,getBtnTest(),data,"",path);
                    break;
                case BtnRecoverCrateId:
                    setViewData(adp,getBtnRecoverCrate(),data,"",path);
                    break;
                case BtnRecoverToInventroyId:
                    setViewData(adp,getBtnRecoverToInventroy(),data,"",path);
                    break;
                case BtnRecoverToDestroyId:
                    setViewData(adp,getBtnRecoverToDestroy(),data,"",path);
                    break;
                case BtnSettingId:
                    setViewData(adp,getBtnSetting(),data,"",path);
                    break;
                case BtnLogoutId:
                    setViewData(adp,getBtnLogout(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
