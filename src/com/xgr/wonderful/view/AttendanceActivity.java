package com.xgr.wonderful.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.GetServerTimeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.bmob.im.demo.CustomApplcation;
import com.stone.shop.model.Attendance;
import com.stone.shop.model.User;
import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.BaseActivity;

public class AttendanceActivity extends BaseActivity implements OnClickListener {

	ImageView ImageView1open, ImageView2open, ImageView1close, ImageView2close;
	TextView textView1, textView2;
	public Boolean ImageView1Flag = false, ImageView2Flag = false;
	int timess = 0000;
	private Vibrator mVibrator;
	private LocationClient mLocationClient1, mLocationClient2;
	private NotiftLocationListener1 listener1;
	private NotiftLocationListener2 listener2;
	private NotifyLister1 mNotifyLister1;
	private NotifyLister2 mNotifyLister2;
	User user = new User();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendance);
		
		ImageView1close = (ImageView) findViewById(R.id.ImageView1close);
		ImageView1close.setOnClickListener(this);
		ImageView2close = (ImageView) findViewById(R.id.ImageView2close);
		ImageView2close.setOnClickListener(this);
		ImageView1open = (ImageView) findViewById(R.id.ImageView1open);
		ImageView1open.setOnClickListener(this);
		ImageView2open = (ImageView) findViewById(R.id.ImageView2open);
		ImageView2open.setOnClickListener(this);
		user = userManager.getCurrentUser(User.class);
		Query();

		listener1 = new NotiftLocationListener1();
		listener2 = new NotiftLocationListener2();
		mVibrator = (Vibrator) getApplicationContext().getSystemService(
				Context.VIBRATOR_SERVICE);
		mLocationClient1 = new LocationClient(this);
		mLocationClient1.registerLocationListener(listener1);
		mLocationClient2 = new LocationClient(this);
		mLocationClient2.registerLocationListener(listener2);
		mLocationClient1.start();
		mLocationClient2.start();


	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mLocationClient1.removeNotifyEvent(mNotifyLister1);
		mLocationClient2.removeNotifyEvent(mNotifyLister2);
		mLocationClient1 = null;
		mLocationClient2 = null;
		mNotifyLister1 = null;
		mNotifyLister2 = null;
		listener1 = null;
		listener2 = null;
	}
	public boolean ImageView1Time() {
		int time1 = getTime();
		if (time1 > 600 && time1 < 700) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ImageView2Time() {
		int time2 = getTime();
		if (time2 > 1815 && time2 < 1900) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.ImageView1close:
			if (ImageView1Flag && ImageView2Time()) {
				Update1();
				Save1();
			} else if (!ImageView1Flag) {
				ShowToast("早操未到操场区域不可签到！");

			} else if (!ImageView1Time()) {
				ShowToast("早操未到签到时间不可签到！");
			}
			break;
		case R.id.ImageView1open:
			ShowToast("早操签到已完成,无需重复签到！");
			break;
		case R.id.ImageView2close:
			if (ImageView2Flag && ImageView2Time()) {
				Update2();
				Save2();
			} else if (!ImageView2Flag) {
				ShowToast("晚自习未到教室区域不可签到！");

			} else if (!ImageView2Time()) {
				ShowToast("晚自习未到签到时间不可签到！");
			}
			break;
		case R.id.ImageView2open:
			ShowToast("晚自习签到已完成，无需重复签到！");
			break;
		}

	}

	@SuppressLint("HandlerLeak")
	private Handler notifyHandler1 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			mNotifyLister1 = new NotifyLister1();
			mNotifyLister1.SetNotifyLocation(39.069328, 117.113021, 100,
					"bd09ll");// 4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
			mLocationClient1.registerNotify(mNotifyLister1);
		}

	};
	@SuppressLint("HandlerLeak")
	private Handler notifyHandler2 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			mNotifyLister2 = new NotifyLister2();
			mNotifyLister2.SetNotifyLocation(39.07667, 117.115868, 100,
					"bd09ll");// 4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
			mLocationClient2.registerNotify(mNotifyLister2);
		}

	};

	public class NotiftLocationListener1 implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			notifyHandler1.sendEmptyMessage(0);
		}
	}
	public class NotiftLocationListener2 implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			notifyHandler2.sendEmptyMessage(1);
		}
	}

	public class NotifyLister1 extends BDNotifyListener {
		@Override
		public void onNotify(BDLocation mlocation, float distance) {
			if (!ImageView1Flag) {
				mVibrator.vibrate(1000);// 振动提醒已到设定位置附近
				ShowToast("已到早操区域，请在签到时段签到！");
			} else {
				mLocationClient1.removeNotifyEvent(mNotifyLister1);
			}
			ImageView1Flag = true;

		}
	}

	public class NotifyLister2 extends BDNotifyListener {
		@Override
		public void onNotify(BDLocation mlocation, float distance) {
			if (!ImageView2Flag) {
				mVibrator.vibrate(1000);// 振动提醒已到设定位置附近
				ShowToast("已到晚自习区域，请在签到时段签到！");
			} else {
				mLocationClient2.removeNotifyEvent(mNotifyLister2);
			}
			ImageView2Flag = true;

		}
	}

	public int getTime() {
		Bmob.getServerTime(AttendanceActivity.this,
				new GetServerTimeListener() {
					@SuppressLint("SimpleDateFormat")
					@Override
					public void onSuccess(long time) {
						// TODO Auto-generated method stub
						SimpleDateFormat formatter = new SimpleDateFormat(
								"HHmm");
						String times = formatter.format(new Date(time * 1000L));
						timess = Integer.parseInt(times);
					}

					@Override
					public void onFailure(int code, String msg) {
						// TODO Auto-generated method stub
						ShowToast("获取服务器时间失败:" + msg);
					}
				});
		return timess;
	}
	public void Query() {
		BmobQuery<User> query = new BmobQuery<User>();
		query.getObject(this, BmobUser.getCurrentUser(this).getObjectId(), new GetListener<User>() {

		    @Override
		    public void onSuccess(User object) {
		        // TODO Auto-generated method stub
		    	ImageView1Flag = object.getImageA();
		    	ImageView2Flag = object.getImageB();

				user.setImageA(object.getImageA());
				user.setImageB(object.getImageB());		
				if(ImageView1Flag){
					ImageView1open.setVisibility(View.VISIBLE);
					ImageView1close.setVisibility(View.INVISIBLE);
				}else{
					ImageView1open.setVisibility(View.INVISIBLE);
					ImageView1close.setVisibility(View.VISIBLE);
				}
				if(ImageView2Flag){
					ImageView2open.setVisibility(View.VISIBLE);
					ImageView2close.setVisibility(View.INVISIBLE);
				}else{
					ImageView2open.setVisibility(View.INVISIBLE);
					ImageView2close.setVisibility(View.VISIBLE);
				}
		    }

		    @Override
		    public void onFailure(int code, String arg0) {
		        // TODO Auto-generated method stub
		        ShowToast("查询签到情况失败："+arg0);
		    }

		});

	}
	public void Update1() {
		ImageView2Flag = true;
		user.setImageA(ImageView2Flag);
		user.update(this,new UpdateListener() {
		    @Override
		    public void onSuccess() {
		        // TODO Auto-generated method stub
				ImageView1open.setVisibility(View.VISIBLE);
				ImageView1close.setVisibility(View.INVISIBLE);
		        ShowToast("早操签到成功!");
		    }

		    @Override
		    public void onFailure(int code, String msg) {
		        // TODO Auto-generated method stub
		        ShowToast("早操签到失败："+msg);
		    }
		});

	}
	public void Update2() {
		ImageView2Flag = true;
		user.setImageB(ImageView2Flag);
		user.update(this, new UpdateListener() {
		    @Override
		    public void onSuccess() {
		        // TODO Auto-generated method stub
				ImageView2open.setVisibility(View.VISIBLE);
				ImageView2close.setVisibility(View.INVISIBLE);
		        ShowToast("晚自习签到成功!");
		    }

		    @Override
		    public void onFailure(int code, String msg) {
		        // TODO Auto-generated method stub
		        ShowToast("晚自习签到失败："+msg);
		    }
		});

	}
	public void Save1() {
		User user = BmobUser.getCurrentUser(this,User.class);
		Attendance attendance = new Attendance();
		attendance.setUsername(user.getUsername());
		attendance.setScnumber(20135070);
		attendance.setLocation(CustomApplcation.lastPoint);
		attendance.setType("早操");		
		attendance.save(mContext, new SaveListener() {
	
		    @Override
		    public void onSuccess() {
		        // TODO Auto-generated method stub
		    }

		    @Override
		    public void onFailure(int code, String arg0) {
		        // TODO Auto-generated method stub
		        ShowToast("晚自习签到失败："+arg0);
		    }
		});
	}
	public void Save2() {
		User user = BmobUser.getCurrentUser(this,User.class);
		Attendance attendance = new Attendance();
		attendance.setUsername(user.getUsername());
		attendance.setScnumber(20135070);
		attendance.setLocation(CustomApplcation.lastPoint);
		attendance.setType("晚自习");		
		attendance.save(mContext, new SaveListener() {
	
		    @Override
		    public void onSuccess() {
		        // TODO Auto-generated method stub
		    }

		    @Override
		    public void onFailure(int code, String arg0) {
		        // TODO Auto-generated method stub
		        ShowToast("晚自习签到失败："+arg0);
		    }
		});
	}
}
