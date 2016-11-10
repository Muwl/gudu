package vip.gudugudu.gudu.ui.account;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.ui.forget.ForgetContract;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface AccountContract {

    interface Model extends BaseModel {

    }

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<Model,View> {


    }
}
