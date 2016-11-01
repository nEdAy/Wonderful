package com.stone.shop.adapter;

import com.xgr.wonderful.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * MineActivity 项目列表适配器
 * @date 2014-5-10
 * @author Stone
 */
public class MineSoftAdapter extends BaseAdapter {
	
	@SuppressWarnings("unused")
	private Context mContext;
	
	private String[] mItemNames; 		// 项目列表名称
	private String[] mItemContents;		//项目列表的备注值
	private LayoutInflater mInflater = null;
	
	public MineSoftAdapter(Context context, String[] names, String[] contents) {
		mContext = context;
		mItemNames = names;
		mItemContents = contents;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mItemNames.length;
	}

	@Override
	public Object getItem(int position) {
		return mItemNames[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MineListHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.mine_soft_list_item, null);
			holder = new MineListHolder();
			holder.tvItemName = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tvItemContent = (TextView) convertView.findViewById(R.id.tv_item_content);
			convertView.setTag(holder);
		} else {
			holder = (MineListHolder) convertView.getTag();
		}
		holder.tvItemName.setText(mItemNames[position]);
		holder.tvItemContent.setText(mItemContents[position]);
		return convertView;
	}

}
