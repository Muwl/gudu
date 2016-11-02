package vip.gudugudu.gudu.ui.feedback;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.RegisterEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface FeedBackContract {

    interface Model extends BaseModel{
        void feedback(String content,String contacts);
    }

    interface View extends BaseView{
        void getFeedBackView();
        void getFeedBackError(String s);
        void getFeedBackTokenError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void feedback(String content,String contacts);
    }
}
