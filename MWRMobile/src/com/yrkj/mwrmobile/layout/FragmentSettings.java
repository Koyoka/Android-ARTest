package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class FragmentSettings extends BaseLayout{

    public static final int BtnSettingAysnDataId = R.id.btnSettingAysnData;
    public static final int BtnSettingEditHostId = R.id.btnSettingEditHost;

    protected android.widget.Button mBtnSettingAysnData;
    protected android.widget.Button mBtnSettingEditHost;

    protected Activity mCurActy;

    public FragmentSettings(Activity acty){
        mCurActy = acty;
        mBtnSettingAysnData = (android.widget.Button) acty.findViewById(BtnSettingAysnDataId);
        mBtnSettingEditHost = (android.widget.Button) acty.findViewById(BtnSettingEditHostId);
    }   

    public FragmentSettings(android.view.View acty){
        mBtnSettingAysnData = (android.widget.Button) acty.findViewById(BtnSettingAysnDataId);
        mBtnSettingEditHost = (android.widget.Button) acty.findViewById(BtnSettingEditHostId);
    }   
    public android.widget.Button getBtnSettingAysnData() {
        return mBtnSettingAysnData;
    }
    public android.widget.Button getBtnSettingEditHost() {
        return mBtnSettingEditHost;
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
                case BtnSettingAysnDataId:
                    setViewData(adp,getBtnSettingAysnData(),data,joinData.formatString,joinData.data);
                    break;
                case BtnSettingEditHostId:
                    setViewData(adp,getBtnSettingEditHost(),data,joinData.formatString,joinData.data);
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
                case BtnSettingAysnDataId:
                    setViewData(adp,getBtnSettingAysnData(),data,"",path);
                    break;
                case BtnSettingEditHostId:
                    setViewData(adp,getBtnSettingEditHost(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
