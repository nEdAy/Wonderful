package com.xgr.wonderful.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.model.LuckyUser;
import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.EraseView;

/**
 * 每日一抽页面
 * 
 * @date 2014-5-18
 * @author Stone
 */
public class AwardActivity extends Activity {

	private TextView etAwardNew;
	private TextView etAwardOld;

	private String awardNew;
	private String awardOld;
	EraseView eraseView1;
	Button erniebtn;
	TextView textView1;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
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
		setContentView(R.layout.activity_award);
		initData();
	}

	private void initView() {
		etAwardNew = (TextView) findViewById(R.id.et_award_new);
		etAwardOld = (TextView) findViewById(R.id.et_award_old);

		etAwardNew.setText(awardNew);
		etAwardOld.setText(awardOld);

		erniebtn = (Button) findViewById(R.id.button1);
		erniebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showErnie();
				eraseView1.setVisibility(View.VISIBLE);
				erniebtn.setVisibility(View.GONE);;
				textView1.setVisibility(View.VISIBLE);;
			}
		});
		textView1 = (TextView) findViewById(R.id.textView1);
		eraseView1 = (EraseView) findViewById(R.id.eraseView1);
	}
	private void showErnie(){
		String level = getLevel();
		textView1.setText(level);
	}
	
	/**
	 * 获取奖励等级
	 * @return
	 */
	public String getLevel(){
		//随机，看看几等奖
		double d=Math.random()*100;
		if(d<50){
			return "很遗憾，未中奖。";
		}
		if(d<80){
			return "恭喜您，获得了三等奖。";
		}
		if(d<95){
			return "恭喜您，获得了二等奖。";
		}
		return "恭喜您，获得了一等奖。";
				
	}
	private void initData() {
		BmobQuery<LuckyUser> query = new BmobQuery<LuckyUser>();
		query.order("-updateAt");
		query.findObjects(this, new FindListener<LuckyUser>() {

			@Override
			public void onSuccess(List<LuckyUser> list) {
				awardNew = list.get(0).getUsername() + "      "
						+ list.get(0).getAward();
				awardOld = list.get(1).getUsername() + "      "
						+ list.get(1).getAward();
				Message msg = new Message();
				msg.what = 0;
				mHandler.sendMessage(msg);
			}

			@Override
			public void onError(int arg0, String arg1) {
				toast("获取中奖名单失败");
			}
		});
	}

	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	public void clickBack(View v) {
		finish();
	}

}
