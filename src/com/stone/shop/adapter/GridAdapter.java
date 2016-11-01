package com.stone.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stone.date.TypeDef;
import com.xgr.wonderful.R;

/**
 * 小菜-- 网格布局(ImageView+TextView)适配器
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class GridAdapter extends BaseAdapter {

	private Context mContext;
	private int mIndex = 0; // 代表当前需要适配页面中第几个GridView

	// 吃饭小菜(小菜点餐)
	public static String[] mFoodTexts = TypeDef.typeSonList2;
	private int[] mFoodImages = { R.drawable.ic_grid_market, R.drawable.ic_4,
			R.drawable.ic_4, R.drawable.ic_4, R.drawable.ic_4, R.drawable.ic_4 };

	// 购物小菜(校园服务)
	public static String[] mGiftTexts = TypeDef.typeSonList3;
	private int[] mGiftImages = { R.drawable.ic_grid_bxt,
			R.drawable.ic_grid_party, R.drawable.ic_grid_study,
			R.drawable.ic_grid_hire, R.drawable.ic_grid_outdoor,
			R.drawable.ic_grid_trade };

	public GridAdapter(Context context, int index) {
		mContext = context;
		mIndex = index;
	}

	@Override
	public int getCount() {
		int count = 0;
		switch (mIndex) {
		case 0:
			count = mFoodImages.length;
			break;
		case 1:
			count = mGiftImages.length;
			break;
		default:
			break;
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.shop_grid_item, null);

		ImageView image = (ImageView) view.findViewById(R.id.img_chooseImage);
		TextView text = (TextView) view.findViewById(R.id.tv_chooseText);
		switch (mIndex) {
		case 0:
			image.setImageResource(mFoodImages[position]);
			text.setText(mFoodTexts[position]);
			break;
		case 1:
			image.setImageResource(mGiftImages[position]);
			text.setText(mGiftTexts[position]);
			break;
		default:
			break;
		}

		return view;
	}

}
