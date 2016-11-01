package com.xgr.wonderful.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.stone.shop.model.FeedBack;
import com.xgr.wonderful.R;

/**
 * 意见反馈界面
 * 
 * @date 2014-5-27
 * @author Stone
 */
public class FeedBackActivity extends Activity implements OnClickListener {

	private EditText etContent;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		initView();
	}

	private void initView() {
		etContent = (EditText) findViewById(R.id.et_feedback_content);
		btnSubmit = (Button) findViewById(R.id.btn_feedback_submit);
		btnSubmit.setOnClickListener(this);
	}

	/**
	 * 提交用户的反馈信息
	 */
	private void submit() {
		String content = etContent.getText().toString();
		if (content.equals("")) {
			toast("亲，请先写点东西吧");
		} else {
			BmobUser user = BmobUser.getCurrentUser(this);
			FeedBack fb = new FeedBack();
			fb.setUsername(user.getUsername());
			fb.setEmail(user.getEmail());
			fb.setContent(content);
			fb.save(this, new SaveListener() {

				@Override
				public void onSuccess() {
					toast("提交成功, 小小菜会尽快回复");
					back();
				}

				@Override
				public void onFailure(int arg0, String msg) {
					toast("提交失败");
				}

			});

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_feedback_submit:
			submit();
			break;

		default:
			break;
		}
	}

	private void back() {
		finish();
	}

	public void clickFeedBack(View v) {
		finish();
	}

	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

}
