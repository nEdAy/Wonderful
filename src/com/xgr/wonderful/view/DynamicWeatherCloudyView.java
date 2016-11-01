﻿package com.xgr.wonderful.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class DynamicWeatherCloudyView extends View implements Runnable {

	/**
	 * 要处理的图
	 */
	private Bitmap bitmap;

	private int left;
	private int top;

	/**
	 * 图片移动频率
	 */
	private int dx = 1;
	private int sleepTime;

	/**
	 * 图片是否在移动
	 */
	private static boolean IsRunning = true;

	private Handler handler;

	@SuppressLint("HandlerLeak") public DynamicWeatherCloudyView(Context context, int resource, int left,
			int top, int sleepTime) {
		super(context);

		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		bitmap = BitmapFactory.decodeResource(getResources(), resource);
		this.left = left;
		this.top = top;
		this.sleepTime = sleepTime;

		handler = new Handler() {
			@SuppressLint("HandlerLeak") public void handleMessage(android.os.Message msg) {
				Log.i("icer", "handleMessage()");
				DynamicWeatherCloudyView.this.invalidate();
			};
		};

	}

	public void move() {
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, left, top, null);
	}

	@Override
	public void run() {

		while (DynamicWeatherCloudyView.IsRunning) {
				if ((bitmap != null) && (left > (getWidth()))) {
					left = -bitmap.getWidth();
				}
			left = left + dx;
			Log.i("icer", left + "");
			handler.sendMessage(handler.obtainMessage());
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
