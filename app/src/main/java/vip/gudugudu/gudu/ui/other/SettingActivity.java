package vip.gudugudu.gudu.ui.other;

import android.content.Context;
import android.content.Intent;
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
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.ui.account.AccountActivity;
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
    @Bind(R.id.setting_logout)
    TextView settingLogout;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CustomeDialog.RET_OK:
                    int s= (int) msg.obj;
                    if (s==0){
                        DataCleanManager.clearAllCache(SettingActivity.this);
                    }else if(s==-100){
                        ToosUtils.goReLogin(SettingActivity.this);
                        finish();
                    }

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
        if (ToosUtils.isStringEmpty(SpUtil.getUserToken())){
            settingLogout.setVisibility(View.GONE);
        }else{
            settingLogout.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.title_back, R.id.setting_account, R.id.setting_clear,R.id.setting_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.setting_account:
                Intent intent=new Intent(SettingActivity.this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_clear:
                CustomeDialog customeDialog = new CustomeDialog(SettingActivity.this, handler, "确定清楚缓存？", 0);
                break;
            case R.id.setting_logout:
                CustomeDialog customeDialog2 = new CustomeDialog(SettingActivity.this, handler, "确定退出登录？", -100);
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
