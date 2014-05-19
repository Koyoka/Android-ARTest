package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentReport extends BaseLayout{

    public static final int LayoutUserHeaderInfoContainerViewId = R.id.layoutUserHeaderInfoContainerView;
    public static final int TxtReportInfoViewId = R.id.txtReportInfoView;
    public static final int TxtReportTotleCountViewId = R.id.txtReportTotleCountView;
    public static final int LayoutReportTotleViewId = R.id.layoutReportTotleView;
    public static final int UctrlRC1ViewId = R.id.uctrlRC1View;
    public static final int UctrlRC2ViewId = R.id.uctrlRC2View;
    public static final int UctrlRC3ViewId = R.id.uctrlRC3View;
    public static final int UctrlRC4ViewId = R.id.uctrlRC4View;
    public static final int UctrlRC5ViewId = R.id.uctrlRC5View;
    public static final int UctrlRC6ViewId = R.id.uctrlRC6View;
    public static final int UctrlRC7ViewId = R.id.uctrlRC7View;
    public static final int UctrlRC8ViewId = R.id.uctrlRC8View;
    public static final int UctrlRC9ViewId = R.id.uctrlRC9View;
    public static final int UctrlRC10ViewId = R.id.uctrlRC10View;
    public static final int UctrlRC11ViewId = R.id.uctrlRC11View;
    public static final int UctrlRC12ViewId = R.id.uctrlRC12View;

    protected android.widget.RelativeLayout mLayoutUserHeaderInfoContainerView;
    protected android.widget.TextView mTxtReportInfoView;
    protected android.widget.TextView mTxtReportTotleCountView;
    protected android.widget.LinearLayout mLayoutReportTotleView;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC1View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC2View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC3View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC4View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC5View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC6View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC7View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC8View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC9View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC10View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC11View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC12View;

    protected Activity mCurActy;

    public FragmentReport(Activity acty){
        mCurActy = acty;
        mLayoutUserHeaderInfoContainerView = (android.widget.RelativeLayout) acty.findViewById(LayoutUserHeaderInfoContainerViewId);
        mTxtReportInfoView = (android.widget.TextView) acty.findViewById(TxtReportInfoViewId);
        mTxtReportTotleCountView = (android.widget.TextView) acty.findViewById(TxtReportTotleCountViewId);
        mLayoutReportTotleView = (android.widget.LinearLayout) acty.findViewById(LayoutReportTotleViewId);
        mUctrlRC1View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC1ViewId);
        mUctrlRC2View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC2ViewId);
        mUctrlRC3View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC3ViewId);
        mUctrlRC4View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC4ViewId);
        mUctrlRC5View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC5ViewId);
        mUctrlRC6View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC6ViewId);
        mUctrlRC7View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC7ViewId);
        mUctrlRC8View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC8ViewId);
        mUctrlRC9View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC9ViewId);
        mUctrlRC10View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC10ViewId);
        mUctrlRC11View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC11ViewId);
        mUctrlRC12View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC12ViewId);
    }   

    public FragmentReport(android.view.View acty){
        mLayoutUserHeaderInfoContainerView = (android.widget.RelativeLayout) acty.findViewById(LayoutUserHeaderInfoContainerViewId);
        mTxtReportInfoView = (android.widget.TextView) acty.findViewById(TxtReportInfoViewId);
        mTxtReportTotleCountView = (android.widget.TextView) acty.findViewById(TxtReportTotleCountViewId);
        mLayoutReportTotleView = (android.widget.LinearLayout) acty.findViewById(LayoutReportTotleViewId);
        mUctrlRC1View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC1ViewId);
        mUctrlRC2View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC2ViewId);
        mUctrlRC3View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC3ViewId);
        mUctrlRC4View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC4ViewId);
        mUctrlRC5View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC5ViewId);
        mUctrlRC6View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC6ViewId);
        mUctrlRC7View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC7ViewId);
        mUctrlRC8View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC8ViewId);
        mUctrlRC9View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC9ViewId);
        mUctrlRC10View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC10ViewId);
        mUctrlRC11View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC11ViewId);
        mUctrlRC12View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC12ViewId);
    }   
    public android.widget.RelativeLayout getLayoutUserHeaderInfoContainerView() {
        return mLayoutUserHeaderInfoContainerView;
    }
    public android.widget.TextView getTxtReportInfoView() {
        return mTxtReportInfoView;
    }
    public android.widget.TextView getTxtReportTotleCountView() {
        return mTxtReportTotleCountView;
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
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC8View() {
        return mUctrlRC8View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC9View() {
        return mUctrlRC9View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC10View() {
        return mUctrlRC10View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC11View() {
        return mUctrlRC11View;
    }
    public com.yrkj.elderlycareassess.widget.UIReportCount getUctrlRC12View() {
        return mUctrlRC12View;
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
                case LayoutUserHeaderInfoContainerViewId:
                    setViewData(adp,getLayoutUserHeaderInfoContainerView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtReportInfoViewId:
                    setViewData(adp,getTxtReportInfoView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtReportTotleCountViewId:
                    setViewData(adp,getTxtReportTotleCountView(),data,joinData.formatString,joinData.data);
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
                case UctrlRC8ViewId:
                    setViewData(adp,getUctrlRC8View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC9ViewId:
                    setViewData(adp,getUctrlRC9View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC10ViewId:
                    setViewData(adp,getUctrlRC10View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC11ViewId:
                    setViewData(adp,getUctrlRC11View(),data,joinData.formatString,joinData.data);
                    break;
                case UctrlRC12ViewId:
                    setViewData(adp,getUctrlRC12View(),data,joinData.formatString,joinData.data);
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
                case LayoutUserHeaderInfoContainerViewId:
                    setViewData(adp,getLayoutUserHeaderInfoContainerView(),data,"",path);
                    break;
                case TxtReportInfoViewId:
                    setViewData(adp,getTxtReportInfoView(),data,"",path);
                    break;
                case TxtReportTotleCountViewId:
                    setViewData(adp,getTxtReportTotleCountView(),data,"",path);
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
                case UctrlRC8ViewId:
                    setViewData(adp,getUctrlRC8View(),data,"",path);
                    break;
                case UctrlRC9ViewId:
                    setViewData(adp,getUctrlRC9View(),data,"",path);
                    break;
                case UctrlRC10ViewId:
                    setViewData(adp,getUctrlRC10View(),data,"",path);
                    break;
                case UctrlRC11ViewId:
                    setViewData(adp,getUctrlRC11View(),data,"",path);
                    break;
                case UctrlRC12ViewId:
                    setViewData(adp,getUctrlRC12View(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
