package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class FragmentUserHeaderinfo extends BaseLayout{

    public static final int ImgUserAvatarViewId = R.id.imgUserAvatarView;
    public static final int TxtUserNameViewId = R.id.txtUserNameView;
    public static final int TxtAddressViewId = R.id.txtAddressView;
    public static final int TxtOfficeViewId = R.id.txtOfficeView;
    public static final int TxtLastLoginDateViewId = R.id.txtLastLoginDateView;

    protected android.widget.ImageView mImgUserAvatarView;
    protected android.widget.TextView mTxtUserNameView;
    protected android.widget.TextView mTxtAddressView;
    protected android.widget.TextView mTxtOfficeView;
    protected android.widget.TextView mTxtLastLoginDateView;

    protected Activity mCurActy;

    public FragmentUserHeaderinfo(Activity acty){
        mCurActy = acty;
        mImgUserAvatarView = (android.widget.ImageView) acty.findViewById(ImgUserAvatarViewId);
        mTxtUserNameView = (android.widget.TextView) acty.findViewById(TxtUserNameViewId);
        mTxtAddressView = (android.widget.TextView) acty.findViewById(TxtAddressViewId);
        mTxtOfficeView = (android.widget.TextView) acty.findViewById(TxtOfficeViewId);
        mTxtLastLoginDateView = (android.widget.TextView) acty.findViewById(TxtLastLoginDateViewId);
    }   

    public FragmentUserHeaderinfo(android.view.View acty){
        mImgUserAvatarView = (android.widget.ImageView) acty.findViewById(ImgUserAvatarViewId);
        mTxtUserNameView = (android.widget.TextView) acty.findViewById(TxtUserNameViewId);
        mTxtAddressView = (android.widget.TextView) acty.findViewById(TxtAddressViewId);
        mTxtOfficeView = (android.widget.TextView) acty.findViewById(TxtOfficeViewId);
        mTxtLastLoginDateView = (android.widget.TextView) acty.findViewById(TxtLastLoginDateViewId);
    }   
    public android.widget.ImageView getImgUserAvatarView() {
        return mImgUserAvatarView;
    }
    public android.widget.TextView getTxtUserNameView() {
        return mTxtUserNameView;
    }
    public android.widget.TextView getTxtAddressView() {
        return mTxtAddressView;
    }
    public android.widget.TextView getTxtOfficeView() {
        return mTxtOfficeView;
    }
    public android.widget.TextView getTxtLastLoginDateView() {
        return mTxtLastLoginDateView;
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
                case ImgUserAvatarViewId:
                    setViewData(adp,getImgUserAvatarView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtUserNameViewId:
                    setViewData(adp,getTxtUserNameView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtAddressViewId:
                    setViewData(adp,getTxtAddressView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtOfficeViewId:
                    setViewData(adp,getTxtOfficeView(),data,joinData.formatString,joinData.data);
                    break;
                case TxtLastLoginDateViewId:
                    setViewData(adp,getTxtLastLoginDateView(),data,joinData.formatString,joinData.data);
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
                case ImgUserAvatarViewId:
                    setViewData(adp,getImgUserAvatarView(),data,"",path);
                    break;
                case TxtUserNameViewId:
                    setViewData(adp,getTxtUserNameView(),data,"",path);
                    break;
                case TxtAddressViewId:
                    setViewData(adp,getTxtAddressView(),data,"",path);
                    break;
                case TxtOfficeViewId:
                    setViewData(adp,getTxtOfficeView(),data,"",path);
                    break;
                case TxtLastLoginDateViewId:
                    setViewData(adp,getTxtLastLoginDateView(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
