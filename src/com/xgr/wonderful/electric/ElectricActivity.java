package com.xgr.wonderful.electric;

import com.xgr.wonderful.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ElectricActivity extends Activity{
	private View adBannerView;
	ImageView app_logo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		CustomSurfaceView view = new CustomSurfaceView(this, null);
		setContentView(view);
		ShowToast("按返回键可关闭彩蛋..");
	}

	@SuppressLint({ "InlinedApi", "InflateParams" }) void showBannerAd() {
		FrameLayout mLayout = (FrameLayout) LayoutInflater.from(this).inflate(
				R.layout.adbanner, null);
		FrameLayout.LayoutParams lytp = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 80);
		lytp.gravity = Gravity.TOP;
		addContentView(mLayout, lytp);
		ViewGroup containerView = (ViewGroup) mLayout
				.findViewById(R.id.banner_ad_container);

		if (adBannerView != null
				&& containerView.indexOfChild(adBannerView) >= 0) {
			containerView.removeView(adBannerView);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	Toast mToast;
	private void ShowToast(final String text) {
		if (!TextUtils.isEmpty(text)) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (mToast == null) {
						mToast = Toast.makeText(getApplicationContext(), text,
								Toast.LENGTH_LONG);
					} else {
						mToast.setText(text);
					}
					mToast.show();
				}
			});
		}
	}
}
