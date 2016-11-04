package vip.gudugudu.gudu.ui.main;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.AlbumsEntity;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

/**
 * Created by Administrator on 2016/10/16.
 */

public class CollectModel implements CollectContract.Model{

    private ResponseListener<AlbumsEntity> delCollectLister;

    public void setDelCollectLister(ResponseListener<AlbumsEntity> delCollectLister) {
        this.delCollectLister = delCollectLister;
    }

    @Override
    public void delCollect(AlbumsEntity albumsEntity) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("albumid",albumsEntity.AlbumId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiUtil.getStringDataToken(ApiUtil.DELCOLLECT,jsonObject.toString()).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    delCollectLister.onError(callEntity.result);
                }else if(ApiUtil.RETURN_TROKENERROR.equals(callEntity.state)){
                    delCollectLister.OnTokenError(callEntity.result);
                }else{
                    delCollectLister.onSucess(albumsEntity);

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                delCollectLister.onError("请求失败");
            }
        });
    }
}
