package vip.gudugudu.gudu.ui.account;

import java.io.File;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.ui.forget.ForgetContract;

import static u.aly.av.F;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface AccountContract {

    interface Model extends BaseModel {
        void updateIcon(File file);
    }

    interface View extends BaseView {
        void updateIconSuc();
        void updateIconError(String s);
        void updateIconTokenError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void updateIcon(File file);
    }
}
