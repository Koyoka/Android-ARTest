package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ViewAssessOptionsRadiowithdesc extends BaseLayout{

    public static final int LayoutNormalContentViewId = R.id.layoutNormalContentView;

    protected com.yrkj.elderlycareassess.widget.UIRadioGroup mLayoutNormalContentView;

    protected Activity mCurActy;

    public ViewAssessOptionsRadiowithdesc(Activity acty){
        mCurActy = acty;
        mLayoutNormalContentView = (com.yrkj.elderlycareassess.widget.UIRadioGroup) acty.findViewById(LayoutNormalContentViewId);
    }   

    public ViewAssessOptionsRadiowithdesc(android.view.View acty){
        mLayoutNormalContentView = (com.yrkj.elderlycareassess.widget.UIRadioGroup) acty.findViewById(LayoutNormalContentViewId);
    }   
    public com.yrkj.elderlycareassess.widget.UIRadioGroup getLayoutNormalContentView() {
        return mLayoutNormalContentView;
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
                case LayoutNormalContentViewId:
                    setViewData(adp,getLayoutNormalContentView(),data,joinData.formatString,joinData.data);
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
                case LayoutNormalContentViewId:
                    setViewData(adp,getLayoutNormalContentView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
