package com.yrkj.artaskgame;

import com.yrkj.util.log.DLog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_scan_view);

		View lineView = findViewById(R.id.lineView);

		final View target = lineView;
		final View targetParent = (View) target.getParent();
		final Context c = this;
		targetParent.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// TODO Auto-generated method stub
						targetParent.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						int height = targetParent.getHeight();
						Animation a = new TranslateAnimation(0.0f, 0.0f, 0.0f,
								height);
						DLog.LOG("ElevenQCAR", "height [" + height
								+ "] ");
						a.setDuration(800);
						a.setStartOffset(300);
						a.setRepeatMode(Animation.RESTART);
						a.setRepeatCount(Animation.INFINITE);

						a.setInterpolator(AnimationUtils.loadInterpolator(c,
								android.R.anim.accelerate_interpolator));
						target.startAnimation(a);
					}
				});
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	// public static void main(String[] args) {
	// // System.out.println("aasdfadsf");
	// }

}
