package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class MyActionProvider extends BaseLayout{

    public static final int Popup_viewId = R.id.popup_view;

    protected android.widget.ImageView mPopup_view;

    protected Activity mCurActy;

    public MyActionProvider(Activity acty){
        mCurActy = acty;
        mPopup_view = (android.widget.ImageView) acty.findViewById(Popup_viewId);
    }   

    public MyActionProvider(android.view.View acty){
        mPopup_view = (android.widget.ImageView) acty.findViewById(Popup_viewId);
    }   
    public android.widget.ImageView getPopup_view() {
        return mPopup_view;
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
                case Popup_viewId:
                    setViewData(adp,getPopup_view(),data,joinData.formatString,joinData.data);
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
                case Popup_viewId:
                    setViewData(adp,getPopup_view(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
