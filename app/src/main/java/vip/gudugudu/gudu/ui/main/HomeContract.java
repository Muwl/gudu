package vip.gudugudu.gudu.ui.main;

import java.util.List;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.TableShowEntity;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface HomeContract {

    interface Model extends BaseModel{
        void getTabs();
    }

    interface View extends BaseView{
        void showTabList(List<TableShowEntity> mtabs);
        void getTableError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void getTabList();


    }

}
