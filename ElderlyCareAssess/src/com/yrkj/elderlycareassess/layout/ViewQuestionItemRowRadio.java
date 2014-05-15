package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ViewQuestionItemRowRadio extends BaseLayout{

    public static final int RdoQuestionItem1ViewId = R.id.rdoQuestionItem1View;
    public static final int RdoQuestionItem2ViewId = R.id.rdoQuestionItem2View;

    protected android.widget.RadioButton mRdoQuestionItem1View;
    protected android.widget.RadioButton mRdoQuestionItem2View;

    protected Activity mCurActy;

    public ViewQuestionItemRowRadio(Activity acty){
        mCurActy = acty;
        mRdoQuestionItem1View = (android.widget.RadioButton) acty.findViewById(RdoQuestionItem1ViewId);
        mRdoQuestionItem2View = (android.widget.RadioButton) acty.findViewById(RdoQuestionItem2ViewId);
    }   

    public ViewQuestionItemRowRadio(android.view.View acty){
        mRdoQuestionItem1View = (android.widget.RadioButton) acty.findViewById(RdoQuestionItem1ViewId);
        mRdoQuestionItem2View = (android.widget.RadioButton) acty.findViewById(RdoQuestionItem2ViewId);
    }   
    public android.widget.RadioButton getRdoQuestionItem1View() {
        return mRdoQuestionItem1View;
    }
    public android.widget.RadioButton getRdoQuestionItem2View() {
        return mRdoQuestionItem2View;
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
                case RdoQuestionItem1ViewId:
                    setViewData(adp,getRdoQuestionItem1View(),data,joinData.formatString,joinData.data);
                    break;
                case RdoQuestionItem2ViewId:
                    setViewData(adp,getRdoQuestionItem2View(),data,joinData.formatString,joinData.data);
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
                case RdoQuestionItem1ViewId:
                    setViewData(adp,getRdoQuestionItem1View(),data,"",path);
                    break;
                case RdoQuestionItem2ViewId:
                    setViewData(adp,getRdoQuestionItem2View(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
