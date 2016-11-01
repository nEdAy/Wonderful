package com.stone.shop.adapter;

import java.util.List;

import com.stone.shop.model.SComment;
import com.xgr.wonderful.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 店铺评论列表适配器
 * @date 2014-5-3
 * @author Stone
 */
public class SCommentAdapter extends BaseAdapter {
	
	@SuppressWarnings("unused")
	private Context mContext;
	
	private LayoutInflater mInflater = null;
	private List<SComment> mSComList = null; // 所选分类下的所有店铺列表
	
	public SCommentAdapter(Context context, List<SComment> list) {
		mContext = context;
		mSComList = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mSComList.size();
	}

	@Override
	public Object getItem(int position) {
		return mSComList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SComHolder scomHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.scom_list_item, null);
			scomHolder = new SComHolder();
			scomHolder.tvComUser = (TextView) convertView
					.findViewById(R.id.tv_commit_user);
			scomHolder.tvComContent = (TextView) convertView
					.findViewById(R.id.tv_commit_content);
			convertView.setTag(scomHolder);
		} else {
			scomHolder = (SComHolder) convertView.getTag();
		}
		scomHolder.tvComUser.setText(mSComList.get(position).getUserName());
		scomHolder.tvComContent.setText(mSComList.get(position).getContent());
		return convertView;
	}

}
