package com.xgr.wonderful.view;

import com.lenovo.lps.sus.SUS;
import com.xgr.wonderful.R;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 应用主界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	private TabHost tabHost;
	private LayoutInflater layoutInflater;

	String[] mTitle = new String[] { "校园服务", "百宝箱", "我" };
	int[] mIcon = new int[] { R.drawable.ic_shop, R.drawable.ic_sale,
			R.drawable.ic_mine };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initTabView();
		boolean isStartVersionUpdateFlag = false;
		/* 应用首次启动时自动启动版本更新功能 */
		if (!isStartVersionUpdateFlag) {
			isStartVersionUpdateFlag = true;
			if (!SUS.isVersionUpdateStarted()) {
				SUS.AsyncStartVersionUpdate(this);
			}
		}

	}

	@SuppressLint("InflateParams")
	public View getTabItemView(int i) {
		// TODO Auto-generated method stub
		View view = layoutInflater.inflate(R.layout.tab_widget_item, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mIcon[i]);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTitle[i]);
		return view;
	}

	public void initTabView() {

		/**
		 * tabHost.newTabSpec("artist")创建一个标签项，其中artist为它的标签标识符，
		 * 相当于jsp页面标签的name属性
		 * setIndicator("艺术标签",resources.getDrawable(R.drawable
		 * .ic_tab))设置标签显示文本以及标签上的图标（该图标并不是一个图片，而是一个xml文件哦）
		 * setContent(intent)为当前标签指定一个意图 tabHost.addTab(spec); 将标签项添加到标签中
		 */

		tabHost = getTabHost();
		layoutInflater = LayoutInflater.from(this);
		TabHost.TabSpec spec;

		// 小菜
		Intent intent1 = new Intent(this, ShopActivity.class);
		spec = tabHost.newTabSpec(mTitle[0]).setIndicator(getTabItemView(0))
				.setContent(intent1);
		tabHost.addTab(spec);

		// 发现
		Intent intent2 = new Intent(this, FinderActivity.class);
		spec = tabHost.newTabSpec(mTitle[1]).setIndicator(getTabItemView(1))
				.setContent(intent2);
		tabHost.addTab(spec);

		// 我的
		Intent intent3 = new Intent(this, MineActivity.class);
		spec = tabHost.newTabSpec(mTitle[2]).setIndicator(getTabItemView(2))
				.setContent(intent3);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(1);
	}
	private static long firstTime;
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			
				if (firstTime + 2000 > System.currentTimeMillis()) {
					SUS.finish();
					super.onBackPressed();
				} else {
					ShowToast("再按一次,后台运行程序");
				}
				firstTime = System.currentTimeMillis();

			return false;
		}
		return super.dispatchKeyEvent(event);
	};
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
