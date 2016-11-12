package vip.gudugudu.gudu.ui.account;

import java.io.File;

import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.data.ResponseListener;

/**
 * Created by Administrator on 2016/11/11.
 */

public class AccountPresenter extends AccountContract.Presenter implements ResponseListener<String>{
    @Override
    public void onStart() {
        ((AccountModel)mModel).setUpdateIconLister(this);
    }

    @Override
    public void updateIcon(File file) {
        mModel.updateIcon(file);
    }

    @Override
    public void onSucess(String data) {
        SpUtil.saveUserIcon(data);
        mView.updateIconSuc();
    }

    @Override
    public void OnTokenError(String s) {
        mView.updateIconTokenError(s);
    }

    @Override
    public void onError(String s) {
        mView.updateIconError(s);
    }
}
