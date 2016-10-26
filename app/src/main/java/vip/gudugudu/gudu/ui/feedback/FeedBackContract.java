package vip.gudugudu.gudu.ui.feedback;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface FeedBackContract {

    interface Model extends BaseModel{

    }

    interface View extends BaseView{

    }

    abstract class Presenter extends BasePresenter<Model,View>{

    }
}
