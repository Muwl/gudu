package vip.gudugudu.gudu.ui.login;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.RegisterEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface LoginContract {

    interface Model extends BaseModel{
        void login(String mobile,String pwd);
        void thirdLogin(String openid,String nickname,String figureurl);
    }

    interface View extends BaseView{
        void getLoginView(RegisterEntity registerEntity);
        void getLoginrError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void login(String mobile,String pwd);
        public abstract void thirdLogin(String openid,String nickname,String figureurl);
    }
}
