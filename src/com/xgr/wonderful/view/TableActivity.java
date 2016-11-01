package com.xgr.wonderful.view;

import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.BaseActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends BaseActivity {
	private int colors[] = { Color.argb(0x00, 0x00, 0x00, 0x00),
			Color.rgb(0xf0, 0x96, 0x09), Color.rgb(0x8c, 0xbf, 0x26),
			Color.rgb(0x00, 0xab, 0xa9), Color.rgb(0x99, 0x6c, 0x33),
			Color.rgb(0x3b, 0x92, 0xbc), Color.rgb(0xd5, 0x4d, 0x34),
			Color.rgb(0xcc, 0xcc, 0xcc), Color.rgb(0xd5, 0xcc, 0xa9),
			Color.rgb(0x99, 0x4d, 0x26) };
	// 定义sensor管理器
	private SensorManager mSensorManager;
	// 震动监听器
	private sensorListener sensor;
	public static int flag = 0;
	private static HorizontalScrollView L = null;
	LinearLayout ll1,ll2,ll3,ll4,ll5;
	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class);

		
		L = (HorizontalScrollView)findViewById(R.id.L);
		Resources r = getResources();
		L.setBackgroundDrawable(r.getDrawable(R.drawable.bg01));
		// 获取传感器管理服务
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// 监听器
		sensor = new sensorListener();
		// 加速度传感器
		mSensorManager.registerListener(sensor,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				// 还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
				// 根据不同应用，需要的反应速率不同，具体根据实际情况设定
				SensorManager.SENSOR_DELAY_NORMAL);

		// 每天的课程设置
		setClass_2013_1();
	}
	void setClass_2013_1(){
		// 分别表示周一到周日
		LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
		LinearLayout ll2 = (LinearLayout) findViewById(R.id.ll2);
		LinearLayout ll3 = (LinearLayout) findViewById(R.id.ll3);
		LinearLayout ll4 = (LinearLayout) findViewById(R.id.ll4);
		LinearLayout ll5 = (LinearLayout) findViewById(R.id.ll5);
		setClass(ll1, "1大学英语Ⅲ", "B401", "2-17", "李墨", 2, 1);
		setClass(ll1, "计算机网络技术", "B409#C408", "-5,14-17#6-13", "魏立华", 2, 2);
		setNoClass(ll1, 3, 0);
		setClass(ll1, "毛概", "3阶", "2-17", "于丽", 2, 3);
		setNoClass(ll1, 1, 0);
		setClass(ll1, "职业生涯发展概论", "3阶", "2-17", "韩旭彤", 2, 4);

		setClass(ll2, "微机原理与接口技术", "B514", "2-17", "魏立华", 2, 5);
		setClass(ll2, "MS面向对象应用技术", "B515", "2-17", "张一鸣", 2, 8);
		setNoClass(ll2, 3, 0);
		setClass(ll2, "离散数学", "B409", "2-17", "李迎春", 2, 6);
		setNoClass(ll2, 3, 0);

		setNoClass(ll3, 2, 0);
		setClass(ll3, "计算机网络技术", "B409", "1-17", "魏立华", 2, 2);
		setNoClass(ll3, 8, 0);

		setClass(ll4, "微机原理与接口技术", "B514#C509", "-5,14-17#6-13", "魏立华", 2, 5);
		setClass(ll4, "大学英语Ⅲ", "B515", "2-17 ", "李墨", 2, 1);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "离散数学", "B514", "2-17", "李迎春", 2, 6);
		setClass(ll4, "体育Ⅲ", "B409#操场", "1,16#2-15,17", "陈桂凤,刘晓英", 2, 7);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "毛概", "3阶", "1-17", "于丽", 2, 3);

		setNoClass(ll4, 2, 0);
		setClass(ll5, "MS面向对象应用技术", "C408", "1-17", "张一鸣", 2, 8);
		setNoClass(ll5, 3, 0);
		setClass(ll5, "市场营销", "3阶", "1-17", "朱利清", 2, 9);
		setNoClass(ll5, 3, 0);
	}
	void setClass_2013_2(){
		// 分别表示周一到周日
		LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
		LinearLayout ll2 = (LinearLayout) findViewById(R.id.ll2);
		LinearLayout ll3 = (LinearLayout) findViewById(R.id.ll3);
		LinearLayout ll4 = (LinearLayout) findViewById(R.id.ll4);
		LinearLayout ll5 = (LinearLayout) findViewById(R.id.ll5);
		setClass(ll1, "2大学英语Ⅲ", "B401", "2-17", "李墨", 2, 1);
		setClass(ll1, "计算机网络技术", "B409#C408", "-5,14-17#6-13", "魏立华", 2, 2);
		setNoClass(ll1, 3, 0);
		setClass(ll1, "毛概", "3阶", "2-17", "于丽", 2, 3);
		setNoClass(ll1, 1, 0);
		setClass(ll1, "职业生涯发展概论", "3阶", "2-17", "韩旭彤", 2, 4);

		setClass(ll2, "微机原理与接口技术", "B514", "2-17", "魏立华", 2, 5);
		setClass(ll2, "MS面向对象应用技术", "B515", "2-17", "张一鸣", 2, 8);
		setNoClass(ll2, 3, 0);
		setClass(ll2, "离散数学", "B409", "2-17", "李迎春", 2, 6);
		setNoClass(ll2, 3, 0);

		setNoClass(ll3, 2, 0);
		setClass(ll3, "计算机网络技术", "B409", "1-17", "魏立华", 2, 2);
		setNoClass(ll3, 8, 0);

		setClass(ll4, "微机原理与接口技术", "B514#C509", "-5,14-17#6-13", "魏立华", 2, 5);
		setClass(ll4, "大学英语Ⅲ", "B515", "2-17 ", "李墨", 2, 1);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "离散数学", "B514", "2-17", "李迎春", 2, 6);
		setClass(ll4, "体育Ⅲ", "B409#操场", "1,16#2-15,17", "陈桂凤,刘晓英", 2, 7);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "毛概", "3阶", "1-17", "于丽", 2, 3);

		setNoClass(ll4, 2, 0);
		setClass(ll5, "MS面向对象应用技术", "C408", "1-17", "张一鸣", 2, 8);
		setNoClass(ll5, 3, 0);
		setClass(ll5, "市场营销", "3阶", "1-17", "朱利清", 2, 9);
		setNoClass(ll5, 3, 0);
	}
	void setClass_2014_1(){
		// 分别表示周一到周日
		LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
		LinearLayout ll2 = (LinearLayout) findViewById(R.id.ll2);
		LinearLayout ll3 = (LinearLayout) findViewById(R.id.ll3);
		LinearLayout ll4 = (LinearLayout) findViewById(R.id.ll4);
		LinearLayout ll5 = (LinearLayout) findViewById(R.id.ll5);
		setClass(ll1, "3大学英语Ⅲ", "B401", "2-17", "李墨", 2, 1);
		setClass(ll1, "计算机网络技术", "B409#C408", "-5,14-17#6-13", "魏立华", 2, 2);
		setNoClass(ll1, 3, 0);
		setClass(ll1, "毛概", "3阶", "2-17", "于丽", 2, 3);
		setNoClass(ll1, 1, 0);
		setClass(ll1, "职业生涯发展概论", "3阶", "2-17", "韩旭彤", 2, 4);

		setClass(ll2, "微机原理与接口技术", "B514", "2-17", "魏立华", 2, 5);
		setClass(ll2, "MS面向对象应用技术", "B515", "2-17", "张一鸣", 2, 8);
		setNoClass(ll2, 3, 0);
		setClass(ll2, "离散数学", "B409", "2-17", "李迎春", 2, 6);
		setNoClass(ll2, 3, 0);

		setNoClass(ll3, 2, 0);
		setClass(ll3, "计算机网络技术", "B409", "1-17", "魏立华", 2, 2);
		setNoClass(ll3, 8, 0);

		setClass(ll4, "微机原理与接口技术", "B514#C509", "-5,14-17#6-13", "魏立华", 2, 5);
		setClass(ll4, "大学英语Ⅲ", "B515", "2-17 ", "李墨", 2, 1);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "离散数学", "B514", "2-17", "李迎春", 2, 6);
		setClass(ll4, "体育Ⅲ", "B409#操场", "1,16#2-15,17", "陈桂凤,刘晓英", 2, 7);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "毛概", "3阶", "1-17", "于丽", 2, 3);

		setNoClass(ll4, 2, 0);
		setClass(ll5, "MS面向对象应用技术", "C408", "1-17", "张一鸣", 2, 8);
		setNoClass(ll5, 3, 0);
		setClass(ll5, "市场营销", "3阶", "1-17", "朱利清", 2, 9);
		setNoClass(ll5, 3, 0);
	}
	void setClass_2014_2(){
		// 分别表示周一到周日
		LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
		LinearLayout ll2 = (LinearLayout) findViewById(R.id.ll2);
		LinearLayout ll3 = (LinearLayout) findViewById(R.id.ll3);
		LinearLayout ll4 = (LinearLayout) findViewById(R.id.ll4);
		LinearLayout ll5 = (LinearLayout) findViewById(R.id.ll5);
		setClass(ll1, "4大学英语Ⅲ", "B401", "2-17", "李墨", 2, 1);
		setClass(ll1, "计算机网络技术", "B409#C408", "-5,14-17#6-13", "魏立华", 2, 2);
		setNoClass(ll1, 3, 0);
		setClass(ll1, "毛概", "3阶", "2-17", "于丽", 2, 3);
		setNoClass(ll1, 1, 0);
		setClass(ll1, "职业生涯发展概论", "3阶", "2-17", "韩旭彤", 2, 4);

		setClass(ll2, "微机原理与接口技术", "B514", "2-17", "魏立华", 2, 5);
		setClass(ll2, "MS面向对象应用技术", "B515", "2-17", "张一鸣", 2, 8);
		setNoClass(ll2, 3, 0);
		setClass(ll2, "离散数学", "B409", "2-17", "李迎春", 2, 6);
		setNoClass(ll2, 3, 0);

		setNoClass(ll3, 2, 0);
		setClass(ll3, "计算机网络技术", "B409", "1-17", "魏立华", 2, 2);
		setNoClass(ll3, 8, 0);

		setClass(ll4, "微机原理与接口技术", "B514#C509", "-5,14-17#6-13", "魏立华", 2, 5);
		setClass(ll4, "大学英语Ⅲ", "B515", "2-17 ", "李墨", 2, 1);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "离散数学", "B514", "2-17", "李迎春", 2, 6);
		setClass(ll4, "体育Ⅲ", "B409#操场", "1,16#2-15,17", "陈桂凤,刘晓英", 2, 7);
		setNoClass(ll4, 1, 0);
		setClass(ll4, "毛概", "3阶", "1-17", "于丽", 2, 3);

		setNoClass(ll4, 2, 0);
		setClass(ll5, "MS面向对象应用技术", "C408", "1-17", "张一鸣", 2, 8);
		setNoClass(ll5, 3, 0);
		setClass(ll5, "市场营销", "3阶", "1-17", "朱利清", 2, 9);
		setNoClass(ll5, 3, 0);
	}
	/**
	 * 设置课程的方法
	 * 
	 * @param ll
	 * @param title
	 *            课程名称
	 * @param place
	 *            地点
	 * @param last
	 *            时间
	 * @param time
	 *            周次
	 * @param classes
	 *            节数
	 * @param color
	 *            背景色
	 */
	@SuppressLint("InflateParams")
	void setClass(LinearLayout ll, String title, String place, String last,
			String time, int classes, int color) {
		View view = LayoutInflater.from(this)
				.inflate(R.layout.class_item, null);
		view.setMinimumHeight(dip2px(this, classes * 48));
		view.setBackgroundColor(colors[color]);
		((TextView) view.findViewById(R.id.title)).setText(title);
		((TextView) view.findViewById(R.id.place)).setText(place);
		((TextView) view.findViewById(R.id.last)).setText(last);
		((TextView) view.findViewById(R.id.time)).setText(time);
		// 为课程View设置点击的监听器
		view.setOnClickListener(new OnClickClassListener());
		TextView blank1 = new TextView(this);
		TextView blank2 = new TextView(this);
		blank1.setHeight(dip2px(this, classes));
		blank2.setHeight(dip2px(this, classes));
		ll.addView(blank1);
		ll.addView(view);
		ll.addView(blank2);
	}

	/**
	 * 设置无课（空百）
	 * 
	 * @param ll
	 * @param classes
	 *            无课的节数（长度）
	 * @param color
	 */
	void setNoClass(LinearLayout ll, int classes, int color) {
		TextView blank = new TextView(this);
		if (color == 0)
			blank.setMinHeight(dip2px(this, classes * 50));
		blank.setBackgroundColor(colors[color]);
		ll.addView(blank);
	}

	// 点击课程的监听器
	class OnClickClassListener implements OnClickListener {

		public void onClick(View v) {
    		// TODO Auto-generated method stub
    		String title,last,place;
    		title = (String) ((TextView)v.findViewById(R.id.title)).getText();
    		last = (String) ((TextView)v.findViewById(R.id.last)).getText();
    		place = (String) ((TextView)v.findViewById(R.id.place)).getText();
    		
    		Toast.makeText(getApplicationContext(), "您点击的课程是" + title+ ",周次为"+last+",请到"+place+"上课。",Toast.LENGTH_SHORT).show();
    	}
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/** * 根据手机的分辨率从 px(像素) 的单位 转成为 dp */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mHandler.removeMessages(0);
	}
	@SuppressLint("HandlerLeak") public Handler mHandler = new Handler() {
		
		@SuppressWarnings("deprecation")
		@SuppressLint("HandlerLeak") @Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			Resources r = getResources();
			switch (flag) {
			case 0:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg01));
				break;
			case 1:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg02));
				break;
			case 2:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg03));
				break;
			case 3:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg04));
				break;
			case 4:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg_na));
				break;
			case 5:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg_rain_night));
				break;
			case 6:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.bg14_day_snow));
				break;
			case 7:
				L.setBackgroundDrawable(r.getDrawable(R.drawable.yjjc_h_a1));
				break;
			}

		}

    };

	private class sensorListener implements SensorEventListener {
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			int sensorType = event.sensor.getType();
			// values[0]:X轴，values[1]：Y轴，values[2]：Z轴
			float[] values = event.values;
			if (sensorType == Sensor.TYPE_ACCELEROMETER) {

				if ((Math.abs(values[0]) > 12 || Math.abs(values[1]) > 12 || Math
						.abs(values[2]) > 12)) {
					flag++;
					if(flag>7) flag = 0;
					mHandler.sendEmptyMessage(0);
				}
			}
		}
	}
}