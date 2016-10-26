package vip.gudugudu.gudu.ui.other;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        titleTitle.setText("设置");
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
                break;
        }
    }
}
