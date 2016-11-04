package vip.gudugudu.gudu.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import vip.gudugudu.gudu.R;


/**
 * @author 穆文磊
 *         <p>
 *         2014年7月29日 22:30:13
 *         <p>
 *         公共方法 弹出窗口 （确定 、取消 按钮）
 */
public class AlertDiaLogActivity extends Dialog {
    private LinearLayout lly_view;
    private Context context;

    private int screenWidth;

    public AlertDiaLogActivity(Context context) {
        super(context);
        this.context = context;
    }

    public AlertDiaLogActivity(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.all_alert_dialog);
//		lly_view = (LinearLayout) findViewById(R.id.lly_view);
//		DisplayMetrics dm = context.getResources().getDisplayMetrics();
//		screenWidth = dm.widthPixels;
//		android.view.ViewGroup.LayoutParams lps = lly_view.getLayoutParams();
//		lps.width = screenWidth - 50;
//		lly_view.setLayoutParams(lps);
    }


}
