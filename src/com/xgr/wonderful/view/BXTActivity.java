package com.xgr.wonderful.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.adapter.BXTListAdapter;
import com.stone.shop.model.BXTNews;
import com.xgr.wonderful.R;

/**
 * 教学类-博学堂界面
 * 
 * @date 2014-5-10
 * @author Stone
 */
public class BXTActivity extends Activity implements OnItemClickListener,
		SwipeRefreshLayout.OnRefreshListener {

	@SuppressWarnings("unused")
	private static final String TAG = "BXTActivity";

	private ListView lvBXTNews;
	private BXTListAdapter mBxtListAdapter;
	private List<BXTNews> mBXTNewsList;
	private SwipeRefreshLayout swipeLayout;

	// 下拉刷新
	private static final int STATE_REFRESH = 0;// 下拉刷新
	@SuppressWarnings("unused")
	private static final int STATE_MORE = 1;// 加载更多
	private int limit = 10; // 每页的数据是10条
	private int curPage = 0; // 当前页的编号，从0开始

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bxt);
		initView();
		queryData(0, STATE_REFRESH);

	}

	@SuppressLint("InlinedApi") private void initView() {
		
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.lv_shop_all_swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		
		lvBXTNews = (ListView) findViewById(R.id.lv_bxt_news);
		mBXTNewsList = new ArrayList<BXTNews>();
		mBxtListAdapter = new BXTListAdapter(this, mBXTNewsList);
		lvBXTNews.setAdapter(mBxtListAdapter);
		lvBXTNews.setOnItemClickListener(this);
	}

	@SuppressWarnings("unused")
	private void initData() {
		BmobQuery<BXTNews> query = new BmobQuery<BXTNews>();
		query.findObjects(this, new FindListener<BXTNews>() {

			@Override
			public void onSuccess(List<BXTNews> newsList) {
				// toast("查询商品成功, 共" + newsList.size());
				if (newsList.size() == 0)
					toast("亲, 暂时还木有讲座哦");
				else {
					mBXTNewsList = newsList;
					mBxtListAdapter.refresh(newsList);
					mBxtListAdapter.notifyDataSetChanged();
				}

			}

			@Override
			public void onError(int arg0, String arg1) {
				toast("查询失败");
			}
		});
	}
	
	/**
	 * 分页获取数据
	 * @param page	页码
	 * @param actionType	ListView的操作类型（下拉刷新、上拉加载更多）
	 */
	private void queryData(final int page, final int actionType){
		Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);
		
		BmobQuery<BXTNews> query = new BmobQuery<BXTNews>();
		query.order("-createdAt");
		query.setLimit(limit);			// 设置每页多少条数据
		query.setSkip(page*limit);		// 从第几条数据开始，
		query.findObjects(this, new FindListener<BXTNews>() {
			
			@Override
			public void onSuccess(List<BXTNews> arg0) {
				
				if(arg0.size()>0){
					
					// 将本次查询的数据添加到bankCards中
					for (BXTNews bxtNews : arg0) {
						mBXTNewsList.add(bxtNews);
					}
					// 通知Adapter数据更新
					mBxtListAdapter.refresh(mBXTNewsList);
					mBxtListAdapter.notifyDataSetChanged();
					// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
					curPage++;
					toast("第"+(page+1)+"页数据加载完成");
				}else {
					toast("没有更多数据了");
				}
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				toast("查询失败:"+arg1);
			}
		});
	}

	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent toBXTNewsActivity = new Intent(BXTActivity.this,
				BXTNewsActivity.class);
		toBXTNewsActivity.putExtra("title", mBXTNewsList.get(position)
				.getTitle());
		toBXTNewsActivity.putExtra("topic", mBXTNewsList.get(position)
				.getTopic());
		toBXTNewsActivity.putExtra("speaker", mBXTNewsList.get(position)
				.getSpeaker());
		toBXTNewsActivity
				.putExtra("time", mBXTNewsList.get(position).getTime());
		toBXTNewsActivity.putExtra("location", mBXTNewsList.get(position)
				.getLocation());
		toBXTNewsActivity.putExtra("holder1", mBXTNewsList.get(position)
				.getHolder1());
		toBXTNewsActivity.putExtra("holder2", mBXTNewsList.get(position)
				.getHolder2());
		toBXTNewsActivity.putExtra("points", mBXTNewsList.get(position)
				.getPoints());
		toBXTNewsActivity.putExtra("speakerinfo", mBXTNewsList.get(position)
				.getSpeakerinfo());
		startActivity(toBXTNewsActivity);
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				swipeLayout.setRefreshing(true);
				queryData(curPage, STATE_REFRESH);
				swipeLayout.setRefreshing(false);
			}
		}, 1000);
	}

}
