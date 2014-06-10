package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityCddMain extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int DatePicker1Id = R.id.datePicker1;

    protected android.widget.FrameLayout mContainer;
    protected android.widget.DatePicker mDatePicker1;

    protected Activity mCurActy;

    public ActivityCddMain(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.FrameLayout) acty.findViewById(ContainerId);
        mDatePicker1 = (android.widget.DatePicker) acty.findViewById(DatePicker1Id);
    }   

    public ActivityCddMain(android.view.View acty){
        mContainer = (android.widget.FrameLayout) acty.findViewById(ContainerId);
        mDatePicker1 = (android.widget.DatePicker) acty.findViewById(DatePicker1Id);
    }   
    public android.widget.FrameLayout getContainer() {
        return mContainer;
    }
    public android.widget.DatePicker getDatePicker1() {
        return mDatePicker1;
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
                case DatePicker1Id:
                    setViewData(adp,getDatePicker1(),data,joinData.formatString,joinData.data);
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
                case DatePicker1Id:
                    setViewData(adp,getDatePicker1(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
