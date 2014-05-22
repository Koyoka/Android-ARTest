package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessService extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int ChkService1ViewId = R.id.chkService1View;
    public static final int LayoutService1ContentViewId = R.id.layoutService1ContentView;
    public static final int TxtService1Item1ValViewId = R.id.txtService1Item1ValView;
    public static final int ChkService2ViewId = R.id.chkService2View;
    public static final int LayoutService2ContentViewId = R.id.layoutService2ContentView;
    public static final int ChkService2Item1ViewId = R.id.chkService2Item1View;
    public static final int LayoutService2Item1ContentViewId = R.id.layoutService2Item1ContentView;
    public static final int TxtService2Item1ValViewId = R.id.txtService2Item1ValView;
    public static final int ChkService2Item2ViewId = R.id.chkService2Item2View;
    public static final int LayoutService2Item2ContentViewId = R.id.layoutService2Item2ContentView;
    public static final int TxtService2Item2ValViewId = R.id.txtService2Item2ValView;
    public static final int ChkService2Item3ViewId = R.id.chkService2Item3View;
    public static final int ChkService3ViewId = R.id.chkService3View;
    public static final int LayoutService3ContentViewId = R.id.layoutService3ContentView;
    public static final int RdoService3item1ViewId = R.id.rdoService3item1View;
    public static final int RdoService3item2ViewId = R.id.rdoService3item2View;
    public static final int ChkService4ViewId = R.id.chkService4View;
    public static final int LayoutService4ContentViewId = R.id.layoutService4ContentView;
    public static final int ChkService4Item1ViewId = R.id.chkService4Item1View;
    public static final int LayoutService4Item1ContentViewId = R.id.layoutService4Item1ContentView;
    public static final int TxtService4Item1ValViewId = R.id.txtService4Item1ValView;
    public static final int ChkService4Item2ViewId = R.id.chkService4Item2View;
    public static final int LayoutService4Item2ContentViewId = R.id.layoutService4Item2ContentView;
    public static final int TxtService4Item2ValViewId = R.id.txtService4Item2ValView;
    public static final int ChkService5ViewId = R.id.chkService5View;
    public static final int LayoutService5ContentViewId = R.id.layoutService5ContentView;
    public static final int RdoService5item1ViewId = R.id.rdoService5item1View;
    public static final int RdoService5item2ViewId = R.id.rdoService5item2View;
    public static final int ChkService6ViewId = R.id.chkService6View;
    public static final int LayoutService6ContentViewId = R.id.layoutService6ContentView;
    public static final int ChkService6Item1ViewId = R.id.chkService6Item1View;
    public static final int LayoutService6Item1ContentViewId = R.id.layoutService6Item1ContentView;
    public static final int TxtService6Item1ValViewId = R.id.txtService6Item1ValView;
    public static final int ChkService6Item2ViewId = R.id.chkService6Item2View;
    public static final int LayoutService6Item2ContentViewId = R.id.layoutService6Item2ContentView;
    public static final int TxtService6Item2ValViewId = R.id.txtService6Item2ValView;
    public static final int ChkService6Item3ViewId = R.id.chkService6Item3View;
    public static final int ChkService6Item4ViewId = R.id.chkService6Item4View;
    public static final int LayoutService6Item4ContentViewId = R.id.layoutService6Item4ContentView;
    public static final int TxtService6Item4ValViewId = R.id.txtService6Item4ValView;
    public static final int ChkService7ViewId = R.id.chkService7View;
    public static final int LayoutService7ContentViewId = R.id.layoutService7ContentView;
    public static final int ChkService7Item1ViewId = R.id.chkService7Item1View;
    public static final int ChkService7Item2ViewId = R.id.chkService7Item2View;
    public static final int ChkService7Item3ViewId = R.id.chkService7Item3View;
    public static final int ChkService7Item4ViewId = R.id.chkService7Item4View;
    public static final int ChkService8ViewId = R.id.chkService8View;
    public static final int LayoutService8ContentViewId = R.id.layoutService8ContentView;
    public static final int ChkService8Item1ViewId = R.id.chkService8Item1View;
    public static final int LayoutService8Item1ContentViewId = R.id.layoutService8Item1ContentView;
    public static final int TxtService8Item1ValViewId = R.id.txtService8Item1ValView;
    public static final int ChkService8Item2ViewId = R.id.chkService8Item2View;
    public static final int LayoutService8Item2ContentViewId = R.id.layoutService8Item2ContentView;
    public static final int TxtService8Item2ValViewId = R.id.txtService8Item2ValView;
    public static final int ChkService9ViewId = R.id.chkService9View;
    public static final int LayoutService9ContentViewId = R.id.layoutService9ContentView;
    public static final int ChkService9Item1ViewId = R.id.chkService9Item1View;
    public static final int ChkService9Item2ViewId = R.id.chkService9Item2View;
    public static final int ChkService9Item3ViewId = R.id.chkService9Item3View;
    public static final int ChkService10ViewId = R.id.chkService10View;
    public static final int LayoutService10ContentViewId = R.id.layoutService10ContentView;
    public static final int TxtService10Item1ValViewId = R.id.txtService10Item1ValView;
    public static final int ChkService11ViewId = R.id.chkService11View;
    public static final int LayoutService11ContentViewId = R.id.layoutService11ContentView;
    public static final int TxtService11Item1ValViewId = R.id.txtService11Item1ValView;
    public static final int ChkService12ViewId = R.id.chkService12View;
    public static final int LayoutService12ContentViewId = R.id.layoutService12ContentView;
    public static final int ChkService12Item1ViewId = R.id.chkService12Item1View;
    public static final int LayoutService12Item1ContentViewId = R.id.layoutService12Item1ContentView;
    public static final int TxtService12Item1ValViewId = R.id.txtService12Item1ValView;
    public static final int ChkService12Item2ViewId = R.id.chkService12Item2View;
    public static final int LayoutService12Item2ContentViewId = R.id.layoutService12Item2ContentView;
    public static final int TxtService12Item2ValViewId = R.id.txtService12Item2ValView;
    public static final int ChkService12Item3ViewId = R.id.chkService12Item3View;
    public static final int ChkService13ViewId = R.id.chkService13View;
    public static final int LayoutService13ContentViewId = R.id.layoutService13ContentView;
    public static final int TxtService13Item1ValViewId = R.id.txtService13Item1ValView;
    public static final int ChkService14ViewId = R.id.chkService14View;
    public static final int LayoutService14ContentViewId = R.id.layoutService14ContentView;
    public static final int ChkService14Item1ViewId = R.id.chkService14Item1View;
    public static final int ChkService14Item2ViewId = R.id.chkService14Item2View;
    public static final int ChkService15ViewId = R.id.chkService15View;
    public static final int LayoutService15ContentViewId = R.id.layoutService15ContentView;
    public static final int TxtService15Item1ValViewId = R.id.txtService15Item1ValView;
    public static final int ChkService16ViewId = R.id.chkService16View;
    public static final int LayoutService16ContentViewId = R.id.layoutService16ContentView;
    public static final int TxtService16Item1ValViewId = R.id.txtService16Item1ValView;
    public static final int ChkService17ViewId = R.id.chkService17View;
    public static final int LayoutService17ContentViewId = R.id.layoutService17ContentView;
    public static final int TxtService17Item1ValViewId = R.id.txtService17Item1ValView;
    public static final int ChkService18ViewId = R.id.chkService18View;
    public static final int LayoutService18ContentViewId = R.id.layoutService18ContentView;
    public static final int TxtService18Item1ValViewId = R.id.txtService18Item1ValView;

    protected android.widget.ScrollView mContainer;
    protected android.widget.CheckBox mChkService1View;
    protected android.widget.LinearLayout mLayoutService1ContentView;
    protected android.widget.Button mTxtService1Item1ValView;
    protected android.widget.CheckBox mChkService2View;
    protected android.widget.TableLayout mLayoutService2ContentView;
    protected android.widget.CheckBox mChkService2Item1View;
    protected android.widget.LinearLayout mLayoutService2Item1ContentView;
    protected android.widget.Button mTxtService2Item1ValView;
    protected android.widget.CheckBox mChkService2Item2View;
    protected android.widget.LinearLayout mLayoutService2Item2ContentView;
    protected android.widget.Button mTxtService2Item2ValView;
    protected android.widget.CheckBox mChkService2Item3View;
    protected android.widget.CheckBox mChkService3View;
    protected android.widget.RadioGroup mLayoutService3ContentView;
    protected android.widget.RadioButton mRdoService3item1View;
    protected android.widget.RadioButton mRdoService3item2View;
    protected android.widget.CheckBox mChkService4View;
    protected android.widget.TableLayout mLayoutService4ContentView;
    protected android.widget.RadioButton mChkService4Item1View;
    protected android.widget.LinearLayout mLayoutService4Item1ContentView;
    protected android.widget.Button mTxtService4Item1ValView;
    protected android.widget.RadioButton mChkService4Item2View;
    protected android.widget.LinearLayout mLayoutService4Item2ContentView;
    protected android.widget.Button mTxtService4Item2ValView;
    protected android.widget.CheckBox mChkService5View;
    protected android.widget.RadioGroup mLayoutService5ContentView;
    protected android.widget.RadioButton mRdoService5item1View;
    protected android.widget.RadioButton mRdoService5item2View;
    protected android.widget.CheckBox mChkService6View;
    protected android.widget.TableLayout mLayoutService6ContentView;
    protected android.widget.CheckBox mChkService6Item1View;
    protected android.widget.LinearLayout mLayoutService6Item1ContentView;
    protected android.widget.Button mTxtService6Item1ValView;
    protected android.widget.CheckBox mChkService6Item2View;
    protected android.widget.LinearLayout mLayoutService6Item2ContentView;
    protected android.widget.Button mTxtService6Item2ValView;
    protected android.widget.CheckBox mChkService6Item3View;
    protected android.widget.CheckBox mChkService6Item4View;
    protected android.widget.LinearLayout mLayoutService6Item4ContentView;
    protected android.widget.Button mTxtService6Item4ValView;
    protected android.widget.CheckBox mChkService7View;
    protected android.widget.LinearLayout mLayoutService7ContentView;
    protected android.widget.CheckBox mChkService7Item1View;
    protected android.widget.CheckBox mChkService7Item2View;
    protected android.widget.CheckBox mChkService7Item3View;
    protected android.widget.CheckBox mChkService7Item4View;
    protected android.widget.CheckBox mChkService8View;
    protected android.widget.TableLayout mLayoutService8ContentView;
    protected android.widget.CheckBox mChkService8Item1View;
    protected android.widget.LinearLayout mLayoutService8Item1ContentView;
    protected android.widget.Button mTxtService8Item1ValView;
    protected android.widget.CheckBox mChkService8Item2View;
    protected android.widget.LinearLayout mLayoutService8Item2ContentView;
    protected android.widget.Button mTxtService8Item2ValView;
    protected android.widget.CheckBox mChkService9View;
    protected android.widget.LinearLayout mLayoutService9ContentView;
    protected android.widget.CheckBox mChkService9Item1View;
    protected android.widget.CheckBox mChkService9Item2View;
    protected android.widget.CheckBox mChkService9Item3View;
    protected android.widget.CheckBox mChkService10View;
    protected android.widget.LinearLayout mLayoutService10ContentView;
    protected android.widget.Button mTxtService10Item1ValView;
    protected android.widget.CheckBox mChkService11View;
    protected android.widget.LinearLayout mLayoutService11ContentView;
    protected android.widget.Button mTxtService11Item1ValView;
    protected android.widget.CheckBox mChkService12View;
    protected android.widget.TableLayout mLayoutService12ContentView;
    protected android.widget.CheckBox mChkService12Item1View;
    protected android.widget.LinearLayout mLayoutService12Item1ContentView;
    protected android.widget.Button mTxtService12Item1ValView;
    protected android.widget.CheckBox mChkService12Item2View;
    protected android.widget.LinearLayout mLayoutService12Item2ContentView;
    protected android.widget.Button mTxtService12Item2ValView;
    protected android.widget.CheckBox mChkService12Item3View;
    protected android.widget.CheckBox mChkService13View;
    protected android.widget.LinearLayout mLayoutService13ContentView;
    protected android.widget.Button mTxtService13Item1ValView;
    protected android.widget.CheckBox mChkService14View;
    protected android.widget.LinearLayout mLayoutService14ContentView;
    protected android.widget.CheckBox mChkService14Item1View;
    protected android.widget.CheckBox mChkService14Item2View;
    protected android.widget.CheckBox mChkService15View;
    protected android.widget.LinearLayout mLayoutService15ContentView;
    protected android.widget.Button mTxtService15Item1ValView;
    protected android.widget.CheckBox mChkService16View;
    protected android.widget.LinearLayout mLayoutService16ContentView;
    protected android.widget.Button mTxtService16Item1ValView;
    protected android.widget.CheckBox mChkService17View;
    protected android.widget.LinearLayout mLayoutService17ContentView;
    protected android.widget.EditText mTxtService17Item1ValView;
    protected android.widget.CheckBox mChkService18View;
    protected android.widget.LinearLayout mLayoutService18ContentView;
    protected android.widget.EditText mTxtService18Item1ValView;

    protected Activity mCurActy;

    public FragmentAssessService(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.ScrollView) acty.findViewById(ContainerId);
        mChkService1View = (android.widget.CheckBox) acty.findViewById(ChkService1ViewId);
        mLayoutService1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService1ContentViewId);
        mTxtService1Item1ValView = (android.widget.Button) acty.findViewById(TxtService1Item1ValViewId);
        mChkService2View = (android.widget.CheckBox) acty.findViewById(ChkService2ViewId);
        mLayoutService2ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService2ContentViewId);
        mChkService2Item1View = (android.widget.CheckBox) acty.findViewById(ChkService2Item1ViewId);
        mLayoutService2Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService2Item1ContentViewId);
        mTxtService2Item1ValView = (android.widget.Button) acty.findViewById(TxtService2Item1ValViewId);
        mChkService2Item2View = (android.widget.CheckBox) acty.findViewById(ChkService2Item2ViewId);
        mLayoutService2Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService2Item2ContentViewId);
        mTxtService2Item2ValView = (android.widget.Button) acty.findViewById(TxtService2Item2ValViewId);
        mChkService2Item3View = (android.widget.CheckBox) acty.findViewById(ChkService2Item3ViewId);
        mChkService3View = (android.widget.CheckBox) acty.findViewById(ChkService3ViewId);
        mLayoutService3ContentView = (android.widget.RadioGroup) acty.findViewById(LayoutService3ContentViewId);
        mRdoService3item1View = (android.widget.RadioButton) acty.findViewById(RdoService3item1ViewId);
        mRdoService3item2View = (android.widget.RadioButton) acty.findViewById(RdoService3item2ViewId);
        mChkService4View = (android.widget.CheckBox) acty.findViewById(ChkService4ViewId);
        mLayoutService4ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService4ContentViewId);
        mChkService4Item1View = (android.widget.RadioButton) acty.findViewById(ChkService4Item1ViewId);
        mLayoutService4Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService4Item1ContentViewId);
        mTxtService4Item1ValView = (android.widget.Button) acty.findViewById(TxtService4Item1ValViewId);
        mChkService4Item2View = (android.widget.RadioButton) acty.findViewById(ChkService4Item2ViewId);
        mLayoutService4Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService4Item2ContentViewId);
        mTxtService4Item2ValView = (android.widget.Button) acty.findViewById(TxtService4Item2ValViewId);
        mChkService5View = (android.widget.CheckBox) acty.findViewById(ChkService5ViewId);
        mLayoutService5ContentView = (android.widget.RadioGroup) acty.findViewById(LayoutService5ContentViewId);
        mRdoService5item1View = (android.widget.RadioButton) acty.findViewById(RdoService5item1ViewId);
        mRdoService5item2View = (android.widget.RadioButton) acty.findViewById(RdoService5item2ViewId);
        mChkService6View = (android.widget.CheckBox) acty.findViewById(ChkService6ViewId);
        mLayoutService6ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService6ContentViewId);
        mChkService6Item1View = (android.widget.CheckBox) acty.findViewById(ChkService6Item1ViewId);
        mLayoutService6Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService6Item1ContentViewId);
        mTxtService6Item1ValView = (android.widget.Button) acty.findViewById(TxtService6Item1ValViewId);
        mChkService6Item2View = (android.widget.CheckBox) acty.findViewById(ChkService6Item2ViewId);
        mLayoutService6Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService6Item2ContentViewId);
        mTxtService6Item2ValView = (android.widget.Button) acty.findViewById(TxtService6Item2ValViewId);
        mChkService6Item3View = (android.widget.CheckBox) acty.findViewById(ChkService6Item3ViewId);
        mChkService6Item4View = (android.widget.CheckBox) acty.findViewById(ChkService6Item4ViewId);
        mLayoutService6Item4ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService6Item4ContentViewId);
        mTxtService6Item4ValView = (android.widget.Button) acty.findViewById(TxtService6Item4ValViewId);
        mChkService7View = (android.widget.CheckBox) acty.findViewById(ChkService7ViewId);
        mLayoutService7ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService7ContentViewId);
        mChkService7Item1View = (android.widget.CheckBox) acty.findViewById(ChkService7Item1ViewId);
        mChkService7Item2View = (android.widget.CheckBox) acty.findViewById(ChkService7Item2ViewId);
        mChkService7Item3View = (android.widget.CheckBox) acty.findViewById(ChkService7Item3ViewId);
        mChkService7Item4View = (android.widget.CheckBox) acty.findViewById(ChkService7Item4ViewId);
        mChkService8View = (android.widget.CheckBox) acty.findViewById(ChkService8ViewId);
        mLayoutService8ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService8ContentViewId);
        mChkService8Item1View = (android.widget.CheckBox) acty.findViewById(ChkService8Item1ViewId);
        mLayoutService8Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService8Item1ContentViewId);
        mTxtService8Item1ValView = (android.widget.Button) acty.findViewById(TxtService8Item1ValViewId);
        mChkService8Item2View = (android.widget.CheckBox) acty.findViewById(ChkService8Item2ViewId);
        mLayoutService8Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService8Item2ContentViewId);
        mTxtService8Item2ValView = (android.widget.Button) acty.findViewById(TxtService8Item2ValViewId);
        mChkService9View = (android.widget.CheckBox) acty.findViewById(ChkService9ViewId);
        mLayoutService9ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService9ContentViewId);
        mChkService9Item1View = (android.widget.CheckBox) acty.findViewById(ChkService9Item1ViewId);
        mChkService9Item2View = (android.widget.CheckBox) acty.findViewById(ChkService9Item2ViewId);
        mChkService9Item3View = (android.widget.CheckBox) acty.findViewById(ChkService9Item3ViewId);
        mChkService10View = (android.widget.CheckBox) acty.findViewById(ChkService10ViewId);
        mLayoutService10ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService10ContentViewId);
        mTxtService10Item1ValView = (android.widget.Button) acty.findViewById(TxtService10Item1ValViewId);
        mChkService11View = (android.widget.CheckBox) acty.findViewById(ChkService11ViewId);
        mLayoutService11ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService11ContentViewId);
        mTxtService11Item1ValView = (android.widget.Button) acty.findViewById(TxtService11Item1ValViewId);
        mChkService12View = (android.widget.CheckBox) acty.findViewById(ChkService12ViewId);
        mLayoutService12ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService12ContentViewId);
        mChkService12Item1View = (android.widget.CheckBox) acty.findViewById(ChkService12Item1ViewId);
        mLayoutService12Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService12Item1ContentViewId);
        mTxtService12Item1ValView = (android.widget.Button) acty.findViewById(TxtService12Item1ValViewId);
        mChkService12Item2View = (android.widget.CheckBox) acty.findViewById(ChkService12Item2ViewId);
        mLayoutService12Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService12Item2ContentViewId);
        mTxtService12Item2ValView = (android.widget.Button) acty.findViewById(TxtService12Item2ValViewId);
        mChkService12Item3View = (android.widget.CheckBox) acty.findViewById(ChkService12Item3ViewId);
        mChkService13View = (android.widget.CheckBox) acty.findViewById(ChkService13ViewId);
        mLayoutService13ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService13ContentViewId);
        mTxtService13Item1ValView = (android.widget.Button) acty.findViewById(TxtService13Item1ValViewId);
        mChkService14View = (android.widget.CheckBox) acty.findViewById(ChkService14ViewId);
        mLayoutService14ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService14ContentViewId);
        mChkService14Item1View = (android.widget.CheckBox) acty.findViewById(ChkService14Item1ViewId);
        mChkService14Item2View = (android.widget.CheckBox) acty.findViewById(ChkService14Item2ViewId);
        mChkService15View = (android.widget.CheckBox) acty.findViewById(ChkService15ViewId);
        mLayoutService15ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService15ContentViewId);
        mTxtService15Item1ValView = (android.widget.Button) acty.findViewById(TxtService15Item1ValViewId);
        mChkService16View = (android.widget.CheckBox) acty.findViewById(ChkService16ViewId);
        mLayoutService16ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService16ContentViewId);
        mTxtService16Item1ValView = (android.widget.Button) acty.findViewById(TxtService16Item1ValViewId);
        mChkService17View = (android.widget.CheckBox) acty.findViewById(ChkService17ViewId);
        mLayoutService17ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService17ContentViewId);
        mTxtService17Item1ValView = (android.widget.EditText) acty.findViewById(TxtService17Item1ValViewId);
        mChkService18View = (android.widget.CheckBox) acty.findViewById(ChkService18ViewId);
        mLayoutService18ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService18ContentViewId);
        mTxtService18Item1ValView = (android.widget.EditText) acty.findViewById(TxtService18Item1ValViewId);
    }   

    public FragmentAssessService(android.view.View acty){
        mContainer = (android.widget.ScrollView) acty.findViewById(ContainerId);
        mChkService1View = (android.widget.CheckBox) acty.findViewById(ChkService1ViewId);
        mLayoutService1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService1ContentViewId);
        mTxtService1Item1ValView = (android.widget.Button) acty.findViewById(TxtService1Item1ValViewId);
        mChkService2View = (android.widget.CheckBox) acty.findViewById(ChkService2ViewId);
        mLayoutService2ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService2ContentViewId);
        mChkService2Item1View = (android.widget.CheckBox) acty.findViewById(ChkService2Item1ViewId);
        mLayoutService2Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService2Item1ContentViewId);
        mTxtService2Item1ValView = (android.widget.Button) acty.findViewById(TxtService2Item1ValViewId);
        mChkService2Item2View = (android.widget.CheckBox) acty.findViewById(ChkService2Item2ViewId);
        mLayoutService2Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService2Item2ContentViewId);
        mTxtService2Item2ValView = (android.widget.Button) acty.findViewById(TxtService2Item2ValViewId);
        mChkService2Item3View = (android.widget.CheckBox) acty.findViewById(ChkService2Item3ViewId);
        mChkService3View = (android.widget.CheckBox) acty.findViewById(ChkService3ViewId);
        mLayoutService3ContentView = (android.widget.RadioGroup) acty.findViewById(LayoutService3ContentViewId);
        mRdoService3item1View = (android.widget.RadioButton) acty.findViewById(RdoService3item1ViewId);
        mRdoService3item2View = (android.widget.RadioButton) acty.findViewById(RdoService3item2ViewId);
        mChkService4View = (android.widget.CheckBox) acty.findViewById(ChkService4ViewId);
        mLayoutService4ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService4ContentViewId);
        mChkService4Item1View = (android.widget.RadioButton) acty.findViewById(ChkService4Item1ViewId);
        mLayoutService4Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService4Item1ContentViewId);
        mTxtService4Item1ValView = (android.widget.Button) acty.findViewById(TxtService4Item1ValViewId);
        mChkService4Item2View = (android.widget.RadioButton) acty.findViewById(ChkService4Item2ViewId);
        mLayoutService4Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService4Item2ContentViewId);
        mTxtService4Item2ValView = (android.widget.Button) acty.findViewById(TxtService4Item2ValViewId);
        mChkService5View = (android.widget.CheckBox) acty.findViewById(ChkService5ViewId);
        mLayoutService5ContentView = (android.widget.RadioGroup) acty.findViewById(LayoutService5ContentViewId);
        mRdoService5item1View = (android.widget.RadioButton) acty.findViewById(RdoService5item1ViewId);
        mRdoService5item2View = (android.widget.RadioButton) acty.findViewById(RdoService5item2ViewId);
        mChkService6View = (android.widget.CheckBox) acty.findViewById(ChkService6ViewId);
        mLayoutService6ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService6ContentViewId);
        mChkService6Item1View = (android.widget.CheckBox) acty.findViewById(ChkService6Item1ViewId);
        mLayoutService6Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService6Item1ContentViewId);
        mTxtService6Item1ValView = (android.widget.Button) acty.findViewById(TxtService6Item1ValViewId);
        mChkService6Item2View = (android.widget.CheckBox) acty.findViewById(ChkService6Item2ViewId);
        mLayoutService6Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService6Item2ContentViewId);
        mTxtService6Item2ValView = (android.widget.Button) acty.findViewById(TxtService6Item2ValViewId);
        mChkService6Item3View = (android.widget.CheckBox) acty.findViewById(ChkService6Item3ViewId);
        mChkService6Item4View = (android.widget.CheckBox) acty.findViewById(ChkService6Item4ViewId);
        mLayoutService6Item4ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService6Item4ContentViewId);
        mTxtService6Item4ValView = (android.widget.Button) acty.findViewById(TxtService6Item4ValViewId);
        mChkService7View = (android.widget.CheckBox) acty.findViewById(ChkService7ViewId);
        mLayoutService7ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService7ContentViewId);
        mChkService7Item1View = (android.widget.CheckBox) acty.findViewById(ChkService7Item1ViewId);
        mChkService7Item2View = (android.widget.CheckBox) acty.findViewById(ChkService7Item2ViewId);
        mChkService7Item3View = (android.widget.CheckBox) acty.findViewById(ChkService7Item3ViewId);
        mChkService7Item4View = (android.widget.CheckBox) acty.findViewById(ChkService7Item4ViewId);
        mChkService8View = (android.widget.CheckBox) acty.findViewById(ChkService8ViewId);
        mLayoutService8ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService8ContentViewId);
        mChkService8Item1View = (android.widget.CheckBox) acty.findViewById(ChkService8Item1ViewId);
        mLayoutService8Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService8Item1ContentViewId);
        mTxtService8Item1ValView = (android.widget.Button) acty.findViewById(TxtService8Item1ValViewId);
        mChkService8Item2View = (android.widget.CheckBox) acty.findViewById(ChkService8Item2ViewId);
        mLayoutService8Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService8Item2ContentViewId);
        mTxtService8Item2ValView = (android.widget.Button) acty.findViewById(TxtService8Item2ValViewId);
        mChkService9View = (android.widget.CheckBox) acty.findViewById(ChkService9ViewId);
        mLayoutService9ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService9ContentViewId);
        mChkService9Item1View = (android.widget.CheckBox) acty.findViewById(ChkService9Item1ViewId);
        mChkService9Item2View = (android.widget.CheckBox) acty.findViewById(ChkService9Item2ViewId);
        mChkService9Item3View = (android.widget.CheckBox) acty.findViewById(ChkService9Item3ViewId);
        mChkService10View = (android.widget.CheckBox) acty.findViewById(ChkService10ViewId);
        mLayoutService10ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService10ContentViewId);
        mTxtService10Item1ValView = (android.widget.Button) acty.findViewById(TxtService10Item1ValViewId);
        mChkService11View = (android.widget.CheckBox) acty.findViewById(ChkService11ViewId);
        mLayoutService11ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService11ContentViewId);
        mTxtService11Item1ValView = (android.widget.Button) acty.findViewById(TxtService11Item1ValViewId);
        mChkService12View = (android.widget.CheckBox) acty.findViewById(ChkService12ViewId);
        mLayoutService12ContentView = (android.widget.TableLayout) acty.findViewById(LayoutService12ContentViewId);
        mChkService12Item1View = (android.widget.CheckBox) acty.findViewById(ChkService12Item1ViewId);
        mLayoutService12Item1ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService12Item1ContentViewId);
        mTxtService12Item1ValView = (android.widget.Button) acty.findViewById(TxtService12Item1ValViewId);
        mChkService12Item2View = (android.widget.CheckBox) acty.findViewById(ChkService12Item2ViewId);
        mLayoutService12Item2ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService12Item2ContentViewId);
        mTxtService12Item2ValView = (android.widget.Button) acty.findViewById(TxtService12Item2ValViewId);
        mChkService12Item3View = (android.widget.CheckBox) acty.findViewById(ChkService12Item3ViewId);
        mChkService13View = (android.widget.CheckBox) acty.findViewById(ChkService13ViewId);
        mLayoutService13ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService13ContentViewId);
        mTxtService13Item1ValView = (android.widget.Button) acty.findViewById(TxtService13Item1ValViewId);
        mChkService14View = (android.widget.CheckBox) acty.findViewById(ChkService14ViewId);
        mLayoutService14ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService14ContentViewId);
        mChkService14Item1View = (android.widget.CheckBox) acty.findViewById(ChkService14Item1ViewId);
        mChkService14Item2View = (android.widget.CheckBox) acty.findViewById(ChkService14Item2ViewId);
        mChkService15View = (android.widget.CheckBox) acty.findViewById(ChkService15ViewId);
        mLayoutService15ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService15ContentViewId);
        mTxtService15Item1ValView = (android.widget.Button) acty.findViewById(TxtService15Item1ValViewId);
        mChkService16View = (android.widget.CheckBox) acty.findViewById(ChkService16ViewId);
        mLayoutService16ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService16ContentViewId);
        mTxtService16Item1ValView = (android.widget.Button) acty.findViewById(TxtService16Item1ValViewId);
        mChkService17View = (android.widget.CheckBox) acty.findViewById(ChkService17ViewId);
        mLayoutService17ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService17ContentViewId);
        mTxtService17Item1ValView = (android.widget.EditText) acty.findViewById(TxtService17Item1ValViewId);
        mChkService18View = (android.widget.CheckBox) acty.findViewById(ChkService18ViewId);
        mLayoutService18ContentView = (android.widget.LinearLayout) acty.findViewById(LayoutService18ContentViewId);
        mTxtService18Item1ValView = (android.widget.EditText) acty.findViewById(TxtService18Item1ValViewId);
    }   
    public android.widget.ScrollView getContainer() {
        return mContainer;
    }
    public android.widget.CheckBox getChkService1View() {
        return mChkService1View;
    }
    public android.widget.LinearLayout getLayoutService1ContentView() {
        return mLayoutService1ContentView;
    }
    public android.widget.Button getTxtService1Item1ValView() {
        return mTxtService1Item1ValView;
    }
    public android.widget.CheckBox getChkService2View() {
        return mChkService2View;
    }
    public android.widget.TableLayout getLayoutService2ContentView() {
        return mLayoutService2ContentView;
    }
    public android.widget.CheckBox getChkService2Item1View() {
        return mChkService2Item1View;
    }
    public android.widget.LinearLayout getLayoutService2Item1ContentView() {
        return mLayoutService2Item1ContentView;
    }
    public android.widget.Button getTxtService2Item1ValView() {
        return mTxtService2Item1ValView;
    }
    public android.widget.CheckBox getChkService2Item2View() {
        return mChkService2Item2View;
    }
    public android.widget.LinearLayout getLayoutService2Item2ContentView() {
        return mLayoutService2Item2ContentView;
    }
    public android.widget.Button getTxtService2Item2ValView() {
        return mTxtService2Item2ValView;
    }
    public android.widget.CheckBox getChkService2Item3View() {
        return mChkService2Item3View;
    }
    public android.widget.CheckBox getChkService3View() {
        return mChkService3View;
    }
    public android.widget.RadioGroup getLayoutService3ContentView() {
        return mLayoutService3ContentView;
    }
    public android.widget.RadioButton getRdoService3item1View() {
        return mRdoService3item1View;
    }
    public android.widget.RadioButton getRdoService3item2View() {
        return mRdoService3item2View;
    }
    public android.widget.CheckBox getChkService4View() {
        return mChkService4View;
    }
    public android.widget.TableLayout getLayoutService4ContentView() {
        return mLayoutService4ContentView;
    }
    public android.widget.RadioButton getChkService4Item1View() {
        return mChkService4Item1View;
    }
    public android.widget.LinearLayout getLayoutService4Item1ContentView() {
        return mLayoutService4Item1ContentView;
    }
    public android.widget.Button getTxtService4Item1ValView() {
        return mTxtService4Item1ValView;
    }
    public android.widget.RadioButton getChkService4Item2View() {
        return mChkService4Item2View;
    }
    public android.widget.LinearLayout getLayoutService4Item2ContentView() {
        return mLayoutService4Item2ContentView;
    }
    public android.widget.Button getTxtService4Item2ValView() {
        return mTxtService4Item2ValView;
    }
    public android.widget.CheckBox getChkService5View() {
        return mChkService5View;
    }
    public android.widget.RadioGroup getLayoutService5ContentView() {
        return mLayoutService5ContentView;
    }
    public android.widget.RadioButton getRdoService5item1View() {
        return mRdoService5item1View;
    }
    public android.widget.RadioButton getRdoService5item2View() {
        return mRdoService5item2View;
    }
    public android.widget.CheckBox getChkService6View() {
        return mChkService6View;
    }
    public android.widget.TableLayout getLayoutService6ContentView() {
        return mLayoutService6ContentView;
    }
    public android.widget.CheckBox getChkService6Item1View() {
        return mChkService6Item1View;
    }
    public android.widget.LinearLayout getLayoutService6Item1ContentView() {
        return mLayoutService6Item1ContentView;
    }
    public android.widget.Button getTxtService6Item1ValView() {
        return mTxtService6Item1ValView;
    }
    public android.widget.CheckBox getChkService6Item2View() {
        return mChkService6Item2View;
    }
    public android.widget.LinearLayout getLayoutService6Item2ContentView() {
        return mLayoutService6Item2ContentView;
    }
    public android.widget.Button getTxtService6Item2ValView() {
        return mTxtService6Item2ValView;
    }
    public android.widget.CheckBox getChkService6Item3View() {
        return mChkService6Item3View;
    }
    public android.widget.CheckBox getChkService6Item4View() {
        return mChkService6Item4View;
    }
    public android.widget.LinearLayout getLayoutService6Item4ContentView() {
        return mLayoutService6Item4ContentView;
    }
    public android.widget.Button getTxtService6Item4ValView() {
        return mTxtService6Item4ValView;
    }
    public android.widget.CheckBox getChkService7View() {
        return mChkService7View;
    }
    public android.widget.LinearLayout getLayoutService7ContentView() {
        return mLayoutService7ContentView;
    }
    public android.widget.CheckBox getChkService7Item1View() {
        return mChkService7Item1View;
    }
    public android.widget.CheckBox getChkService7Item2View() {
        return mChkService7Item2View;
    }
    public android.widget.CheckBox getChkService7Item3View() {
        return mChkService7Item3View;
    }
    public android.widget.CheckBox getChkService7Item4View() {
        return mChkService7Item4View;
    }
    public android.widget.CheckBox getChkService8View() {
        return mChkService8View;
    }
    public android.widget.TableLayout getLayoutService8ContentView() {
        return mLayoutService8ContentView;
    }
    public android.widget.CheckBox getChkService8Item1View() {
        return mChkService8Item1View;
    }
    public android.widget.LinearLayout getLayoutService8Item1ContentView() {
        return mLayoutService8Item1ContentView;
    }
    public android.widget.Button getTxtService8Item1ValView() {
        return mTxtService8Item1ValView;
    }
    public android.widget.CheckBox getChkService8Item2View() {
        return mChkService8Item2View;
    }
    public android.widget.LinearLayout getLayoutService8Item2ContentView() {
        return mLayoutService8Item2ContentView;
    }
    public android.widget.Button getTxtService8Item2ValView() {
        return mTxtService8Item2ValView;
    }
    public android.widget.CheckBox getChkService9View() {
        return mChkService9View;
    }
    public android.widget.LinearLayout getLayoutService9ContentView() {
        return mLayoutService9ContentView;
    }
    public android.widget.CheckBox getChkService9Item1View() {
        return mChkService9Item1View;
    }
    public android.widget.CheckBox getChkService9Item2View() {
        return mChkService9Item2View;
    }
    public android.widget.CheckBox getChkService9Item3View() {
        return mChkService9Item3View;
    }
    public android.widget.CheckBox getChkService10View() {
        return mChkService10View;
    }
    public android.widget.LinearLayout getLayoutService10ContentView() {
        return mLayoutService10ContentView;
    }
    public android.widget.Button getTxtService10Item1ValView() {
        return mTxtService10Item1ValView;
    }
    public android.widget.CheckBox getChkService11View() {
        return mChkService11View;
    }
    public android.widget.LinearLayout getLayoutService11ContentView() {
        return mLayoutService11ContentView;
    }
    public android.widget.Button getTxtService11Item1ValView() {
        return mTxtService11Item1ValView;
    }
    public android.widget.CheckBox getChkService12View() {
        return mChkService12View;
    }
    public android.widget.TableLayout getLayoutService12ContentView() {
        return mLayoutService12ContentView;
    }
    public android.widget.CheckBox getChkService12Item1View() {
        return mChkService12Item1View;
    }
    public android.widget.LinearLayout getLayoutService12Item1ContentView() {
        return mLayoutService12Item1ContentView;
    }
    public android.widget.Button getTxtService12Item1ValView() {
        return mTxtService12Item1ValView;
    }
    public android.widget.CheckBox getChkService12Item2View() {
        return mChkService12Item2View;
    }
    public android.widget.LinearLayout getLayoutService12Item2ContentView() {
        return mLayoutService12Item2ContentView;
    }
    public android.widget.Button getTxtService12Item2ValView() {
        return mTxtService12Item2ValView;
    }
    public android.widget.CheckBox getChkService12Item3View() {
        return mChkService12Item3View;
    }
    public android.widget.CheckBox getChkService13View() {
        return mChkService13View;
    }
    public android.widget.LinearLayout getLayoutService13ContentView() {
        return mLayoutService13ContentView;
    }
    public android.widget.Button getTxtService13Item1ValView() {
        return mTxtService13Item1ValView;
    }
    public android.widget.CheckBox getChkService14View() {
        return mChkService14View;
    }
    public android.widget.LinearLayout getLayoutService14ContentView() {
        return mLayoutService14ContentView;
    }
    public android.widget.CheckBox getChkService14Item1View() {
        return mChkService14Item1View;
    }
    public android.widget.CheckBox getChkService14Item2View() {
        return mChkService14Item2View;
    }
    public android.widget.CheckBox getChkService15View() {
        return mChkService15View;
    }
    public android.widget.LinearLayout getLayoutService15ContentView() {
        return mLayoutService15ContentView;
    }
    public android.widget.Button getTxtService15Item1ValView() {
        return mTxtService15Item1ValView;
    }
    public android.widget.CheckBox getChkService16View() {
        return mChkService16View;
    }
    public android.widget.LinearLayout getLayoutService16ContentView() {
        return mLayoutService16ContentView;
    }
    public android.widget.Button getTxtService16Item1ValView() {
        return mTxtService16Item1ValView;
    }
    public android.widget.CheckBox getChkService17View() {
        return mChkService17View;
    }
    public android.widget.LinearLayout getLayoutService17ContentView() {
        return mLayoutService17ContentView;
    }
    public android.widget.EditText getTxtService17Item1ValView() {
        return mTxtService17Item1ValView;
    }
    public android.widget.CheckBox getChkService18View() {
        return mChkService18View;
    }
    public android.widget.LinearLayout getLayoutService18ContentView() {
        return mLayoutService18ContentView;
    }
    public android.widget.EditText getTxtService18Item1ValView() {
        return mTxtService18Item1ValView;
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
                case ChkService1ViewId:
                    setViewData(adp,getChkService1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService1ContentViewId:
                    setViewData(adp,getLayoutService1ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService1Item1ValViewId:
                    setViewData(adp,getTxtService1Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService2ViewId:
                    setViewData(adp,getChkService2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService2ContentViewId:
                    setViewData(adp,getLayoutService2ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService2Item1ViewId:
                    setViewData(adp,getChkService2Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService2Item1ContentViewId:
                    setViewData(adp,getLayoutService2Item1ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService2Item1ValViewId:
                    setViewData(adp,getTxtService2Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService2Item2ViewId:
                    setViewData(adp,getChkService2Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService2Item2ContentViewId:
                    setViewData(adp,getLayoutService2Item2ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService2Item2ValViewId:
                    setViewData(adp,getTxtService2Item2ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService2Item3ViewId:
                    setViewData(adp,getChkService2Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService3ViewId:
                    setViewData(adp,getChkService3View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService3ContentViewId:
                    setViewData(adp,getLayoutService3ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoService3item1ViewId:
                    setViewData(adp,getRdoService3item1View(),data,joinData.formatString,joinData.data);
                    break;
                case RdoService3item2ViewId:
                    setViewData(adp,getRdoService3item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService4ViewId:
                    setViewData(adp,getChkService4View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService4ContentViewId:
                    setViewData(adp,getLayoutService4ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService4Item1ViewId:
                    setViewData(adp,getChkService4Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService4Item1ContentViewId:
                    setViewData(adp,getLayoutService4Item1ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService4Item1ValViewId:
                    setViewData(adp,getTxtService4Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService4Item2ViewId:
                    setViewData(adp,getChkService4Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService4Item2ContentViewId:
                    setViewData(adp,getLayoutService4Item2ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService4Item2ValViewId:
                    setViewData(adp,getTxtService4Item2ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService5ViewId:
                    setViewData(adp,getChkService5View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService5ContentViewId:
                    setViewData(adp,getLayoutService5ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoService5item1ViewId:
                    setViewData(adp,getRdoService5item1View(),data,joinData.formatString,joinData.data);
                    break;
                case RdoService5item2ViewId:
                    setViewData(adp,getRdoService5item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService6ViewId:
                    setViewData(adp,getChkService6View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService6ContentViewId:
                    setViewData(adp,getLayoutService6ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService6Item1ViewId:
                    setViewData(adp,getChkService6Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService6Item1ContentViewId:
                    setViewData(adp,getLayoutService6Item1ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService6Item1ValViewId:
                    setViewData(adp,getTxtService6Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService6Item2ViewId:
                    setViewData(adp,getChkService6Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService6Item2ContentViewId:
                    setViewData(adp,getLayoutService6Item2ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService6Item2ValViewId:
                    setViewData(adp,getTxtService6Item2ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService6Item3ViewId:
                    setViewData(adp,getChkService6Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService6Item4ViewId:
                    setViewData(adp,getChkService6Item4View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService6Item4ContentViewId:
                    setViewData(adp,getLayoutService6Item4ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService6Item4ValViewId:
                    setViewData(adp,getTxtService6Item4ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService7ViewId:
                    setViewData(adp,getChkService7View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService7ContentViewId:
                    setViewData(adp,getLayoutService7ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService7Item1ViewId:
                    setViewData(adp,getChkService7Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService7Item2ViewId:
                    setViewData(adp,getChkService7Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService7Item3ViewId:
                    setViewData(adp,getChkService7Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService7Item4ViewId:
                    setViewData(adp,getChkService7Item4View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService8ViewId:
                    setViewData(adp,getChkService8View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService8ContentViewId:
                    setViewData(adp,getLayoutService8ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService8Item1ViewId:
                    setViewData(adp,getChkService8Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService8Item1ContentViewId:
                    setViewData(adp,getLayoutService8Item1ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService8Item1ValViewId:
                    setViewData(adp,getTxtService8Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService8Item2ViewId:
                    setViewData(adp,getChkService8Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService8Item2ContentViewId:
                    setViewData(adp,getLayoutService8Item2ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService8Item2ValViewId:
                    setViewData(adp,getTxtService8Item2ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService9ViewId:
                    setViewData(adp,getChkService9View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService9ContentViewId:
                    setViewData(adp,getLayoutService9ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService9Item1ViewId:
                    setViewData(adp,getChkService9Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService9Item2ViewId:
                    setViewData(adp,getChkService9Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService9Item3ViewId:
                    setViewData(adp,getChkService9Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService10ViewId:
                    setViewData(adp,getChkService10View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService10ContentViewId:
                    setViewData(adp,getLayoutService10ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService10Item1ValViewId:
                    setViewData(adp,getTxtService10Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService11ViewId:
                    setViewData(adp,getChkService11View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService11ContentViewId:
                    setViewData(adp,getLayoutService11ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService11Item1ValViewId:
                    setViewData(adp,getTxtService11Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService12ViewId:
                    setViewData(adp,getChkService12View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService12ContentViewId:
                    setViewData(adp,getLayoutService12ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService12Item1ViewId:
                    setViewData(adp,getChkService12Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService12Item1ContentViewId:
                    setViewData(adp,getLayoutService12Item1ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService12Item1ValViewId:
                    setViewData(adp,getTxtService12Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService12Item2ViewId:
                    setViewData(adp,getChkService12Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService12Item2ContentViewId:
                    setViewData(adp,getLayoutService12Item2ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService12Item2ValViewId:
                    setViewData(adp,getTxtService12Item2ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService12Item3ViewId:
                    setViewData(adp,getChkService12Item3View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService13ViewId:
                    setViewData(adp,getChkService13View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService13ContentViewId:
                    setViewData(adp,getLayoutService13ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService13Item1ValViewId:
                    setViewData(adp,getTxtService13Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService14ViewId:
                    setViewData(adp,getChkService14View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService14ContentViewId:
                    setViewData(adp,getLayoutService14ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService14Item1ViewId:
                    setViewData(adp,getChkService14Item1View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService14Item2ViewId:
                    setViewData(adp,getChkService14Item2View(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService15ViewId:
                    setViewData(adp,getChkService15View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService15ContentViewId:
                    setViewData(adp,getLayoutService15ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService15Item1ValViewId:
                    setViewData(adp,getTxtService15Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService16ViewId:
                    setViewData(adp,getChkService16View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService16ContentViewId:
                    setViewData(adp,getLayoutService16ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService16Item1ValViewId:
                    setViewData(adp,getTxtService16Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService17ViewId:
                    setViewData(adp,getChkService17View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService17ContentViewId:
                    setViewData(adp,getLayoutService17ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService17Item1ValViewId:
                    setViewData(adp,getTxtService17Item1ValView(),data,joinData.formatString,joinData.data);
                    break;
                case ChkService18ViewId:
                    setViewData(adp,getChkService18View(),data,joinData.formatString,joinData.data);
                    break;
                case LayoutService18ContentViewId:
                    setViewData(adp,getLayoutService18ContentView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtService18Item1ValViewId:
                    setViewData(adp,getTxtService18Item1ValView(),data,joinData.formatString,joinData.data);
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
                case ChkService1ViewId:
                    setViewData(adp,getChkService1View(),data,"",path);
                    break;
                case LayoutService1ContentViewId:
                    setViewData(adp,getLayoutService1ContentView(),data,"",path);
                    break;
                case TxtService1Item1ValViewId:
                    setViewData(adp,getTxtService1Item1ValView(),data,"",path);
                    break;
                case ChkService2ViewId:
                    setViewData(adp,getChkService2View(),data,"",path);
                    break;
                case LayoutService2ContentViewId:
                    setViewData(adp,getLayoutService2ContentView(),data,"",path);
                    break;
                case ChkService2Item1ViewId:
                    setViewData(adp,getChkService2Item1View(),data,"",path);
                    break;
                case LayoutService2Item1ContentViewId:
                    setViewData(adp,getLayoutService2Item1ContentView(),data,"",path);
                    break;
                case TxtService2Item1ValViewId:
                    setViewData(adp,getTxtService2Item1ValView(),data,"",path);
                    break;
                case ChkService2Item2ViewId:
                    setViewData(adp,getChkService2Item2View(),data,"",path);
                    break;
                case LayoutService2Item2ContentViewId:
                    setViewData(adp,getLayoutService2Item2ContentView(),data,"",path);
                    break;
                case TxtService2Item2ValViewId:
                    setViewData(adp,getTxtService2Item2ValView(),data,"",path);
                    break;
                case ChkService2Item3ViewId:
                    setViewData(adp,getChkService2Item3View(),data,"",path);
                    break;
                case ChkService3ViewId:
                    setViewData(adp,getChkService3View(),data,"",path);
                    break;
                case LayoutService3ContentViewId:
                    setViewData(adp,getLayoutService3ContentView(),data,"",path);
                    break;
                case RdoService3item1ViewId:
                    setViewData(adp,getRdoService3item1View(),data,"",path);
                    break;
                case RdoService3item2ViewId:
                    setViewData(adp,getRdoService3item2View(),data,"",path);
                    break;
                case ChkService4ViewId:
                    setViewData(adp,getChkService4View(),data,"",path);
                    break;
                case LayoutService4ContentViewId:
                    setViewData(adp,getLayoutService4ContentView(),data,"",path);
                    break;
                case ChkService4Item1ViewId:
                    setViewData(adp,getChkService4Item1View(),data,"",path);
                    break;
                case LayoutService4Item1ContentViewId:
                    setViewData(adp,getLayoutService4Item1ContentView(),data,"",path);
                    break;
                case TxtService4Item1ValViewId:
                    setViewData(adp,getTxtService4Item1ValView(),data,"",path);
                    break;
                case ChkService4Item2ViewId:
                    setViewData(adp,getChkService4Item2View(),data,"",path);
                    break;
                case LayoutService4Item2ContentViewId:
                    setViewData(adp,getLayoutService4Item2ContentView(),data,"",path);
                    break;
                case TxtService4Item2ValViewId:
                    setViewData(adp,getTxtService4Item2ValView(),data,"",path);
                    break;
                case ChkService5ViewId:
                    setViewData(adp,getChkService5View(),data,"",path);
                    break;
                case LayoutService5ContentViewId:
                    setViewData(adp,getLayoutService5ContentView(),data,"",path);
                    break;
                case RdoService5item1ViewId:
                    setViewData(adp,getRdoService5item1View(),data,"",path);
                    break;
                case RdoService5item2ViewId:
                    setViewData(adp,getRdoService5item2View(),data,"",path);
                    break;
                case ChkService6ViewId:
                    setViewData(adp,getChkService6View(),data,"",path);
                    break;
                case LayoutService6ContentViewId:
                    setViewData(adp,getLayoutService6ContentView(),data,"",path);
                    break;
                case ChkService6Item1ViewId:
                    setViewData(adp,getChkService6Item1View(),data,"",path);
                    break;
                case LayoutService6Item1ContentViewId:
                    setViewData(adp,getLayoutService6Item1ContentView(),data,"",path);
                    break;
                case TxtService6Item1ValViewId:
                    setViewData(adp,getTxtService6Item1ValView(),data,"",path);
                    break;
                case ChkService6Item2ViewId:
                    setViewData(adp,getChkService6Item2View(),data,"",path);
                    break;
                case LayoutService6Item2ContentViewId:
                    setViewData(adp,getLayoutService6Item2ContentView(),data,"",path);
                    break;
                case TxtService6Item2ValViewId:
                    setViewData(adp,getTxtService6Item2ValView(),data,"",path);
                    break;
                case ChkService6Item3ViewId:
                    setViewData(adp,getChkService6Item3View(),data,"",path);
                    break;
                case ChkService6Item4ViewId:
                    setViewData(adp,getChkService6Item4View(),data,"",path);
                    break;
                case LayoutService6Item4ContentViewId:
                    setViewData(adp,getLayoutService6Item4ContentView(),data,"",path);
                    break;
                case TxtService6Item4ValViewId:
                    setViewData(adp,getTxtService6Item4ValView(),data,"",path);
                    break;
                case ChkService7ViewId:
                    setViewData(adp,getChkService7View(),data,"",path);
                    break;
                case LayoutService7ContentViewId:
                    setViewData(adp,getLayoutService7ContentView(),data,"",path);
                    break;
                case ChkService7Item1ViewId:
                    setViewData(adp,getChkService7Item1View(),data,"",path);
                    break;
                case ChkService7Item2ViewId:
                    setViewData(adp,getChkService7Item2View(),data,"",path);
                    break;
                case ChkService7Item3ViewId:
                    setViewData(adp,getChkService7Item3View(),data,"",path);
                    break;
                case ChkService7Item4ViewId:
                    setViewData(adp,getChkService7Item4View(),data,"",path);
                    break;
                case ChkService8ViewId:
                    setViewData(adp,getChkService8View(),data,"",path);
                    break;
                case LayoutService8ContentViewId:
                    setViewData(adp,getLayoutService8ContentView(),data,"",path);
                    break;
                case ChkService8Item1ViewId:
                    setViewData(adp,getChkService8Item1View(),data,"",path);
                    break;
                case LayoutService8Item1ContentViewId:
                    setViewData(adp,getLayoutService8Item1ContentView(),data,"",path);
                    break;
                case TxtService8Item1ValViewId:
                    setViewData(adp,getTxtService8Item1ValView(),data,"",path);
                    break;
                case ChkService8Item2ViewId:
                    setViewData(adp,getChkService8Item2View(),data,"",path);
                    break;
                case LayoutService8Item2ContentViewId:
                    setViewData(adp,getLayoutService8Item2ContentView(),data,"",path);
                    break;
                case TxtService8Item2ValViewId:
                    setViewData(adp,getTxtService8Item2ValView(),data,"",path);
                    break;
                case ChkService9ViewId:
                    setViewData(adp,getChkService9View(),data,"",path);
                    break;
                case LayoutService9ContentViewId:
                    setViewData(adp,getLayoutService9ContentView(),data,"",path);
                    break;
                case ChkService9Item1ViewId:
                    setViewData(adp,getChkService9Item1View(),data,"",path);
                    break;
                case ChkService9Item2ViewId:
                    setViewData(adp,getChkService9Item2View(),data,"",path);
                    break;
                case ChkService9Item3ViewId:
                    setViewData(adp,getChkService9Item3View(),data,"",path);
                    break;
                case ChkService10ViewId:
                    setViewData(adp,getChkService10View(),data,"",path);
                    break;
                case LayoutService10ContentViewId:
                    setViewData(adp,getLayoutService10ContentView(),data,"",path);
                    break;
                case TxtService10Item1ValViewId:
                    setViewData(adp,getTxtService10Item1ValView(),data,"",path);
                    break;
                case ChkService11ViewId:
                    setViewData(adp,getChkService11View(),data,"",path);
                    break;
                case LayoutService11ContentViewId:
                    setViewData(adp,getLayoutService11ContentView(),data,"",path);
                    break;
                case TxtService11Item1ValViewId:
                    setViewData(adp,getTxtService11Item1ValView(),data,"",path);
                    break;
                case ChkService12ViewId:
                    setViewData(adp,getChkService12View(),data,"",path);
                    break;
                case LayoutService12ContentViewId:
                    setViewData(adp,getLayoutService12ContentView(),data,"",path);
                    break;
                case ChkService12Item1ViewId:
                    setViewData(adp,getChkService12Item1View(),data,"",path);
                    break;
                case LayoutService12Item1ContentViewId:
                    setViewData(adp,getLayoutService12Item1ContentView(),data,"",path);
                    break;
                case TxtService12Item1ValViewId:
                    setViewData(adp,getTxtService12Item1ValView(),data,"",path);
                    break;
                case ChkService12Item2ViewId:
                    setViewData(adp,getChkService12Item2View(),data,"",path);
                    break;
                case LayoutService12Item2ContentViewId:
                    setViewData(adp,getLayoutService12Item2ContentView(),data,"",path);
                    break;
                case TxtService12Item2ValViewId:
                    setViewData(adp,getTxtService12Item2ValView(),data,"",path);
                    break;
                case ChkService12Item3ViewId:
                    setViewData(adp,getChkService12Item3View(),data,"",path);
                    break;
                case ChkService13ViewId:
                    setViewData(adp,getChkService13View(),data,"",path);
                    break;
                case LayoutService13ContentViewId:
                    setViewData(adp,getLayoutService13ContentView(),data,"",path);
                    break;
                case TxtService13Item1ValViewId:
                    setViewData(adp,getTxtService13Item1ValView(),data,"",path);
                    break;
                case ChkService14ViewId:
                    setViewData(adp,getChkService14View(),data,"",path);
                    break;
                case LayoutService14ContentViewId:
                    setViewData(adp,getLayoutService14ContentView(),data,"",path);
                    break;
                case ChkService14Item1ViewId:
                    setViewData(adp,getChkService14Item1View(),data,"",path);
                    break;
                case ChkService14Item2ViewId:
                    setViewData(adp,getChkService14Item2View(),data,"",path);
                    break;
                case ChkService15ViewId:
                    setViewData(adp,getChkService15View(),data,"",path);
                    break;
                case LayoutService15ContentViewId:
                    setViewData(adp,getLayoutService15ContentView(),data,"",path);
                    break;
                case TxtService15Item1ValViewId:
                    setViewData(adp,getTxtService15Item1ValView(),data,"",path);
                    break;
                case ChkService16ViewId:
                    setViewData(adp,getChkService16View(),data,"",path);
                    break;
                case LayoutService16ContentViewId:
                    setViewData(adp,getLayoutService16ContentView(),data,"",path);
                    break;
                case TxtService16Item1ValViewId:
                    setViewData(adp,getTxtService16Item1ValView(),data,"",path);
                    break;
                case ChkService17ViewId:
                    setViewData(adp,getChkService17View(),data,"",path);
                    break;
                case LayoutService17ContentViewId:
                    setViewData(adp,getLayoutService17ContentView(),data,"",path);
                    break;
                case TxtService17Item1ValViewId:
                    setViewData(adp,getTxtService17Item1ValView(),data,"",path);
                    break;
                case ChkService18ViewId:
                    setViewData(adp,getChkService18View(),data,"",path);
                    break;
                case LayoutService18ContentViewId:
                    setViewData(adp,getLayoutService18ContentView(),data,"",path);
                    break;
                case TxtService18Item1ValViewId:
                    setViewData(adp,getTxtService18Item1ValView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
