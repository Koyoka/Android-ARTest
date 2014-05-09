package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityMain1 extends BaseLayout{

    public static final int MainId = R.id.main;
    public static final int ShowId = R.id.show;
    public static final int SelectionId = R.id.selection;
    public static final int HiddenId = R.id.hidden;

    protected android.widget.LinearLayout mMain;
    protected android.widget.Button mShow;
    protected android.widget.LinearLayout mSelection;
    protected android.widget.Button mHidden;

    protected Activity mCurActy;

    public ActivityMain1(Activity acty){
        mCurActy = acty;
        mMain = (android.widget.LinearLayout) acty.findViewById(MainId);
        mShow = (android.widget.Button) acty.findViewById(ShowId);
        mSelection = (android.widget.LinearLayout) acty.findViewById(SelectionId);
        mHidden = (android.widget.Button) acty.findViewById(HiddenId);
    }   

    public ActivityMain1(android.view.View acty){
        mMain = (android.widget.LinearLayout) acty.findViewById(MainId);
        mShow = (android.widget.Button) acty.findViewById(ShowId);
        mSelection = (android.widget.LinearLayout) acty.findViewById(SelectionId);
        mHidden = (android.widget.Button) acty.findViewById(HiddenId);
    }   
    public android.widget.LinearLayout getMain() {
        return mMain;
    }
    public android.widget.Button getShow() {
        return mShow;
    }
    public android.widget.LinearLayout getSelection() {
        return mSelection;
    }
    public android.widget.Button getHidden() {
        return mHidden;
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
                case MainId:
                    setViewData(adp,getMain(),data,joinData.formatString,joinData.data);
                    break;
                case ShowId:
                    setViewData(adp,getShow(),data,joinData.formatString,joinData.data);
                    break;
                case SelectionId:
                    setViewData(adp,getSelection(),data,joinData.formatString,joinData.data);
                    break;
                case HiddenId:
                    setViewData(adp,getHidden(),data,joinData.formatString,joinData.data);
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
                case MainId:
                    setViewData(adp,getMain(),data,"",path);
                    break;
                case ShowId:
                    setViewData(adp,getShow(),data,"",path);
                    break;
                case SelectionId:
                    setViewData(adp,getSelection(),data,"",path);
                    break;
                case HiddenId:
                    setViewData(adp,getHidden(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
