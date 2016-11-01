package com.stone.shop.adapter;

import java.util.List;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 首页--图片轮播AutoScrollViewPager适配器
 * @author Stone
 */
public class ImagePagerAdapter extends PagerAdapter {
	
	private List<View> mImgViews; 
	
	public ImagePagerAdapter(Context context, List<View> imgViews) {
		this.mImgViews = imgViews;
	}
	
	 @Override  
     public int getCount() {  
		 if(mImgViews != null){
			 return mImgViews.size(); 
		 }
         return 0;
     }  

     @Override  
     public Object instantiateItem(View arg0, int arg1) {  
         ((ViewPager) arg0).addView(mImgViews.get(arg1));  
         return mImgViews.get(arg1);  
     }  

     @Override  
     public void destroyItem(View arg0, int arg1, Object arg2) {  
         ((ViewPager) arg0).removeView(mImgViews.get(arg1));  
     }  

     @Override  
     public boolean isViewFromObject(View arg0, Object arg1) {  
         return arg0 == arg1;  
     }  

     @Override  
     public void restoreState(Parcelable arg0, ClassLoader arg1) {  

     }  

     @Override  
     public Parcelable saveState() {  
         return null;  
     }  

     @Override  
     public void startUpdate(View arg0) {  

     }  

     @Override  
     public void finishUpdate(View arg0) {  

     }  
     
}
