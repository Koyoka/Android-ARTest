package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ViewQuestionItemRowRadio extends BaseLayout{

    public static final int RdoQuestionItemViewId = R.id.rdoQuestionItemView;

    protected android.widget.RadioButton mRdoQuestionItemView;

    protected Activity mCurActy;

    public ViewQuestionItemRowRadio(Activity acty){
        mCurActy = acty;
        mRdoQuestionItemView = (android.widget.RadioButton) acty.findViewById(RdoQuestionItemViewId);
    }   

    public ViewQuestionItemRowRadio(android.view.View acty){
        mRdoQuestionItemView = (android.widget.RadioButton) acty.findViewById(RdoQuestionItemViewId);
    }   
    public android.widget.RadioButton getRdoQuestionItemView() {
        return mRdoQuestionItemView;
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
                case RdoQuestionItemViewId:
                    setViewData(adp,getRdoQuestionItemView(),data,joinData.formatString,joinData.data);
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
                case RdoQuestionItemViewId:
                    setViewData(adp,getRdoQuestionItemView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
