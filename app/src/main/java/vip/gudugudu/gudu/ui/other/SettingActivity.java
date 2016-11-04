package vip.gudugudu.gudu.ui.other;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.base.util.DataCleanManager;
import vip.gudugudu.gudu.view.dialog.CustomeDialog;

/**
 * Created by Administrator on 2016/10/26.
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.setting_account)
    TextView settingAccount;
    @Bind(R.id.setting_clear)
    TextView settingClear;
    @Bind(R.id.setting_toggle)
    ToggleButton settingToggle;
    @Bind(R.id.setting_codename)
    TextView settingCodename;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CustomeDialog.RET_OK:
                    DataCleanManager.clearAllCache(SettingActivity.this);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        titleTitle.setText("设置");
        settingCodename.setText(getVerCode(this));
    }


    @OnClick({R.id.title_back, R.id.setting_account, R.id.setting_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.setting_account:
                break;
            case R.id.setting_clear:
                CustomeDialog customeDialog=new CustomeDialog(SettingActivity.this,handler,"确定清楚缓存？",null);
                break;
        }
    }

    /**
     * 获得当前版本名
     *
     * @param context
     * @return
     */
    private String getVerCode(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "vip.gudugudu.gudu", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;

    }
}
