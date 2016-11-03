package vip.gudugudu.gudu.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import vip.gudugudu.gudu.R;


/**
 * @author Mu
 * @date 2015-3-6
 * @description 普通提示对话框
 */
public class CustomeDialog extends Dialog implements
		android.view.View.OnClickListener {
	public static final int RET_OK=40;
	private Context context;
	private Handler handler;
	private TextView ok;
	private TextView cancel;
	private TextView text;
	private String msg;
	Object object;
	public CustomeDialog(Context context, Handler handler, String msg,Object object) {
		super(context, R.style.dialog2);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.handler = handler;
		this.context = context;
		this.msg=msg;
		this.object=object;
		setContentView(R.layout.custom_dialog);
		getWindow().setBackgroundDrawable(new BitmapDrawable());
		show();
		initView();

	}

	private void initView() {
		text = (TextView) findViewById(R.id.custom_dialog_text);
		ok = (TextView) findViewById(R.id.custom_dialog_ok);
		cancel = (TextView) findViewById(R.id.custom_dialog_cancel);
		text.setText(msg);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.custom_dialog_ok:
			Message message=new Message();
			message.what=RET_OK;
			message.obj=object;
			handler.sendMessage(message);
			dismiss();
			break;
		case R.id.custom_dialog_cancel:

			dismiss();
			break;
		default:
			break;
		}

	}

}
