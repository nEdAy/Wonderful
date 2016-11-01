package com.xgr.wonderful.electric;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.xgr.wonderful.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

@SuppressLint("UseSparseArrays")
public class CustomSurfaceView extends SurfaceView implements Runnable,
		Callback {
	public static CustomSurfaceView mSurfaceView;
	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	private Paint mPaint;
	private Context mContext;
	private SoundPool mSoundPool;
	private Map<Integer, Integer> mSoundMap;
	private int mIndex;
	private CustomPaint mCustomPaint;

	public CustomSurfaceView(Context context, AttributeSet attrs) {
		super(context);
		this.mContext = context;
		mSurfaceView = this;
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
		mPaint.setAlpha(127);
		mPaint.setStrokeWidth(2);
		mHolder = this.getHolder();
		mHolder.setFormat(PixelFormat.TRANSPARENT);
		mHolder.addCallback(this);
		this.setKeepScreenOn(true);
		this.setFocusable(true);
		this.setLongClickable(true);
		this.mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
		this.mSoundMap = new HashMap<Integer, Integer>();
		this.mSoundMap.put(1, mSoundPool.load(context, R.raw.zapp, 1));
		this.mCustomPaint = new CustomPaint();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@SuppressLint({ "NewApi", "ClickableViewAccessibility" }) @Override
	public boolean onTouchEvent(MotionEvent arg1) {
		// TODO Auto-generated method stub
		switch (arg1.getPointerCount()) {
		case 1:
			switch (arg1.getAction()) {
			case MotionEvent.ACTION_DOWN:
				drawLine1(arg1.getX(), arg1.getY());
				viBrator(getContext());
				playSound();
				break;
			case MotionEvent.ACTION_UP:
				drawClear();
				stopSound();
				break;
			case MotionEvent.ACTION_MOVE:
				drawLine1(arg1.getX(), arg1.getY());
				viBrator(getContext());
				break;
			default:
				break;
			}
			break;
		case 2:
			drawAction2(arg1);
			break;
		default:
			drawAction2(arg1);
			break;
		}

		return super.onTouchEvent(arg1);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {

	}

	@Override
	public void run() {
	}

	// 画周围四条线
	public void drawLine1(float x, float y) {
		mCanvas = mHolder.lockCanvas();
		if (mCanvas != null) {
			int width = mContext.getResources().getDisplayMetrics().widthPixels;
			int heigth = mContext.getResources().getDisplayMetrics().heightPixels - 40;
			mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
			mCustomPaint.drawLightning(0, 0, x, y, new Random().nextInt(40),
					mCanvas);
			mCustomPaint.drawLightning(0, 0, x, y, new Random().nextInt(40),
					mCanvas);
			mCustomPaint.drawLightning(width, 0, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightning(width, 0, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightning(0, heigth, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightning(0, heigth, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightning(width, heigth, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightning(width, heigth, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightningBold(x, y, x, y,
					new Random().nextInt(40), mCanvas);
			mCustomPaint.drawLightningBold(x, y, x, y,
					new Random().nextInt(40), mCanvas);

			mHolder.unlockCanvasAndPost(mCanvas);
		}
	}

	// 画中间或者两个手指之间的粗线
	public void drawLine2(float x1, float y1, float x2, float y2) {
		mCanvas = mHolder.lockCanvas();
		if (mCanvas != null) {
			int width = mContext.getResources().getDisplayMetrics().widthPixels;
			int heigth = mContext.getResources().getDisplayMetrics().heightPixels - 40;
			mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

			if (y1 < y2) {
				mCustomPaint.drawLightning(0, 0, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(0, 0, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, 0, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, 0, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(0, heigth, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(0, heigth, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, heigth, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, heigth, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightningBold(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightningBold(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
			} else {
				mCustomPaint.drawLightning(0, 0, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(0, 0, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, 0, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, 0, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(0, heigth, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(0, heigth, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, heigth, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(width, heigth, x1, y1,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightning(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightningBold(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
				mCustomPaint.drawLightningBold(x1, y1, x2, y2,
						new Random().nextInt(40), mCanvas);
			}

			mHolder.unlockCanvasAndPost(mCanvas);
		}
	}

	// 硬件调用—�?—震�?
	public void viBrator(Context context) {
		Vibrator vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(100);
	}

	// 被重复调用的画粗线的方法
	@SuppressLint("NewApi") public void drawAction2(MotionEvent arg1) {
		drawLine2(arg1.getX(0), arg1.getY(0), arg1.getX(1), arg1.getY(1));
		viBrator(getContext());
		// playSound();
	}

	// 播放声音
	public void playSound() {
		mIndex = mSoundPool.play(mSoundMap.get(1), 1, 1, 0, -1, 1);
	}

	public void stopSound() {
		mSoundPool.stop(mIndex);
	}

	// 画布清除
	public void drawClear() {
		mCanvas = mHolder.lockCanvas();
		if (mCanvas != null) {
			mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
			mHolder.unlockCanvasAndPost(mCanvas);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), "keydown", Toast.LENGTH_SHORT).show();
		return super.onKeyDown(keyCode, event);
	}

}
