package com.xgr.wonderful.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class EraseView extends View {

	private Bitmap bitmap = null;
	private Bitmap frontBitmap = null;
	private Path path;
	private Canvas mCanvas;
	private Paint paint;
	private Paint textPaint;

	private int xPos;
	private int yPos;
	private String content = "刮开此图层";
	public EraseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		textPaint = new Paint();
		// textPaint.setColor(Color.parseColor("##"));

		// 计算文字高度
		// 计算文字baseline

		textPaint.setColor(Color.WHITE);
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(38);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (mCanvas == null) {
			EraseBitmp();
		}
		canvas.drawBitmap(bitmap, 0, 0, null);
		xPos = (int) (getWidth() -  (int) textPaint.measureText(content))/2;
		yPos = (int) ((getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
		mCanvas.drawText(content, xPos, yPos, textPaint);
		mCanvas.drawPath(path, paint);
		super.onDraw(canvas);
	}

	public void EraseBitmp() {
		bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
		frontBitmap = CreateBitmap(Color.GRAY, getWidth(), getHeight());
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(20);
		path = new Path();
		mCanvas = new Canvas(bitmap);
		mCanvas.drawBitmap(frontBitmap, 0, 0, null);
	}

	@SuppressLint("ClickableViewAccessibility") @Override
	public boolean onTouchEvent(MotionEvent event) {

		float ax = event.getX();
		float ay = event.getY();

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			path.reset();
			path.moveTo(ax, ay);
			invalidate();
			return true;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			path.lineTo(ax, ay);
			invalidate();
			return true;
		}
		return super.onTouchEvent(event);
	}

	public Bitmap CreateBitmap(int color, int width, int height) {
		int[] rgb = new int[width * height];

		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = color;
		}

		return Bitmap.createBitmap(rgb, width, height, Config.ARGB_8888);
	}

}
