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
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.entity.RegisterEntity;
import vip.gudugudu.gudu.ui.forget.ForgetActiyity;
import vip.gudugudu.gudu.ui.register.RegisterActivity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    public static final int REGISTER_RES=1005;
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
                if (checkInput()){
                    showDialog();
                    mPresenter.login(ToosUtils.getTextContent(loginName),ToosUtils.getTextContent(loginPwd));
                }
                break;
            case R.id.login_register:
                Intent intent3=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent3,REGISTER_RES);
                break;
        }
    }

    /**
     * 注册输入
     *
     * @return
     */
    private boolean checkInput() {
        if (ToosUtils.isTextEmpty(loginName)) {
            ToastUtil.show("手机号不能为空！");
            return false;
        }
        if (!ToosUtils.MatchPhone(ToosUtils.getTextContent(loginName))) {
            ToastUtil.show( "输入的手机号格式错误！");
            return false;
        }
        if (ToosUtils.isTextEmpty(loginPwd)) {
            ToastUtil.show( "密码不能为空！");
            return false;
        }
        if (!ToosUtils.checkPwd(ToosUtils.getTextContent(loginPwd))) {
            ToastUtil.show( "密码不能少于6位！");
            return false;
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!=RESULT_OK){
            return;
        }
        if (requestCode==REGISTER_RES){
            finish();
        }
    }

    @Override
    public void getLoginView(RegisterEntity registerEntity) {
        dissDialog();
        SpUtil.saveUser(registerEntity);
        finish();
    }

    @Override
    public void getLoginrError(String s) {
        ToastUtil.show(s);
        dissDialog();
    }
}
