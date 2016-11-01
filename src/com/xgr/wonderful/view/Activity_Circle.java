package com.xgr.wonderful.view;

import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.LJWebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class Activity_Circle extends Activity{

	private LJWebView mLJWebView = null;
	private String url = "http://hctf.tk/game/show";

	@SuppressLint("SetJavaScriptEnabled") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		
		mLJWebView = (LJWebView) findViewById(R.id.web);
		mLJWebView.setProgressStyle(LJWebView.Circle);
		mLJWebView.setBarHeight(8);
		mLJWebView.setClickable(true);
		mLJWebView.setUseWideViewPort(true);
		mLJWebView.setSupportZoom(true);
		mLJWebView.setBuiltInZoomControls(true);
		mLJWebView.setJavaScriptEnabled(true);
		mLJWebView.setCacheMode(WebSettings.LOAD_NO_CACHE);		
		mLJWebView.setWebViewClient(new WebViewClient() {
			//重写此方法，浏览器内部跳转
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("跳的URL =" + url);
				view.loadUrl(url);
				return true;
			}
			@Override
			// 转向错误时的处理
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(Activity_Circle.this,
						"Oh no! " + description, Toast.LENGTH_SHORT)
						.show();
			}
		});

		mLJWebView.loadUrl(url);


	}

}
