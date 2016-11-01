package com.xgr.wonderful.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.adapter.NewsListAdapter;
import com.stone.shop.model.News;
import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.BaseActivity;
import com.xgr.wonderful.ui.ListScrollView;

/**
 * 主界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class HomeActivity extends BaseActivity implements OnItemClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "HomeActivity";

	private ListScrollView listScrollView;

	// 校园新闻
	private ListView lvNewsList;
	private List<News> newsList = new ArrayList<News>();
	private NewsListAdapter newsListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// 解决ScrollView和ListView之间的冲突
		listScrollView = (ListScrollView) findViewById(R.id.listScrollView);
		lvNewsList = (ListView) findViewById(R.id.lv_news);
		listScrollView.setListView(lvNewsList);

		// 新闻
		newsListAdapter = new NewsListAdapter(this, newsList);
		lvNewsList.setAdapter(newsListAdapter);
		lvNewsList.setOnItemClickListener(this);

		getNewsData();
	}

	/**
	 * 初始化新闻列表数据
	 * @date 2014-5-3
	 * @author Stone
	 */
	public void getNewsData() {
		BmobQuery<News> query = new BmobQuery<News>();
		query.order("-updatedAt");
		query.findObjects(this, new FindListener<News>() {

			@Override
			public void onSuccess(List<News> object) {
				newsList = object;
				// 通知Adapter数据更新
				newsListAdapter.refresh((ArrayList<News>) newsList);
				newsListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
				ShowToast("都怪我, 获取数据失败了");
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// start auto scroll when onResume
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent toNewsDetail = new Intent(HomeActivity.this, NewsActivity.class);
		toNewsDetail.putExtra("NewsID", newsList.get(position).getObjectId());
		toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
		toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getAuthor());
		toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
		toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
		startActivity(toNewsDetail);
	}

	public void clickBack(View v) {
		finish();
	}

}
