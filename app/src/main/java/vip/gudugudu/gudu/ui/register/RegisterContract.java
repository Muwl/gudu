package vip.gudugudu.gudu.ui.register;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.data.entity.RegisterEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface RegisterContract {

    interface Model extends BaseModel{
        void register(String mobile,String pwd);
    }

    interface View extends BaseView{
        void getRegisterView(RegisterEntity registerEntity);
        void getRegisterError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void register(String mobile,String pwd);
    }
}
