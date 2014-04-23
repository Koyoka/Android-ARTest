package com.yrkj.artaskgame.cmobile.acty;


import android.app.Activity;

public class LaunchActivity extends Activity{
	
}

//public class LaunchActivity extends Activity implements
//OnPageChangeListener,
//OnClickListener{
//	
//	final String TAG = "com.yrkj.artaskgame.cmobile.acty.LaunchActivity";
//	
//	private ArrayList<View> viewList = null;
//	private android.support.v4.view.ViewPager mPagerView = null;
//	private TextView mDotView = null;
//	
//	private String mDotOn;
//	private String mDotOff;
//	private String mDotText;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_launch);
//		initData();
//		initActy();
//		loadData();
//	}
//
//	private void initData(){
//		mDotOn = getResources().getString(R.string.text_dot_on);
//		mDotOff = getResources().getString(R.string.text_dot_off);
//	}
//
//	private void initActy(){
//		mDotView = (TextView) findViewById(R.id.txtDotView);
//		
////		LayoutInflater lf = getLayoutInflater().from(this);  
//		LayoutInflater lf = LayoutInflater.from(this);  
//        View view1 = lf.inflate(R.layout.activity_launch_page1, null); 
//        View view2 = lf.inflate(R.layout.activity_launch_page2, null); 
//  
//        viewList = new ArrayList<View>();
//        viewList.add(view1);
//        viewList.add(view2);
//        
//        mPagerView = (android.support.v4.view.ViewPager) findViewById(R.id.pagerView);
//		mPagerView.setOnPageChangeListener(this);
//		mPagerView.setAdapter(mPageAdapter);
//	}
//	
//	private void loadData(){
//		mDotText = "";
//		for (int i = 0; i < viewList.size(); i++)
//			mDotText += i;
//		setDotText(0);
//	}
//	
//	
//	private PagerAdapter mPageAdapter = new PagerAdapter(){
//
//		@Override
//		public int getCount() {
//			return viewList.size();
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			return arg0 == arg1;  
//		}
//		
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView(viewList.get(position));  
//		}
//		
//		public Object instantiateItem(ViewGroup container, int position) {
//			container.addView(viewList.get(position));
//			return viewList.get(position);
//		};
//	};
//
//	@Override
//	public void onClick(View arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onPageScrollStateChanged(int arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onPageScrolled(int arg0, float arg1, int arg2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onPageSelected(int arg0) {
//		setDotText(arg0);
//	}
//	
//	private void setDotText(int index){
//		String defineStr = "";
//		defineStr = mDotText.replace(index+"", mDotOn);//.replaceAll("//d", mDotOff);
//		defineStr = defineStr.replaceAll("\\d", mDotOff);
//		mDotView.setText(defineStr);
//	}
//	
//
//}
