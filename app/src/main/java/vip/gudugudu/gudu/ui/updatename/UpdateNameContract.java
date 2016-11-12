package vip.gudugudu.gudu.ui.updatename;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface UpdateNameContract {

     interface Model extends BaseModel {
        void updateName(String name);
    }

    interface View extends BaseView{
        void updateNameSuc();
        void updateNameError(String s);
        void updateNameTokenError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View> {
       public abstract void updateName(String name);
    }

}
