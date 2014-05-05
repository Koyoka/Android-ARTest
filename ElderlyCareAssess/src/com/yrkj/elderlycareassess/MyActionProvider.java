package com.yrkj.elderlycareassess;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;


public class MyActionProvider extends ActionProvider{
	private ContextWrapper mContextWrapper;
	private OnMenuItemClickListener mOnMenuItemClickListener;
	
	public MyActionProvider(Context context) {
		super(context);
		mContextWrapper = (ContextWrapper)context;
	}

	@Override
	public View onCreateActionView() {
		LayoutInflater layoutInflater = LayoutInflater.from(mContextWrapper);
	    View view = layoutInflater.inflate(R.layout.my_action_provider, null);
	    ImageView popupView = (ImageView)view.findViewById(R.id.popup_view);
        popupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        mOnMenuItemClickListener = new OnMenuItemClickListener() {
			private String TAG = "mOnMenuItemClickListener";

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.pop_acty_1:
					Log.v(TAG , "Settings1");
					break;
				
				case R.id.pop_acty_2:
					Log.v(TAG , "Settings2");
					break;

				default:
					break;
				}
				return false;
			}
		};
        return view;
	}

	protected void showPopup(View v) {
		PopupMenu popup = new PopupMenu(mContextWrapper, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.pop_list_menu, popup.getMenu());
	    popup.setOnMenuItemClickListener(mOnMenuItemClickListener);
	    popup.show();
	}

}