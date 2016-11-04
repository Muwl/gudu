package vip.gudugudu.gudu.ui.login;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.RegisterEntity;
import vip.gudugudu.gudu.ui.register.RegisterModel;

/**
 * Created by Administrator on 2016/10/26.
 */

public class LoginPresenter extends LoginContract.Presenter  implements ResponseListener<RegisterEntity> {
    @Override
    public void onStart() {
        ((LoginModel)mModel).setLoginLister(this);
    }

    @Override
    public void login(String mobile, String pwd) {
        mModel.login(mobile,pwd);
    }

    @Override
    public void thirdLogin(String openid, String nickname, String figureurl) {
        mModel.thirdLogin(openid,nickname,figureurl);
    }

    @Override
    public void onSucess(RegisterEntity data) {
        mView.getLoginView(data);
    }

    @Override
    public void OnTokenError(String s) {

    }

    @Override
    public void onError(String s) {
        mView.getLoginrError(s);
    }
}
