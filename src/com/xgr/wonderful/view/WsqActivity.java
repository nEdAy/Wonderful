package com.xgr.wonderful.view;

import com.xgr.wonderful.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * 微社区主界面
 * @date  2014-11-8
 * @author nEdAy
 */
public class WsqActivity extends Activity {
	
	private static final String URL_WSQ = "http://m.wsq.qq.com/263536241";  
	private WebView wsqWebView;
	
	@SuppressLint("SetJavaScriptEnabled") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		wsqWebView = (WebView) findViewById(R.id.wv_wsq);
		
		// -----------------------------------------------------------------
		
		wsqWebView.getSettings().setJavaScriptEnabled(true);	// 设置使用够执行JS脚本
		wsqWebView.getSettings().setDefaultFontSize(12);
		wsqWebView.setWebChromeClient(new WebChromeClient());
		wsqWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,
					String url) {
				view.loadUrl(url);// 使用当前WebView处理跳转
				return true;// true表示此事件在此处被处理，不需要再广播
			}

			@Override
			// 转向错误时的处理
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(WsqActivity.this,
						"Oh no! " + description, Toast.LENGTH_SHORT)
						.show();
			}
		});
		wsqWebView.loadUrl(URL_WSQ);
		// ------------------------------------------------
		
	}

}
