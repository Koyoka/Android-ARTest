package com.yrkj.util.ui.layout;

import java.util.Map;

import android.app.Activity;

import com.yrkj.elderlycareassess.R;


public class CommDialogSelectphotoView extends BaseLayout{

    public static final int BtnOpenCameraViewId = R.id.btnOpenCameraView;
    public static final int BtnOpenPhotoAlbumViewId = R.id.btnOpenPhotoAlbumView;
    public static final int BtnCancelDialogViewId = R.id.btnCancelDialogView;

    protected android.widget.Button mBtnOpenCameraView;
    protected android.widget.Button mBtnOpenPhotoAlbumView;
    protected android.widget.Button mBtnCancelDialogView;

    protected Activity mCurActy;

    public CommDialogSelectphotoView(Activity acty){
        mCurActy = acty;
        mBtnOpenCameraView = (android.widget.Button) acty.findViewById(BtnOpenCameraViewId);
        mBtnOpenPhotoAlbumView = (android.widget.Button) acty.findViewById(BtnOpenPhotoAlbumViewId);
        mBtnCancelDialogView = (android.widget.Button) acty.findViewById(BtnCancelDialogViewId);
    }   

    public CommDialogSelectphotoView(android.view.View acty){
        mBtnOpenCameraView = (android.widget.Button) acty.findViewById(BtnOpenCameraViewId);
        mBtnOpenPhotoAlbumView = (android.widget.Button) acty.findViewById(BtnOpenPhotoAlbumViewId);
        mBtnCancelDialogView = (android.widget.Button) acty.findViewById(BtnCancelDialogViewId);
    }   

    public android.widget.Button getBtnOpenCameraView() {
        return mBtnOpenCameraView;
    }
    public android.widget.Button getBtnOpenPhotoAlbumView() {
        return mBtnOpenPhotoAlbumView;
    }
    public android.widget.Button getBtnCancelDialogView() {
        return mBtnCancelDialogView;
    }

    public void bindData(LayoutDataAdapter adp,com.yrkj.util.basedao.BaseBean data){
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
                case BtnOpenCameraViewId:
                    setViewData(adp,getBtnOpenCameraView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnOpenPhotoAlbumViewId:
                    setViewData(adp,getBtnOpenPhotoAlbumView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnCancelDialogViewId:
                    setViewData(adp,getBtnCancelDialogView(),data,joinData.formatString,joinData.data);
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
                case BtnOpenCameraViewId:
                    setViewData(adp,getBtnOpenCameraView(),data,"",path);
                    break;
                case BtnOpenPhotoAlbumViewId:
                    setViewData(adp,getBtnOpenPhotoAlbumView(),data,"",path);
                    break;
                case BtnCancelDialogViewId:
                    setViewData(adp,getBtnCancelDialogView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
