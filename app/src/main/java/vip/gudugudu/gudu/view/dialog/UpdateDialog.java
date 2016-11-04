package vip.gudugudu.gudu.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import vip.gudugudu.gudu.R;


/**
 * @author Mu
 * @date 2015-3-6
 * @description
 */
public class UpdateDialog extends Dialog implements
        View.OnClickListener {

    public static final int CUSDIALOG_OK = 40;
    public static final int CUSDIALOG_CANCEL = 41;
    private Context context;
    private Handler handler;
    private TextView ok;
    private TextView cancel;
    private TextView text;
    private String msg;
    private int position;
    private int flag;
    private Object object;
    public UpdateDialog(Context context, Handler handler, String msg, int position, int flag, Object object) {
        super(context, R.style.dialog2);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.handler = handler;
        this.context = context;
        this.msg = msg;
        this.position = position;
        this.flag = flag;
        this.object=object;
        setContentView(R.layout.all_alert_dialog);
        setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        show();
        initView();

    }

    private void initView() {
        text = (TextView) findViewById(R.id.alert_message);
        ok = (TextView) findViewById(R.id.alert_ok);
        cancel = (TextView) findViewById(R.id.alert_back);
        text.setText(msg);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alert_ok:
                Message message = new Message();
                message.what = CUSDIALOG_OK;
                message.arg1 = position;
                message.arg2 = flag;
                message.obj=object;
                handler.sendMessage(message);
                dismiss();
                break;
            case R.id.alert_back:
                Message message2 = new Message();
                message2.what = CUSDIALOG_CANCEL;
                message2.arg1 = position;
                handler.sendMessage(message2);
                dismiss();
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Message message2 = new Message();
            message2.what = CUSDIALOG_CANCEL;
            message2.arg1 = position;
            handler.sendMessage(message2);
            dismiss();
        }
        return false;
    }

}
