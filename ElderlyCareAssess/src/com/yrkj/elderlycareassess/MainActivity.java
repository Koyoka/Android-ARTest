package com.yrkj.elderlycareassess;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.yrkj.util.log.ToastUtil;



public class MainActivity extends ActionBarActivity {

	MainActivity mActy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActy = this;
		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//			.add(arg0, arg1)
			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment())
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		ViewGroup viewG = (ViewGroup) getLayoutInflater().inflate(R.layout.actionbar_cust_default, null);
		ActionBar bar = getActionBar();
        bar.setCustomView(viewG,
                new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));	
        bar.setDisplayShowCustomEnabled(true);
        bar.setDisplayShowHomeEnabled(false);
        
        viewG.findViewById(R.id.btnActionBarBackView).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtil.show(mActy, "back click");
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
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
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




//public class StudentInfo extends Fragment
//{
//    // Fragment对应的标签，当Fragment依附于Activity时得到
//    private String tag;
//
//    @Override
//    public void onAttach(Activity activity)
//    {
//        super.onAttach(activity);
//        tag = getTag();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        TextView textView = new TextView(getActivity());
//        textView.setText(tag);
//        return textView;
//    }
//}
//
//class DropDownListenser implements OnNavigationListener
//{
//    // 得到和SpinnerAdapter里一致的字符数组
//    String[] listNames = getResources().getStringArray(R.array.action_list);
//
//    /* 当选择下拉菜单项的时候，将Activity中的内容置换为对应的Fragment */
//    public boolean onNavigationItemSelected(int itemPosition, long itemId)
//    {
//        // 生成自定的Fragment
//        StudentInfo student = new StudentInfo();
//        FragmentManager manager = getFragmentManager();
////        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        // 将Activity中的内容替换成对应选择的Fragment
//        transaction.replace(R.id.container, student, listNames[itemPosition]);
//        transaction.commit();
//        return true;
//    }
//}
//
//
//private void initActy(){
//	SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_dropdown_item);
//
//	android.app.ActionBar actionBar = getActionBar();
//    // 将ActionBar的操作模型设置为NAVIGATION_MODE_LIST
//    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//    // 为ActionBar设置下拉菜单和监听器
//    actionBar.setListNavigationCallbacks(adapter, new DropDownListenser());
//}
