package com.xgr.wonderful.view;

import com.xgr.wonderful.R;
import com.xgr.wonderful.electric.ElectricActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 关于软件
 * @author Stone
 */
public class AboutActivity extends Activity implements OnClickListener{

	ImageView app_logo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		app_logo = (ImageView)findViewById(R.id.app_logo);
		app_logo.setOnClickListener(this);
	}
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,ElectricActivity.class));
	}
	

}
