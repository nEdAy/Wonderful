package com.xgr.wonderful.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CountListener;

import com.bmob.im.demo.CustomApplcation;
import com.bmob.im.demo.ui.ImMainActivity;
import com.lenovo.lps.sus.SUS;
import com.stone.date.MessageDef;
import com.stone.shop.adapter.MineListAdapter;
import com.stone.shop.model.Order;
import com.xgr.wonderful.R;

/**
 * 个人中心主界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class MineActivity extends Activity implements OnItemClickListener {

	private String[] userItemNames = { "stonekity" };
	private String[] userItemContents = { "" };
	private String[] orderItemNames = { "当前订单", "历史订单" };
	private String[] orderItemContents = { "*", "" };
	private String[] aboutItemNames = { "约聊会话", "意见反馈", "推荐给朋友", "检查更新", "退出账号" };
	private String[] aboutItemContents = { "", "", "", "", "" };

	private int[] userImgIds = { R.drawable.ic_menu_myplaces };
	private int[] orderImgIds = { R.drawable.ic_menu_find_holo_light,
			R.drawable.ic_menu_copy_holo_light };
	private int[] aboutImgIds = { R.drawable.ic_menu_notifications,
			R.drawable.ic_menu_info_details, R.drawable.ic_menu_share,
			R.drawable.ic_menu_upversion, R.drawable.ic_star_yes };

	private ListView lvMineUser;
	private ListView lvMineOrder;
	private ListView lvMineAbout;
	private MineListAdapter userListAdapter;
	private MineListAdapter orderListAdapter;
	private MineListAdapter aboutListAdapter;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MessageDef.MINE_FINISH_LOAD_DATA:
				// toast("Handler 收到数据加载完成的消息");
				orderListAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine);

		initData("已取餐");
		initData("未取餐");
		initView();
	}

	private void initView() {

		lvMineUser = (ListView) findViewById(R.id.lv_mine_user);
		lvMineOrder = (ListView) findViewById(R.id.lv_mine_order);
		lvMineAbout = (ListView) findViewById(R.id.lv_mine_about);

		userListAdapter = new MineListAdapter(this, userItemNames,
				userItemContents, userImgIds);
		orderListAdapter = new MineListAdapter(this, orderItemNames,
				orderItemContents, orderImgIds);
		aboutListAdapter = new MineListAdapter(this, aboutItemNames,
				aboutItemContents, aboutImgIds);

		lvMineUser.setAdapter(userListAdapter);
		lvMineOrder.setAdapter(orderListAdapter);
		lvMineAbout.setAdapter(aboutListAdapter);

		lvMineUser.setOnItemClickListener(this);
		lvMineOrder.setOnItemClickListener(this);
		lvMineAbout.setOnItemClickListener(this);

	}

	// 初始化列表菜单中数据
	public void initData(final String type) {
		// 获取用户
		BmobUser user = BmobUser.getCurrentUser(this);
		userItemNames[0] = user.getUsername();

		// 获取小菜订单(数量)·
		BmobQuery<Order> query = new BmobQuery<Order>();
		query.order("-updatedAt");
		query.addWhereEqualTo("userName", user.getUsername());
		query.addWhereEqualTo("state", type);
		query.count(this, Order.class, new CountListener() {

			@Override
			public void onSuccess(int count) {
				if (type.equals("未取餐")) {
					orderItemContents[0] = "( " + count + " )";
				}
				if (type.equals("已取餐")) {
					orderItemContents[1] = "( " + count + " )";
				}
				Message msg = new Message();
				msg.what = MessageDef.MINE_FINISH_LOAD_DATA;
				mHandler.sendMessage(msg);
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				toast("查询失败");
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// 个人资料
		if (parent.getId() == R.id.lv_mine_user) {
			switch (position) {
			case 0: // 资料卡
				// toast("点击个人资料");
				Intent toMineInfo = new Intent(MineActivity.this,
						MineInfoActivity.class);
				startActivity(toMineInfo);
				break;

			default:
				break;
			}
		}

		// 小菜订单
		if (parent.getId() == R.id.lv_mine_order) {
			// toast("点击了订单区域");
			Intent toOrderInfo;
			switch (position) {
			case 0:
				toOrderInfo = new Intent(MineActivity.this,
						OrderInfoActivity.class);
				toOrderInfo.putExtra("type", "now");
				startActivity(toOrderInfo);
				break;
			case 1:
				toOrderInfo = new Intent(MineActivity.this,
						OrderInfoActivity.class);
				toOrderInfo.putExtra("type", "old");
				startActivity(toOrderInfo);
				break;
			default:
				break;
			}
		}

		// 其他
		if (parent.getId() == R.id.lv_mine_about) {

			switch (position) {
			case 1: // 意见反馈
				Intent toFeedBack = new Intent(MineActivity.this,
						FeedBackActivity.class);
				startActivity(toFeedBack);
				break;
			case 2: // 推荐给朋友
				Intent toShare = new Intent(Intent.ACTION_SEND);
				toShare.setType("text/plain");
				toShare.putExtra(Intent.EXTRA_SUBJECT, "分享");
				toShare.putExtra(Intent.EXTRA_TEXT, "华信XXX" + "\n"
						+ "针对软件园测试版上线了，赶紧下载体验吧" + "http://hxxxx.bmob.cn");
				startActivity(Intent.createChooser(toShare, "分享到"));
				break;
			case 3: // 检查更新
				if (!SUS.isVersionUpdateStarted()) {
					SUS.AsyncStartVersionUpdate_IgnoreUserSettings(this);
					
				}
				startActivity(new Intent(this,AboutActivity.class));
				break;
			case 4: // 退出当期账号
				CustomApplcation.getInstance().logout();
				startActivity(new Intent(this, RegisterAndLoginActivity.class));
				finish();
				break;

			default:
				// toast("点击了约聊会话");
				startActivity(new Intent(MineActivity.this,
						ImMainActivity.class));
				break;
			}

		}

	}

	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

}
