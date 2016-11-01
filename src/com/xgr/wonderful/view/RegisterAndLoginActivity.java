package com.xgr.wonderful.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.im.BmobChat;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import com.lenovo.lps.sus.SUS;
import com.xgr.wonderful.R;
import com.xgr.wonderful.proxy.UserProxy;
import com.xgr.wonderful.proxy.UserProxy.ILoginListener;
import com.xgr.wonderful.proxy.UserProxy.IResetPasswordListener;
import com.xgr.wonderful.proxy.UserProxy.ISignUpListener;
import com.xgr.wonderful.ui.BasePageActivity;
import com.xgr.wonderful.utils.ActivityUtil;
import com.xgr.wonderful.utils.CommonUtils;
import com.xgr.wonderful.utils.LogUtils;
import com.xgr.wonderful.utils.StringUtils;
import com.xgr.wonderful.widget.ActionBar;
import com.xgr.wonderful.widget.ActionBar.Action;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * @author kingofglory email: kingofglory@yeah.net blog: http:www.google.com
 * @date 2014-3-13 TODO
 */

public class RegisterAndLoginActivity extends BasePageActivity implements
		OnClickListener, ILoginListener, ISignUpListener,
		IResetPasswordListener {

	ActionBar actionbar;
	TextView loginTitle;
	TextView registerTitle;
	TextView resetPassword;

	DeletableEditText userNameInput;
	DeletableEditText userPasswordInput;
	DeletableEditText userEmailInput;

	Button registerButton;
	SmoothProgressBar progressbar;
	UserProxy userProxy;

	private enum UserOperation {
		LOGIN, REGISTER, RESET_PASSWORD
	}

	UserOperation operation = UserOperation.LOGIN;
	@Override
	protected void setLayoutView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_register);
		// BmobChat SDK初始化--只需要这一段代码即可完成初始化
		BmobChat.getInstance(this).init("30a7db2beab5c063fb08bb828a0b52de");
		StartVersionUpdate();

	}
	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		actionbar = (ActionBar) findViewById(R.id.actionbar_register);

		loginTitle = (TextView) findViewById(R.id.login_menu);
		registerTitle = (TextView) findViewById(R.id.register_menu);
		resetPassword = (TextView) findViewById(R.id.reset_password_menu);

		userNameInput = (DeletableEditText) findViewById(R.id.user_name_input);
		userPasswordInput = (DeletableEditText) findViewById(R.id.user_password_input);
		userEmailInput = (DeletableEditText) findViewById(R.id.user_email_input);

		registerButton = (Button) findViewById(R.id.register);
		progressbar = (SmoothProgressBar) findViewById(R.id.sm_progressbar);
	}

	@Override
	protected void setupViews(Bundle bundle) {
		// TODO Auto-generated method stub
		actionbar.setTitle("华信XXX");
		actionbar.setDisplayHomeAsUpEnabled(false);
		actionbar.setHomeAction(new Action() {

			@Override
			public void performAction(View view) {
				// TODO Auto-generated method stub
				// finish();
			}

			@Override
			public int getDrawable() {
				// TODO Auto-generated method stub
				return R.drawable.logo;
			}
		});
		updateLayout(operation);

		userProxy = new UserProxy(mContext);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub'
		loginTitle.setOnClickListener(this);
		registerTitle.setOnClickListener(this);
		resetPassword.setOnClickListener(this);
		registerButton.setOnClickListener(this);

	}

	@Override
	protected void fetchData() {
		// TODO Auto-generated method stub

	}

	private void Registerphone() {
		// 打开注册页面
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
			@Override
			public void afterEvent(int event, int result, Object data) {
				// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
					// 提交用户注册信息
					userProxy.signUp(userNameInput.getText().toString().trim(),
							userPasswordInput.getText().toString().trim(),
							userEmailInput.getText().toString().trim());
				}
			}
		});
		registerPage.show(getBaseContext(), userNameInput.getText().toString());
	}

	private void resetphone() {
		// 打开注册页面
		RegisterPage registerPage = new RegisterPage();
		registerPage.setRegisterCallback(new EventHandler() {
			@Override
			public void afterEvent(int event, int result, Object data) {
				// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
					// 提交用户信息
					userProxy.resetPassword(userNameInput.getText().toString()
							.trim(), userPasswordInput.getText().toString()
							.trim());
				}
			}
		});
		registerPage.show(getBaseContext(), userNameInput.getText().toString());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
		if (!isNetConnected) {
			ShowToast(R.string.network_tips);
			return;
		}
		switch (v.getId()) {
		case R.id.register:
			if (operation == UserOperation.LOGIN) {
				if (TextUtils.isEmpty(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入手机号码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(userPasswordInput.getText())) {
					userPasswordInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				userProxy.setOnLoginListener(this);
				LogUtils.i(TAG, "login begin....");
				progressbar.setVisibility(View.VISIBLE);
				userProxy.login(userNameInput.getText().toString().trim(),
						userPasswordInput.getText().toString().trim());

			} else if (operation == UserOperation.REGISTER) {
				if (TextUtils.isEmpty(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入手机号码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (!StringUtils.isPhoneNumberValid(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "亲, 请输入正确的手机号码",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(userPasswordInput.getText())) {
					userPasswordInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(userEmailInput.getText())) {
					userEmailInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入邮箱地址", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (!StringUtils.isValidEmail(userEmailInput.getText())) {
					userEmailInput.setShakeAnimation();
					Toast.makeText(mContext, "亲, 您的邮箱格式不正确", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				userProxy.setOnSignUpListener(this);
				LogUtils.i(TAG, "register begin....");
				progressbar.setVisibility(View.VISIBLE);
				// 短信验证
				SMSSDK.initSDK(this, "41df0e644a2a",
						"5e8127c093102d47e9d19c880b7a4414");
				Registerphone();
				// 短信验证结束
			} else {
				if (TextUtils.isEmpty(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入手机号码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (!StringUtils.isPhoneNumberValid(userNameInput.getText())) {
					userNameInput.setShakeAnimation();
					Toast.makeText(mContext, "亲, 请输入正确的手机号码",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (TextUtils.isEmpty(userPasswordInput.getText())) {
					userPasswordInput.setShakeAnimation();
					Toast.makeText(mContext, "请输入新密码", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				LogUtils.i(TAG, "reset password begin....");

				progressbar.setVisibility(View.VISIBLE);
				// 短信验证
				SMSSDK.initSDK(this, "41df0e644a2a",
						"5e8127c093102d47e9d19c880b7a4414");
				resetphone();
				// 短信验证结束
				userProxy.setOnResetPasswordListener(this);
			}
			break;
		case R.id.login_menu:
			operation = UserOperation.LOGIN;
			updateLayout(operation);
			break;
		case R.id.register_menu:
			operation = UserOperation.REGISTER;
			updateLayout(operation);
			break;
		case R.id.reset_password_menu:
			operation = UserOperation.RESET_PASSWORD;
			updateLayout(operation);
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("deprecation")
	private void updateLayout(UserOperation op) {
		if (op == UserOperation.LOGIN) {
			loginTitle.setTextColor(Color.parseColor("#D95555"));
			loginTitle.setBackgroundResource(R.drawable.bg_login_tab);
			loginTitle.setPadding(16, 16, 16, 16);
			loginTitle.setGravity(Gravity.CENTER);

			registerTitle.setTextColor(Color.parseColor("#888888"));
			registerTitle.setBackgroundDrawable(null);
			registerTitle.setPadding(16, 16, 16, 16);
			registerTitle.setGravity(Gravity.CENTER);

			resetPassword.setTextColor(Color.parseColor("#888888"));
			resetPassword.setBackgroundDrawable(null);
			resetPassword.setPadding(16, 16, 16, 16);
			resetPassword.setGravity(Gravity.CENTER);

			userNameInput.setVisibility(View.VISIBLE);
			userPasswordInput.setVisibility(View.VISIBLE);
			userEmailInput.setVisibility(View.GONE);
			registerButton.setText("登录");
		} else if (op == UserOperation.REGISTER) {
			loginTitle.setTextColor(Color.parseColor("#888888"));
			loginTitle.setBackgroundDrawable(null);
			loginTitle.setPadding(16, 16, 16, 16);
			loginTitle.setGravity(Gravity.CENTER);

			registerTitle.setTextColor(Color.parseColor("#D95555"));
			registerTitle.setBackgroundResource(R.drawable.bg_login_tab);
			registerTitle.setPadding(16, 16, 16, 16);
			registerTitle.setGravity(Gravity.CENTER);

			resetPassword.setTextColor(Color.parseColor("#888888"));
			resetPassword.setBackgroundDrawable(null);
			resetPassword.setPadding(16, 16, 16, 16);
			resetPassword.setGravity(Gravity.CENTER);

			userNameInput.setVisibility(View.VISIBLE);
			userPasswordInput.setVisibility(View.VISIBLE);
			userEmailInput.setVisibility(View.VISIBLE);
			registerButton.setText("注册");
		} else {
			loginTitle.setTextColor(Color.parseColor("#888888"));
			loginTitle.setBackgroundDrawable(null);
			loginTitle.setPadding(16, 16, 16, 16);
			loginTitle.setGravity(Gravity.CENTER);

			registerTitle.setTextColor(Color.parseColor("#888888"));
			registerTitle.setBackgroundDrawable(null);
			registerTitle.setPadding(16, 16, 16, 16);
			registerTitle.setGravity(Gravity.CENTER);

			resetPassword.setTextColor(Color.parseColor("#D95555"));
			resetPassword.setBackgroundResource(R.drawable.bg_login_tab);
			resetPassword.setPadding(16, 16, 16, 16);
			resetPassword.setGravity(Gravity.CENTER);

			userNameInput.setVisibility(View.VISIBLE);
			userPasswordInput.setVisibility(View.VISIBLE);
			userEmailInput.setVisibility(View.GONE);
			registerButton.setText("找回密码");
		}
	}

	private void dimissProgressbar() {
		if (progressbar != null && progressbar.isShown()) {
			progressbar.setVisibility(View.GONE);

		}
	}

	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ActivityUtil.show(mContext, "正在获取好友列表...");
			}
		});
		// 更新用户的地理位置以及好友的资料
		updateUserInfos();

		dimissProgressbar();
		ActivityUtil.show(this, "登录成功。");
		LogUtils.i(TAG, "login sucessed!");
		setResult(RESULT_OK);
		Intent intent2 = new Intent(this, MainActivity.class);
		startActivity(intent2);
		finish();
	}

	@Override
	public void onLoginFailure(String msg) {
		// TODO Auto-generated method stub
		dimissProgressbar();
		ActivityUtil.show(this, "登录失败。");
		LogUtils.i(TAG, "login failed!" + msg);
	}

	@Override
	public void onSignUpSuccess() {
		// TODO Auto-generated method stub
		dimissProgressbar();
		ActivityUtil.show(this, "注册成功");
		// 更新地理位置信息
		updateUserLocation();
		LogUtils.i(TAG, "register successed！");
		operation = UserOperation.LOGIN;
		updateLayout(operation);
		// Intent intent2 = new Intent(this, MainActivity.class);
		// startActivity(intent2);
		// 发广播通知登陆页面退出
		// sendBroadcast(new
		// Intent(BmobConstants.ACTION_REGISTER_SUCCESS_FINISH));
	}

	@Override
	public void onSignUpFailure(String msg) {
		// TODO Auto-generated method stub
		dimissProgressbar();
		ActivityUtil.show(this, "注册失败。");
		LogUtils.i(TAG, "register failed！");
	}

	@Override
	public void onResetSuccess() {
		// TODO Auto-generated method stub
		dimissProgressbar();
		ActivityUtil.showL(this, "重置密码成功。");
		LogUtils.i(TAG, "reset successed！");
		operation = UserOperation.LOGIN;
		updateLayout(operation);
	}

	@Override
	public void onResetFailure(String msg) {
		// TODO Auto-generated method stub
		dimissProgressbar();
		ActivityUtil.show(this, "重置密码失败。");
		LogUtils.i(TAG, "register failed！");
	}
	protected void onDestory() {
		// TODO Auto-generated method stub
		SUS.finish();
		super.onPause();
	}
}
