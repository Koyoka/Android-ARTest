package com.yrkj.elderlycareassess.acty;

import java.util.ArrayList;
import java.util.List;

import ru.truba.touchgallery.GalleryWidget.BasePagerAdapter.OnItemChangeListener;
import ru.truba.touchgallery.GalleryWidget.FilePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;

public class AlbumActivity extends Activity {

	AlbumActivity mActy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		mActy = this;
		initActy();
	}
	private GalleryViewPager mViewPager;
	private void initActy(){
        List<String> items = new ArrayList<String>();

        ArrayList<AssessTaskAttachmentImageData> itemlist =
				AttachmentDBCtrl.getAttachmentImgList(this);
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
