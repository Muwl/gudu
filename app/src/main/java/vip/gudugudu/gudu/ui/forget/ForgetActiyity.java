package vip.gudugudu.gudu.ui.forget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.gudugudu.gudu.R;
import vip.gudugudu.gudu.base.BaseActivity;
import vip.gudugudu.gudu.ui.resertpwd.ResertActivity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ForgetActiyity extends BaseActivity<ForgetPresenter, ForgetModel> implements ForgetContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.forgetpwd_phone)
    EditText forgetpwdPhone;
    @Bind(R.id.forgetpwd_getcode)
    TextView forgetpwdGetcode;
    @Bind(R.id.forgetpwd_code)
    EditText forgetpwdCode;
    @Bind(R.id.forgetpwd_next)
    TextView forgetpwdNext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    public void initView() {
        titleTitle.setText("找回密码");
    }


    @OnClick({R.id.title_back, R.id.forgetpwd_getcode, R.id.forgetpwd_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.forgetpwd_getcode:
                break;
            case R.id.forgetpwd_next:
                Intent intent=new Intent(ForgetActiyity.this, ResertActivity.class);
                startActivity(intent);
                break;
        }
    }
}
