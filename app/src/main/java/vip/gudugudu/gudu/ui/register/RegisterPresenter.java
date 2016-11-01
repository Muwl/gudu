package vip.gudugudu.gudu.ui.register;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.RegisterEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class RegisterPresenter extends RegisterContract.Presenter implements ResponseListener<RegisterEntity>{
    @Override
    public void onStart() {
        ((RegisterModel)mModel).setGetDetailLister(this);
    }

    @Override
    public void register(String mobile, String pwd) {
        mModel.register(mobile,pwd);
    }

    @Override
    public void onSucess(RegisterEntity data) {
        mView.getRegisterView(data);
    }

    @Override
    public void OnTokenError(String s) {

    }

    @Override
    public void onError(String s) {
        mView.getRegisterError(s);
    }
}
