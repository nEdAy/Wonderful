package com.xgr.wonderful.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stone.shop.model.BXTNews;
import com.xgr.wonderful.R;

/**
 * 教学类-博学堂-讲座详情界面
 * @date 2014-5-10
 * @author Stone
 */
public class BXTNewsActivity extends Activity {
	
	@SuppressWarnings("unused")
	private static final String TAG = "BXTNewsActivity"; 
	
	@SuppressWarnings("unused")
	private BXTNews news;
	
	private LinearLayout llBXTNewsTopic;
	private LinearLayout llBXTNewsSpeaker;
	private LinearLayout llBXTNewsTime;
	private LinearLayout llBXTNewsLoc;
	private LinearLayout llBXTNewsHolder1;
	private LinearLayout llBXTNewsHolder2;
	private LinearLayout llBXTNewsPoints;
	private LinearLayout llBXTNewsSpeakerInfo;
	
	private TextView tvBXTNewsTitle;
	private TextView tvBXTNewsTopic;
	private TextView tvBXTNewsSpeaker;
	private TextView tvBXTNewsTime;
	private TextView tvBXTNewsLoc;
	private TextView tvBXTNewsHolder1;
	private TextView tvBXTNewsHolder2;
	private TextView tvBXTNewsPoints;
	private TextView tvBXTNewsSpeakerInfo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bxt_news);
		
		initView();
	}
	
	private void initView() {
		
		tvBXTNewsTitle = (TextView) findViewById(R.id.tv_bxt_news_title);
		tvBXTNewsTopic = (TextView) findViewById(R.id.tv_bxt_news_topic);
		tvBXTNewsSpeaker = (TextView) findViewById(R.id.tv_bxt_news_speaker);
		tvBXTNewsTime = (TextView) findViewById(R.id.tv_bxt_news_time);
		tvBXTNewsLoc = (TextView) findViewById(R.id.tv_bxt_news_loc);
		tvBXTNewsHolder1 = (TextView) findViewById(R.id.tv_bxt_news_holder1);
		tvBXTNewsHolder2 = (TextView) findViewById(R.id.tv_bxt_news_holder2);
		tvBXTNewsPoints = (TextView) findViewById(R.id.tv_bxt_news_point);
		tvBXTNewsSpeakerInfo = (TextView) findViewById(R.id.tv_bxt_news_speaker_info);
		
		llBXTNewsTopic = (LinearLayout) findViewById(R.id.ll_bxt_news_topic);
		llBXTNewsSpeaker = (LinearLayout) findViewById(R.id.ll_bxt_news_speaker);
		llBXTNewsTime = (LinearLayout) findViewById(R.id.ll_bxt_news_time);
		llBXTNewsLoc = (LinearLayout) findViewById(R.id.ll_bxt_news_loc);
		llBXTNewsHolder1 = (LinearLayout) findViewById(R.id.ll_bxt_news_holder1);
		llBXTNewsHolder2 = (LinearLayout) findViewById(R.id.ll_bxt_news_holder2);
		llBXTNewsPoints = (LinearLayout) findViewById(R.id.ll_bxt_news_point);
		llBXTNewsSpeakerInfo = (LinearLayout) findViewById(R.id.ll_bxt_news_speaker_info);
		
		tvBXTNewsTitle.setText(getIntent().getStringExtra("title"));
		if(getIntent().getStringExtra("topic")==null)
			llBXTNewsTopic.setVisibility(View.GONE);
		else
			tvBXTNewsTopic.setText(getIntent().getStringExtra("topic"));
		
		if(getIntent().getStringExtra("speaker")==null)
			llBXTNewsSpeaker.setVisibility(View.GONE);
		else
			tvBXTNewsSpeaker.setText(getIntent().getStringExtra("speaker"));
		
		if(getIntent().getStringExtra("time")==null)
			llBXTNewsTime.setVisibility(View.GONE);
		else
			tvBXTNewsTime.setText(getIntent().getStringExtra("time"));
		
		if(getIntent().getStringExtra("location")==null)
			llBXTNewsLoc.setVisibility(View.GONE);
		else
			tvBXTNewsLoc.setText(getIntent().getStringExtra("location"));
		
		if(getIntent().getStringExtra("holder1")==null)
			llBXTNewsHolder1.setVisibility(View.GONE);
		else
			tvBXTNewsHolder1.setText(getIntent().getStringExtra("holder1"));
		
		if(getIntent().getStringExtra("holder2")==null)
			llBXTNewsHolder2.setVisibility(View.GONE);
		else
			tvBXTNewsHolder2.setText(getIntent().getStringExtra("holder2"));
		
		if(getIntent().getStringExtra("points")==null)
			llBXTNewsPoints.setVisibility(View.GONE);
		else
			tvBXTNewsPoints.setText(getIntent().getStringExtra("points"));
		
		if(getIntent().getStringExtra("speakerinfo")==null)
			llBXTNewsSpeakerInfo.setVisibility(View.GONE);
		else
			tvBXTNewsSpeakerInfo.setText(getIntent().getStringExtra("speakerinfo"));
		
	}
	
}
