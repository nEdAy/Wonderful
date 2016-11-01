package com.stone.shop.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stone.shop.model.BXTNews;
import com.xgr.wonderful.R;

/**
 * 教学类-博学堂-讲座列表适配器
 * 
 * @date 2014-5-10
 * @author Stone
 */
public class BXTListAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context mContext;
	
	private List<BXTNews> mNewsList; // 新闻列表
	private LayoutInflater mInflater = null;

	public BXTListAdapter(Context context, List<BXTNews> newsList) {

		mNewsList = newsList;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mNewsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<BXTNews> list) {
		Log.i("BXTNewsAdapter", "Adapter刷新数据");
		mNewsList = list;
		notifyDataSetChanged();
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BXTNewsHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.bxt_list_item, null);
			newsHolder = new BXTNewsHolder();
			newsHolder.tvBXTNewsLabel = (TextView) convertView
					.findViewById(R.id.tv_bxt_news_item_label);
			newsHolder.tvBXTNewsTitle = (TextView) convertView
					.findViewById(R.id.tv_bxt_news_item_title);
			convertView.setTag(newsHolder);
		} else {
			newsHolder = (BXTNewsHolder) convertView.getTag();
		}
		newsHolder.tvBXTNewsTitle.setText(mNewsList.get(position).getTitle());
		
		//设置标签
		String label = getNewsLabelsText(compareTime(mNewsList.get(position).getTime()));
		newsHolder.tvBXTNewsLabel.setText(label);
		if (label.equals("已结束")) {
			newsHolder.tvBXTNewsLabel.setBackgroundColor(0xFFDC143C);
		} else if (label.equals("今天")) {
			newsHolder.tvBXTNewsLabel.setBackgroundColor(0xFF3CB371);
		} else if(label.equals("预告")){
			newsHolder.tvBXTNewsLabel.setBackgroundColor(0xFF48D1CC);
		}else {
			newsHolder.tvBXTNewsLabel.setBackgroundColor(0xFF000000);
		}
		return convertView;
	}


	/**
	 * 根据日期得到对应的显示标签
	 * 
	 * @author Stone
	 * @date 2014-9-17
	 * @return 1----已结束 2----今天 3----预告
	 */
	private int compareTime(String timeOfBXTNews) {
		if(timeOfBXTNews==null)
			return 0;
		Time time = new Time("GMT+8");
		time.setToNow();
		int yearOfCurrent = time.year;
		int monthOfCurrent = time.month + 1;
		int dayOfCurrent = time.monthDay;

		String[] year = timeOfBXTNews.split("年");
		String[] month = year[1].split("月");
		String[] day = month[1].split("日");

		int yearOfNews = Integer.parseInt(year[0]);
		int monthOfNews = Integer.parseInt(month[0]);
		int dayOfNews = Integer.parseInt(day[0]);

		Log.d("讲座时间", yearOfNews + " " + monthOfNews + " " + dayOfNews);
		Log.d("当前时间", yearOfCurrent + " " + monthOfCurrent + " " + dayOfCurrent);

		if (yearOfCurrent < yearOfNews) {
			return 3; // 预告
		} else if (yearOfCurrent == yearOfNews) {
			if (monthOfCurrent < monthOfNews) {
				return 3;
			} else if (monthOfCurrent == monthOfNews) {
				if (dayOfCurrent < dayOfNews) {
					return 3;
				} else if (dayOfCurrent == dayOfNews) {
					return 2;// 今天
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		} else {
			return 1; // 已结束
		}
	}

	/**
	 * 将数字转化为对应的文字
	 * 
	 * @author Stone
	 * @date 2014-9-17
	 */
	private String getNewsLabelsText(int code) {
		if (code == 1) {
			return "已结束";
		} else if (code == 2) {
			return "今天";
		} else if (code ==3){
			return "预告";
		} else {
			return "无时间";
		}
	}

}
