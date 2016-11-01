package com.xgr.wonderful.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.adapter.OrderInfoListAdapter;
import com.stone.shop.model.Order;
import com.xgr.wonderful.R;

/**
 * 订单详情页面
 * 
 * @date 2014-5-27
 * @author Stone
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class OrderInfoActivity extends Activity implements OnItemLongClickListener{

	@SuppressWarnings("unused")
	private static final String TAG = "OrderInfoActivity";

	private ListView lvOrderInfo;
	private OrderInfoListAdapter orderInfoListAdapter;
	private List<Order> orderList = new ArrayList<Order>();
	
	private String type = "";  // now-当前订单  old-历史订单

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_info);
		
		type = getIntent().getStringExtra("type");

		initData();
		initView();
	}

	private void initView() {
		lvOrderInfo = (ListView) findViewById(R.id.lv_order_info);
		orderInfoListAdapter = new OrderInfoListAdapter(this, orderList);
		lvOrderInfo.setAdapter(orderInfoListAdapter);
		lvOrderInfo.setOnItemLongClickListener(this);
	}

	// 初始化列表菜单中数据
	public void initData() {
		// 获取用户
		BmobUser user = BmobUser.getCurrentUser(this);

		// 获取小菜订单(数量)
		BmobQuery<Order> query = new BmobQuery<Order>();
		query.order("-updatedAt");
		query.addWhereEqualTo("userName", user.getUsername());
		if(type.equals("now")) {
			query.addWhereEqualTo("state", "未取餐");
		} else if(type.equals("old")) {
			query.addWhereEqualTo("state", "已取餐");
		} else {
			// do nothing
		}
		query.findObjects(this, new FindListener<Order>() {

			@Override
			public void onSuccess(List<Order> object) {
				if (object.size() == 0)
					toast("亲, 您还木有订单哦");
				orderList = object;
				// 通知Adapter数据更新
				orderInfoListAdapter.refresh(orderList);
				orderInfoListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int arg0, String arg1) {
				toast("查询失败");
			}
		});

	}
	
	private void toast(String toast) { 
		Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
	}

	//订单长按响应事件
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		PopupMenu popup = new PopupMenu(this, lvOrderInfo);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
			public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(OrderInfoActivity.this, "Clicked popup menu item " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popup.show();
		return false;
	};

}
