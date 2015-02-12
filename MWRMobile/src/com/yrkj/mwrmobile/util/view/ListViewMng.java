package com.yrkj.mwrmobile.util.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

public class ListViewMng {

	public static void setListView(ListView v)
	{
		v.setDivider(new ColorDrawable(Color.WHITE));
		v.setDividerHeight(0);
		// v.setDividerHeight(1); // 不显示分割线
		v.setCacheColorHint(Color.TRANSPARENT); // 防止listview背景变黑
		
	}
}
