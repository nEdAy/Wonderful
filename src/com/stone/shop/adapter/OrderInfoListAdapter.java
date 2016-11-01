package com.stone.shop.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stone.shop.model.Order;
import com.xgr.wonderful.R;

/**
 * 适配器--适配订单列表中的数据
 * 
 * @date 2014-5-27
 * @author Stone
 */
public class OrderInfoListAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context mContext;
	
	private LayoutInflater mInflater = null;
	private List<Order> mOrderList = null; // 所选分类下的所有店铺列表
	
	@SuppressWarnings("unused")
	private String mType; // 商店的分类

	public OrderInfoListAdapter(Context context, List<Order> orderList) {
		mContext = context;
		mOrderList = orderList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mOrderList.size();
	}

	@Override
	public Object getItem(int position) {
		return mOrderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void refresh(List<Order> list) {
		mOrderList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		OrderInfoHolder orderInfoHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.order_info_list_item, null);
			orderInfoHolder = new OrderInfoHolder();
			orderInfoHolder.tvOrderInfoGoodName = (TextView) convertView
					.findViewById(R.id.tv_order_info_good_name);
			orderInfoHolder.tvOrderInfoShopName = (TextView) convertView
					.findViewById(R.id.tv_order_info_shop_name);
			orderInfoHolder.tvOrderInfoPrice = (TextView) convertView
					.findViewById(R.id.tv_order_info_price);
			orderInfoHolder.tvOrderInfoState = (TextView) convertView
					.findViewById(R.id.tv_order_info_state);
			convertView.setTag(orderInfoHolder);
		} else {
			orderInfoHolder = (OrderInfoHolder) convertView.getTag();
		}
		orderInfoHolder.tvOrderInfoGoodName.setText(mOrderList.get(position).getGoodName());
		orderInfoHolder.tvOrderInfoShopName.setText(mOrderList.get(position).getShopName());
		orderInfoHolder.tvOrderInfoPrice.setText("￥" + mOrderList.get(position).getPrice());
		orderInfoHolder.tvOrderInfoState.setText(mOrderList.get(position).getState());
		return convertView;
	}

}
