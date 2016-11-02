package vip.gudugudu.gudu.ui.albumdetail;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import rx.functions.Action1;
import vip.gudugudu.gudu.base.util.ApiUtil;
import vip.gudugudu.gudu.base.util.ToosUtils;
import vip.gudugudu.gudu.data.ResponseListener;
import vip.gudugudu.gudu.data.entity.AlbumDetailEntity;
import vip.gudugudu.gudu.data.entity.ReturnCallEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class AlbumDetailModel implements AlbumDetailContract.Model {

    private ResponseListener<AlbumDetailEntity> getDetailLister;

    public void setGetDetailLister(ResponseListener<AlbumDetailEntity> getDetailLister) {
        this.getDetailLister = getDetailLister;
    }

    @Override
    public void getDetail(String albumid) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("albumid",albumid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiUtil.getStringDataNoToken(ApiUtil.GETALBUMSDETIAL,jsonObject.toString()).subscribe(new Action1<ReturnCallEntity>() {
            @Override
            public void call(ReturnCallEntity callEntity) {
                if (ToosUtils.isStringEmpty(callEntity.state) || ApiUtil.RETURN_ERROR.equals(callEntity.state)){
                    getDetailLister.onError(callEntity.result);
                }else{
                    try{
                        AlbumDetailEntity detailEntity=new Gson().fromJson(callEntity.result,AlbumDetailEntity.class);
                        getDetailLister.onSucess(detailEntity);
                    }catch (Exception e){
                        getDetailLister.onError("请求失败");
                    }

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                getDetailLister.onError("请求失败");
            }
        });
    }


}
