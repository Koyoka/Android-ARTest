package com.yrkj.util.log;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

	public static void show(Context activity,String text){
		show(activity, text, Toast.LENGTH_SHORT);
	}
	
	public static void show(Context activity,String text,int duration){
		Toast toast = Toast.makeText(activity, text, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
	}
}
