package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessLiving extends BaseLayout{

    public static final int LayoutQuestionBar1ViewId = R.id.layoutQuestionBar1View;
    public static final int ChkQ1Item1ViewId = R.id.chkQ1Item1View;
    public static final int ChkQ1Item2ViewId = R.id.chkQ1Item2View;
    public static final int ChkQ1Item3ViewId = R.id.chkQ1Item3View;
    public static final int ChkQ1Item4ViewId = R.id.chkQ1Item4View;
    public static final int LayoutQuestionBar2ViewId = R.id.layoutQuestionBar2View;
    public static final int ChkQ2Item1ViewId = R.id.chkQ2Item1View;
    public static final int ChkQ2Item2ViewId = R.id.chkQ2Item2View;
    public static final int ChkQ2Item3ViewId = R.id.chkQ2Item3View;
    public static final int ChkQ2Item4ViewId = R.id.chkQ2Item4View;
    public static final int LayoutQuestionBar3ViewId = R.id.layoutQuestionBar3View;
    public static final int RdoQ3ItemViewId = R.id.rdoQ3ItemView;
    public static final int ChkQ3Item1ViewId = R.id.chkQ3Item1View;
    public static final int ChkQ3Item2ViewId = R.id.chkQ3Item2View;
    public static final int ChkQ3Item3ViewId = R.id.chkQ3Item3View;
    public static final int ChkQ3Item4ViewId = R.id.chkQ3Item4View;
    public static final int LayoutQuestionBar4ViewId = R.id.layoutQuestionBar4View;
    public static final int RdoQ4ItemViewId = R.id.rdoQ4ItemView;
    public static final int ChkQ4ItemYViewId = R.id.chkQ4ItemYView;
    public static final int ChkQ4ItemNViewId = R.id.chkQ4ItemNView;
    public static final int ChkQ4ItemLabViewId = R.id.chkQ4ItemLabView;
    public static final int ChkQ4ItemViewId = R.id.chkQ4ItemView;
    public static final int ChkQ4Item1ViewId = R.id.chkQ4Item1View;
    public static final int ChkQ4Item2ViewId = R.id.chkQ4Item2View;
    public static final int ChkQ4Item3ViewId = R.id.chkQ4Item3View;
    public static final int ChkQ4Item4ViewId = R.id.chkQ4Item4View;
    public static final int TxtQ4ItemEditValViewId = R.id.txtQ4ItemEditValView;
    public static final int LayoutQuestionBar5ViewId = R.id.layoutQuestionBar5View;
    public static final int ChkQ5Item1ViewId = R.id.chkQ5Item1View;
    public static final int ChkQ5Item2ViewId = R.id.chkQ5Item2View;
    public static final int ChkQ5Item3ViewId = R.id.chkQ5Item3View;
    public static final int TxtQ5ItemEditValViewId = R.id.txtQ5ItemEditValView;

    protected android.widget.RelativeLayout mLayoutQuestionBar1View;
    protected android.widget.CheckBox mChkQ1Item1View;
    protected android.widget.CheckBox mChkQ1Item2View;
    protected android.widget.CheckBox mChkQ1Item3View;
    protected android.widget.CheckBox mChkQ1Item4View;
    protected android.widget.RelativeLayout mLayoutQuestionBar2View;
    protected android.widget.CheckBox mChkQ2Item1View;
    protected android.widget.CheckBox mChkQ2Item2View;
    protected android.widget.CheckBox mChkQ2Item3View;
    protected android.widget.CheckBox mChkQ2Item4View;
    protected android.widget.RelativeLayout mLayoutQuestionBar3View;
    protected android.widget.RadioGroup mRdoQ3ItemView;
    protected android.widget.RadioButton mChkQ3Item1View;
    protected android.widget.RadioButton mChkQ3Item2View;
    protected android.widget.RadioButton mChkQ3Item3View;
    protected android.widget.RadioButton mChkQ3Item4View;
    protected android.widget.RelativeLayout mLayoutQuestionBar4View;
    protected android.widget.RadioGroup mRdoQ4ItemView;
    protected android.widget.RadioButton mChkQ4ItemYView;
    protected android.widget.RadioButton mChkQ4ItemNView;
    protected android.widget.TextView mChkQ4ItemLabView;
    protected android.widget.LinearLayout mChkQ4ItemView;
    protected android.widget.CheckBox mChkQ4Item1View;
    protected android.widget.CheckBox mChkQ4Item2View;
    protected android.widget.CheckBox mChkQ4Item3View;
    protected android.widget.CheckBox mChkQ4Item4View;
    protected android.widget.EditText mTxtQ4ItemEditValView;
    protected android.widget.RelativeLayout mLayoutQuestionBar5View;
    protected android.widget.CheckBox mChkQ5Item1View;
    protected android.widget.CheckBox mChkQ5Item2View;
    protected android.widget.CheckBox mChkQ5Item3View;
    protected android.widget.EditText mTxtQ5ItemEditValView;

    protected Activity mCurActy;

    public FragmentAssessLiving(Activity acty){
        mCurActy = acty;
        mLayoutQuestionBar1View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar1ViewId);
        mChkQ1Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item1ViewId);
        mChkQ1Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item2ViewId);
        mChkQ1Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item3ViewId);
        mChkQ1Item4View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item4ViewId);
        mLayoutQuestionBar2View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar2ViewId);
        mChkQ2Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item1ViewId);
        mChkQ2Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item2ViewId);
        mChkQ2Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item3ViewId);
        mChkQ2Item4View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item4ViewId);
        mLayoutQuestionBar3View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar3ViewId);
        mRdoQ3ItemView = (android.widget.RadioGroup) acty.findViewById(RdoQ3ItemViewId);
        mChkQ3Item1View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item1ViewId);
        mChkQ3Item2View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item2ViewId);
        mChkQ3Item3View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item3ViewId);
        mChkQ3Item4View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item4ViewId);
        mLayoutQuestionBar4View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar4ViewId);
        mRdoQ4ItemView = (android.widget.RadioGroup) acty.findViewById(RdoQ4ItemViewId);
        mChkQ4ItemYView = (android.widget.RadioButton) acty.findViewById(ChkQ4ItemYViewId);
        mChkQ4ItemNView = (android.widget.RadioButton) acty.findViewById(ChkQ4ItemNViewId);
        mChkQ4ItemLabView = (android.widget.TextView) acty.findViewById(ChkQ4ItemLabViewId);
        mChkQ4ItemView = (android.widget.LinearLayout) acty.findViewById(ChkQ4ItemViewId);
        mChkQ4Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item1ViewId);
        mChkQ4Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item2ViewId);
        mChkQ4Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item3ViewId);
        mChkQ4Item4View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item4ViewId);
        mTxtQ4ItemEditValView = (android.widget.EditText) acty.findViewById(TxtQ4ItemEditValViewId);
        mLayoutQuestionBar5View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar5ViewId);
        mChkQ5Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ5Item1ViewId);
        mChkQ5Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ5Item2ViewId);
        mChkQ5Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ5Item3ViewId);
        mTxtQ5ItemEditValView = (android.widget.EditText) acty.findViewById(TxtQ5ItemEditValViewId);
    }   

    public FragmentAssessLiving(android.view.View acty){
        mLayoutQuestionBar1View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar1ViewId);
        mChkQ1Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item1ViewId);
        mChkQ1Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item2ViewId);
        mChkQ1Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item3ViewId);
        mChkQ1Item4View = (android.widget.CheckBox) acty.findViewById(ChkQ1Item4ViewId);
        mLayoutQuestionBar2View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar2ViewId);
        mChkQ2Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item1ViewId);
        mChkQ2Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item2ViewId);
        mChkQ2Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item3ViewId);
        mChkQ2Item4View = (android.widget.CheckBox) acty.findViewById(ChkQ2Item4ViewId);
        mLayoutQuestionBar3View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar3ViewId);
        mRdoQ3ItemView = (android.widget.RadioGroup) acty.findViewById(RdoQ3ItemViewId);
        mChkQ3Item1View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item1ViewId);
        mChkQ3Item2View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item2ViewId);
        mChkQ3Item3View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item3ViewId);
        mChkQ3Item4View = (android.widget.RadioButton) acty.findViewById(ChkQ3Item4ViewId);
        mLayoutQuestionBar4View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar4ViewId);
        mRdoQ4ItemView = (android.widget.RadioGroup) acty.findViewById(RdoQ4ItemViewId);
        mChkQ4ItemYView = (android.widget.RadioButton) acty.findViewById(ChkQ4ItemYViewId);
        mChkQ4ItemNView = (android.widget.RadioButton) acty.findViewById(ChkQ4ItemNViewId);
        mChkQ4ItemLabView = (android.widget.TextView) acty.findViewById(ChkQ4ItemLabViewId);
        mChkQ4ItemView = (android.widget.LinearLayout) acty.findViewById(ChkQ4ItemViewId);
        mChkQ4Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item1ViewId);
        mChkQ4Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item2ViewId);
        mChkQ4Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item3ViewId);
        mChkQ4Item4View = (android.widget.CheckBox) acty.findViewById(ChkQ4Item4ViewId);
        mTxtQ4ItemEditValView = (android.widget.EditText) acty.findViewById(TxtQ4ItemEditValViewId);
        mLayoutQuestionBar5View = (android.widget.RelativeLayout) acty.findViewById(LayoutQuestionBar5ViewId);
        mChkQ5Item1View = (android.widget.CheckBox) acty.findViewById(ChkQ5Item1ViewId);
        mChkQ5Item2View = (android.widget.CheckBox) acty.findViewById(ChkQ5Item2ViewId);
        mChkQ5Item3View = (android.widget.CheckBox) acty.findViewById(ChkQ5Item3ViewId);
        mTxtQ5ItemEditValView = (android.widget.EditText) acty.findViewById(TxtQ5ItemEditValViewId);
    }   
    public android.widget.RelativeLayout getLayoutQuestionBar1View() {
        return mLayoutQuestionBar1View;
    }
    public android.widget.CheckBox getChkQ1Item1View() {
        return mChkQ1Item1View;
    }
    public android.widget.CheckBox getChkQ1Item2View() {
        return mChkQ1Item2View;
    }
    public android.widget.CheckBox getChkQ1Item3View() {
        return mChkQ1Item3View;
    }
    public android.widget.CheckBox getChkQ1Item4View() {
        return mChkQ1Item4View;
    }
    public android.widget.RelativeLayout getLayoutQuestionBar2View() {
        return mLayoutQuestionBar2View;
    }
    public android.widget.CheckBox getChkQ2Item1View() {
        return mChkQ2Item1View;
    }
    public android.widget.CheckBox getChkQ2Item2View() {
        return mChkQ2Item2View;
    }
    public android.widget.CheckBox getChkQ2Item3View() {
        return mChkQ2Item3View;
    }
    public android.widget.CheckBox getChkQ2Item4View() {
        return mChkQ2Item4View;
    }
    public android.widget.RelativeLayout getLayoutQuestionBar3View() {
        return mLayoutQuestionBar3View;
    }
    public android.widget.RadioGroup getRdoQ3ItemView() {
        return mRdoQ3ItemView;
    }
    public android.widget.RadioButton getChkQ3Item1View() {
        return mChkQ3Item1View;
    }
    public android.widget.RadioButton getChkQ3Item2View() {
        return mChkQ3Item2View;
    }
    public android.widget.RadioButton getChkQ3Item3View() {
        return mChkQ3Item3View;
    }
    public android.widget.RadioButton getChkQ3Item4View() {
        return mChkQ3Item4View;
    }
    public android.widget.RelativeLayout getLayoutQuestionBar4View() {
        return mLayoutQuestionBar4View;
    }
    public android.widget.RadioGroup getRdoQ4ItemView() {
        return mRdoQ4ItemView;
    }
    public android.widget.RadioButton getChkQ4ItemYView() {
        return mChkQ4ItemYView;
    }
    public android.widget.RadioButton getChkQ4ItemNView() {
        return mChkQ4ItemNView;
    }
    public android.widget.TextView getChkQ4ItemLabView() {
        return mChkQ4ItemLabView;
    }
    public android.widget.LinearLayout getChkQ4ItemView() {
        return mChkQ4ItemView;
    }
    public android.widget.CheckBox getChkQ4Item1View() {
        return mChkQ4Item1View;
    }
    public android.widget.CheckBox getChkQ4Item2View() {
        return mChkQ4Item2View;
    }
    public android.widget.CheckBox getChkQ4Item3View() {
        return mChkQ4Item3View;
    }
    public android.widget.CheckBox getChkQ4Item4View() {
        return mChkQ4Item4View;
    }
    public android.widget.EditText getTxtQ4ItemEditValView() {
        return mTxtQ4ItemEditValView;
    }
    public android.widget.RelativeLayout getLayoutQuestionBar5View() {
        return mLayoutQuestionBar5View;
    }
    public android.widget.CheckBox getChkQ5Item1View() {
        return mChkQ5Item1View;
    }
    public android.widget.CheckBox getChkQ5Item2View() {
        return mChkQ5Item2View;
    }
    public android.widget.CheckBox getChkQ5Item3View() {
        return mChkQ5Item3View;
    }
    public android.widget.EditText getTxtQ5ItemEditValView() {
        return mTxtQ5ItemEditValView;
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
                case LayoutQuestionBar1ViewId:
                    setViewData(adp,getLayoutQuestionBar1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ1Item1ViewId:
                    setViewData(adp,getChkQ1Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ1Item2ViewId:
                    setViewData(adp,getChkQ1Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ1Item3ViewId:
                    setViewData(adp,getChkQ1Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ1Item4ViewId:
                    setViewData(adp,getChkQ1Item4View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutQuestionBar2ViewId:
                    setViewData(adp,getLayoutQuestionBar2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ2Item1ViewId:
                    setViewData(adp,getChkQ2Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ2Item2ViewId:
                    setViewData(adp,getChkQ2Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ2Item3ViewId:
                    setViewData(adp,getChkQ2Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ2Item4ViewId:
                    setViewData(adp,getChkQ2Item4View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutQuestionBar3ViewId:
                    setViewData(adp,getLayoutQuestionBar3View(),data,joinData.formatString,joinData.data);
                    break;
                case RdoQ3ItemViewId:
                    setViewData(adp,getRdoQ3ItemView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ3Item1ViewId:
                    setViewData(adp,getChkQ3Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ3Item2ViewId:
                    setViewData(adp,getChkQ3Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ3Item3ViewId:
                    setViewData(adp,getChkQ3Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ3Item4ViewId:
                    setViewData(adp,getChkQ3Item4View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutQuestionBar4ViewId:
                    setViewData(adp,getLayoutQuestionBar4View(),data,joinData.formatString,joinData.data);
                    break;
                case RdoQ4ItemViewId:
                    setViewData(adp,getRdoQ4ItemView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4ItemYViewId:
                    setViewData(adp,getChkQ4ItemYView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4ItemNViewId:
                    setViewData(adp,getChkQ4ItemNView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4ItemLabViewId:
                    setViewData(adp,getChkQ4ItemLabView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4ItemViewId:
                    setViewData(adp,getChkQ4ItemView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4Item1ViewId:
                    setViewData(adp,getChkQ4Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4Item2ViewId:
                    setViewData(adp,getChkQ4Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4Item3ViewId:
                    setViewData(adp,getChkQ4Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ4Item4ViewId:
                    setViewData(adp,getChkQ4Item4View(),data,joinData.formatString,joinData.data);
                    break;
                case TxtQ4ItemEditValViewId:
                    setViewData(adp,getTxtQ4ItemEditValView(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutQuestionBar5ViewId:
                    setViewData(adp,getLayoutQuestionBar5View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ5Item1ViewId:
                    setViewData(adp,getChkQ5Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ5Item2ViewId:
                    setViewData(adp,getChkQ5Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkQ5Item3ViewId:
                    setViewData(adp,getChkQ5Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case TxtQ5ItemEditValViewId:
                    setViewData(adp,getTxtQ5ItemEditValView(),data,joinData.formatString,joinData.data);
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
                case LayoutQuestionBar1ViewId:
                    setViewData(adp,getLayoutQuestionBar1View(),data,"",path);
                    break;
                case ChkQ1Item1ViewId:
                    setViewData(adp,getChkQ1Item1View(),data,"",path);
                    break;
                case ChkQ1Item2ViewId:
                    setViewData(adp,getChkQ1Item2View(),data,"",path);
                    break;
                case ChkQ1Item3ViewId:
                    setViewData(adp,getChkQ1Item3View(),data,"",path);
                    break;
                case ChkQ1Item4ViewId:
                    setViewData(adp,getChkQ1Item4View(),data,"",path);
                    break;
                case LayoutQuestionBar2ViewId:
                    setViewData(adp,getLayoutQuestionBar2View(),data,"",path);
                    break;
                case ChkQ2Item1ViewId:
                    setViewData(adp,getChkQ2Item1View(),data,"",path);
                    break;
                case ChkQ2Item2ViewId:
                    setViewData(adp,getChkQ2Item2View(),data,"",path);
                    break;
                case ChkQ2Item3ViewId:
                    setViewData(adp,getChkQ2Item3View(),data,"",path);
                    break;
                case ChkQ2Item4ViewId:
                    setViewData(adp,getChkQ2Item4View(),data,"",path);
                    break;
                case LayoutQuestionBar3ViewId:
                    setViewData(adp,getLayoutQuestionBar3View(),data,"",path);
                    break;
                case RdoQ3ItemViewId:
                    setViewData(adp,getRdoQ3ItemView(),data,"",path);
                    break;
                case ChkQ3Item1ViewId:
                    setViewData(adp,getChkQ3Item1View(),data,"",path);
                    break;
                case ChkQ3Item2ViewId:
                    setViewData(adp,getChkQ3Item2View(),data,"",path);
                    break;
                case ChkQ3Item3ViewId:
                    setViewData(adp,getChkQ3Item3View(),data,"",path);
                    break;
                case ChkQ3Item4ViewId:
                    setViewData(adp,getChkQ3Item4View(),data,"",path);
                    break;
                case LayoutQuestionBar4ViewId:
                    setViewData(adp,getLayoutQuestionBar4View(),data,"",path);
                    break;
                case RdoQ4ItemViewId:
                    setViewData(adp,getRdoQ4ItemView(),data,"",path);
                    break;
                case ChkQ4ItemYViewId:
                    setViewData(adp,getChkQ4ItemYView(),data,"",path);
                    break;
                case ChkQ4ItemNViewId:
                    setViewData(adp,getChkQ4ItemNView(),data,"",path);
                    break;
                case ChkQ4ItemLabViewId:
                    setViewData(adp,getChkQ4ItemLabView(),data,"",path);
                    break;
                case ChkQ4ItemViewId:
                    setViewData(adp,getChkQ4ItemView(),data,"",path);
                    break;
                case ChkQ4Item1ViewId:
                    setViewData(adp,getChkQ4Item1View(),data,"",path);
                    break;
                case ChkQ4Item2ViewId:
                    setViewData(adp,getChkQ4Item2View(),data,"",path);
                    break;
                case ChkQ4Item3ViewId:
                    setViewData(adp,getChkQ4Item3View(),data,"",path);
                    break;
                case ChkQ4Item4ViewId:
                    setViewData(adp,getChkQ4Item4View(),data,"",path);
                    break;
                case TxtQ4ItemEditValViewId:
                    setViewData(adp,getTxtQ4ItemEditValView(),data,"",path);
                    break;
                case LayoutQuestionBar5ViewId:
                    setViewData(adp,getLayoutQuestionBar5View(),data,"",path);
                    break;
                case ChkQ5Item1ViewId:
                    setViewData(adp,getChkQ5Item1View(),data,"",path);
                    break;
                case ChkQ5Item2ViewId:
                    setViewData(adp,getChkQ5Item2View(),data,"",path);
                    break;
                case ChkQ5Item3ViewId:
                    setViewData(adp,getChkQ5Item3View(),data,"",path);
                    break;
                case TxtQ5ItemEditValViewId:
                    setViewData(adp,getTxtQ5ItemEditValView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
