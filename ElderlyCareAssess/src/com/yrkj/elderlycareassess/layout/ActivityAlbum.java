package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityAlbum extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int ViewerId = R.id.viewer;

    protected android.widget.LinearLayout mContainer;
    protected ru.truba.touchgallery.GalleryWidget.GalleryViewPager mViewer;

    protected Activity mCurActy;

    public ActivityAlbum(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mViewer = (ru.truba.touchgallery.GalleryWidget.GalleryViewPager) acty.findViewById(ViewerId);
    }   

    public ActivityAlbum(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mViewer = (ru.truba.touchgallery.GalleryWidget.GalleryViewPager) acty.findViewById(ViewerId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public ru.truba.touchgallery.GalleryWidget.GalleryViewPager getViewer() {
        return mViewer;
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
                case ViewerId:
                    setViewData(adp,getViewer(),data,joinData.formatString,joinData.data);
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
                case ViewerId:
                    setViewData(adp,getViewer(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
