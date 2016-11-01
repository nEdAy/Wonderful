package com.xgr.wonderful.view;

import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.MyImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class FinderActivity extends Activity implements OnClickListener {

	private MyImageView c_joke;
	private MyImageView c_idea;
	private MyImageView c_constellation;
	private MyImageView c_recommend;
	private MyImageView bottom_1, bottom_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finder);
		initView();
	}

	private void initView() {
		c_joke = (MyImageView) findViewById(R.id.c_joke);
		c_idea = (MyImageView) findViewById(R.id.c_idea);
		c_constellation = (MyImageView) findViewById(R.id.c_constellation);
		c_recommend = (MyImageView) findViewById(R.id.c_recommend);
		bottom_1 = (MyImageView) findViewById(R.id.bottom_1);
		bottom_2 = (MyImageView) findViewById(R.id.bottom_2);
		c_joke.setOnClickListener(this);
		c_idea.setOnClickListener(this);
		c_constellation.setOnClickListener(this);
		c_recommend.setOnClickListener(this);
		bottom_1.setOnClickListener(this);
		bottom_2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 校园速推
		case R.id.c_joke:
			Intent toCampusNews = new Intent(FinderActivity.this,
					HomeActivity.class);
			startActivity(toCampusNews);
			break;

		// 开心吐槽
		case R.id.c_idea:
			Intent toWsqToCao = new Intent(FinderActivity.this,
					WsqActivity.class);
			startActivity(toWsqToCao);
			break;

		// 每日一抽
		case R.id.c_constellation:
			Intent toLuckyAward = new Intent(FinderActivity.this,
					AwardActivity.class);
			startActivity(toLuckyAward);
			break;
		// 课表
		case R.id.c_recommend:
			Intent toClass = new Intent(FinderActivity.this,
					TableActivity.class);
			startActivity(toClass);
			break;
		// 游戏
		case R.id.bottom_1:
			Intent toGame = new Intent(FinderActivity.this,
					Activity_Circle.class);
			startActivity(toGame);
			break;
		// 地图
		case R.id.bottom_2:
			Intent toMap = new Intent(FinderActivity.this,
					LocationSourceActivity.class);
			startActivity(toMap);
			break;
		default:
			break;
		}
	}
}
