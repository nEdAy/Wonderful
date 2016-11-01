package com.xgr.wonderful.ui;

import com.xgr.wonderful.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;   
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * 对话框订单
 * @date 2014-5-11
 * @author Stone
 */
public class DialogOrder extends Dialog implements OnClickListener{
	
	@SuppressWarnings("unused")
	private Context context;
	private Button btnDlgOk;
	private TimePicker tpDlgTime;
	private OrderDialogListener listener;
	
	public interface OrderDialogListener{   
        public void onClick(View view);   
    }
	
    public DialogOrder(Context context) {
        super(context);
        this.context = context;
    }
    
    public DialogOrder(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    
    public DialogOrder(Context context, int theme, DialogOrder.OrderDialogListener orderDialogListener){
        super(context, theme);
        this.context = context;
        this.listener = orderDialogListener;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dlg_order_settime);
        initView();
    }
    
    private  void initView() {
    	tpDlgTime = (TimePicker) findViewById(R.id.tp_dlg_time);
    	tpDlgTime.setIs24HourView(true);
    	btnDlgOk = (Button) findViewById(R.id.btn_dlg_ok);
    	btnDlgOk.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		listener.onClick(v);
	}
	
	public String getTime() {
		return tpDlgTime.getCurrentHour() + " : " + tpDlgTime.getCurrentMinute();
	}
    
}
