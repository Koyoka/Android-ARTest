package com.yrkj.elderlycareassess.fragment.widget;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
//import android.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
	public static final int DATE_PICKER_DIALOG = 1;
	public static final int DATE_PICKER_DIALOG_YM = 4;
	public static final int ALTER_DIALOG = 2;
	public static final int TIME_PICKER_DiALOG = 3;
	private onDateSelected mOnDateSelected;
	public void setOnDateSelected(onDateSelected l){
		mOnDateSelected = l;
	}
	public interface onDateSelected{
		public void onSelected(int y,int m,int d);
	}
	
	public onDialogClosed mOnDialogClosed;
	public void setOnDialogClosed(onDialogClosed l){
		mOnDialogClosed = l;
	}
	public interface onDialogClosed{
		public void onClosed(boolean r);
	}
	

	public static MyDialogFragment newInstance(int title) {
		MyDialogFragment myDialogFragment = new MyDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("title", title);
		myDialogFragment.setArguments(bundle);
		
		Calendar date=Calendar.getInstance();
		int m = date.get(date.MONTH);
		int d = date.get(date.DAY_OF_MONTH);
		int y = date.get(date.YEAR);
		
		myDialogFragment.setDateDialogValue(y,m,d);
		
		
		return myDialogFragment;
	}
	
	private int mY = 0;
	private int mM = 0;
	private int mD = 0;
	public void setDateDialogValue(int y,int m,int d){
		mY = y;
		mM = m;
		mD = d;
				
	}

	
	public static DatePicker findDatePicker(ViewGroup group) {  
        if (group != null) {  
            for (int i = 0, j = group.getChildCount(); i < j; i++) {  
                View child = group.getChildAt(i);  
                if (child instanceof DatePicker) {  
                    return (DatePicker) child;  
                } else if (child instanceof ViewGroup) {  
                    DatePicker result = findDatePicker((ViewGroup) child);  
                    if (result != null)  
                        return result;  
                }  
            }  
        }  
        return null;  
    }   
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		int args = getArguments().getInt("title");
		//根据传进来的参数选择创建哪种Dialog
		switch (args) {
		case DATE_PICKER_DIALOG_YM:
			DatePickerDialog d = new DatePickerDialog(getActivity(), new OnDateSetListener() {
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					//月是从0开始的
					System.out.println( "year-->" + year + "  month-->" + monthOfYear
							+ "  day-->" + dayOfMonth);
					if(mOnDateSelected != null)
						mOnDateSelected.onSelected(year,monthOfYear,dayOfMonth);
				}
			}, mY,mM, mD);
			
			
			return d;
		
		
		case DATE_PICKER_DIALOG:
			
//			DatePickerDialog d;
			
			
			return new DatePickerDialog(getActivity(), new OnDateSetListener() {
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					//月是从0开始的
					System.out.println( "year-->" + year + "  month-->" + monthOfYear
							+ "  day-->" + dayOfMonth);
					if(mOnDateSelected != null)
						mOnDateSelected.onSelected(year,monthOfYear,dayOfMonth);
				}
			}, mY,mM, mD);
		case ALTER_DIALOG:
//			View v = 
			
			return new AlertDialog.Builder(getActivity())
//				.setIcon(R.drawable.ic_launcher)
				.setCancelable(false)
				.setTitle("确认消息")
//				.setView(view)
				.setMessage(getTag())
				.setPositiveButton("确认",
						new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					//点击ok触发的事件
					if(mOnDialogClosed != null){
						mOnDialogClosed.onClosed(true);
					}
//					System.out.println("click ok!");
				}
				})
			.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					//点击cancle触发的时间
					if(mOnDialogClosed != null){
						mOnDialogClosed.onClosed(false);
					}
				}
				})
				.create();
			
		
		case TIME_PICKER_DiALOG:
			return new TimePickerDialog(getActivity(),new OnTimeSetListener() {
				
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					System.out.println("hour-->"+hourOfDay+"  minute-->"+ minute);
				}
			}, 13, 23, true);
		}
		return null;
	}
	
	

}
