package vip.gudugudu.gudu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.ui.forget.ForgetActiyity;
import vip.gudugudu.gudu.ui.register.RegisterActivity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.login_forget)
    TextView loginForget;
    @Bind(R.id.login_login)
    TextView loginLogin;
    @Bind(R.id.login_register)
    TextView loginRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        titleTitle.setText("登录");

    }

    @OnClick({R.id.title_back, R.id.login_forget, R.id.login_login, R.id.login_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.login_forget:
                Intent intent=new Intent(LoginActivity.this, ForgetActiyity.class);
                startActivity(intent);

                break;
            case R.id.login_login:

                break;
            case R.id.login_register:
                Intent intent3=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
