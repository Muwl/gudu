package vip.gudugudu.gudu.ui.main;

import java.util.List;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface HomeContract {

    interface Model extends BaseModel{
        List<TableShowEntity> getTabs();
    }

    interface View extends BaseView{
        void showTabList(List<TableShowEntity> mtabs);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void getTabList();
    }

}
