package com.yrkj.yrkjdemo;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yrkj.util.db.DBMng;
import com.yrkj.util.db.DBUpdate.OnDBUpdateListen;
import com.yrkj.util.dialog.DialogHelper;
import com.yrkj.util.log.ToastUtil;
import com.yrkj.yrkjdemo.dao.DemoDBMng;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

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
			initFram(rootView);
			return rootView;
		}
		
		Button mBtn = null;
		TextView mTxt = null;
		Button mBtnLook = null;
		TextView mTxtShow = null;
		
		private void initFram(View v){
			
			mTxt = (TextView) v.findViewById(R.id.txtDescView);
			mTxtShow = (TextView) v.findViewById(R.id.txtShowView);
			
			mBtn = (Button) v.findViewById(R.id.btnStartView);
			mBtnLook = (Button) v.findViewById(R.id.btnLookView);
			
			
			mBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					boolean r = DemoDBMng.init(getActivity(), new OnDBUpdateListen() {

						@Override
						public void l(String s) {
							// TODO Auto-generated method stub
							mTxt.setText(s);
						}

						@Override
						public void begin() {
							// TODO Auto-generated method stub
							DialogHelper.getProgressDialogInstance().show(getActivity(), "数据库更新中");
						}

						@Override
						public void end() {
							// TODO Auto-generated method stub
							mTxt.setText("完成");
							DialogHelper.getProgressDialogInstance().close();
						}
						
						
					});
					
					ToastUtil.show(getActivity(), r?"success":"failure");
					
				}
			});


			mBtnLook.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String s = testDBData();
					String s2 = testDBData2();
					mTxtShow.setText(s + s2);
				}
			});
			
		}
		
		private String  testDBData(){
			DemoDBMng dbMng = new DemoDBMng(getActivity());
			
			dbMng.open();
			
			dbMng.execSQL("insert into tbl_1(id,name,age,sex,id2) values(\"1\",\"2\",\"3\",\"4\",\"5\");");
			Cursor cursor = dbMng.rawQuery("Select * From MAIN.[tbl_1] Limit 1000;");
			dbMng.close();
			
			String s = "";
			if(cursor.moveToFirst()){
				do{
					String id = DBMng.GetDataString(cursor, "id");
					String name = DBMng.GetDataString(cursor, "name");
					String age = DBMng.GetDataString(cursor, "age");
					String sex = DBMng.GetDataString(cursor, "sex");
					String id2 = DBMng.GetDataString(cursor, "id2");
					s += "[" + id + ","+name+","+age+","+sex+","+id2+"] ";
				}while(cursor.moveToNext());
			}
			cursor.close();
			return s;
		}
		private String  testDBData2(){
			DemoDBMng dbMng = new DemoDBMng(getActivity());
			
			dbMng.open();
			
			dbMng.execSQL("insert into tbl_2(id,name,age) values(\"1\",\"2\",\"3\");");
			Cursor cursor = dbMng.rawQuery("Select * From MAIN.[tbl_2] Limit 1000;");
			dbMng.close();
			
			String s = "";
			if(cursor.moveToFirst()){
				do{
					String id = DBMng.GetDataString(cursor, "id");
					String name = DBMng.GetDataString(cursor, "name");
					String age = DBMng.GetDataString(cursor, "age");
//					String sex = DBMng.GetDataString(cursor, "sex");
					s += "<" + id + ","+name+","+age+"> ";
				}while(cursor.moveToNext());
			}
			cursor.close();
			return s;
		}
		
	}

}
