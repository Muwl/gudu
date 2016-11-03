package vip.gudugudu.gudu.ui.feedback;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.ui.login.LoginModel;

/**
 * Created by Administrator on 2016/10/26.
 */

public class FeedBackPresenter extends FeedBackContract.Presenter implements ResponseListener<String>{
    @Override
    public void onStart() {
        ((FeedBackModel)mModel).setFeedBackLister(this);
    }

    @Override
    public void feedback(String content, String contacts) {
        mModel.feedback(content,contacts);
    }

    @Override
    public void onSucess(String data) {
        mView.getFeedBackView();
    }

    @Override
    public void OnTokenError(String s) {
        mView.getFeedBackTokenError(s);
    }

    @Override
    public void onError(String s) {
        mView.getFeedBackError(s);
    }
}
