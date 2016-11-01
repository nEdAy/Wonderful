package com.xgr.wonderful.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.model.News;
import com.xgr.wonderful.R;

/**
 * 新闻内容显示界面
 * 
 * @date 2014-5-8
 * @author Stone
 */
public class NewsActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = "NewsActivity";

	private TextView tvNewsTitle;
	private TextView tvNewsAuthor;
	private TextView tvNewsTime;
	private TextView tvNewsContent;
	private ImageView imgNews;

	private String newsID;
	private String newsTitle;
	private String newsAuthor;
	private String newsTime;
	private String newsContent;

	private News news;
	@SuppressLint("HandlerLeak") Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				// 加载图片
				loadImage();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		getIntentData();
		getNewsByID();
		initView();
	}

	private void initView() {
		tvNewsTitle = (TextView) findViewById(R.id.tv_news_title);
		tvNewsAuthor = (TextView) findViewById(R.id.tv_news_author);
		tvNewsTime = (TextView) findViewById(R.id.tv_news_time);
		tvNewsContent = (TextView) findViewById(R.id.tv_news_content);
		imgNews = (ImageView) findViewById(R.id.img_news);

		tvNewsTitle.setText(newsTitle);
		tvNewsAuthor.setText("作者: " + newsAuthor);
		tvNewsTime.setText("发布日期 : " + newsTime);
		tvNewsContent.setText(newsContent);
	}

	// 获取Intent中传入的新闻数据
	private void getIntentData() {
		newsID = getIntent().getStringExtra("NewsID");
		newsTitle = getIntent().getStringExtra("NewsTitle");
		newsAuthor = getIntent().getStringExtra("NewsAuthor");
		newsTime = getIntent().getStringExtra("NewsTime");
		newsContent = getIntent().getStringExtra("NewsContent");

		newsTitle = splitString(newsTitle); // 拆分字符串, 将新闻标题设置为 "】" 后面的内容
	}

	private String splitString(String str) {
		String[] strs = null;
		if (str.equals("")) {
			return "";
		} else if (!(str.contains("【") || str.contains("】"))) {
			return str;
		}
		strs = str.split("】");
		return strs[1];
	}

	/**
	 * 根据ID查找新闻
	 * 
	 * @date 2014-9-16
	 * @author Stone
	 */
	private void getNewsByID() {
		news = new News();
		BmobQuery<News> query = new BmobQuery<News>();
		query.addWhereEqualTo("objectId", newsID);
		query.findObjects(this, new FindListener<News>() {

			@Override
			public void onSuccess(List<News> object) {
				if (object != null)
				{
					news = object.get(0);
					//发送消息开始加载图片
					Message msg = new Message();  
	                msg.what = 0;  
	                mHandler.sendMessage(msg);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				toast("都怪小菜我, 获取数据失败了");
			}
		});
	}

	
	/**
	 * 加载图片显示
	 * @author Stone
	 * @date 2014-9-16
	 */
	private void loadImage() {
		//只加载图片,后面两个参数是图片的大小
		if (news.getPicNews() != null)
			news.getPicNews().loadImage(this, imgNews, 300, 180);
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

}
