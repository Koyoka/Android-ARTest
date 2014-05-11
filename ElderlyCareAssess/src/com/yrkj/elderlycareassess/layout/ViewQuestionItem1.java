package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ViewQuestionItem1 extends BaseLayout{

    public static final int RdoQuestionItemViewId = R.id.rdoQuestionItemView;
    public static final int TxtQuestionItemViewId = R.id.txtQuestionItemView;

    protected android.widget.RadioButton mRdoQuestionItemView;
    protected android.widget.TextView mTxtQuestionItemView;

    protected Activity mCurActy;

    public ViewQuestionItem1(Activity acty){
        mCurActy = acty;
        mRdoQuestionItemView = (android.widget.RadioButton) acty.findViewById(RdoQuestionItemViewId);
        mTxtQuestionItemView = (android.widget.TextView) acty.findViewById(TxtQuestionItemViewId);
    }   

    public ViewQuestionItem1(android.view.View acty){
        mRdoQuestionItemView = (android.widget.RadioButton) acty.findViewById(RdoQuestionItemViewId);
        mTxtQuestionItemView = (android.widget.TextView) acty.findViewById(TxtQuestionItemViewId);
    }   
    public android.widget.RadioButton getRdoQuestionItemView() {
        return mRdoQuestionItemView;
    }
    public android.widget.TextView getTxtQuestionItemView() {
        return mTxtQuestionItemView;
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
                case TxtQuestionItemViewId:
                    setViewData(adp,getTxtQuestionItemView(),data,joinData.formatString,joinData.data);
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
                case TxtQuestionItemViewId:
                    setViewData(adp,getTxtQuestionItemView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
