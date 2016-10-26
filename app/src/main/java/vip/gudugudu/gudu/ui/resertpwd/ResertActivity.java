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

    @Override
    public int getLayoutId() {
        return R.layout.activity_resertpwd;
    }

    @Override
    public void initView() {
        titleTitle.setText("重置密码");
    }



    @OnClick({R.id.title_back, R.id.resert_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.resert_ok:
                break;
        }
    }
}
