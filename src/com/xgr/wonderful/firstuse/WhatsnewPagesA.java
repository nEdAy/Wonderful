package com.xgr.wonderful.firstuse;

import java.util.ArrayList;

import com.xgr.wonderful.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;


public class WhatsnewPagesA extends Activity {
	private ViewPager viewPager;
	private ImageView imageView;
	private ArrayList<View> pageViews;
	private ImageView[] imageViews;
	private ViewGroup viewPictures;
	private ViewGroup viewPoints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.first_viewpager01, null));
		pageViews.add(inflater.inflate(R.layout.first_viewpager02, null));
		pageViews.add(inflater.inflate(R.layout.first_viewpager03, null));
		pageViews.add(inflater.inflate(R.layout.first_viewpager04, null));
		pageViews.add(inflater.inflate(R.layout.first_viewpager05, null));
		pageViews.add(inflater.inflate(R.layout.first_viewpager06, null));

		imageViews = new ImageView[pageViews.size()];
		viewPictures = (ViewGroup) inflater.inflate(R.layout.first_viewpagers, null);

		viewPager = (ViewPager) viewPictures.findViewById(R.id.guidePagers);
		viewPoints = (ViewGroup) viewPictures.findViewById(R.id.viewPoints);

		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(WhatsnewPagesA.this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(5, 0, 5, 0);
			imageViews[i] = imageView;
			if (i == 0)
				imageViews[i].setImageDrawable(getResources().getDrawable(
						R.drawable.first_page_indicator_focused));
			else
				imageViews[i].setImageDrawable(getResources().getDrawable(
						R.drawable.first_page_indicator_unfocused));
			viewPoints.addView(imageViews[i]);
		}

		setContentView(viewPictures);

		viewPager.setAdapter(new NavigationPageAdapter());
		viewPager.setOnPageChangeListener(new NavigationPageChangeListener());
	}

	class NavigationPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(pageViews.get(position));
			return pageViews.get(position);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(pageViews.get(position));
		}

	}

	class NavigationPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[i].setImageDrawable(getResources().getDrawable(
						R.drawable.first_page_indicator_focused));
				if (position != i)
					imageViews[i].setImageDrawable(getResources().getDrawable(
							R.drawable.first_page_indicator_unfocused));
			}
		}

	}

	public void startbutton(View v) {
		Intent intent = new Intent(WhatsnewPagesA.this, WhatsnewAnimationA.class);
		startActivity(intent);
		WhatsnewPagesA.this.finish();
	}

}
