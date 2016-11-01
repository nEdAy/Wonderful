package com.xgr.wonderful.firstuse;


import com.xgr.wonderful.R;
import com.xgr.wonderful.view.RegisterAndLoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class WhatsnewAnimationA extends Activity {

	private ImageView img_left, img_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_whatnew_animation);
		img_left = (ImageView) findViewById(R.id.doorpage_left);
		img_right = (ImageView) findViewById(R.id.doorpage_right);

		AnimationSet animLeft = new AnimationSet(true);
		TranslateAnimation transLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				-1f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);

		transLeft.setDuration(2000);

		animLeft.addAnimation(transLeft);
		animLeft.setFillAfter(true);
		img_left.startAnimation(transLeft);
		transLeft.startNow();

		AnimationSet animRight = new AnimationSet(true);
		TranslateAnimation transRight = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
		transRight.setDuration(2000);
		animRight.addAnimation(transRight);
		animRight.setFillAfter(true);
		img_right.startAnimation(transRight);
		transRight.startNow();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WhatsnewAnimationA.this,
						RegisterAndLoginActivity.class);
				startActivity(intent);
				WhatsnewAnimationA.this.finish();
			}
		}, 1000);
	}

}
