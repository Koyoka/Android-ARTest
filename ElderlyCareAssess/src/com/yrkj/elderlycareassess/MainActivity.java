package com.yrkj.elderlycareassess;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.elderlycareassess.widget.UIReportCount;
import com.yrkj.util.log.DLog;
import com.yrkj.util.log.ToastUtil;



public class MainActivity extends Activity {

	MainActivity mActy;
	private int index = 0;;
	
	private void t2(final int[] i, int id,int sId){
		final UIReportCount v1 = 
				(UIReportCount) findViewById(id);
		v1.setData(getResources().getString(sId));
				v1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						ToastUtil.show(mActy, "aa");
						v1.setValue(i[index]);
						index++;
						if(index >= i.length){
							index = 0;
						}
						
					}
				});
				v1.setValue(i[2]);
	}
	private void t1(){
		t2(new int[]{10,80,30},R.id.uctrlRC1View,R.string.fragment_home_report_m1);
		t2(new int[]{40,80,20},R.id.uctrlRC2View,R.string.fragment_home_report_m2);
		t2(new int[]{90,50,70},R.id.uctrlRC3View,R.string.fragment_home_report_m3);
		t2(new int[]{64,30,10},R.id.uctrlRC4View,R.string.fragment_home_report_m4);
		t2(new int[]{30,80,23},R.id.uctrlRC5View,R.string.fragment_home_report_m5);
		t2(new int[]{60,35,85},R.id.uctrlRC6View,R.string.fragment_home_report_m6);
		t2(new int[]{90,40,1},R.id.uctrlRC7View,R.string.fragment_home_report_m7);
		if(true)
			return;
		
		
		{
			final int[] i = new int[]{90,80,70};
			
			final UIReportCount v1 = 
			(UIReportCount) findViewById(R.id.uctrlRC1View);
			v1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ToastUtil.show(mActy, "aa");
					v1.setValue(i[index]);
					index++;
					if(index >= i.length){
						index = 0;
					}
					
				}
			});
			v1.setValue(40);
		}
		{
			final UIReportCount v1 = 
					(UIReportCount) findViewById(R.id.uctrlRC2View);
			v1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					v1.setValue(80);
				}
			});
		}
	}
	int t = -100;
	private void aaa(){
		final View v = findViewById(R.id.testAAAView);
//		v.setPadding(0, 0, 0, 0);
		if(t==100){
			t = -100;
		}else{
			t = 100;
		}
		
		
		Animation a 
		= new TranslateAnimation(0.0f,0.0f, 
				0,
				t
				);
		a.setDuration(400);
        a.setStartOffset(10);
        a.setFillAfter(true);
        a.setInterpolator(AnimationUtils.loadInterpolator(this,
                android.R.anim.accelerate_interpolator));
        a.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
//				v.layout(v.getLeft(), t, v.getRight(), v.getTop());
				FrameLayout.LayoutParams lp1 = (android.widget.FrameLayout.LayoutParams) v.getLayoutParams();
				lp1.setMargins(0, t==-100?0:100, 0, 0);
				v.setLayoutParams(lp1);
				v.clearAnimation();
//				findViewById(R.id.testBBBView).setPadding(0, t==-100?0:100, 0, 0);
				DLog.LOG(" end ");
			}
		});
        v.startAnimation(a);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActy = this;
		t1();
		if(true){
			return;
		}
		findViewById(R.id.testClickView).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DLog.LOG(SysMng.TAG_UCTRL, "!!!!!!!");
				aaa();
			}
		});
		if(true){
			return;
		}
		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//			.add(arg0, arg1)
//			getSupportFragmentManager().beginTransaction()
			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment())
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		View viewG = getLayoutInflater().inflate(R.layout.actionbar_cust_default, null);
//		View viewG = getLayoutInflater().inflate(R.layout.action_bar_display_options_custom, null);
		ActionBar bar = getActionBar();
		
		 ActionBar.LayoutParams lp = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//	        lp.gravity = Gravity.END;
//	        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//	        lp.gravity = Gravity.END;
        bar.setCustomView(viewG,
                lp);	
       
        
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setDisplayShowTitleEnabled(false);
//        bar.setDisplayShowHomeEnabled(false);
//        bar.setDisplayHomeAsUpEnabled(true);
//        bar.setDisplayShowCustomEnabled(true);
//        bar.setDisplayShowTitleEnabled(true);
        
        
//        bar.setIcon(R.drawable.ic_menu_back);
//        bar.setLogo(R.drawable.ic_menu_back);
        
        viewG.findViewById(R.id.btnActionBarBackView).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mActy.finish();
			}
		});
        
        viewG.findViewById(R.id.btnActionBarRightView).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtil.show(mActy, "Right click");
				pop(v);
			}
		});
	}
	
	private void pop(View v){
		 PopupMenu popup = new PopupMenu(this, v);
	        popup.getMenuInflater().inflate(R.menu.pop_list_menu, popup.getMenu());

	        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
	            public boolean onMenuItemClick(MenuItem item) {
	                Toast.makeText(MainActivity.this, "Clicked popup menu item " + item.getTitle(),
	                        Toast.LENGTH_SHORT).show();
	                return true;
	            }
	        });

	        popup.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.pop_action_menu, menu);  
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		 switch (item.getItemId()) {
	        case android.R.id.home:
	            this.finish();
	            break;
//	        default:
//	            return super.onOptionsItemSelected(item);
	    }
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}


	
}




//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.animation.Animation;
//import android.view.animation.Animation.AnimationListener;
//import android.view.animation.AnimationUtils;
//import android.widget.AbsoluteLayout;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//public class MainActivity extends Activity
//{
//	/** Called when the activity is first created. */
//	LinearLayout main, selection;
//	Button hidden, show;
//	@Override
//	public void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main_1);
//		main = (LinearLayout) findViewById(R.id.main);
//		selection = (LinearLayout) findViewById(R.id.selection);
//		hidden = (Button) findViewById(R.id.hidden);
//		show = (Button) findViewById(R.id.show);
//		show.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				Animation showAnim=AnimationUtils.loadAnimation(MainActivity.this, R.anim.show);
////				translate.setFillAfter(true);
//				//如果只用setFillAfter方法保存移动后的位置，真实位置不会移动
//				selection.startAnimation(showAnim);
//                                //如果下面的View是一个listview,可能需要先执行selection.requestFocusFromTouch();否则第二次不会显示动画
//                               //需要把下面的控件 enable设为false,防止点中下面的控件
//				show.setEnabled(false);
//				//必须设为false,因为如果连续点击两次，就会连着执行两次移动位置，并不是我们想要的结果
//				//等把拉下的view移回去后，再设为true
//				showAnim.setAnimationListener(new AnimationListener(){
//
//					@Override
//					public void onAnimationEnd(Animation animation)
//					{
//						// TODO Auto-generated method stub
//						hidden.setEnabled(true);
//						//当下拉动画结束后，把隐藏的按钮设为可用
//						selection.clearAnimation();
//						selection.layout(selection.getLeft(), 0, selection.getRight(), 200);
////						selection.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.FILL_PARENT, 200, 0, 0){});
//						//上面两行功能相同
//						//移动控件到动画结束的位置，clearAnimation方法可以清除动画，屏幕就不会闪，没有的话会闪
//						//setFillAfter不能为true,虽然即使为true，控件真实位置也不会变，但是我们看到的位置是会变的，如果再用layout方法，我们看到的位置还会再变
//						System.out.println(selection.getLeft()+" "+selection.getTop()+" "+selection.getRight()+" "+selection.getBottom());
//						//输出移动后的位置，经过测试，如果不使用layout方法移动控件，动画前，动画后，控件的位置都是不变的
//						//所以，亲眼所见并非真相
//					}
//					@Override
//					public void onAnimationRepeat(Animation animation)
//					{
//						// TODO Auto-generated method stub
//					}
//					@Override
//					public void onAnimationStart(Animation animation)
//					{
//						// TODO Auto-generated method stub
//						System.out.println(selection.getLeft()+" "+selection.getTop()+" "+selection.getRight()+" "+selection.getBottom());
//					}});
//			}
//		});
//		hidden.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				Animation hiddenAnim=AnimationUtils.loadAnimation(MainActivity.this, R.anim.hide);
//				selection.startAnimation(hiddenAnim);
//				hidden.setEnabled(false);
//				hiddenAnim.setAnimationListener(new AnimationListener(){
//
//					@Override
//					public void onAnimationEnd(Animation animation)
//					{
//						// TODO Auto-generated method stub
//						show.setEnabled(true);
//						selection.clearAnimation();
////						selection.layout(selection.getLeft(), -190, selection.getRight(), 10);
//						selection.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.FILL_PARENT, 200, 0, -190){});
//						//以上两行，功能相同
//					}
//
//					@Override
//					public void onAnimationRepeat(Animation animation)
//					{
//						// TODO Auto-generated method stub
//
//					}
//
//					@Override
//					public void onAnimationStart(Animation animation)
//					{
//						// TODO Auto-generated method stub
//
//					}});
//
//
//			}});
//
//	}
//}
//
//
