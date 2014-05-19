package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ListItemDoneTask extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int ImgAssessLevelViewId = R.id.imgAssessLevelView;
    public static final int TxtTaskNumViewId = R.id.txtTaskNumView;
    public static final int TxtTaskUserNameViewId = R.id.txtTaskUserNameView;
    public static final int TxtAssessTaskDetailTotleViewId = R.id.txtAssessTaskDetailTotleView;
    public static final int BtnAysnViewId = R.id.btnAysnView;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.ImageView mImgAssessLevelView;
    protected android.widget.TextView mTxtTaskNumView;
    protected android.widget.TextView mTxtTaskUserNameView;
    protected android.widget.TextView mTxtAssessTaskDetailTotleView;
    protected android.widget.RelativeLayout mBtnAysnView;

    protected Activity mCurActy;

    public ListItemDoneTask(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mImgAssessLevelView = (android.widget.ImageView) acty.findViewById(ImgAssessLevelViewId);
        mTxtTaskNumView = (android.widget.TextView) acty.findViewById(TxtTaskNumViewId);
        mTxtTaskUserNameView = (android.widget.TextView) acty.findViewById(TxtTaskUserNameViewId);
        mTxtAssessTaskDetailTotleView = (android.widget.TextView) acty.findViewById(TxtAssessTaskDetailTotleViewId);
        mBtnAysnView = (android.widget.RelativeLayout) acty.findViewById(BtnAysnViewId);
    }   

    public ListItemDoneTask(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mImgAssessLevelView = (android.widget.ImageView) acty.findViewById(ImgAssessLevelViewId);
        mTxtTaskNumView = (android.widget.TextView) acty.findViewById(TxtTaskNumViewId);
        mTxtTaskUserNameView = (android.widget.TextView) acty.findViewById(TxtTaskUserNameViewId);
        mTxtAssessTaskDetailTotleView = (android.widget.TextView) acty.findViewById(TxtAssessTaskDetailTotleViewId);
        mBtnAysnView = (android.widget.RelativeLayout) acty.findViewById(BtnAysnViewId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.ImageView getImgAssessLevelView() {
        return mImgAssessLevelView;
    }
    public android.widget.TextView getTxtTaskNumView() {
        return mTxtTaskNumView;
    }
    public android.widget.TextView getTxtTaskUserNameView() {
        return mTxtTaskUserNameView;
    }
    public android.widget.TextView getTxtAssessTaskDetailTotleView() {
        return mTxtAssessTaskDetailTotleView;
    }
    public android.widget.RelativeLayout getBtnAysnView() {
        return mBtnAysnView;
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
                case ImgAssessLevelViewId:
                    setViewData(adp,getImgAssessLevelView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskNumViewId:
                    setViewData(adp,getTxtTaskNumView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTaskUserNameViewId:
                    setViewData(adp,getTxtTaskUserNameView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAssessTaskDetailTotleViewId:
                    setViewData(adp,getTxtAssessTaskDetailTotleView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnAysnViewId:
                    setViewData(adp,getBtnAysnView(),data,joinData.formatString,joinData.data);
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
                case ImgAssessLevelViewId:
                    setViewData(adp,getImgAssessLevelView(),data,"",path);
                    break;
                case TxtTaskNumViewId:
                    setViewData(adp,getTxtTaskNumView(),data,"",path);
                    break;
                case TxtTaskUserNameViewId:
                    setViewData(adp,getTxtTaskUserNameView(),data,"",path);
                    break;
                case TxtAssessTaskDetailTotleViewId:
                    setViewData(adp,getTxtAssessTaskDetailTotleView(),data,"",path);
                    break;
                case BtnAysnViewId:
                    setViewData(adp,getBtnAysnView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
