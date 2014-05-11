package com.yrkj.elderlycareassess.widget;

import android.content.Context;
import android.content.res.TypedArray;
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

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.base.SysMng;
import com.yrkj.util.log.DLog;

public class UIReportCount extends LinearLayout{

	private Context mContext;
	
	public UIReportCount(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		TypedArray a = context.obtainStyledAttributes(attrs,  
                R.styleable.UIReportCount); 
		 
		 initCtrl(a);
	}

	private View mCountBox = null;
	private View mCount = null;
//	private TextView mTitle = null;
	private TextView mDesc = null;
	
	private void initCtrl(TypedArray a){
		this.setOrientation(LinearLayout.VERTICAL);
//		{
//			TextView v = new TextView(mContext);
//			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//			
//			lp.weight = 1;
//			v.setGravity(Gravity.CENTER_HORIZONTAL);
//			v.setText("m1");
//			v.setBackgroundColor(Color.WHITE);
//			this.addView(v);
//			mTitle = v;
//		}
		{
			int textColor = a.getColor(R.styleable.UIReportCount_vColor,  
					 Color.parseColor("#EAFBC0")); 
			FrameLayout countBox = new FrameLayout(mContext);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			countBox.setLayoutParams(lp);
			
			{
				RelativeLayout count = new RelativeLayout(mContext);
				FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				lp1.height=2000;
				count.setLayoutParams(lp1);
//				count.setBackgroundColor(Color.parseColor("#EAFBC0"));
				count.setBackgroundColor(textColor);
				count.setVisibility(View.GONE);
				countBox.addView(count);
				mCount = count;
			}
			this.addView(countBox);
			mCountBox = countBox;
			
		}
		
		{
			String s = a.getString(R.styleable.UIReportCount_vText); 
			TextView v = new TextView(mContext);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			lp.weight = 1;
			v.setGravity(Gravity.CENTER_HORIZONTAL);
			v.setText(s);
			v.setBackgroundColor(Color.WHITE);
			this.addView(v);
			mDesc = v;
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
				setMargin(mH-1);
				mCount.setVisibility(View.VISIBLE);
				setValue(mDefaultV);
				mDefaultV = 0;
			}
		});
	}
	
	private void setMargin(int h){
		FrameLayout.LayoutParams lp1 = 
				(android.widget.FrameLayout.LayoutParams) mCount.getLayoutParams();
		lp1.setMargins(0, h, 0, 0);
		mCount.setLayoutParams(lp1);
	}
	
	public void setData(String s){
		mDesc.setText(s);
	}
	public void setColor(String c){
		mCount.setBackgroundColor(Color.parseColor(c));
	}
	int mDefaultV = 0;
	public void setValue(int v){
		if(mH == 0){
			mDefaultV = v;
			return;
		}
		
		if(v>=0&&v<=100){
//			mTitle.setText();
			float defineResult = (v/(float)100)*(float)mH;
			FrameLayout.LayoutParams lp1 = 
					(android.widget.FrameLayout.LayoutParams) mCount.getLayoutParams();
			
			doAnim(lp1.topMargin,defineResult);
		}
		
	}
	
	private synchronized void doAnim(int t,final float h){
		
		int c = 0;
		c = h==0?mH-1:mH-(int)h;
//		final int cc = c;
		int g = 0;//mH-t>h?c:(mH-t<h?-c:0);
		g = h==0?mH-t - 1:mH-t - (int)h;
		
		DLog.LOG(SysMng.TAG_UCTRL,"结束位置【"+h+"】移动距离["+g+"] 当前 位置【" 
		+ (mH-t) + "】 mH["+mH+"] h["+h+"] r["+c+"]");
		
		final int r = c;
		Animation a 
		= new TranslateAnimation(0.0f,0.0f, 
				0,
				g
				);
		
        a.setDuration(500);
        a.setStartOffset(10);
        a.setFillAfter(true);
        a.setInterpolator(AnimationUtils.loadInterpolator(mContext,
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
				mCount.clearAnimation();
				FrameLayout.LayoutParams lp1 = 
						(android.widget.FrameLayout.LayoutParams) mCount.getLayoutParams();
				lp1.setMargins(0, r, 0, 0);
				mCount.setLayoutParams(lp1);
			}
		});
        mCount.startAnimation(a);
	}
	
}
