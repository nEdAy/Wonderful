package com.xgr.wonderful.view;

import cn.bmob.im.BmobChat;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.bmob.im.demo.CustomApplcation;
import com.bmob.im.demo.ui.ImBaseActivity;
import com.umeng.message.PushAgent;
import com.xgr.wonderful.R;
import com.xgr.wonderful.firstuse.WelcomeA;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * TODO 闪屏界面，根据指定时间进行跳转 在activity_splash.xml中加入background属性并传入图片资源ID即可
 */
public class SplashActivity extends ImBaseActivity {

	private static final long DELAY_TIME = 1000L;
	private static final int GO_HOME = 100;
	private static final int GO_NOUSER = 200;
	// 定位获取当前用户的地理位置
	private LocationClient mLocationClient;

	private BaiduReceiver mReceiver;// 注册广播接收器，用于监听网络以及验证key
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//可设置调试模式，当为true的时候，会在logcat的BmobChat下输出一些日志，包括推送服务是否正常运行，如果服务端返回错误，也会一并打印出来。方便开发者调试
		BmobChat.DEBUG_MODE = true;
		// Bmob SDK初始化--只需要这一段代码即可完成初始化
		BmobChat.getInstance(this).init("30a7db2beab5c063fb08bb828a0b52de");
		// 开启定位
		initLocClient();
        //友盟推送注册
        PushAgent.getInstance(getBaseContext()).onAppStart();
        PushAgent mPushAgent = PushAgent.getInstance(getBaseContext());
        mPushAgent.enable();


		// 注册地图 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new BaiduReceiver();
		registerReceiver(mReceiver, iFilter);
		if (userManager.getCurrentUser() != null) {
			updateUserInfos();
			mHandler.sendEmptyMessageDelayed(GO_HOME, DELAY_TIME);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_NOUSER, DELAY_TIME);
		}
	}
	/**
	 * 开启定位，更新当前用户的经纬度坐标
	 * @Title: initLocClient
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initLocClient() {
		mLocationClient = CustomApplcation.getInstance().mLocationClient;
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式:高精度模式
		option.setCoorType("bd09ll"); // 设置坐标类型:百度经纬度
		option.setScanSpan(1000);// 设置发起定位请求的间隔时间为1000ms:低于1000为手动定位一次，大于或等于1000则为定时定位
		option.setIsNeedAddress(false);// 不需要包含地址信息
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}
	/**
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class BaiduReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				ShowToast("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				ShowToast("当前网络连接不稳定，请检查您的网络设置!");
			}
		}
	}
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
//				double d=Math.random()*100;
//				if(d<=33){
//					startActivity(new Intent(SplashActivity.this,
//							SnowActivity.class));
//					finish();
//					break;
//				}
//				if(d<=66){
//					startActivity(new Intent(SplashActivity.this,
//							WindyActivity.class));
//					finish();
//					break;
//				}
//				if(d<=100){
//					startActivity(new Intent(SplashActivity.this,
//							WindmillActivity.class));
//					finish();
//					break;
//				}
				startActivity(new Intent(SplashActivity.this,
						MainActivity.class));
				finish();
				break;
			case GO_NOUSER:
				startActivity(new Intent(SplashActivity.this,
						WelcomeA.class));
				finish();
				break;
			}
		}
	};
	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		if (mLocationClient != null && mLocationClient.isStarted()) {
			mLocationClient.stop();
		}
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
}
