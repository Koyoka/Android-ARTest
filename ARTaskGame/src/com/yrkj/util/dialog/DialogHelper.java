package com.yrkj.util.dialog;

import com.yrkj.artaskgame.R;
import com.yrkj.util.ui.layout.CommDialogSelectphotoView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;



public class DialogHelper {
	
	public interface ConfirmDialogListener{
		public void onClickListener(boolean result);
	}
	
	public static void createConfirmDialog(final Activity acty,String content,final ConfirmDialogListener l){
		new AlertDialog.Builder(acty)
      .setMessage(content)
      .setPositiveButton("确认", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
            	  if(l!= null){
            		  l.onClickListener(true);
            	  }
//            	  acty.finish();
//                  new Thread(new Runnable(){
//
//					@Override
//					public void run() {
//						BaseApplication.instance.xmppManager.getConnection().disconnect();
//						
//					}}).start();
                  
              }
          })
          .setNegativeButton("取消", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
            	  if(l!= null){
            		  l.onClickListener(false);
            	  }
                  /* User clicked Cancel so do some stuff */
              }
          })
      .show();
	}
	
	public interface DialogSelected{
		void onSelected(int which);
	}
	public static void createSelectListDialog(final Activity acty,String title,final String[] items,final DialogSelected l){
		final AlertDialog dialog =  new AlertDialog.Builder(acty)
        .setTitle(title)
        .setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                new AlertDialog.Builder(acty)
//                        .setMessage("You selected: " + which + " , " + items[which])
//                        .show();
                
                if(l!= null){
                	l.onSelected(which);
                }
            }
        })
        .create();
		
		dialog.show();
	}
	
	public static void createTextDialog(final Activity acty,String title,String message){
		new AlertDialog.Builder(acty)
			.setTitle(title)
	      .setMessage(message)
	      .show();
	}
	
	private static void userSelectPhotoDialog(final Activity acty,final OnClickListener l){
		final Dialog d = new Dialog(acty, R.style.UserDialog);
		d.setContentView(R.layout.comm_dialog_selectphoto_view);
		d.show();
		
		OnClickListener clickL = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				d.dismiss();
				if(l!=null){
					l.onClick(v);
				}
			}
		};
		d.findViewById(CommDialogSelectphotoView.BtnOpenCameraViewId).setOnClickListener(clickL);
		d.findViewById(CommDialogSelectphotoView.BtnOpenPhotoAlbumViewId).setOnClickListener(clickL);
		d.findViewById(CommDialogSelectphotoView.BtnCancelDialogViewId).setOnClickListener(clickL);
		
//		LayoutInflater factory = LayoutInflater.from(acty);
//		final View textEntryView = factory.inflate(
//				R.layout.comm_dialog_selectphoto_view, null);
//		CommDialogSelectphotoView mCommDialogSelectphotoView = new CommDialogSelectphotoView(textEntryView);
//		mCommDialogSelectphotoView.getBtnCancelDialogView().setOnClickListener(clickL);
//		mCommDialogSelectphotoView.getBtnOpenCameraView().setOnClickListener(clickL);
//		mCommDialogSelectphotoView.getBtnOpenPhotoAlbumView().setOnClickListener(clickL);
	}
	
	public static void createSelectPhotoDialog(final Activity acty,final OnClickListener l) {
		userSelectPhotoDialog(acty,l);
		if(true){
			return;
		}
		LayoutInflater factory = LayoutInflater.from(acty);
		final View textEntryView = factory.inflate(
				R.layout.comm_dialog_selectphoto_view, null);

		
		
		
		final AlertDialog dialog = new AlertDialog.Builder(acty).setTitle("选择上传图片")
				
				.setView(textEntryView).create();
		
		dialog.show();
		WindowManager windowManager = acty.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int)(display.getWidth()); 
		dialog.getWindow().setAttributes(lp);
		
		OnClickListener clickL = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				if(l!=null){
					l.onClick(v);
				}
			}
		};
		
		CommDialogSelectphotoView mCommDialogSelectphotoView = new CommDialogSelectphotoView(textEntryView);
		mCommDialogSelectphotoView.getBtnCancelDialogView().setOnClickListener(clickL);
		mCommDialogSelectphotoView.getBtnOpenCameraView().setOnClickListener(clickL);
		mCommDialogSelectphotoView.getBtnOpenPhotoAlbumView().setOnClickListener(clickL);
	}


	public static DialogHelper getProgressDialogInstance(){
		if(_instance == null){
			_instance = new DialogHelper();
		}
//		_instance.set_context(context);
		return _instance;
	}
	public void show(Context context,String msg){
		if(_instance == null){
			_instance = new DialogHelper();
		}
		
		_dialog = ProgressDialog.show(context, msg, msg, true);
		_dialog.setContentView(R.layout.loading);
		((TextView)_dialog.findViewById(R.id.txtLoadingMsgView)).setText(msg);
		_dialog.setCancelable(false);
//		_dialog.setCancelable(true);
	}
	public void close(){
		if (_dialog != null) {
			_dialog.dismiss();
		}
	}
	private Context _context = null;
	public Context get_context() {
		return _context;
	}
	public void set_context(Context _context) {
		this._context = _context;
	}
	
	
	private ProgressDialog _dialog = null;
	private static DialogHelper _instance = null;
	
//	class dd extends ProgressDialog{
//
//		public dd(Context context) {
//			super(context);
//			// TODO Auto-generated constructor stub
//		}
//		
//	}

}
