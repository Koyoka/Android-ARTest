package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;
import java.util.List;

import ru.truba.touchgallery.GalleryWidget.BasePagerAdapter.OnItemChangeListener;
import ru.truba.touchgallery.GalleryWidget.FilePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;

public class AlbumActivity extends Activity {

	public static final String INTENT_KEY_TASKHEADER_ID = "headerId";
	public static final String INTENT_KEY_CATE_ID = "cateId";
	
	public int mTaskHeaderId = 0;
	public int mCateId = 0;
	
	AlbumActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		
		Intent intent = getIntent();
		if(intent !=null){
			mTaskHeaderId = intent.getIntExtra(INTENT_KEY_TASKHEADER_ID,0);
			mCateId = intent.getIntExtra(INTENT_KEY_CATE_ID,0);
		}
		
		mActy = this;
		initActy();
	}
	private GalleryViewPager mViewPager;
	private void initActy(){

		if(mTaskHeaderId == 0 || mCateId == 0){
			return;
		}
		
        ArrayList<AssessTaskAttachmentImageData> itemlist =
				AttachmentDBCtrl.getAttachmentImgList(this,mTaskHeaderId,mCateId);
        if(itemlist.size() == 0){
        	return;
        }
        
        List<String> items = new ArrayList<String>();
		for (AssessTaskAttachmentImageData item : itemlist) {
			items.add(item.ImgPath);
		}
        
        FilePagerAdapter pagerAdapter = new FilePagerAdapter(this, items);
        pagerAdapter.setOnItemChangeListener(new OnItemChangeListener()
		{
			@Override
			public void onItemChange(int currentPosition)
			{
			}
		});
        mViewPager = (GalleryViewPager)findViewById(R.id.viewer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);
	}

}
