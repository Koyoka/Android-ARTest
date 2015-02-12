package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class FragmentEntryCrateDialog extends BaseLayout{

    public static final int SpnVendorId = R.id.spnVendor;
    public static final int SpnWasterId = R.id.spnWaster;
    public static final int BtnConfirmDialogId = R.id.btnConfirmDialog;
    public static final int BtnCloseDialogId = R.id.btnCloseDialog;

    protected android.widget.Spinner mSpnVendor;
    protected android.widget.Spinner mSpnWaster;
    protected android.widget.Button mBtnConfirmDialog;
    protected android.widget.Button mBtnCloseDialog;

    protected Activity mCurActy;

    public FragmentEntryCrateDialog(Activity acty){
        mCurActy = acty;
        mSpnVendor = (android.widget.Spinner) acty.findViewById(SpnVendorId);
        mSpnWaster = (android.widget.Spinner) acty.findViewById(SpnWasterId);
        mBtnConfirmDialog = (android.widget.Button) acty.findViewById(BtnConfirmDialogId);
        mBtnCloseDialog = (android.widget.Button) acty.findViewById(BtnCloseDialogId);
    }   

    public FragmentEntryCrateDialog(android.view.View acty){
        mSpnVendor = (android.widget.Spinner) acty.findViewById(SpnVendorId);
        mSpnWaster = (android.widget.Spinner) acty.findViewById(SpnWasterId);
        mBtnConfirmDialog = (android.widget.Button) acty.findViewById(BtnConfirmDialogId);
        mBtnCloseDialog = (android.widget.Button) acty.findViewById(BtnCloseDialogId);
    }   
    public android.widget.Spinner getSpnVendor() {
        return mSpnVendor;
    }
    public android.widget.Spinner getSpnWaster() {
        return mSpnWaster;
    }
    public android.widget.Button getBtnConfirmDialog() {
        return mBtnConfirmDialog;
    }
    public android.widget.Button getBtnCloseDialog() {
        return mBtnCloseDialog;
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
                case SpnVendorId:
                    setViewData(adp,getSpnVendor(),data,joinData.formatString,joinData.data);
                    break;
                case SpnWasterId:
                    setViewData(adp,getSpnWaster(),data,joinData.formatString,joinData.data);
                    break;
                case BtnConfirmDialogId:
                    setViewData(adp,getBtnConfirmDialog(),data,joinData.formatString,joinData.data);
                    break;
                case BtnCloseDialogId:
                    setViewData(adp,getBtnCloseDialog(),data,joinData.formatString,joinData.data);
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
                case SpnVendorId:
                    setViewData(adp,getSpnVendor(),data,"",path);
                    break;
                case SpnWasterId:
                    setViewData(adp,getSpnWaster(),data,"",path);
                    break;
                case BtnConfirmDialogId:
                    setViewData(adp,getBtnConfirmDialog(),data,"",path);
                    break;
                case BtnCloseDialogId:
                    setViewData(adp,getBtnCloseDialog(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
