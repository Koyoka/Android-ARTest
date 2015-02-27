package com.yrkj.mwrmobile.util.view;

import com.yrkj.mwrmobile.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ListView;

public class ListViewMng {

	public static void setListView(Context c,ListView v)
	{
		v.setDivider(new ColorDrawable(Color.GRAY));
		v.setDividerHeight(1);
		
//		Resources resources = c.getResources();
//		Drawable drawable = resources.getDrawable(R.drawable.liner);
//		v.setDivider(drawable);
//		v.setDividerHeight(1);
		// v.setDividerHeight(1); // ����ʾ�ָ���
		v.setCacheColorHint(Color.TRANSPARENT); // ��ֹlistview�������
		
	}
}
