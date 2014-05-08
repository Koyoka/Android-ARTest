package com.yrkj.elderlycareassess.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.util.log.DLog;

public class UIReportCount extends LinearLayout{

	private Context mContext;
	
	public UIReportCount(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initCtrl();
	}

	private View mCountBox = null;
	private View mCount = null;
	
	private void initCtrl(){
		this.setOrientation(LinearLayout.VERTICAL);
		
		{
			
			FrameLayout countBox = new FrameLayout(mContext);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			countBox.setLayoutParams(lp);
			
			{
				RelativeLayout count = new RelativeLayout(mContext);
				FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//				lp1.setMargins(0, 200, 0, 0);
				lp1.height=2000;
				count.setLayoutParams(lp1);
				count.setBackgroundColor(Color.parseColor("#ff00ff"));
//				count.setBackgroundResource(R.drawable.actionbar_bg	);
				countBox.addView(count);
				mCount = count;
			}
			this.addView(countBox);
			mCountBox = countBox;
//			mCount.setPadding(0, 100, 0, 0);
			
		}
		
		{
			TextView v = new TextView(mContext);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			lp.weight = 1;
			v.setGravity(Gravity.CENTER_HORIZONTAL);
			v.setText("m1");
			v.setBackgroundColor(Color.WHITE);
			this.addView(v);
			
		}
		getSize();
	}
	
	private int mH = 0;
	private void getSize(){
		final View v = this;
		ViewTreeObserver vto2 = this.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				v.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				DLog.LOG(SysMng.TAG_UCTRL, "this h["+v.getHeight()+"] w["+v.getWidth()+"]");
				DLog.LOG(SysMng.TAG_UCTRL, "mCount h["+mCountBox.getHeight()+"] w["+mCountBox.getWidth()+"]");
				mH = mCountBox.getHeight();
				
				setHeight();
			}
		});
	}
	
	private void setHeight(){
//		LinearLayout.LayoutParams lp = (LayoutParams) mCount.getLayoutParams();
//		lp.height = mH;
//		mCount.setLayoutParams(lp);
	}
	
	
	public void setData(String s){
		
	}
	public void setValue(int v){
		
		if(v>0&&v<100){
			float defineResult = (v/(float)100)*(float)mH;
//			DLog.LOG(SysMng.TAG_UCTRL,"mCount.getPaddingTop() ["+mCount.getPaddingTop()+"]  defineResult ["+(int)defineResult+"] v/100["+v/100+"] v["+v+"]");
			
//			mCount.getPaddingTop();
//			defineResult = 50;
//			doAnim(mCountBox.getPaddingTop(),defineResult);
			FrameLayout.LayoutParams lp1 = 
					(android.widget.FrameLayout.LayoutParams) mCount.getLayoutParams();

			doAnim(lp1.topMargin,defineResult);
			
			
		}
	}
	
	void doA(){
		
	}
	Runnable run;
	int ii = 1;
	private synchronized void doAnim(int t,final float h){
		
		int c = 0;
		c = mH-(int)h;
		final int cc = c;
		int g = mH-t>h?c:(mH-t<h?-c:0);
		g = mH-t - (int)h;
		
		DLog.LOG(SysMng.TAG_UCTRL,"结束位置【"+h+"】移动距离["+g+"] 当前 位置【" + (mH-t) + "】 mH["+mH+"] h["+h+"]");
		final int r = c;
//		final int i = 4;
		final int tt = 1;
		Animation a 
		= new TranslateAnimation(0.0f,0.0f, 
				0,
				g
				);
		
        a.setDuration(200);
        a.setStartOffset(10);
        a.setFillAfter(true);
        a.setInterpolator(AnimationUtils.loadInterpolator(mContext,
                android.R.anim.accelerate_interpolator));
        a.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				setHeight();
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
//				mCountBox.setPadding(0, r, 0, 0);
				mCount.clearAnimation();
				FrameLayout.LayoutParams lp1 = 
						(android.widget.FrameLayout.LayoutParams) mCount.getLayoutParams();
//				lp1.height=1000;
				lp1.setMargins(0, r, 0, 0);
				mCount.setLayoutParams(lp1);
				DLog.LOG(" end ");
			}
		});
        mCount.startAnimation(a);
//        mCountBox.layout(l, t, r, b)
	}
	
}
