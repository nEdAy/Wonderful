package com.xgr.wonderful.view;

import com.bmob.im.demo.ui.NearPeopleActivity;
import com.stone.shop.adapter.GridAdapter;
import com.xgr.wonderful.R;
import com.xgr.wonderful.ui.MyGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * 商品主界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class ShopActivity extends Activity implements OnItemClickListener {

	// V1.9.2-------------------------------------
	private MyGridView gvFoodClass; // 吃饭小菜(小菜点餐)
	private MyGridView gvGiftClass; // 购物小菜(校园服务)

	// V1.9.2-------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		initView();
	}

	/**
	 * 初始化组件并适配数据
	 */
	public void initView() {
		gvFoodClass = (MyGridView) findViewById(R.id.gv_food_class);
		gvGiftClass = (MyGridView) findViewById(R.id.gv_gift_class);

		// V1.9.2-------------------------------------
		// 小菜点餐
		gvFoodClass.setAdapter(new GridAdapter(this, 0));
		gvFoodClass.setOnItemClickListener(this);

		// 校园服务
		gvGiftClass.setAdapter(new GridAdapter(this, 1));
		gvGiftClass.setOnItemClickListener(this);
		// V1.9.2-------------------------------------

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i("GridView点击了： ", "position" + position);
		switch (parent.getId()) {
		// 小菜点餐
		case R.id.gv_food_class:
			toShopAllActivity(GridAdapter.mFoodTexts[position], "2"
					+ (position + 1));
			break;

		// 校园服务
		case R.id.gv_gift_class:
			Intent intent = new Intent();
			if (position == 0) {
				intent = new Intent(ShopActivity.this, BXTActivity.class);
				startActivity(intent);
			} else if (position == 3) {	//附近的人
				intent = new Intent(ShopActivity.this,
						NearPeopleActivity.class);
				startActivity(intent);
			} else if (position == 4) { //出勤签到
				intent = new Intent(ShopActivity.this,
						AttendanceActivity.class);
				startActivity(intent);
			} else if (position == 5) {
				intent = new Intent(ShopActivity.this,
						SecondTradeActivity.class);
				intent.putExtra("title", "二手交易");
				startActivity(intent);
			} else {
				toShopAllActivity(GridAdapter.mGiftTexts[position], "3"
						+ (position + 1));
			}
			break;

		default:
			break;
		}

	}

	@SuppressWarnings("unused")
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	};

	/**
	 * @param title
	 *            父分类标题
	 * @param type
	 */
	private void toShopAllActivity(String title, String type) {
		Intent toShopAll = new Intent(ShopActivity.this, ShopAllActivity.class);
		toShopAll.putExtra("title", title);
		// 当前点击的项的父分类
		toShopAll.putExtra("type", type);
		startActivity(toShopAll);
	}

}
