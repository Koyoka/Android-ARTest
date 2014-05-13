package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessQuestionnaire extends BaseLayout{

    public static final int LayoutQuestionBarViewId = R.id.layoutQuestionBarView;
    public static final int TxtQuestionTitleViewId = R.id.txtQuestionTitleView;
    public static final int BtnQuestionShorthandViewId = R.id.btnQuestionShorthandView;
    public static final int LayoutSpecialItemViewId = R.id.layoutSpecialItemView;
    public static final int LayoutSpecialItem1ViewId = R.id.layoutSpecialItem1View;
    public static final int LayoutSpecialItem2ViewId = R.id.layoutSpecialItem2View;
    public static final int LayoutSpecialItem3ViewId = R.id.layoutSpecialItem3View;
    public static final int LayoutSpecialItem4ViewId = R.id.layoutSpecialItem4View;
    public static final int LayoutNormalContentViewId = R.id.layoutNormalContentView;

    protected android.widget.RelativeLayout mLayoutQuestionBarView;
    protected android.widget.TextView mTxtQuestionTitleView;
    protected android.widget.Button mBtnQuestionShorthandView;
    protected android.widget.LinearLayout mLayoutSpecialItemView;
    protected android.widget.LinearLayout mLayoutSpecialItem1View;
    protected android.widget.LinearLayout mLayoutSpecialItem2View;
    protected android.widget.LinearLayout mLayoutSpecialItem3View;
    protected android.widget.LinearLayout mLayoutSpecialItem4View;
    protected android.widget.LinearLayout mLayoutNormalContentView;

    protected Activity mCurActy;

    public FragmentAssessQuestionnaire(Activity acty){
        mCurActy = acty;
        mLayoutQuestionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBarViewId);
        mTxtQuestionTitleView = (android.widget.TextView) acty.findViewById(TxtQuestionTitleViewId);
        mBtnQuestionShorthandView = (android.widget.Button) acty.findViewById(BtnQuestionShorthandViewId);
        mLayoutSpecialItemView = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItemViewId);
        mLayoutSpecialItem1View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem1ViewId);
        mLayoutSpecialItem2View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem2ViewId);
        mLayoutSpecialItem3View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem3ViewId);
        mLayoutSpecialItem4View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem4ViewId);
        mLayoutNormalContentView = (android.widget.LinearLayout) acty.findViewById(LayoutNormalContentViewId);
    }   

    public FragmentAssessQuestionnaire(android.view.View acty){
        mLayoutQuestionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBarViewId);
        mTxtQuestionTitleView = (android.widget.TextView) acty.findViewById(TxtQuestionTitleViewId);
        mBtnQuestionShorthandView = (android.widget.Button) acty.findViewById(BtnQuestionShorthandViewId);
        mLayoutSpecialItemView = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItemViewId);
        mLayoutSpecialItem1View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem1ViewId);
        mLayoutSpecialItem2View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem2ViewId);
        mLayoutSpecialItem3View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem3ViewId);
        mLayoutSpecialItem4View = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItem4ViewId);
        mLayoutNormalContentView = (android.widget.LinearLayout) acty.findViewById(LayoutNormalContentViewId);
    }   
    public android.widget.RelativeLayout getLayoutQuestionBarView() {
        return mLayoutQuestionBarView;
    }
    public android.widget.TextView getTxtQuestionTitleView() {
        return mTxtQuestionTitleView;
    }
    public android.widget.Button getBtnQuestionShorthandView() {
        return mBtnQuestionShorthandView;
    }
    public android.widget.LinearLayout getLayoutSpecialItemView() {
        return mLayoutSpecialItemView;
    }
    public android.widget.LinearLayout getLayoutSpecialItem1View() {
        return mLayoutSpecialItem1View;
    }
    public android.widget.LinearLayout getLayoutSpecialItem2View() {
        return mLayoutSpecialItem2View;
    }
    public android.widget.LinearLayout getLayoutSpecialItem3View() {
        return mLayoutSpecialItem3View;
    }
    public android.widget.LinearLayout getLayoutSpecialItem4View() {
        return mLayoutSpecialItem4View;
    }
    public android.widget.LinearLayout getLayoutNormalContentView() {
        return mLayoutNormalContentView;
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
                case LayoutQuestionBarViewId:
                    setViewData(adp,getLayoutQuestionBarView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtQuestionTitleViewId:
                    setViewData(adp,getTxtQuestionTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case BtnQuestionShorthandViewId:
                    setViewData(adp,getBtnQuestionShorthandView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutSpecialItemViewId:
                    setViewData(adp,getLayoutSpecialItemView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutSpecialItem1ViewId:
                    setViewData(adp,getLayoutSpecialItem1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutSpecialItem2ViewId:
                    setViewData(adp,getLayoutSpecialItem2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutSpecialItem3ViewId:
                    setViewData(adp,getLayoutSpecialItem3View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutSpecialItem4ViewId:
                    setViewData(adp,getLayoutSpecialItem4View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutNormalContentViewId:
                    setViewData(adp,getLayoutNormalContentView(),data,joinData.formatString,joinData.data);
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
                case LayoutQuestionBarViewId:
                    setViewData(adp,getLayoutQuestionBarView(),data,"",path);
                    break;
                case TxtQuestionTitleViewId:
                    setViewData(adp,getTxtQuestionTitleView(),data,"",path);
                    break;
                case BtnQuestionShorthandViewId:
                    setViewData(adp,getBtnQuestionShorthandView(),data,"",path);
                    break;
                case LayoutSpecialItemViewId:
                    setViewData(adp,getLayoutSpecialItemView(),data,"",path);
                    break;
                case LayoutSpecialItem1ViewId:
                    setViewData(adp,getLayoutSpecialItem1View(),data,"",path);
                    break;
                case LayoutSpecialItem2ViewId:
                    setViewData(adp,getLayoutSpecialItem2View(),data,"",path);
                    break;
                case LayoutSpecialItem3ViewId:
                    setViewData(adp,getLayoutSpecialItem3View(),data,"",path);
                    break;
                case LayoutSpecialItem4ViewId:
                    setViewData(adp,getLayoutSpecialItem4View(),data,"",path);
                    break;
                case LayoutNormalContentViewId:
                    setViewData(adp,getLayoutNormalContentView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
