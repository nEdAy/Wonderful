package com.xgr.wonderful.firstuse;

import com.xgr.wonderful.view.RegisterAndLoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class WelcomeA extends Activity {
	SharedPreferences setting;
	final String INITIALIZED = "initialized";
	Boolean user_first;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setting=getSharedPreferences("setting", 0);
		user_first = setting.getBoolean("FIRST",true);
		
	    if(user_first){
			Intent intent = new Intent(WelcomeA.this, WhatsnewPagesA.class);
			startActivity(intent);
	    }else{
			Intent intent=new Intent(this,RegisterAndLoginActivity.class);
			startActivity(intent);
		}
	    setting.edit().putBoolean("FIRST", false).commit();	   
	    finish();
		}
}


