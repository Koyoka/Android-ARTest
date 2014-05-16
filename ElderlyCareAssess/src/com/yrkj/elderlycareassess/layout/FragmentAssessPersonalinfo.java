package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentAssessPersonalinfo extends BaseLayout{

    public static final int ContainerId = R.id.container;
    public static final int TxtCustomerNameViewId = R.id.txtCustomerNameView;
    public static final int TxtIdNumberViewId = R.id.txtIdNumberView;
    public static final int RdoSexViewId = R.id.rdoSexView;
    public static final int RdoSexMViewId = R.id.rdoSexMView;
    public static final int RdoSexFViewId = R.id.rdoSexFView;
    public static final int TxtSocialSecurityViewId = R.id.txtSocialSecurityView;
    public static final int TxtEthnicViewId = R.id.txtEthnicView;
    public static final int TxtEducationViewId = R.id.txtEducationView;
    public static final int TxtBirthdayViewId = R.id.txtBirthdayView;
    public static final int TxtWorkViewId = R.id.txtWorkView;
    public static final int TxtProvinceViewId = R.id.txtProvinceView;
    public static final int TxtIsMarryViewId = R.id.txtIsMarryView;
    public static final int TxtHouseHoldAddrViewId = R.id.txtHouseHoldAddrView;
    public static final int TxtAddressViewId = R.id.txtAddressView;
    public static final int TxtHouseHoldMailViewId = R.id.txtHouseHoldMailView;
    public static final int TxtMailViewId = R.id.txtMailView;
    public static final int TxtTelViewId = R.id.txtTelView;
    public static final int TxtMobliePhoneViewId = R.id.txtMobliePhoneView;
    public static final int TxtProxyNameViewId = R.id.txtProxyNameView;
    public static final int TxtProxyRelationViewId = R.id.txtProxyRelationView;
    public static final int TxtProxyAddrViewId = R.id.txtProxyAddrView;
    public static final int TxtProxyMailViewId = R.id.txtProxyMailView;
    public static final int TxtProxyTelViewId = R.id.txtProxyTelView;
    public static final int TxtProxyPhoneViewId = R.id.txtProxyPhoneView;

    protected android.widget.LinearLayout mContainer;
    protected android.widget.EditText mTxtCustomerNameView;
    protected android.widget.EditText mTxtIdNumberView;
    protected android.widget.RadioGroup mRdoSexView;
    protected android.widget.RadioButton mRdoSexMView;
    protected android.widget.RadioButton mRdoSexFView;
    protected android.widget.EditText mTxtSocialSecurityView;
    protected android.widget.TextView mTxtEthnicView;
    protected android.widget.TextView mTxtEducationView;
    protected android.widget.TextView mTxtBirthdayView;
    protected android.widget.EditText mTxtWorkView;
    protected android.widget.TextView mTxtProvinceView;
    protected android.widget.TextView mTxtIsMarryView;
    protected android.widget.EditText mTxtHouseHoldAddrView;
    protected android.widget.EditText mTxtAddressView;
    protected android.widget.EditText mTxtHouseHoldMailView;
    protected android.widget.EditText mTxtMailView;
    protected android.widget.EditText mTxtTelView;
    protected android.widget.EditText mTxtMobliePhoneView;
    protected android.widget.EditText mTxtProxyNameView;
    protected android.widget.TextView mTxtProxyRelationView;
    protected android.widget.EditText mTxtProxyAddrView;
    protected android.widget.EditText mTxtProxyMailView;
    protected android.widget.EditText mTxtProxyTelView;
    protected android.widget.EditText mTxtProxyPhoneView;

    protected Activity mCurActy;

    public FragmentAssessPersonalinfo(Activity acty){
        mCurActy = acty;
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mTxtCustomerNameView = (android.widget.EditText) acty.findViewById(TxtCustomerNameViewId);
        mTxtIdNumberView = (android.widget.EditText) acty.findViewById(TxtIdNumberViewId);
        mRdoSexView = (android.widget.RadioGroup) acty.findViewById(RdoSexViewId);
        mRdoSexMView = (android.widget.RadioButton) acty.findViewById(RdoSexMViewId);
        mRdoSexFView = (android.widget.RadioButton) acty.findViewById(RdoSexFViewId);
        mTxtSocialSecurityView = (android.widget.EditText) acty.findViewById(TxtSocialSecurityViewId);
        mTxtEthnicView = (android.widget.TextView) acty.findViewById(TxtEthnicViewId);
        mTxtEducationView = (android.widget.TextView) acty.findViewById(TxtEducationViewId);
        mTxtBirthdayView = (android.widget.TextView) acty.findViewById(TxtBirthdayViewId);
        mTxtWorkView = (android.widget.EditText) acty.findViewById(TxtWorkViewId);
        mTxtProvinceView = (android.widget.TextView) acty.findViewById(TxtProvinceViewId);
        mTxtIsMarryView = (android.widget.TextView) acty.findViewById(TxtIsMarryViewId);
        mTxtHouseHoldAddrView = (android.widget.EditText) acty.findViewById(TxtHouseHoldAddrViewId);
        mTxtAddressView = (android.widget.EditText) acty.findViewById(TxtAddressViewId);
        mTxtHouseHoldMailView = (android.widget.EditText) acty.findViewById(TxtHouseHoldMailViewId);
        mTxtMailView = (android.widget.EditText) acty.findViewById(TxtMailViewId);
        mTxtTelView = (android.widget.EditText) acty.findViewById(TxtTelViewId);
        mTxtMobliePhoneView = (android.widget.EditText) acty.findViewById(TxtMobliePhoneViewId);
        mTxtProxyNameView = (android.widget.EditText) acty.findViewById(TxtProxyNameViewId);
        mTxtProxyRelationView = (android.widget.TextView) acty.findViewById(TxtProxyRelationViewId);
        mTxtProxyAddrView = (android.widget.EditText) acty.findViewById(TxtProxyAddrViewId);
        mTxtProxyMailView = (android.widget.EditText) acty.findViewById(TxtProxyMailViewId);
        mTxtProxyTelView = (android.widget.EditText) acty.findViewById(TxtProxyTelViewId);
        mTxtProxyPhoneView = (android.widget.EditText) acty.findViewById(TxtProxyPhoneViewId);
    }   

    public FragmentAssessPersonalinfo(android.view.View acty){
        mContainer = (android.widget.LinearLayout) acty.findViewById(ContainerId);
        mTxtCustomerNameView = (android.widget.EditText) acty.findViewById(TxtCustomerNameViewId);
        mTxtIdNumberView = (android.widget.EditText) acty.findViewById(TxtIdNumberViewId);
        mRdoSexView = (android.widget.RadioGroup) acty.findViewById(RdoSexViewId);
        mRdoSexMView = (android.widget.RadioButton) acty.findViewById(RdoSexMViewId);
        mRdoSexFView = (android.widget.RadioButton) acty.findViewById(RdoSexFViewId);
        mTxtSocialSecurityView = (android.widget.EditText) acty.findViewById(TxtSocialSecurityViewId);
        mTxtEthnicView = (android.widget.TextView) acty.findViewById(TxtEthnicViewId);
        mTxtEducationView = (android.widget.TextView) acty.findViewById(TxtEducationViewId);
        mTxtBirthdayView = (android.widget.TextView) acty.findViewById(TxtBirthdayViewId);
        mTxtWorkView = (android.widget.EditText) acty.findViewById(TxtWorkViewId);
        mTxtProvinceView = (android.widget.TextView) acty.findViewById(TxtProvinceViewId);
        mTxtIsMarryView = (android.widget.TextView) acty.findViewById(TxtIsMarryViewId);
        mTxtHouseHoldAddrView = (android.widget.EditText) acty.findViewById(TxtHouseHoldAddrViewId);
        mTxtAddressView = (android.widget.EditText) acty.findViewById(TxtAddressViewId);
        mTxtHouseHoldMailView = (android.widget.EditText) acty.findViewById(TxtHouseHoldMailViewId);
        mTxtMailView = (android.widget.EditText) acty.findViewById(TxtMailViewId);
        mTxtTelView = (android.widget.EditText) acty.findViewById(TxtTelViewId);
        mTxtMobliePhoneView = (android.widget.EditText) acty.findViewById(TxtMobliePhoneViewId);
        mTxtProxyNameView = (android.widget.EditText) acty.findViewById(TxtProxyNameViewId);
        mTxtProxyRelationView = (android.widget.TextView) acty.findViewById(TxtProxyRelationViewId);
        mTxtProxyAddrView = (android.widget.EditText) acty.findViewById(TxtProxyAddrViewId);
        mTxtProxyMailView = (android.widget.EditText) acty.findViewById(TxtProxyMailViewId);
        mTxtProxyTelView = (android.widget.EditText) acty.findViewById(TxtProxyTelViewId);
        mTxtProxyPhoneView = (android.widget.EditText) acty.findViewById(TxtProxyPhoneViewId);
    }   
    public android.widget.LinearLayout getContainer() {
        return mContainer;
    }
    public android.widget.EditText getTxtCustomerNameView() {
        return mTxtCustomerNameView;
    }
    public android.widget.EditText getTxtIdNumberView() {
        return mTxtIdNumberView;
    }
    public android.widget.RadioGroup getRdoSexView() {
        return mRdoSexView;
    }
    public android.widget.RadioButton getRdoSexMView() {
        return mRdoSexMView;
    }
    public android.widget.RadioButton getRdoSexFView() {
        return mRdoSexFView;
    }
    public android.widget.EditText getTxtSocialSecurityView() {
        return mTxtSocialSecurityView;
    }
    public android.widget.TextView getTxtEthnicView() {
        return mTxtEthnicView;
    }
    public android.widget.TextView getTxtEducationView() {
        return mTxtEducationView;
    }
    public android.widget.TextView getTxtBirthdayView() {
        return mTxtBirthdayView;
    }
    public android.widget.EditText getTxtWorkView() {
        return mTxtWorkView;
    }
    public android.widget.TextView getTxtProvinceView() {
        return mTxtProvinceView;
    }
    public android.widget.TextView getTxtIsMarryView() {
        return mTxtIsMarryView;
    }
    public android.widget.EditText getTxtHouseHoldAddrView() {
        return mTxtHouseHoldAddrView;
    }
    public android.widget.EditText getTxtAddressView() {
        return mTxtAddressView;
    }
    public android.widget.EditText getTxtHouseHoldMailView() {
        return mTxtHouseHoldMailView;
    }
    public android.widget.EditText getTxtMailView() {
        return mTxtMailView;
    }
    public android.widget.EditText getTxtTelView() {
        return mTxtTelView;
    }
    public android.widget.EditText getTxtMobliePhoneView() {
        return mTxtMobliePhoneView;
    }
    public android.widget.EditText getTxtProxyNameView() {
        return mTxtProxyNameView;
    }
    public android.widget.TextView getTxtProxyRelationView() {
        return mTxtProxyRelationView;
    }
    public android.widget.EditText getTxtProxyAddrView() {
        return mTxtProxyAddrView;
    }
    public android.widget.EditText getTxtProxyMailView() {
        return mTxtProxyMailView;
    }
    public android.widget.EditText getTxtProxyTelView() {
        return mTxtProxyTelView;
    }
    public android.widget.EditText getTxtProxyPhoneView() {
        return mTxtProxyPhoneView;
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
                case TxtCustomerNameViewId:
                    setViewData(adp,getTxtCustomerNameView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtIdNumberViewId:
                    setViewData(adp,getTxtIdNumberView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoSexViewId:
                    setViewData(adp,getRdoSexView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoSexMViewId:
                    setViewData(adp,getRdoSexMView(),data,joinData.formatString,joinData.data);
                    break;
                case RdoSexFViewId:
                    setViewData(adp,getRdoSexFView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtSocialSecurityViewId:
                    setViewData(adp,getTxtSocialSecurityView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtEthnicViewId:
                    setViewData(adp,getTxtEthnicView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtEducationViewId:
                    setViewData(adp,getTxtEducationView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtBirthdayViewId:
                    setViewData(adp,getTxtBirthdayView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtWorkViewId:
                    setViewData(adp,getTxtWorkView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProvinceViewId:
                    setViewData(adp,getTxtProvinceView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtIsMarryViewId:
                    setViewData(adp,getTxtIsMarryView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtHouseHoldAddrViewId:
                    setViewData(adp,getTxtHouseHoldAddrView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAddressViewId:
                    setViewData(adp,getTxtAddressView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtHouseHoldMailViewId:
                    setViewData(adp,getTxtHouseHoldMailView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtMailViewId:
                    setViewData(adp,getTxtMailView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtTelViewId:
                    setViewData(adp,getTxtTelView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtMobliePhoneViewId:
                    setViewData(adp,getTxtMobliePhoneView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProxyNameViewId:
                    setViewData(adp,getTxtProxyNameView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProxyRelationViewId:
                    setViewData(adp,getTxtProxyRelationView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProxyAddrViewId:
                    setViewData(adp,getTxtProxyAddrView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProxyMailViewId:
                    setViewData(adp,getTxtProxyMailView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProxyTelViewId:
                    setViewData(adp,getTxtProxyTelView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtProxyPhoneViewId:
                    setViewData(adp,getTxtProxyPhoneView(),data,joinData.formatString,joinData.data);
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
                case TxtCustomerNameViewId:
                    setViewData(adp,getTxtCustomerNameView(),data,"",path);
                    break;
                case TxtIdNumberViewId:
                    setViewData(adp,getTxtIdNumberView(),data,"",path);
                    break;
                case RdoSexViewId:
                    setViewData(adp,getRdoSexView(),data,"",path);
                    break;
                case RdoSexMViewId:
                    setViewData(adp,getRdoSexMView(),data,"",path);
                    break;
                case RdoSexFViewId:
                    setViewData(adp,getRdoSexFView(),data,"",path);
                    break;
                case TxtSocialSecurityViewId:
                    setViewData(adp,getTxtSocialSecurityView(),data,"",path);
                    break;
                case TxtEthnicViewId:
                    setViewData(adp,getTxtEthnicView(),data,"",path);
                    break;
                case TxtEducationViewId:
                    setViewData(adp,getTxtEducationView(),data,"",path);
                    break;
                case TxtBirthdayViewId:
                    setViewData(adp,getTxtBirthdayView(),data,"",path);
                    break;
                case TxtWorkViewId:
                    setViewData(adp,getTxtWorkView(),data,"",path);
                    break;
                case TxtProvinceViewId:
                    setViewData(adp,getTxtProvinceView(),data,"",path);
                    break;
                case TxtIsMarryViewId:
                    setViewData(adp,getTxtIsMarryView(),data,"",path);
                    break;
                case TxtHouseHoldAddrViewId:
                    setViewData(adp,getTxtHouseHoldAddrView(),data,"",path);
                    break;
                case TxtAddressViewId:
                    setViewData(adp,getTxtAddressView(),data,"",path);
                    break;
                case TxtHouseHoldMailViewId:
                    setViewData(adp,getTxtHouseHoldMailView(),data,"",path);
                    break;
                case TxtMailViewId:
                    setViewData(adp,getTxtMailView(),data,"",path);
                    break;
                case TxtTelViewId:
                    setViewData(adp,getTxtTelView(),data,"",path);
                    break;
                case TxtMobliePhoneViewId:
                    setViewData(adp,getTxtMobliePhoneView(),data,"",path);
                    break;
                case TxtProxyNameViewId:
                    setViewData(adp,getTxtProxyNameView(),data,"",path);
                    break;
                case TxtProxyRelationViewId:
                    setViewData(adp,getTxtProxyRelationView(),data,"",path);
                    break;
                case TxtProxyAddrViewId:
                    setViewData(adp,getTxtProxyAddrView(),data,"",path);
                    break;
                case TxtProxyMailViewId:
                    setViewData(adp,getTxtProxyMailView(),data,"",path);
                    break;
                case TxtProxyTelViewId:
                    setViewData(adp,getTxtProxyTelView(),data,"",path);
                    break;
                case TxtProxyPhoneViewId:
                    setViewData(adp,getTxtProxyPhoneView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
