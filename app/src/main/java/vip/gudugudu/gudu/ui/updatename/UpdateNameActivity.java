package vip.gudugudu.gudu.ui.updatename;

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
 * Created by Administrator on 2016/11/12.
 */

public class UpdateNameActivity extends BaseActivity<UpdateNamePresenter, UpdateNameModel> implements UpdateNameContract.View {


    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.updatename_ok)
    TextView updatenameOk;
    @Bind(R.id.updatename_name)
    EditText updatenameName;

    @Override
    public int getLayoutId() {
        return R.layout.actiivty_updatename;
    }

    @Override
    public void initView() {
        titleTitle.setText("修改昵称");
        updatenameName.setText(SpUtil.getNickname());
    }

    @Override
    public void updateNameSuc() {
        dissDialog();
        ToastUtil.show("修改成功");
        finish();
    }

    @Override
    public void updateNameError(String s) {
        dissDialog();
        ToastUtil.show(s);
    }

    @Override
    public void updateNameTokenError(String s) {
        dissDialog();
        ToosUtils.goReLogin(this);
        ToastUtil.show(s);
    }


    @OnClick({R.id.title_back, R.id.updatename_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.updatename_ok:
                if (ToosUtils.isTextEmpty(updatenameName)){
                    ToastUtil.show("昵称不能为空");
                    return;
                }
                showDialog();
                mPresenter.updateName(ToosUtils.getTextContent(updatenameName));
                break;
        }
    }

}
