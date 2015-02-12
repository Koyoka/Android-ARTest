package com.yrkj.mwrmobile.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.mwrmobile.R;


public class ActivityRecoverDetail extends BaseLayout{

    public static final int TextView1Id = R.id.textView1;
    public static final int Spinner1Id = R.id.spinner1;

    protected android.widget.TextView mTextView1;
    protected android.widget.Spinner mSpinner1;

    protected Activity mCurActy;

    public ActivityRecoverDetail(Activity acty){
        mCurActy = acty;
        mTextView1 = (android.widget.TextView) acty.findViewById(TextView1Id);
        mSpinner1 = (android.widget.Spinner) acty.findViewById(Spinner1Id);
    }   

    public ActivityRecoverDetail(android.view.View acty){
        mTextView1 = (android.widget.TextView) acty.findViewById(TextView1Id);
        mSpinner1 = (android.widget.Spinner) acty.findViewById(Spinner1Id);
    }   
    public android.widget.TextView getTextView1() {
        return mTextView1;
    }
    public android.widget.Spinner getSpinner1() {
        return mSpinner1;
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
                case TextView1Id:
                    setViewData(adp,getTextView1(),data,joinData.formatString,joinData.data);
                    break;
                case Spinner1Id:
                    setViewData(adp,getSpinner1(),data,joinData.formatString,joinData.data);
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
                case TextView1Id:
                    setViewData(adp,getTextView1(),data,"",path);
                    break;
                case Spinner1Id:
                    setViewData(adp,getSpinner1(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
