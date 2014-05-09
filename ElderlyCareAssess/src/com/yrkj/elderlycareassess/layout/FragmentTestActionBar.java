package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentTestActionBar extends BaseLayout{

    public static final int Section_labelId = R.id.section_label;

    protected android.widget.TextView mSection_label;

    protected Activity mCurActy;

    public FragmentTestActionBar(Activity acty){
        mCurActy = acty;
        mSection_label = (android.widget.TextView) acty.findViewById(Section_labelId);
    }   

    public FragmentTestActionBar(android.view.View acty){
        mSection_label = (android.widget.TextView) acty.findViewById(Section_labelId);
    }   
    public android.widget.TextView getSection_label() {
        return mSection_label;
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
                case Section_labelId:
                    setViewData(adp,getSection_label(),data,joinData.formatString,joinData.data);
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
                case Section_labelId:
                    setViewData(adp,getSection_label(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
