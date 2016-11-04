package vip.gudugudu.gudu.ui.main;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface CollectContract {

    interface Model extends BaseModel{
        void delCollect(AlbumsEntity AlbumsEntity);
    }

    interface View extends BaseView{
        void delCollectView(AlbumsEntity albumsEntity);
        void delCollectError(String s);
        void delCollectTokenError(String s);
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        abstract void delCollect(AlbumsEntity AlbumsEntity);
    }

}
