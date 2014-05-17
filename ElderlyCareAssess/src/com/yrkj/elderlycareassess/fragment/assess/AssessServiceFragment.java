package com.yrkj.elderlycareassess.fragment.assess;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupMenu;
import android.widget.RadioButton;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.MainAssessActivity;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.bean.AssessTaskDetailData;
import com.yrkj.elderlycareassess.bean.AssessTaskServiceData;
import com.yrkj.elderlycareassess.bean.CustomerData;
import com.yrkj.elderlycareassess.dao.AssessDBCtrl;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment;
import com.yrkj.elderlycareassess.fragment.widget.MyDialogFragment.onDialogClosed;
import com.yrkj.elderlycareassess.layout.FragmentAssessService;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;

public class AssessServiceFragment extends AssessBaseFragment implements OnCheckedChangeListener, OnClickListener {
	
	FragmentAssessService mLayout;
	private String mTaskHeaderId = "";
	public AssessServiceFragment(MainAssessActivity a,CustomerData c,String id) {
		super(a,c);
		mTaskHeaderId = id;
	}
	
	View mV=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_assess_service, container,
				false);
		mV = v;
		mLayout = new FragmentAssessService(v);
		setChkEvent();
		initFragment();
		bindData();
		return v;
	}
	
	
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		super.saveData();
		
		AssessDBCtrl.deleteAssessTaskService(getActivity(), mTaskHeaderId);
		
		ToastUtil.show(getActivity(), "eleven");
		ArrayList<AssessTaskServiceData> dList =  
				setData();
		for (AssessTaskServiceData d : dList) {
			AssessDBCtrl.insertAssessTaskService(getActivity(), d);
//			DLog.LOG(SysMng.TAG_ASSESS, 
//					d.ServiceId + " " +
//							d.ServiceName + " " +
//							d.ServiceContent + " " +
//							(d.prefix.equals("1")?"每":d.prefix) + "" +
//							AssessTaskServiceData.getUnitDesc(d.unit) + "" +
//							d.count + "次 " +
//					"");
		}
	}
	
	private void initFragment(){
		setTitle(getResources().getString(R.string.assess_title_service));
		
		mLayout.getChkService1View().setChecked(false);
		mLayout.getChkService2View().setChecked(false);
		mLayout.getChkService3View().setChecked(false);
		mLayout.getChkService4View().setChecked(false);
		mLayout.getChkService5View().setChecked(false);
		mLayout.getChkService6View().setChecked(false);
		mLayout.getChkService7View().setChecked(false);
		mLayout.getChkService8View().setChecked(false);
		mLayout.getChkService9View().setChecked(false);
		mLayout.getChkService10View().setChecked(false);
		mLayout.getChkService11View().setChecked(false);
		mLayout.getChkService12View().setChecked(false);
		mLayout.getChkService13View().setChecked(false);
		mLayout.getChkService14View().setChecked(false);
		mLayout.getChkService15View().setChecked(false);
		mLayout.getChkService16View().setChecked(false);
		mLayout.getChkService17View().setChecked(false);
		mLayout.getChkService18View().setChecked(false);

		
	}
	
	private AssessTaskServiceData getServiceDataByView(CheckBox v){
		if(v.isChecked()){
			AssessTaskServiceData data = new AssessTaskServiceData();
			data.ServiceName = v.getText()+"";
			data.TaskHeaderId = mTaskHeaderId;
			return data;
		}
		return null;
	}

	@Override
	public ArrayList<AssessTaskDetailData> getSelectData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setChkEvent(){
		
		mLayout.getChkService1View().setOnCheckedChangeListener(this);
//		ToastUtil.show(getActivity(), ((CheckBox)mV.findViewWithTag("Eleven")).getText().toString());
		mLayout.getChkService2View().setOnCheckedChangeListener(this);
		mLayout.getChkService3View().setOnCheckedChangeListener(this);
		mLayout.getChkService4View().setOnCheckedChangeListener(this);
		mLayout.getChkService5View().setOnCheckedChangeListener(this);
		mLayout.getChkService6View().setOnCheckedChangeListener(this);
		mLayout.getChkService7View().setOnCheckedChangeListener(this);
		mLayout.getChkService8View().setOnCheckedChangeListener(this);
		mLayout.getChkService9View().setOnCheckedChangeListener(this);
		mLayout.getChkService10View().setOnCheckedChangeListener(this);
		mLayout.getChkService11View().setOnCheckedChangeListener(this);
		mLayout.getChkService12View().setOnCheckedChangeListener(this);
		mLayout.getChkService13View().setOnCheckedChangeListener(this);
		mLayout.getChkService14View().setOnCheckedChangeListener(this);
		mLayout.getChkService15View().setOnCheckedChangeListener(this);
		mLayout.getChkService16View().setOnCheckedChangeListener(this);
		mLayout.getChkService17View().setOnCheckedChangeListener(this);
		mLayout.getChkService18View().setOnCheckedChangeListener(this);
		
		mLayout.getChkService18View().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!mLayout.getChkService18View().isChecked()){
					return;
				}
				MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.ALTER_DIALOG);
				newDialog.setOnDialogClosed(new onDialogClosed() {
					@Override
					public void onClosed(boolean r) {
						if(!r){
							mLayout.getChkService18View().setChecked(false);
						}
					}
				});
				newDialog.show(getChildFragmentManager(), "您即将更改服务形式,是否确认更改？");
			}
		});
		
		
		mLayout.getChkService2Item1View().setOnCheckedChangeListener(this);
		mLayout.getChkService2Item2View().setOnCheckedChangeListener(this);

		mLayout.getChkService4Item1View().setOnCheckedChangeListener(this);
		mLayout.getChkService4Item2View().setOnCheckedChangeListener(this);
		
		mLayout.getChkService6Item1View().setOnCheckedChangeListener(this);
		mLayout.getChkService6Item2View().setOnCheckedChangeListener(this);
		mLayout.getChkService6Item4View().setOnCheckedChangeListener(this);
		
		
		mLayout.getChkService8Item1View().setOnCheckedChangeListener(this);
		mLayout.getChkService8Item2View().setOnCheckedChangeListener(this);
		
		mLayout.getChkService12Item1View().setOnCheckedChangeListener(this);
		mLayout.getChkService12Item2View().setOnCheckedChangeListener(this);
		
		
		
		mLayout.getTxtService1Item1ValView().setOnClickListener(this);
		mLayout.getTxtService2Item1ValView().setOnClickListener(this);
		mLayout.getTxtService2Item2ValView().setOnClickListener(this);
		mLayout.getTxtService4Item1ValView().setOnClickListener(this);
		mLayout.getTxtService4Item2ValView().setOnClickListener(this);
		mLayout.getTxtService6Item1ValView().setOnClickListener(this);
		mLayout.getTxtService6Item2ValView().setOnClickListener(this);
		mLayout.getTxtService6Item4ValView().setOnClickListener(this);
		mLayout.getTxtService8Item1ValView().setOnClickListener(this);
		mLayout.getTxtService8Item2ValView().setOnClickListener(this);
		mLayout.getTxtService10Item1ValView().setOnClickListener(this);
		mLayout.getTxtService11Item1ValView().setOnClickListener(this);
		mLayout.getTxtService12Item1ValView().setOnClickListener(this);
		mLayout.getTxtService12Item2ValView().setOnClickListener(this);
		mLayout.getTxtService13Item1ValView().setOnClickListener(this);
		mLayout.getTxtService15Item1ValView().setOnClickListener(this);
		mLayout.getTxtService16Item1ValView().setOnClickListener(this);

		
	}
	
	
	private void pop(final Button v){
		PopupMenu popup = new PopupMenu(getActivity(), v);
		popup.getMenuInflater().inflate(R.menu.count, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				v.setText(item.getTitle());
				return true;
			}
		});
		popup.show();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		switch (buttonView.getId()) {
		case FragmentAssessService.ChkService1ViewId:
			mLayout.getLayoutService1ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService2ViewId:
			mLayout.getLayoutService2ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService3ViewId:
			mLayout.getLayoutService3ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService4ViewId:
			mLayout.getLayoutService4ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService5ViewId:
			mLayout.getLayoutService5ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService6ViewId:
			mLayout.getLayoutService6ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService7ViewId:
			mLayout.getLayoutService7ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService8ViewId:
			mLayout.getLayoutService8ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService9ViewId:
			mLayout.getLayoutService9ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService10ViewId:
			mLayout.getLayoutService10ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService11ViewId:
			mLayout.getLayoutService11ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService12ViewId:
			mLayout.getLayoutService12ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService13ViewId:
			mLayout.getLayoutService13ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService14ViewId:
			mLayout.getLayoutService14ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService15ViewId:
			mLayout.getLayoutService15ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService16ViewId:
			mLayout.getLayoutService16ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService17ViewId:
			mLayout.getLayoutService17ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService18ViewId:
//			if(Service18CheckedChanged(isChecked)){
				mLayout.getLayoutService18ContentView().setVisibility(
						isChecked ? View.VISIBLE : View.GONE);
//			}
			break;

			
			
		case FragmentAssessService.ChkService2Item1ViewId:
			mLayout.getLayoutService2Item1ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService2Item2ViewId:
			mLayout.getLayoutService2Item2ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService4Item1ViewId:
			mLayout.getLayoutService4Item1ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			if(mLayout.getChkService4Item2View().isChecked() != !isChecked)
				mLayout.getChkService4Item2View().setChecked(!isChecked);
			
			break;
		case FragmentAssessService.ChkService4Item2ViewId:
			mLayout.getLayoutService4Item2ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			if(mLayout.getChkService4Item1View().isChecked() != !isChecked)
				mLayout.getChkService4Item1View().setChecked(!isChecked);
			
			break;
		case FragmentAssessService.ChkService6Item1ViewId:
			mLayout.getLayoutService6Item1ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService6Item2ViewId:
			mLayout.getLayoutService6Item2ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService6Item4ViewId:
			mLayout.getLayoutService6Item4ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService8Item1ViewId:
			mLayout.getLayoutService8Item1ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService8Item2ViewId:
			mLayout.getLayoutService8Item2ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService12Item1ViewId:
			mLayout.getLayoutService12Item1ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
		case FragmentAssessService.ChkService12Item2ViewId:
			mLayout.getLayoutService12Item2ContentView().setVisibility(
					isChecked ? View.VISIBLE : View.GONE);
			break;
			
			
		default:
			break;
		}
		
	}
	
//	private boolean Service18CheckedChanged(final boolean isChecked){
//		if(isChecked){
//			MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.ALTER_DIALOG);
//			
//			newDialog.setOnDialogClosed(new onDialogClosed() {
//				@Override
//				public void onClosed(boolean r) {
//					if(r){
//						mLayout.getLayoutService18ContentView().setVisibility(
//								isChecked ? View.VISIBLE : View.GONE);
//					}else{
//						mLayout.getChkService18View().setChecked(false);
//					}
//				}
//			});
//			newDialog.show(getChildFragmentManager(), "您即将更改服务形式,是否确认更改？");
//			return false;
//		}
//		return true;
//		
//		
//	}

	@Override
	public void onClick(View v) {
		pop((Button)v); 
	}
	
//	boolean isBind = true;
	private void bindData(){
		ArrayList<AssessTaskServiceData> itemList
			= AssessDBCtrl.getAssessTaskServiceByTaskId(getActivity(), mTaskHeaderId);
		DLog.LOG(SysMng.TAG_DB, itemList.size()+" ===============================");
		for (AssessTaskServiceData item : itemList) {
			
			if(false){}
			else if( item.ServiceId.equals("1")){ 
				
				mLayout.getChkService1View().setChecked(true); 
				mLayout.getTxtService1Item1ValView().setText(item.count);
			}
			else if( item.ServiceId.equals("2")){ 
				mLayout.getChkService2View().setChecked(true); 
				if (false) {
				} else if (mLayout.getChkService2Item1View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService2Item1View().setChecked(true);
					mLayout.getTxtService2Item1ValView().setText(item.Value);
				} else if (mLayout.getChkService2Item2View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService2Item2View().setChecked(true);
					mLayout.getTxtService2Item2ValView().setText(item.Value);
				} else if (mLayout.getChkService2Item3View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService2Item3View().setChecked(true);
				} 
				
			}
			else if( item.ServiceId.equals("3")){ 
				mLayout.getChkService3View().setChecked(true); 
				if(mLayout.getRdoService3item1View().getText().equals(item.ServiceContent)){
					mLayout.getLayoutService3ContentView()
						.check(mLayout.getRdoService3item1View().getId());
				}else if(mLayout.getRdoService3item2View().getText().equals(item.ServiceContent)){
					mLayout.getLayoutService3ContentView()
					.check(mLayout.getRdoService3item2View().getId());
				}
			}
			else if( item.ServiceId.equals("4")){ 
				mLayout.getChkService4View().setChecked(true); 
				if (false) {
				} else if (mLayout.getChkService4Item1View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService4Item1View().setChecked(true);
					mLayout.getTxtService4Item1ValView().setText(item.Value);
				} else if (mLayout.getChkService4Item2View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService4Item2View().setChecked(true);
					mLayout.getTxtService4Item2ValView().setText(item.Value);
				} 

			}
			else if( item.ServiceId.equals("5")){ 
				mLayout.getChkService5View().setChecked(true); 
				if (false) {}
				else if(mLayout.getRdoService5item1View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService5ContentView().check(mLayout.getRdoService5item1View().getId());
				}
				else if(mLayout.getRdoService5item2View().getText().equals(item.ServiceContent)){
					mLayout.getLayoutService5ContentView().check(mLayout.getRdoService5item2View().getId());
				}

			}
			else if (item.ServiceId.equals("6")) {
				mLayout.getChkService6View().setChecked(true);
				if (false) {
				} else if (mLayout.getChkService6Item1View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService6Item1View().setChecked(true);
					mLayout.getTxtService6Item1ValView().setText(item.Value);
				} else if (mLayout.getChkService6Item2View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService6Item2View().setChecked(true);
					mLayout.getTxtService6Item2ValView().setText(item.Value);
				} else if (mLayout.getChkService6Item3View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService6Item3View().setChecked(true);
				} else if (mLayout.getChkService6Item4View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService6Item4View().setChecked(true);
					mLayout.getTxtService6Item4ValView().setText(item.Value);
				}

			} else if (item.ServiceId.equals("7")) {
				mLayout.getChkService7View().setChecked(true);
				if(false){}
				else if(mLayout.getRdoService7item1View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService7ContentView().check(mLayout.getRdoService7item1View().getId());
				}
				else if(mLayout.getRdoService7item2View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService7ContentView().check(mLayout.getRdoService7item2View().getId());
				}
				else if(mLayout.getRdoService7item3View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService7ContentView().check(mLayout.getRdoService7item3View().getId());
				}
				else if(mLayout.getRdoService7item4View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService7ContentView().check(mLayout.getRdoService7item4View().getId());
				}


			} else if (item.ServiceId.equals("8")) {
				mLayout.getChkService8View().setChecked(true);
				if (false) {
				} else if (mLayout.getChkService8Item1View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService8Item1View().setChecked(true);
					mLayout.getTxtService8Item1ValView().setText(item.Value);
				} else if (mLayout.getChkService8Item2View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService8Item2View().setChecked(true);
					mLayout.getTxtService8Item2ValView().setText(item.Value);
				} 

			} else if (item.ServiceId.equals("9")) {
				mLayout.getChkService9View().setChecked(true);
				
				if(false){}
				else if(mLayout.getRdoService9item1View().getText().equals(item.ServiceContent)){
					mLayout.getLayoutService9ContentView().check(mLayout.getRdoService9item1View().getId());
				}
				else if(mLayout.getRdoService9item2View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService9ContentView().check(mLayout.getRdoService9item2View().getId());
				}
				else if(mLayout.getRdoService9item3View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService9ContentView().check(mLayout.getRdoService9item3View().getId());
				}
//				else if(mLayout.getRdoService9item4View().getText().equals(item.ServiceContent)){ 
//					mLayout.getLayoutService9ContentView().check(mLayout.getRdoService9item4View().getId());
//				}


			} else if (item.ServiceId.equals("10")) {
				mLayout.getChkService10View().setChecked(true);
				mLayout.getTxtService10Item1ValView().setText(item.count);

			} else if (item.ServiceId.equals("11")) {
				mLayout.getChkService11View().setChecked(true);
				mLayout.getTxtService11Item1ValView().setText(item.count);

			} else if (item.ServiceId.equals("12")) {
				mLayout.getChkService12View().setChecked(true);
				if (false) {
				} else if (mLayout.getChkService12Item1View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService12Item1View().setChecked(true);
					mLayout.getTxtService12Item1ValView().setText(item.Value);
				} else if (mLayout.getChkService12Item2View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService12Item2View().setChecked(true);
					mLayout.getTxtService12Item2ValView().setText(item.Value);
				} else if (mLayout.getChkService12Item3View().getText()
						.equals(item.ServiceContent)) {
					mLayout.getChkService12Item3View().setChecked(true);
				} 

			} else if (item.ServiceId.equals("13")) {
				mLayout.getChkService13View().setChecked(true);
				mLayout.getTxtService13Item1ValView().setText(item.count);

			} else if (item.ServiceId.equals("14")) {
				mLayout.getChkService14View().setChecked(true);
				if(false){}
				else if(mLayout.getRdoService14item1View().getText().equals(item.ServiceContent)){
					mLayout.getLayoutService14ContentView().check(mLayout.getRdoService14item1View().getId());
				}
				else if(mLayout.getRdoService14item2View().getText().equals(item.ServiceContent)){ 
					mLayout.getLayoutService14ContentView().check(mLayout.getRdoService14item2View().getId());
				}


			} else if (item.ServiceId.equals("15")) {
				mLayout.getChkService15View().setChecked(true);
				mLayout.getTxtService15Item1ValView().setText(item.count);

			} else if (item.ServiceId.equals("16")) {
				mLayout.getChkService16View().setChecked(true);
				mLayout.getTxtService16Item1ValView().setText(item.count);

			} else if (item.ServiceId.equals("17")) {
				mLayout.getChkService17View().setChecked(true);
				mLayout.getTxtService17Item1ValView().setText(
						item.ServiceContent);

			} else if (item.ServiceId.equals("18")) {
				mLayout.getChkService18View().setChecked(true);
				mLayout.getTxtService18Item1ValView().setText(
						item.ServiceContent);
			}
			
//			if(false){}
//			else if( mLayout.getChkService2Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService2Item1View().setChecked(true); mLayout.getTxtService2Item1ValView().setText(item.Value); }
//			else if( mLayout.getChkService2Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService2Item2View().setChecked(true); mLayout.getTxtService2Item2ValView().setText(item.Value); }
//			else if( mLayout.getChkService4Item1View().getText().equals(item.ServiceContent)){ 
//				
//				mLayout.getChkService4Item1View().setChecked(true); 
//				mLayout.getTxtService4Item1ValView().setText(item.Value); 
//				
//			}
//			else if( mLayout.getChkService4Item2View().getText().equals(item.ServiceContent)){
//
//				mLayout.getChkService4Item2View().setChecked(true); 
//				mLayout.getTxtService4Item2ValView().setText(item.Value); 
//				
//			}
//			else if( mLayout.getChkService6Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item1View().setChecked(true); mLayout.getTxtService6Item1ValView().setText(item.Value); }
//			else if( mLayout.getChkService6Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item2View().setChecked(true); mLayout.getTxtService6Item2ValView().setText(item.Value); }
//			else if( mLayout.getChkService6Item4View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item4View().setChecked(true); mLayout.getTxtService6Item4ValView().setText(item.Value); }
//			else if( mLayout.getChkService8Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService8Item1View().setChecked(true); mLayout.getTxtService8Item1ValView().setText(item.Value); }
//			else if( mLayout.getChkService8Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService8Item2View().setChecked(true); mLayout.getTxtService8Item2ValView().setText(item.Value); }
//			else if( mLayout.getChkService12Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService12Item1View().setChecked(true); mLayout.getTxtService12Item1ValView().setText(item.Value); }
//			else if( mLayout.getChkService12Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService12Item2View().setChecked(true); mLayout.getTxtService12Item2ValView().setText(item.Value); }

//			else if( mLayout.getChkService2Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService2Item1View().setChecked(true); mLayout.getTxtService2Item1ValView().setText(item.Value); }
//			else if( mLayout.getChkService2Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService2Item2View().setChecked(true); }
//			else if( mLayout.getChkService4Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService4Item1View().setChecked(true); }
//			else if( mLayout.getChkService4Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService4Item2View().setChecked(true); }
//			else if( mLayout.getChkService6Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item1View().setChecked(true); }
//			else if( mLayout.getChkService6Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item2View().setChecked(true); }
//			else if( mLayout.getChkService6Item4View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item4View().setChecked(true); }
//			else if( mLayout.getChkService8Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService8Item1View().setChecked(true); }
//			else if( mLayout.getChkService8Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService8Item2View().setChecked(true); }
//			else if( mLayout.getChkService12Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService12Item1View().setChecked(true); }
//			else if( mLayout.getChkService12Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService12Item2View().setChecked(true); }
//
//			else if( mLayout.getChkService2Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService2Item1View().setChecked(true); }
//			else if( mLayout.getChkService2Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService2Item2View().setChecked(true); }
//			else if( mLayout.getChkService4Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService4Item1View().setChecked(true); }
//			else if( mLayout.getChkService4Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService4Item2View().setChecked(true); }
//			else if( mLayout.getChkService6Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item1View().setChecked(true); }
//			else if( mLayout.getChkService6Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item2View().setChecked(true); }
//			else if( mLayout.getChkService6Item4View().getText().equals(item.ServiceContent)){ mLayout.getChkService6Item4View().setChecked(true); }
//			else if( mLayout.getChkService8Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService8Item1View().setChecked(true); }
//			else if( mLayout.getChkService8Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService8Item2View().setChecked(true); }
//			else if( mLayout.getChkService12Item1View().getText().equals(item.ServiceContent)){ mLayout.getChkService12Item1View().setChecked(true); }
//			else if( mLayout.getChkService12Item2View().getText().equals(item.ServiceContent)){ mLayout.getChkService12Item2View().setChecked(true); }

		}
		
		

	}
	
	
	private ArrayList<AssessTaskServiceData> setData(){
		
		
		ArrayList<AssessTaskServiceData> dataList = new ArrayList<AssessTaskServiceData>();
//		1.===============
		{
			String id = "1";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService1View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService1View().getText()+"";
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_MONTH;
				data.count = mLayout.getTxtService1Item1ValView().getText()+"";
				data.Value = mLayout.getTxtService1Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		
//		2.================
		if(mLayout.getChkService2Item1View().isChecked())
		{
			String id = "2";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService2View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService2View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService2Item1View().getText();
				data.prefix = mLayout.getTxtService2Item1ValView().getText()+"";
				data.unit = AssessTaskServiceData.UNIT_HOUR;
				data.count = "1";
				data.Value = mLayout.getTxtService2Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService2Item2View().isChecked())
		{
			String id = "2";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService2View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService2View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService2Item2View().getText();
				data.prefix = mLayout.getTxtService2Item2ValView().getText()+"";
				data.unit = AssessTaskServiceData.UNIT_HOUR;
				data.count = "1";
				
				data.Value = mLayout.getTxtService2Item2ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService2Item3View().isChecked())
		{
			String id = "2";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService2View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService2View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService2Item3View().getText();
				dataList.add(data);
			}
		}
		
		
		
//		3.================
		if(mLayout.getLayoutService3ContentView().
				getCheckedRadioButtonId() != -1)
		{
			String id = "3";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService3View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService3View().getText()+"";
				data.ServiceContent = ""+
						
						((RadioButton)
						mV.findViewById(
						mLayout.getLayoutService3ContentView().
						getCheckedRadioButtonId()
						))
						.getText();
				dataList.add(data);
			}
		}
		
		
//		4.================
		if(mLayout.getChkService4Item1View().isChecked())
		{
			String id = "4";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService4View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService4View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService4Item1View().getText();
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService4Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService4Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService4Item2View().isChecked())
		{
			String id = "4";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService4View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService4View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService4Item2View().getText();
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService4Item2ValView().getText()+"";
				
				data.Value = mLayout.getTxtService4Item2ValView().getText()+"";
				dataList.add(data);
			}
		}
		
//		5.================
		if(mLayout.getLayoutService5ContentView().getCheckedRadioButtonId()
				!= -1)
		{
			String id = "5";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService5View());
			data.ServiceId = id;
			data.ServiceName = mLayout.getChkService5View().getText()+"";
			data.ServiceContent = ""+
					((RadioButton)
					mV.findViewById(
					mLayout.getLayoutService5ContentView().
					getCheckedRadioButtonId()
					))
					.getText();
			dataList.add(data);
		}
		
//		6.================
		if(mLayout.getChkService6Item1View().isChecked())
		{
			String id = "6";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService6View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService6View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService6Item1View().getText();
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_DAY;
				data.count = mLayout.getTxtService6Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService6Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService6Item2View().isChecked())
		{
			String id = "6";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService6View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService6View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService6Item2View().getText();
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_DAY;
				data.count = mLayout.getTxtService6Item2ValView().getText()+"";
				
				data.Value = mLayout.getTxtService6Item2ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService6Item3View().isChecked())
		{
			String id = "6";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService6View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService6View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService6Item3View().getText();
				dataList.add(data);
			}
		}
		if(mLayout.getChkService6Item4View().isChecked())
		{
			String id = "6";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService6View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService6View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService6Item4View().getText();
				data.prefix =  mLayout.getTxtService6Item4ValView().getText()+"";
				data.unit = AssessTaskServiceData.UNIT_HOUR;
				data.count = "1";
				
				data.Value = mLayout.getTxtService6Item4ValView().getText()+"";
				dataList.add(data);
			}
		}
		
//		7.================
		if(mLayout.getLayoutService7ContentView().getCheckedRadioButtonId()
				!= -1)
		{
			String id = "7";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService7View());
			data.ServiceId = id;
			data.ServiceName = mLayout.getChkService7View().getText()+"";
			data.ServiceContent = ""+
					((RadioButton)
					mV.findViewById(
					mLayout.getLayoutService7ContentView().
					getCheckedRadioButtonId()
					))
					.getText();
			dataList.add(data);
		}
		
//		8.================
		if(mLayout.getChkService8Item1View().isChecked())
		{
			String id = "8";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService8View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService8View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService8Item1View().getText();
				data.prefix = mLayout.getTxtService8Item1ValView().getText()+"";
				data.unit = AssessTaskServiceData.UNIT_HOUR;
				data.count = "1";
				
				data.Value = mLayout.getTxtService8Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService8Item2View().isChecked())
		{
			String id = "8";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService8View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService8View().getText()+"";
				data.ServiceContent = ""+
						mLayout.getChkService8Item2View().getText();
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_DAY;
				data.count = mLayout.getTxtService8Item2ValView().getText()+"";
				
				data.Value = mLayout.getTxtService8Item2ValView().getText()+"";
				dataList.add(data);
			}
		}
		
//		9.================
		if(mLayout.getLayoutService9ContentView().getCheckedRadioButtonId()
				!= -1)
		{
			String id = "9";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService9View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService9View().getText()+"";
				data.ServiceContent = ""+
						((RadioButton)
						mV.findViewById(
						mLayout.getLayoutService9ContentView().
						getCheckedRadioButtonId()
						))
						.getText();
				dataList.add(data);
			}
		}
		
//		10.===============
		{
			String id = "10";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService10View());
			if(data != null){
				data.ServiceId = id;	
				data.ServiceName = mLayout.getChkService10View().getText()+"";
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_MONTH;
				data.count = mLayout.getTxtService10Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService10Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		
//		11.===============
		{
			String id = "11";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService11View());
			if(data != null){
				data.ServiceId = id;	
				data.ServiceName = mLayout.getChkService11View().getText()+"";
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_MONTH;
				data.count = mLayout.getTxtService11Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService11Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
//		12.===============
		if(mLayout.getChkService12Item1View().isChecked())
		{
			String id = "12";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService12View());
			if(data != null){

				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService12View().getText()+"";
				data.ServiceContent = mLayout.getChkService12Item1View().getText()+"";
				
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService12Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService12Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService12Item2View().isChecked())
		{
			String id = "12";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService12View());
			if(data != null){
				
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService12View().getText()+"";
				data.ServiceContent = mLayout.getChkService12Item2View().getText()+"";
				
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService12Item2ValView().getText()+"";
				
				data.Value = mLayout.getTxtService12Item2ValView().getText()+"";
				dataList.add(data);
			}
		}
		if(mLayout.getChkService12Item3View().isChecked())
		{
			String id = "12";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService12View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService12View().getText()+"";
				data.ServiceContent = mLayout.getChkService12Item3View().getText()+"";
				
				dataList.add(data);
			}
		}
//		13.===============
		{
			String id = "13";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService13View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService13View().getText()+"";
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService13Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService13Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
//		14.===============
		if(mLayout.getLayoutService14ContentView().getCheckedRadioButtonId()
				!= -1)
		{
			String id = "14";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService14View());
			if(data != null){

				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService14View().getText()+"";
				data.ServiceContent = ""+
						((RadioButton)
						mV.findViewById(
						mLayout.getLayoutService14ContentView().
						getCheckedRadioButtonId()
						))
						.getText();
				dataList.add(data);
			}
		}
//		15.===============
		{
			String id = "15";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService15View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService15View().getText()+"";
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService15Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService15Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
//		16.===============
		{
			String id = "16";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService16View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService16View().getText()+"";
				data.prefix = "1";
				data.unit = AssessTaskServiceData.UNIT_WEEK;
				data.count = mLayout.getTxtService16Item1ValView().getText()+"";
				
				data.Value = mLayout.getTxtService16Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
//		17.===============
		{
			String id = "17";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService17View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService17View().getText()+"";
				data.ServiceContent = mLayout.getTxtService17Item1ValView().getText()+"";
				dataList.add(data);
			}
		}
//		18.===============
		{
			String id = "18";
			AssessTaskServiceData data = getServiceDataByView(mLayout
					.getChkService18View());
			if(data != null){
				data.ServiceId = id;
				data.ServiceName = mLayout.getChkService18View().getText()+"";
				data.ServiceContent = mLayout.getTxtService18Item1ValView().getText()+"";
				
				dataList.add(data);
			}
		}
		
		return dataList;
		
	}
	
}
