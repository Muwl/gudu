package vip.gudugudu.gudu.ui.main;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.ui.albumdetail.AlbumDetailModel;

/**
 * Created by Administrator on 2016/10/16.
 */

public class CollectPresenter extends CollectContract.Presenter implements ResponseListener<AlbumsEntity> {

    @Override
    public void onStart() {
        ((CollectModel)mModel).setDelCollectLister(this);
    }


    @Override
    void delCollect(AlbumsEntity AlbumsEntity) {
        mModel.delCollect(AlbumsEntity);
    }

    @Override
    public void onSucess(AlbumsEntity data) {
        mView.delCollectView(data);
    }

    @Override
    public void OnTokenError(String s) {
        mView.delCollectTokenError(s);
    }

    @Override
    public void onError(String s) {
        mView.delCollectError(s);
    }
}
