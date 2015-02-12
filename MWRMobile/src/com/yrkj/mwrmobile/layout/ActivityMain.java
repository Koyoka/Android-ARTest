package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityMain extends BaseLayout{

    public static final int BtnTestId = R.id.btnTest;
    public static final int BtnRecoverCrateId = R.id.btnRecoverCrate;
    public static final int BtnRecoverToInventroyId = R.id.btnRecoverToInventroy;
    public static final int BtnRecoverToDestroyId = R.id.btnRecoverToDestroy;
    public static final int BtnSettingId = R.id.btnSetting;
    public static final int BtnLogoutId = R.id.btnLogout;

    protected android.widget.Button mBtnTest;
    protected android.widget.Button mBtnRecoverCrate;
    protected android.widget.Button mBtnRecoverToInventroy;
    protected android.widget.Button mBtnRecoverToDestroy;
    protected android.widget.Button mBtnSetting;
    protected android.widget.Button mBtnLogout;

    protected Activity mCurActy;

    public ActivityMain(Activity acty){
        mCurActy = acty;
        mBtnTest = (android.widget.Button) acty.findViewById(BtnTestId);
        mBtnRecoverCrate = (android.widget.Button) acty.findViewById(BtnRecoverCrateId);
        mBtnRecoverToInventroy = (android.widget.Button) acty.findViewById(BtnRecoverToInventroyId);
        mBtnRecoverToDestroy = (android.widget.Button) acty.findViewById(BtnRecoverToDestroyId);
        mBtnSetting = (android.widget.Button) acty.findViewById(BtnSettingId);
        mBtnLogout = (android.widget.Button) acty.findViewById(BtnLogoutId);
    }   

    public ActivityMain(android.view.View acty){
        mBtnTest = (android.widget.Button) acty.findViewById(BtnTestId);
        mBtnRecoverCrate = (android.widget.Button) acty.findViewById(BtnRecoverCrateId);
        mBtnRecoverToInventroy = (android.widget.Button) acty.findViewById(BtnRecoverToInventroyId);
        mBtnRecoverToDestroy = (android.widget.Button) acty.findViewById(BtnRecoverToDestroyId);
        mBtnSetting = (android.widget.Button) acty.findViewById(BtnSettingId);
        mBtnLogout = (android.widget.Button) acty.findViewById(BtnLogoutId);
    }   
    public android.widget.Button getBtnTest() {
        return mBtnTest;
    }
    public android.widget.Button getBtnRecoverCrate() {
        return mBtnRecoverCrate;
    }
    public android.widget.Button getBtnRecoverToInventroy() {
        return mBtnRecoverToInventroy;
    }
    public android.widget.Button getBtnRecoverToDestroy() {
        return mBtnRecoverToDestroy;
    }
    public android.widget.Button getBtnSetting() {
        return mBtnSetting;
    }
    public android.widget.Button getBtnLogout() {
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
