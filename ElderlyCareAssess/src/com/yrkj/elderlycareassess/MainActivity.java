package com.yrkj.elderlycareassess;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//			.add(arg0, arg1)
//			getSupportFragmentManager().beginTransaction()
			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment())
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		initActy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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

	public class StudentInfo extends Fragment
	{
	    // Fragment对应的标签，当Fragment依附于Activity时得到
	    private String tag;

	    @Override
	    public void onAttach(Activity activity)
	    {
	        super.onAttach(activity);
	        tag = getTag();
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	    {
	        TextView textView = new TextView(getActivity());
	        textView.setText(tag);
	        return textView;
	    }
	}
	
	class DropDownListenser implements OnNavigationListener
    {
        // 得到和SpinnerAdapter里一致的字符数组
        String[] listNames = getResources().getStringArray(R.array.action_list);

        /* 当选择下拉菜单项的时候，将Activity中的内容置换为对应的Fragment */
        public boolean onNavigationItemSelected(int itemPosition, long itemId)
        {
            // 生成自定的Fragment
            StudentInfo student = new StudentInfo();
            FragmentManager manager = getFragmentManager();
//            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            // 将Activity中的内容替换成对应选择的Fragment
            transaction.replace(R.id.container, student, listNames[itemPosition]);
            transaction.commit();
            return true;
        }
    }
	
	
	private void initActy(){
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_dropdown_item);
	
		android.app.ActionBar actionBar = getActionBar();
        // 将ActionBar的操作模型设置为NAVIGATION_MODE_LIST
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // 为ActionBar设置下拉菜单和监听器
        actionBar.setListNavigationCallbacks(adapter, new DropDownListenser());
	}
	
}
