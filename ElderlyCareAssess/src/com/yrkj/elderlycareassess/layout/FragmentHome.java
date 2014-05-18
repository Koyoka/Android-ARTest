package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentHome extends BaseLayout{

    public static final int LayoutHomeContentDoneTaskViewId = R.id.layoutHomeContentDoneTaskView;
    public static final int ImgIconDoneTaskViewId = R.id.imgIconDoneTaskView;
    public static final int TxtDoneTaskTitleViewId = R.id.txtDoneTaskTitleView;
    public static final int TxtDoneTaskDescViewId = R.id.txtDoneTaskDescView;
    public static final int LayoutHomeContentDoingTaskViewId = R.id.layoutHomeContentDoingTaskView;
    public static final int ImgIconDoingTaskViewId = R.id.imgIconDoingTaskView;
    public static final int TxtDoingTaskTitleViewId = R.id.txtDoingTaskTitleView;
    public static final int TxtDoingTaskDescViewId = R.id.txtDoingTaskDescView;
    public static final int LayoutHomeContentLogViewId = R.id.layoutHomeContentLogView;
    public static final int ImgIconLogViewId = R.id.imgIconLogView;
    public static final int TxtLogTitleViewId = R.id.txtLogTitleView;
    public static final int TxtLogDescViewId = R.id.txtLogDescView;
    public static final int LayoutHomeContentReportViewId = R.id.layoutHomeContentReportView;
    public static final int TxtReportTitleViewId = R.id.txtReportTitleView;
    public static final int TxtReportInfoViewId = R.id.txtReportInfoView;
    public static final int LayoutReportTotleViewId = R.id.layoutReportTotleView;
    public static final int UctrlRC1ViewId = R.id.uctrlRC1View;
    public static final int UctrlRC2ViewId = R.id.uctrlRC2View;
    public static final int UctrlRC3ViewId = R.id.uctrlRC3View;
    public static final int UctrlRC4ViewId = R.id.uctrlRC4View;
    public static final int UctrlRC5ViewId = R.id.uctrlRC5View;
    public static final int UctrlRC6ViewId = R.id.uctrlRC6View;
    public static final int UctrlRC7ViewId = R.id.uctrlRC7View;
    public static final int TxtReportTotleCountViewId = R.id.txtReportTotleCountView;

    protected android.widget.RelativeLayout mLayoutHomeContentDoneTaskView;
    protected android.widget.ImageView mImgIconDoneTaskView;
    protected android.widget.TextView mTxtDoneTaskTitleView;
    protected android.widget.TextView mTxtDoneTaskDescView;
    protected android.widget.RelativeLayout mLayoutHomeContentDoingTaskView;
    protected android.widget.ImageView mImgIconDoingTaskView;
    protected android.widget.TextView mTxtDoingTaskTitleView;
    protected android.widget.TextView mTxtDoingTaskDescView;
    protected android.widget.RelativeLayout mLayoutHomeContentLogView;
    protected android.widget.ImageView mImgIconLogView;
    protected android.widget.TextView mTxtLogTitleView;
    protected android.widget.TextView mTxtLogDescView;
    protected android.widget.RelativeLayout mLayoutHomeContentReportView;
    protected android.widget.TextView mTxtReportTitleView;
    protected android.widget.TextView mTxtReportInfoView;
    protected android.widget.LinearLayout mLayoutReportTotleView;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC1View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC2View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC3View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC4View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC5View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC6View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC7View;
    protected android.widget.TextView mTxtReportTotleCountView;

    protected Activity mCurActy;

    public FragmentHome(Activity acty){
        mCurActy = acty;
        mLayoutHomeContentDoneTaskView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentDoneTaskViewId);
        mImgIconDoneTaskView = (android.widget.ImageView) acty.findViewById(ImgIconDoneTaskViewId);
        mTxtDoneTaskTitleView = (android.widget.TextView) acty.findViewById(TxtDoneTaskTitleViewId);
        mTxtDoneTaskDescView = (android.widget.TextView) acty.findViewById(TxtDoneTaskDescViewId);
        mLayoutHomeContentDoingTaskView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentDoingTaskViewId);
        mImgIconDoingTaskView = (android.widget.ImageView) acty.findViewById(ImgIconDoingTaskViewId);
        mTxtDoingTaskTitleView = (android.widget.TextView) acty.findViewById(TxtDoingTaskTitleViewId);
        mTxtDoingTaskDescView = (android.widget.TextView) acty.findViewById(TxtDoingTaskDescViewId);
        mLayoutHomeContentLogView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentLogViewId);
        mImgIconLogView = (android.widget.ImageView) acty.findViewById(ImgIconLogViewId);
        mTxtLogTitleView = (android.widget.TextView) acty.findViewById(TxtLogTitleViewId);
        mTxtLogDescView = (android.widget.TextView) acty.findViewById(TxtLogDescViewId);
        mLayoutHomeContentReportView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentReportViewId);
        mTxtReportTitleView = (android.widget.TextView) acty.findViewById(TxtReportTitleViewId);
        mTxtReportInfoView = (android.widget.TextView) acty.findViewById(TxtReportInfoViewId);
        mLayoutReportTotleView = (android.widget.LinearLayout) acty.findViewById(LayoutReportTotleViewId);
        mUctrlRC1View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC1ViewId);
        mUctrlRC2View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC2ViewId);
        mUctrlRC3View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC3ViewId);
        mUctrlRC4View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC4ViewId);
        mUctrlRC5View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC5ViewId);
        mUctrlRC6View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC6ViewId);
        mUctrlRC7View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC7ViewId);
        mTxtReportTotleCountView = (android.widget.TextView) acty.findViewById(TxtReportTotleCountViewId);
    }   

    public FragmentHome(android.view.View acty){
        mLayoutHomeContentDoneTaskView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentDoneTaskViewId);
        mImgIconDoneTaskView = (android.widget.ImageView) acty.findViewById(ImgIconDoneTaskViewId);
        mTxtDoneTaskTitleView = (android.widget.TextView) acty.findViewById(TxtDoneTaskTitleViewId);
        mTxtDoneTaskDescView = (android.widget.TextView) acty.findViewById(TxtDoneTaskDescViewId);
        mLayoutHomeContentDoingTaskView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentDoingTaskViewId);
        mImgIconDoingTaskView = (android.widget.ImageView) acty.findViewById(ImgIconDoingTaskViewId);
        mTxtDoingTaskTitleView = (android.widget.TextView) acty.findViewById(TxtDoingTaskTitleViewId);
        mTxtDoingTaskDescView = (android.widget.TextView) acty.findViewById(TxtDoingTaskDescViewId);
        mLayoutHomeContentLogView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentLogViewId);
        mImgIconLogView = (android.widget.ImageView) acty.findViewById(ImgIconLogViewId);
        mTxtLogTitleView = (android.widget.TextView) acty.findViewById(TxtLogTitleViewId);
        mTxtLogDescView = (android.widget.TextView) acty.findViewById(TxtLogDescViewId);
        mLayoutHomeContentReportView = (android.widget.RelativeLayout) acty.findViewById(LayoutHomeContentReportViewId);
        mTxtReportTitleView = (android.widget.TextView) acty.findViewById(TxtReportTitleViewId);
        mTxtReportInfoView = (android.widget.TextView) acty.findViewById(TxtReportInfoViewId);
        mLayoutReportTotleView = (android.widget.LinearLayout) acty.findViewById(LayoutReportTotleViewId);
        mUctrlRC1View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC1ViewId);
        mUctrlRC2View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC2ViewId);
        mUctrlRC3View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC3ViewId);
        mUctrlRC4View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC4ViewId);
        mUctrlRC5View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC5ViewId);
        mUctrlRC6View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC6ViewId);
        mUctrlRC7View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC7ViewId);
        mTxtReportTotleCountView = (android.widget.TextView) acty.findViewById(TxtReportTotleCountViewId);
    }   
    public android.widget.RelativeLayout getLayoutHomeContentDoneTaskView() {
        return mLayoutHomeContentDoneTaskView;
    }
    public android.widget.ImageView getImgIconDoneTaskView() {
        return mImgIconDoneTaskView;
    }
    public android.widget.TextView getTxtDoneTaskTitleView() {
        return mTxtDoneTaskTitleView;
    }
    public android.widget.TextView getTxtDoneTaskDescView() {
        return mTxtDoneTaskDescView;
    }
    public android.widget.RelativeLayout getLayoutHomeContentDoingTaskView() {
        return mLayoutHomeContentDoingTaskView;
    }
    public android.widget.ImageView getImgIconDoingTaskView() {
        return mImgIconDoingTaskView;
    }
    public android.widget.TextView getTxtDoingTaskTitleView() {
        return mTxtDoingTaskTitleView;
    }
    public android.widget.TextView getTxtDoingTaskDescView() {
        return mTxtDoingTaskDescView;
    }
    public android.widget.RelativeLayout getLayoutHomeContentLogView() {
        return mLayoutHomeContentLogView;
    }
    public android.widget.ImageView getImgIconLogView() {
        return mImgIconLogView;
    }
    public android.widget.TextView getTxtLogTitleView() {
        return mTxtLogTitleView;
    }
    public android.widget.TextView getTxtLogDescView() {
        return mTxtLogDescView;
    }
    public android.widget.RelativeLayout getLayoutHomeContentReportView() {
        return mLayoutHomeContentReportView;
    }
    public android.widget.TextView getTxtReportTitleView() {
        return mTxtReportTitleView;
    }
    public android.widget.TextView getTxtReportInfoView() {
        return mTxtReportInfoView;
    }
    public android.widget.LinearLayout getLayoutReportTotleView() {
        return mLayoutReportTotleView;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC1View() {
        return mUctrlRC1View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC2View() {
        return mUctrlRC2View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC3View() {
        return mUctrlRC3View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC4View() {
        return mUctrlRC4View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC5View() {
        return mUctrlRC5View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC6View() {
        return mUctrlRC6View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC7View() {
        return mUctrlRC7View;
    }
    public android.widget.TextView getTxtReportTotleCountView() {
        return mTxtReportTotleCountView;
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
                case LayoutHomeContentDoneTaskViewId:
                    setViewData(adp,getLayoutHomeContentDoneTaskView(),data,joinData.formatString,joinData.data);
                    break;
                case ImgIconDoneTaskViewId:
                    setViewData(adp,getImgIconDoneTaskView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDoneTaskTitleViewId:
                    setViewData(adp,getTxtDoneTaskTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDoneTaskDescViewId:
                    setViewData(adp,getTxtDoneTaskDescView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutHomeContentDoingTaskViewId:
                    setViewData(adp,getLayoutHomeContentDoingTaskView(),data,joinData.formatString,joinData.data);
                    break;
                case ImgIconDoingTaskViewId:
                    setViewData(adp,getImgIconDoingTaskView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDoingTaskTitleViewId:
                    setViewData(adp,getTxtDoingTaskTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtDoingTaskDescViewId:
                    setViewData(adp,getTxtDoingTaskDescView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutHomeContentLogViewId:
                    setViewData(adp,getLayoutHomeContentLogView(),data,joinData.formatString,joinData.data);
                    break;
                case ImgIconLogViewId:
                    setViewData(adp,getImgIconLogView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtLogTitleViewId:
                    setViewData(adp,getTxtLogTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtLogDescViewId:
                    setViewData(adp,getTxtLogDescView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutHomeContentReportViewId:
                    setViewData(adp,getLayoutHomeContentReportView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtReportTitleViewId:
                    setViewData(adp,getTxtReportTitleView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtReportInfoViewId:
                    setViewData(adp,getTxtReportInfoView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutReportTotleViewId:
                    setViewData(adp,getLayoutReportTotleView(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC1ViewId:
                    setViewData(adp,getUctrlRC1View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC2ViewId:
                    setViewData(adp,getUctrlRC2View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC3ViewId:
                    setViewData(adp,getUctrlRC3View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC4ViewId:
                    setViewData(adp,getUctrlRC4View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC5ViewId:
                    setViewData(adp,getUctrlRC5View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC6ViewId:
                    setViewData(adp,getUctrlRC6View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC7ViewId:
                    setViewData(adp,getUctrlRC7View(),data,joinData.formatString,joinData.data);
                    break;
                case TxtReportTotleCountViewId:
                    setViewData(adp,getTxtReportTotleCountView(),data,joinData.formatString,joinData.data);
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
                case LayoutHomeContentDoneTaskViewId:
                    setViewData(adp,getLayoutHomeContentDoneTaskView(),data,"",path);
                    break;
                case ImgIconDoneTaskViewId:
                    setViewData(adp,getImgIconDoneTaskView(),data,"",path);
                    break;
                case TxtDoneTaskTitleViewId:
                    setViewData(adp,getTxtDoneTaskTitleView(),data,"",path);
                    break;
                case TxtDoneTaskDescViewId:
                    setViewData(adp,getTxtDoneTaskDescView(),data,"",path);
                    break;
                case LayoutHomeContentDoingTaskViewId:
                    setViewData(adp,getLayoutHomeContentDoingTaskView(),data,"",path);
                    break;
                case ImgIconDoingTaskViewId:
                    setViewData(adp,getImgIconDoingTaskView(),data,"",path);
                    break;
                case TxtDoingTaskTitleViewId:
                    setViewData(adp,getTxtDoingTaskTitleView(),data,"",path);
                    break;
                case TxtDoingTaskDescViewId:
                    setViewData(adp,getTxtDoingTaskDescView(),data,"",path);
                    break;
                case LayoutHomeContentLogViewId:
                    setViewData(adp,getLayoutHomeContentLogView(),data,"",path);
                    break;
                case ImgIconLogViewId:
                    setViewData(adp,getImgIconLogView(),data,"",path);
                    break;
                case TxtLogTitleViewId:
                    setViewData(adp,getTxtLogTitleView(),data,"",path);
                    break;
                case TxtLogDescViewId:
                    setViewData(adp,getTxtLogDescView(),data,"",path);
                    break;
                case LayoutHomeContentReportViewId:
                    setViewData(adp,getLayoutHomeContentReportView(),data,"",path);
                    break;
                case TxtReportTitleViewId:
                    setViewData(adp,getTxtReportTitleView(),data,"",path);
                    break;
                case TxtReportInfoViewId:
                    setViewData(adp,getTxtReportInfoView(),data,"",path);
                    break;
                case LayoutReportTotleViewId:
                    setViewData(adp,getLayoutReportTotleView(),data,"",path);
                    break;
                case UctrlRC1ViewId:
                    setViewData(adp,getUctrlRC1View(),data,"",path);
                    break;
                case UctrlRC2ViewId:
                    setViewData(adp,getUctrlRC2View(),data,"",path);
                    break;
                case UctrlRC3ViewId:
                    setViewData(adp,getUctrlRC3View(),data,"",path);
                    break;
                case UctrlRC4ViewId:
                    setViewData(adp,getUctrlRC4View(),data,"",path);
                    break;
                case UctrlRC5ViewId:
                    setViewData(adp,getUctrlRC5View(),data,"",path);
                    break;
                case UctrlRC6ViewId:
                    setViewData(adp,getUctrlRC6View(),data,"",path);
                    break;
                case UctrlRC7ViewId:
                    setViewData(adp,getUctrlRC7View(),data,"",path);
                    break;
                case TxtReportTotleCountViewId:
                    setViewData(adp,getTxtReportTotleCountView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
