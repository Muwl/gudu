package vip.gudugudu.gudu.ui.resertpwd;

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
import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.base.util.ToastUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ResertActivity extends BaseActivity<ResertPresenter, ResertModel> implements ResertContract.View {
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.title_send)
    TextView titleSend;
    @Bind(R.id.resert_pwd)
    EditText resertPwd;
    @Bind(R.id.resert_respwd)
    EditText resertRespwd;
    @Bind(R.id.resert_ok)
    TextView resertOk;

    private String sphone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_resertpwd;
    }

    @Override
    public void initView() {
        titleTitle.setText("重置密码");
        sphone=getIntent().getStringExtra("phone");
    }



    @OnClick({R.id.title_back, R.id.resert_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.resert_ok:
                if (checkInput()){
                    showDialog();
                    mPresenter.resertPwd(sphone,ToosUtils.getTextContent(resertPwd));
                }
                break;
        }
    }

    @Override
    public void getResertPwdView(String s) {
        dissDialog();
        ToastUtil.show("修改密码成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void getResertPwdError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }


    /**
     * 注册输入
     *
     * @return
     */
    private boolean checkInput() {

        if (ToosUtils.isTextEmpty(resertPwd)) {
            ToastUtil.show( "密码不能为空！");
            return false;
        }

        if (!ToosUtils.checkPwd(ToosUtils.getTextContent(resertPwd))) {
            ToastUtil.show( "密码不能少于6位！");
            return false;
        }
        if (!ToosUtils.getTextContent(resertPwd).equals(ToosUtils.getTextContent(resertRespwd))) {
            ToastUtil.show( "两次输入的密码不一致！");
            return false;
        }
        return true;

    }
}
