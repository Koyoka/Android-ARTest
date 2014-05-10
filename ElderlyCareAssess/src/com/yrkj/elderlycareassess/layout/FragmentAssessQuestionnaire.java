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
    public static final int LayoutNormalItemViewId = R.id.layoutNormalItemView;

    protected android.widget.RelativeLayout mLayoutQuestionBarView;
    protected android.widget.TextView mTxtQuestionTitleView;
    protected android.widget.Button mBtnQuestionShorthandView;
    protected android.widget.LinearLayout mLayoutSpecialItemView;
    protected android.widget.RadioGroup mLayoutNormalItemView;

    protected Activity mCurActy;

    public FragmentAssessQuestionnaire(Activity acty){
        mCurActy = acty;
        mLayoutQuestionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBarViewId);
        mTxtQuestionTitleView = (android.widget.TextView) acty.findViewById(TxtQuestionTitleViewId);
        mBtnQuestionShorthandView = (android.widget.Button) acty.findViewById(BtnQuestionShorthandViewId);
        mLayoutSpecialItemView = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItemViewId);
        mLayoutNormalItemView = (android.widget.RadioGroup) acty.findViewById(LayoutNormalItemViewId);
    }   

    public FragmentAssessQuestionnaire(android.view.View acty){
        mLayoutQuestionBarView = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBarViewId);
        mTxtQuestionTitleView = (android.widget.TextView) acty.findViewById(TxtQuestionTitleViewId);
        mBtnQuestionShorthandView = (android.widget.Button) acty.findViewById(BtnQuestionShorthandViewId);
        mLayoutSpecialItemView = (android.widget.LinearLayout) acty.findViewById(LayoutSpecialItemViewId);
        mLayoutNormalItemView = (android.widget.RadioGroup) acty.findViewById(LayoutNormalItemViewId);
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
    public android.widget.RadioGroup getLayoutNormalItemView() {
        return mLayoutNormalItemView;
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
                case LayoutNormalItemViewId:
                    setViewData(adp,getLayoutNormalItemView(),data,joinData.formatString,joinData.data);
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
                case LayoutNormalItemViewId:
                    setViewData(adp,getLayoutNormalItemView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
