package com.stone.shop.adapter;

import com.xgr.wonderful.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * MineActivity 项目列表适配器
 * @date 2014-5-10
 * @author Stone
 */
public class MineListAdapter extends BaseAdapter {
	
	@SuppressWarnings("unused")
	private Context mContext;
	
	private String[] mItemNames; 		// 项目列表名称
	private String[] mItemContents;		//项目列表的备注值
	private int[] mItemImgIds;			// 项目列表Icon	
	private LayoutInflater mInflater = null;
	
	public MineListAdapter(Context context, String[] names, String[] contents, int[] imgIds) {
		mContext = context;
		mItemNames = names;
		mItemContents = contents;
		mItemImgIds = imgIds;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MineListHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.mine_list_item, null);
			holder = new MineListHolder();
			holder.imgItem = (ImageView) convertView.findViewById(R.id.img_item);
			holder.tvItemName = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tvItemContent = (TextView) convertView.findViewById(R.id.tv_item_content);
			convertView.setTag(holder);
		} else {
			holder = (MineListHolder) convertView.getTag();
		}
		holder.imgItem.setBackgroundResource(mItemImgIds[position]);
		holder.tvItemName.setText(mItemNames[position]);
		holder.tvItemContent.setText(mItemContents[position]);
		return convertView;
	}

}
