package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class ActivityMain extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int TestBBBViewId = R.id.testBBBView;
    public static final int TestAAAViewId = R.id.testAAAView;
    public static final int TestClickViewId = R.id.testClickView;
    public static final int UctrlRC1ViewId = R.id.uctrlRC1View;
    public static final int UctrlRC2ViewId = R.id.uctrlRC2View;
    public static final int UctrlRC3ViewId = R.id.uctrlRC3View;
    public static final int UctrlRC4ViewId = R.id.uctrlRC4View;
    public static final int UctrlRC5ViewId = R.id.uctrlRC5View;
    public static final int UctrlRC6ViewId = R.id.uctrlRC6View;
    public static final int UctrlRC7ViewId = R.id.uctrlRC7View;

    protected android.widget.FrameLayout mContainer;
    protected android.widget.FrameLayout mTestBBBView;
    protected android.widget.RelativeLayout mTestAAAView;
    protected android.widget.Button mTestClickView;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC1View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC2View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC3View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC4View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC5View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC6View;
    protected com.yrkj.elderlycareassess.widget.UIReportCount mUctrlRC7View;

    protected Activity mCurActy;

    public ActivityMain(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.FrameLayout) acty.findViewById(ContainerId);
        mTestBBBView = (android.widget.FrameLayout) acty.findViewById(TestBBBViewId);
        mTestAAAView = (android.widget.RelativeLayout) acty.findViewById(TestAAAViewId);
        mTestClickView = (android.widget.Button) acty.findViewById(TestClickViewId);
        mUctrlRC1View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC1ViewId);
        mUctrlRC2View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC2ViewId);
        mUctrlRC3View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC3ViewId);
        mUctrlRC4View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC4ViewId);
        mUctrlRC5View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC5ViewId);
        mUctrlRC6View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC6ViewId);
        mUctrlRC7View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC7ViewId);
    }   

    public ActivityMain(android.view.View acty){
        mContainer = (android.widget.FrameLayout) acty.findViewById(ContainerId);
        mTestBBBView = (android.widget.FrameLayout) acty.findViewById(TestBBBViewId);
        mTestAAAView = (android.widget.RelativeLayout) acty.findViewById(TestAAAViewId);
        mTestClickView = (android.widget.Button) acty.findViewById(TestClickViewId);
        mUctrlRC1View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC1ViewId);
        mUctrlRC2View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC2ViewId);
        mUctrlRC3View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC3ViewId);
        mUctrlRC4View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC4ViewId);
        mUctrlRC5View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC5ViewId);
        mUctrlRC6View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC6ViewId);
        mUctrlRC7View = (com.yrkj.elderlycareassess.widget.UIReportCount) acty.findViewById(UctrlRC7ViewId);
    }   
    public android.widget.FrameLayout getContainer() {
        return mContainer;
    }
    public android.widget.FrameLayout getTestBBBView() {
        return mTestBBBView;
    }
    public android.widget.RelativeLayout getTestAAAView() {
        return mTestAAAView;
    }
    public android.widget.Button getTestClickView() {
        return mTestClickView;
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
                case TestBBBViewId:
                    setViewData(adp,getTestBBBView(),data,joinData.formatString,joinData.data);
                    break;
                case TestAAAViewId:
                    setViewData(adp,getTestAAAView(),data,joinData.formatString,joinData.data);
                    break;
                case TestClickViewId:
                    setViewData(adp,getTestClickView(),data,joinData.formatString,joinData.data);
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
                case TestBBBViewId:
                    setViewData(adp,getTestBBBView(),data,"",path);
                    break;
                case TestAAAViewId:
                    setViewData(adp,getTestAAAView(),data,"",path);
                    break;
                case TestClickViewId:
                    setViewData(adp,getTestClickView(),data,"",path);
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
                    
                default:
                    break;
                }
            }
         }
    }
}
