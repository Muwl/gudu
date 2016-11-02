package vip.gudugudu.gudu.ui.albumdetail;

import java.util.List;

import vip.gudugudu.gudu.base.BaseModel;
import vip.gudugudu.gudu.base.BasePresenter;
import vip.gudugudu.gudu.base.BaseView;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.data.entity.TableShowEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface AlbumDetailContract {

    interface Model extends BaseModel{
        void getDetail(String albumid);
        void getCollect(String albumid);
    }
    interface View extends BaseView{
        void getDetailView(AlbumDetailEntity detailEntity);
        void getTableError(String s);

        void getCollectView(String s);
        void getCollectError(String s);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void getDetail(String albumid);

        public abstract void getCollec(String albumid);
    }



}
