package com.xgr.wonderful.ui;

import com.bmob.im.demo.ui.ImBaseActivity;
import com.lenovo.lps.sus.SUS;
import com.xgr.wonderful.MyApplication;
import com.xgr.wonderful.utils.Constant;
import com.xgr.wonderful.utils.Sputil;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

public class BaseActivity extends ImBaseActivity implements
		OnSharedPreferenceChangeListener {

	protected static String TAG;

	protected MyApplication mMyApplication;
	protected Sputil sputil;
	protected Resources mResources;
	protected Context mContext;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		TAG = this.getClass().getSimpleName();
		initConfigure();
	}

	protected void StartVersionUpdate() {
		boolean isStartVersionUpdateFlag = false;
		/* 应用首次启动时自动启动版本更新功能 */
		if (!isStartVersionUpdateFlag) {
			isStartVersionUpdateFlag = true;

			if (!SUS.isVersionUpdateStarted()) {
				SUS.AsyncStartVersionUpdate(this);
			}
		}
	}

	private void initConfigure() {
		mContext = this;
		if (null == mMyApplication) {
			mMyApplication = MyApplication.getInstance();
		}
		mMyApplication.addActivity(this);
		if (null == sputil) {
			sputil = new Sputil(this, Constant.PRE_NAME);
		}
		sputil.getInstance().registerOnSharedPreferenceChangeListener(this);
		mResources = getResources();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		// 可用于监听设置参数，然后作出响应
	}

	/**
	 * Activity跳转
	 * 
	 * @param context
	 * @param targetActivity
	 * @param bundle
	 */
	public void redictToActivity(Context context, Class<?> targetActivity,
			Bundle bundle) {
		Intent intent = new Intent(context, targetActivity);
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	Toast mToast;

	@Override
	public void ShowToast(final String text) {
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
    protected boolean isGpsEnable() {  
    	LocationManager locationManager = (LocationManager)getBaseContext().  
    			getSystemService(Context.LOCATION_SERVICE);  
    			return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);  
    }  
}
