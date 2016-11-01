package com.xgr.wonderful.proxy;


import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.im.BmobUserManager;
import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.SaveListener;
import com.stone.shop.model.User;
import com.xgr.wonderful.utils.LogUtils;

import android.content.Context;

public class UserProxy {

	public static final String TAG = "UserProxy";
	protected BmobUserManager userManager;
	
	private Context mContext;
	
	public UserProxy(Context context){
		this.mContext = context;
	}
	
	public void signUp(String userName,String password,String email){
		final User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		user.setEmail(email);
		//将user和设备id进行绑定
		user.setDeviceType("android");
		user.setInstallId(BmobInstallation.getInstallationId(mContext));
		user.signUp(mContext, new SaveListener() {
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				if(signUpLister != null){
					// 将设备与username进行绑定
//					userManager.bindInstallationForRegister(user.getUsername());
					signUpLister.onSignUpSuccess();
				}else{
					LogUtils.i(TAG,"signup listener is null,you must set one!");
				}
			}

			@Override
			public void onFailure(int arg0, String msg) {
				// TODO Auto-generated method stub
				if(signUpLister != null){
					signUpLister.onSignUpFailure(msg);
				}else{
					LogUtils.i(TAG,"signup listener is null,you must set one!");
				}
			}
		});
	}
	
	public interface ISignUpListener{
		void onSignUpSuccess();
		void onSignUpFailure(String msg);
	}
	private ISignUpListener signUpLister;
	public void setOnSignUpListener(ISignUpListener signUpLister){
		this.signUpLister = signUpLister;
	}
	
	
	public User getCurrentUser(){
		User user = BmobUser.getCurrentUser(mContext, User.class);
		if(user != null){
			LogUtils.i(TAG,"本地用户信息" + user.getObjectId() + "-"
					+ user.getUsername() + "-"
					+ user.getSessionToken() + "-"
					+ user.getCreatedAt() + "-"
					+ user.getUpdatedAt());
			return user;
		}else{
			LogUtils.i(TAG,"本地用户为null,请登录。");
		}
		return null;
	}
	
	public void login(String userName,String password){
		final BmobUser user = new BmobUser();
		user.setUsername(userName);
		user.setPassword(password);
		user.login(mContext, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				if(loginListener != null){
					loginListener.onLoginSuccess();
				}else{
					LogUtils.i(TAG, "login listener is null,you must set one!");
				}
			}

			@Override
			public void onFailure(int arg0, String msg) {
				// TODO Auto-generated method stub
				if(loginListener != null){
					loginListener.onLoginFailure(msg);
				}else{
					LogUtils.i(TAG, "login listener is null,you must set one!");
				}
			}
		});
	}
	
	public interface ILoginListener{
		void onLoginSuccess();
		void onLoginFailure(String msg);
	}
	private ILoginListener loginListener;
	public void setOnLoginListener(ILoginListener loginListener){
		this.loginListener  = loginListener;
	}
	
	public void logout(){
		BmobUser.logOut(mContext);
		LogUtils.i(TAG, "logout result:"+(null == getCurrentUser()));
	}
	
	
	
	public void resetPassword(String phone,String newpassword){
		JSONObject jso = new JSONObject();
		try {
			jso.put("phone", phone);
			jso.put("password", newpassword);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
		//第一个参数是上下文对象，第二个参数是云端代码的方法名称，第三个参数是上传到云端代码的参数列表（JSONObject cloudCodeParams），第四个参数是回调类
		ace.callEndpoint(mContext,"onRequest",jso, 
		    new CloudCodeListener() {
		            @Override
		            public void onSuccess(Object object) {
		                // TODO Auto-generated method stub
		        		if(resetPasswordListener != null){
							resetPasswordListener.onResetSuccess();
						}else{
							LogUtils.i(TAG,"reset listener is null,you must set one!");
						}
		            }
		            @Override
		            public void onFailure(int code, String msg) {
		                // TODO Auto-generated method stub
						if(resetPasswordListener != null){
							resetPasswordListener.onResetFailure(msg);
						}else{
							LogUtils.i(TAG,"reset listener is null,you must set one!");
						}
		            }
		        });
	}
	public interface IResetPasswordListener{
		void onResetSuccess();
		void onResetFailure(String msg);
	}
	private IResetPasswordListener resetPasswordListener;
	public void setOnResetPasswordListener(IResetPasswordListener resetPasswordListener){
		this.resetPasswordListener = resetPasswordListener;
	}
}
