package com.xgr.wonderful.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.stone.date.MessageDef;
import com.stone.shop.model.User;
import com.xgr.wonderful.R;

/**
 * 修改个人资料卡
 * @date 2014-5-28 
 * @author Stone
 */
public class MineInfoEditActivity extends Activity {
	
	private EditText etUsername;
	private EditText etSchool;
	private EditText etCademy;
	private EditText etDorPart;
	private EditText etDorNum;
	private EditText etPhone;
	private EditText etQQ;
	
	private User curUser;
	
	@SuppressWarnings("unused")
	private Bundle bundle;
	
	@SuppressLint("HandlerLeak") private Handler mHandler = new Handler() {  
		  @Override  
		  public void handleMessage(Message msg) {  
		      switch (msg.what) {
				case MessageDef.MINE_INFO_FINISH_FIND_USER:
					initView();
					break;
				default:
					break;
				}
		  }  
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine_info_edit);
		
		setCurUser();
	}
	
	private void initView() {
		etUsername = (EditText) findViewById(R.id.et_mineinfo_username);
		etSchool = (EditText) findViewById(R.id.et_mineinfo_school);
		etCademy = (EditText) findViewById(R.id.et_mineinfo_cademy);
		etDorPart = (EditText) findViewById(R.id.et_mineinfo_dorpart);
		etDorNum = (EditText) findViewById(R.id.et_mineinfo_dornum);
		etPhone = (EditText) findViewById(R.id.et_mineinfo_phone);
		etQQ = (EditText) findViewById(R.id.et_mineinfo_qq);
		
		etUsername.setText(curUser.getUsername());
		etSchool.setText(curUser.getSchool());
		etCademy.setText(curUser.getCademy());
		etDorPart.setText(curUser.getDorPart());
		etDorNum.setText(curUser.getDorNum());
		etPhone.setText(curUser.getUsername());
		etQQ.setText(curUser.getQQ());
	}
	
	private void setCurUser() {
		BmobUser bmobUser = BmobUser.getCurrentUser(this);
		BmobQuery<User> query = new BmobQuery<User>();
		query.addWhereEqualTo("objectId", bmobUser.getObjectId());
		query.findObjects(this, new FindListener<User>() {
			
			@Override
			public void onSuccess(List<User> object) {
				curUser = object.get(0);
				//toast("查询到用户  " + object.size());
				Message msg = new Message();
				msg.what = MessageDef.MINE_INFO_FINISH_FIND_USER;
				mHandler.sendMessage(msg);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				toast("亲， 获取当前用户失败");
			}
		});
		
	}
	
	private void saveUserInfo() {
		if(curUser == null) {
			toast("请先登录");
			Intent toLogin = new Intent(MineInfoEditActivity.this, RegisterAndLoginActivity.class);
			startActivity(toLogin);
		} else {
			Log.i("当前用户的ID: ", curUser.getObjectId());
			curUser.setUsername(etUsername.getText().toString());
			curUser.setSchool(etSchool.getText().toString());
			curUser.setCademy(etCademy.getText().toString());
			curUser.setDorPart(etDorPart.getText().toString());
			curUser.setDorNum(etDorNum.getText().toString());
			curUser.setUsername(etPhone.getText().toString());
			curUser.setQQ(etQQ.getText().toString());
			curUser.update(this, curUser.getObjectId(), new UpdateListener() {
				
				@Override
				public void onSuccess() {
					Intent back = new Intent(MineInfoEditActivity.this, MineInfoActivity.class);
					setResult(200, back);  //返回成功码
					finish();
					toast("个人资料修改成功");
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					toast("更新失败");
				}
			});
		}
		
	}
	
	public void clickSave(View v) {
		saveUserInfo();
	}
	
	public void clickCancel(View v) {
		finish();
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	
}
