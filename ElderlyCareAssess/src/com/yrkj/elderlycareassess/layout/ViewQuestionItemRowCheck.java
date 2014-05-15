package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ViewQuestionItemRowCheck extends BaseLayout{

    public static final int ChkQuestionItem1ViewId = R.id.chkQuestionItem1View;
    public static final int ChkQuestionItem2ViewId = R.id.chkQuestionItem2View;

    protected android.widget.CheckBox mChkQuestionItem1View;
    protected android.widget.CheckBox mChkQuestionItem2View;

    protected Activity mCurActy;

    public ViewQuestionItemRowCheck(Activity acty){
        mCurActy = acty;
        mChkQuestionItem1View = (android.widget.CheckBox) acty.findViewById(ChkQuestionItem1ViewId);
        mChkQuestionItem2View = (android.widget.CheckBox) acty.findViewById(ChkQuestionItem2ViewId);
    }   

    public ViewQuestionItemRowCheck(android.view.View acty){
        mChkQuestionItem1View = (android.widget.CheckBox) acty.findViewById(ChkQuestionItem1ViewId);
        mChkQuestionItem2View = (android.widget.CheckBox) acty.findViewById(ChkQuestionItem2ViewId);
    }   
    public android.widget.CheckBox getChkQuestionItem1View() {
        return mChkQuestionItem1View;
    }
    public android.widget.CheckBox getChkQuestionItem2View() {
        return mChkQuestionItem2View;
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
                case ChkQuestionItem1ViewId:
                    setViewData(adp,getChkQuestionItem1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQuestionItem2ViewId:
                    setViewData(adp,getChkQuestionItem2View(),data,joinData.formatString,joinData.data);
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
                case ChkQuestionItem1ViewId:
                    setViewData(adp,getChkQuestionItem1View(),data,"",path);
                    break;
                case ChkQuestionItem2ViewId:
                    setViewData(adp,getChkQuestionItem2View(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
