package vip.gudugudu.gudu.ui.resertpwd;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.RegisterEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface ResertContract  {

    interface Model extends BaseModel{
        void resertPwd(String mobile,String pwd);
    }

    interface View extends BaseView{
        void getResertPwdView(String s);
        void getResertPwdError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void resertPwd(String mobile,String pwd);
    }
}
