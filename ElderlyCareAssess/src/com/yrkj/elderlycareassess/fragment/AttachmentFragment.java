package com.yrkj.elderlycareassess.fragment;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.yrkj.elderlycareassess.R;
import com.yrkj.elderlycareassess.acty.AlbumActivity;
import com.yrkj.elderlycareassess.acty.SoundActivity;
import com.yrkj.elderlycareassess.bean.AssessTaskAttachmentImageData;
import com.yrkj.elderlycareassess.dao.AttachmentDBCtrl;
import com.yrkj.elderlycareassess.layout.FragmentAttachment;
import com.yrkj.elderlycareassess.util.FragmentMediaHelper;
import com.yrkj.elderlycareassess.util.sound.Recorder;
import com.yrkj.util.bitmap.BitmapHelper;
import com.yrkj.util.bitmap.MediaHelper;

public class AttachmentFragment extends Fragment implements OnClickListener {
	
	private FragmentAttachment mLayout;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_attachment, container,
				false);
		
		mLayout = new FragmentAttachment(v);
//      mViewPager = (GalleryViewPager)v.findViewById(R.id.listGalleryView);
		initFragment();
		return v;
	}
	
	private void initFragment(){
		mLayout.getBtnDiseaseView().setOnClickListener(this);
		mLayout.getBtnCameraView().setOnClickListener(this);
		bind();
	}
	
	private void popDisease(){
		PopupMenu popup = new PopupMenu(getActivity(), mLayout.getBtnDiseaseView());
		popup.getMenuInflater().inflate(R.menu.disease, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				mLayout.getBtnDiseaseView().setText(item.getTitle());
				return true;
			}
		});
		
		popup.show();
	}
	private String mSavePath = "";
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case FragmentAttachment.BtnDiseaseViewId:
			popDisease();
			break;

		case FragmentAttachment.BtnCameraViewId:
//			a();
//			mSavePath = FragmentMediaHelper.getSavePath();
//			FragmentMediaHelper.setMedia(this,MediaHelper.MEDIA_IMG_CAMERA /*| MediaHelper.MEDIA_IMG_CROP*/,mSavePath);
			break;
			
		default:
			break;
		}
			
	}
	
	
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String imgPath = FragmentMediaHelper.getMedioResultPath(this,data,requestCode,mSavePath);
		
		AssessTaskAttachmentImageData d = new AssessTaskAttachmentImageData();
		d.ImgPath = imgPath;
		
		AttachmentDBCtrl.addAttachmentImg(getActivity(), d);
		bind();
	}
	
	private void bind(){
		mLayout.getLayoutImgList().removeAllViews();
		ArrayList<AssessTaskAttachmentImageData> itemlist =
				AttachmentDBCtrl.getAttachmentImgList(getActivity());
		for (AssessTaskAttachmentImageData item : itemlist) {
			
			Bitmap b = BitmapHelper.decodeSampledBitmapFromLacolPath(item.ImgPath,40,40);
			
			ImageView v = new ImageView(getActivity());
			
			LinearLayout.LayoutParams lp =
					new LinearLayout.LayoutParams(120, 120);
			lp.setMargins(10, 0, 10, 0);
			v.setLayoutParams(lp);
			
			v.setScaleType(ImageView.ScaleType.CENTER_CROP);
            v.setImageBitmap(b);
            mLayout.getLayoutImgList().addView(v);
            v.setOnClickListener(imgClick);
           
		}
		
	}
	
	OnClickListener imgClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(getActivity(), AlbumActivity.class);
			startActivity(intent);
		}
	};
	
//	private GalleryViewPager mViewPager;
//	 List<String> items = new ArrayList<String>();
//	public void bindImage(){
////		 String[] urls = null;
//		ArrayList<AssessTaskAttachmentImageData> itemlist =
//				AttachmentDBCtrl.getAttachmentImgList(getActivity());
//		for (AssessTaskAttachmentImageData item : itemlist) {
//			items.add(item.ImgPath);
//		}
//	        
//        FilePagerAdapter pagerAdapter = new FilePagerAdapter(getActivity(), items);
//        pagerAdapter.setOnItemChangeListener(new OnItemChangeListener()
//		{
//			@Override
//			public void onItemChange(int currentPosition)
//			{
//				ToastUtil.show(getActivity(), "Current item is " + currentPosition);
////				Toast.makeText(GalleryFileActivity.this, "Current item is " + currentPosition, Toast.LENGTH_SHORT).show();
//			}
//		});
//        
//
//        mViewPager.setOffscreenPageLimit(3);
//        mViewPager.setAdapter(pagerAdapter);   
//	        
//	}
	
}
