package vip.gudugudu.gudu.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements RegisterContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.register_name)
    EditText registerName;
    @Bind(R.id.register_getcode)
    TextView registerGetcode;
    @Bind(R.id.register_code)
    EditText registerCode;
    @Bind(R.id.register_pwd)
    EditText registerPwd;
    @Bind(R.id.register_cb)
    CheckBox registerCb;
    @Bind(R.id.register_register)
    TextView registerRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        titleTitle.setText("注册");
    }


    @OnClick({R.id.title_back, R.id.register_getcode, R.id.register_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.register_getcode:
                break;
            case R.id.register_register:

                break;
        }
    }
}
