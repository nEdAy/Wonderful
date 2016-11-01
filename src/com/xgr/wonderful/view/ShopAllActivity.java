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
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.adapter.ShopListAdapter;
import com.stone.shop.model.Shop;
import com.xgr.wonderful.R;

/**
 * 某一分类下的所有店铺页面
 * @author Stone
 * @date  2014-4-26 
 */
public class ShopAllActivity extends Activity implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
	
	private static final String TAG = "ShopAllActivity" ; 

	private TextView tvTitle;
	private TextView tvEmptyBg;  //当数据为空时现实的视图
	
	private ListView lvShopAllList;
	private ShopListAdapter shopListAdapter;
	private SwipeRefreshLayout swipeLayout;
	
	//记录从ShopActivity中传过来的当前点击项的类型
	private String type;
	private List<Shop> shopList = new ArrayList<Shop>();
	
	//下拉刷新
	private static final int STATE_REFRESH = 0;// 下拉刷新
	@SuppressWarnings("unused")
	private static final int STATE_MORE = 1;// 加载更多
	
	private int limit = 10;		// 每页的数据是10条
	private int curPage = 0;		// 当前页的编号，从0开始
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_all);
		
		//得到从上级Activity中传入的Type数据
		type = getIntent().getStringExtra("type");
		
		//获取商店数据
		queryData(0, STATE_REFRESH);
		
		initView();
		
	}
	
	@SuppressLint("InlinedApi") public void initView() {
		//设置标题
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText(getIntent().getStringExtra("title"));
		
		tvEmptyBg = (TextView) findViewById(R.id.ll_msg_empty);
		tvEmptyBg.setVisibility(View.GONE);
		
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.lv_shop_all_swipe_container);
		
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		
		lvShopAllList = (ListView) findViewById(R.id.lv_shop_all);
		shopListAdapter = new ShopListAdapter(this, (ArrayList<Shop>) shopList, type);
		lvShopAllList.setAdapter(shopListAdapter);
		lvShopAllList.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//toast("点击了： " + position);
		//将当前点击的Shop对象传递给下一个Activity
		Intent toShopItem = new Intent(ShopAllActivity.this, ShopItemActivity.class);
		Bundle bundle = new Bundle();  
        bundle.putSerializable("shop", shopList.get(position) );  
        bundle.putString("shopID", shopList.get(position).getObjectId()); //商铺的ID需要单独传递,否则获取到的是null
        Log.i(TAG, ">>发出>>" + "shopID: "+shopList.get(position).getObjectId()+" shopName: "+shopList.get(position).getName());
        toShopItem.putExtras(bundle);
		startActivity(toShopItem);
	}
	
	/**
	 * 加载当前分类的所有店铺到ListView中
	 */
	@SuppressWarnings("unused")
	private void getShopsDate() {
		BmobQuery<Shop> query = new BmobQuery<Shop>();
		query.order("-createdAt");
		Shop shop = new Shop();
		shop.setType(type);
		query.addWhereEqualTo("type", shop.getType());    // 查询当前类型的所有店铺
		query.findObjects(this, new FindListener<Shop>() {
			
		    @Override
		    public void onSuccess(List<Shop> object) {
		        //toast("查询成功. 共计" + object.size());
		    	if(object.size()==0)
		    		toast("亲, 你来得太早了点哦");
		        shopList = object;
		        // 通知Adapter数据更新
		        shopListAdapter.refresh((ArrayList<Shop>) shopList);
		        shopListAdapter.notifyDataSetChanged();

		    }
		    
			@Override
			public void onError(int arg0, String msg) {
				toast("查询失败:"+msg);
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
		
		BmobQuery<Shop> query = new BmobQuery<Shop>();
		Shop shop = new Shop();
		shop.setType(type);
		query.addWhereEqualTo("type", shop.getType());    // 查询当前类型的所有店铺
		query.order("-createdAt");
		query.setLimit(limit);			// 设置每页多少条数据
		query.setSkip(page*limit);		// 从第几条数据开始，
		query.findObjects(this, new FindListener<Shop>() {
			
			@Override
			public void onSuccess(List<Shop> arg0) {
				
				if(arg0.size()>0){
					
					// 将本次查询的数据添加到bankCards中
					for (Shop shop : arg0) {
						shopList.add(shop);
					}
					// 通知Adapter数据更新
			        shopListAdapter.refresh((ArrayList<Shop>) shopList);
					shopListAdapter.notifyDataSetChanged();
					// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
					curPage++;
					toast("第"+(page+1)+"页数据加载完成");
				}else {
					if(page == 0)
					{
						tvEmptyBg.setVisibility(View.VISIBLE);
					}
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
		Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				swipeLayout.setRefreshing(false);
				queryData(curPage, STATE_REFRESH);
			}
		}, 1000);
	}

}
