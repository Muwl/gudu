package vip.gudugudu.gudu.ui.updatename;

import vip.gudugudu.gudu.base.util.SpUtil;
import vip.gudugudu.gudu.data.ResponseListener;

/**
 * Created by Administrator on 2016/11/12.
 */

public class UpdateNamePresenter extends UpdateNameContract.Presenter implements ResponseListener<String> {


    @Override
    public void onStart() {
        ((UpdateNameModel)mModel).setUpdateNameLister(this);
    }

    @Override
    public void updateName(String name) {
        mModel.updateName(name);
    }

    @Override
    public void onSucess(String data) {
        SpUtil.saveUserName(data);
        mView.updateNameSuc();
    }

    @Override
    public void OnTokenError(String s) {
        mView.updateNameTokenError(s);
    }

    @Override
    public void onError(String s) {
        mView.updateNameError(s);
    }


}
