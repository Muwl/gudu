package vip.gudugudu.gudu.ui.resertpwd;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.ui.register.RegisterModel;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ResertPresenter extends ResertContract.Presenter implements ResponseListener<String> {
    @Override
    public void onStart() {
        ((ResertModel)mModel).setResertPwdLister(this);
    }

    @Override
    public void resertPwd(String mobile, String pwd) {
        mModel.resertPwd(mobile,pwd);
    }

    @Override
    public void onSucess(String data) {
        mView.getResertPwdView(data);
    }

    @Override
    public void OnTokenError(String s) {

    }

    @Override
    public void onError(String s) {
        mView.getResertPwdError(s);
    }
}
