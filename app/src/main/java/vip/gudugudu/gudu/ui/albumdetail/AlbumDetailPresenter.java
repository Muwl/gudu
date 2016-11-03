package vip.gudugudu.gudu.ui.albumdetail;

import android.util.Log;

import java.util.List;

import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.data.entity.TableShowEntity;
import vip.gudugudu.gudu.ui.main.HomeModel;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2016/10/26.
 */

public class AlbumDetailPresenter  extends  AlbumDetailContract.Presenter implements ResponseListener<Object> {
    @Override
    public void onStart() {
        ((AlbumDetailModel)mModel).setGetDetailLister(this);

    }

    @Override
    public void getDetail(String albumid) {
        mModel.getDetail(albumid);
    }

    @Override
    public void getCollec(String albumid) {
        mModel.getCollect(albumid);
    }

//    @Override
//    public void onSucess(AlbumDetailEntity data) {
//        mView.getDetailView(data);
//    }
//
//    @Override
//    public void onSucess(String data) {
//
//    }

    @Override
    public void onSucess(Object data) {
        if (data instanceof String){
            mView.getCollectView((String) data);
        }else if (data instanceof AlbumDetailEntity){
            mView.getDetailView((AlbumDetailEntity)data);
        }
    }

    @Override
    public void OnTokenError(String s) {
            mView.getCollectTokenError(s);
    }

    @Override
    public void onError(String s) {
        mView.getTableError(s);
    }
}
